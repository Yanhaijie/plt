package com.supermoney.loan.api.service.impl;

import com.google.common.collect.Maps;
import com.supermoney.loan.api.dao.STransfertoTopupHisMapper;
import com.supermoney.loan.api.entity.STransfertoTopupHis;
import com.supermoney.loan.api.entity.SUserAccount;
import com.supermoney.loan.api.service.SExchangeRateService;
import com.supermoney.loan.api.service.STransfertoTopupHisService;
import com.supermoney.loan.api.service.SUserService;
import com.supermoney.loan.api.service.UserAccountBussService;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ArrayUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/07/06.
 */
@Service
@Transactional
public class STransfertoTopupHisServiceImpl extends AbstractService<STransfertoTopupHis> implements STransfertoTopupHisService {
    @Resource
    private STransfertoTopupHisMapper sTransfertoTopupHisMapper;

    @Autowired
    private TransferToUtil transferToUtil;

    @Autowired
    private UserAccountBussService userAccountBussService;

    @Resource
    private SExchangeRateService sExchangeRateService;


    @Value("${transferto.topup.productLimit}")
    private Integer productLimit;

    public void saveTopUpRecord(Map map){
        sTransfertoTopupHisMapper.saveTopUpRecord(map);

    }

    public Result topup(Integer userId,Integer phoneNumberType, String phoneNumber, Integer product, String msisdn, String yesOrNo, String smsTxt, String sendYesOrNo, String sSmsTxt){
        boolean isLimit = this.isLimitTopUp(userId,product);
        if(isLimit){
            //充值达到上限
            return  ResultGenerator.genFailResult("Pengisian telah mencapai batas maximal");
        }
        BigDecimal feeUsd = null;
        Integer fee = null;
        //根据手机号获取话费充值列表
//        Map<String,String> productMap = transferToUtil.msisdn_info(phoneNumber);
//        if(productMap != null && productMap.size() > 0){
//            //判断接口调用是否成功
//            String code = productMap.get("error_code");
//            String message = productMap.get("error_txt");
//            if("0".equals(code) && "Transaction successful".equals(message)){
//                //匹配充值产品，以及需要锁定的额度
//                String productList = productMap.get("product_list");
//                int index = -1;//产品对应的下标
//                if(StringUtils.isNotBlank(productList)){
//                    String[] prodList = productList.split(",");
//                    if(prodList.length > 0){
//                        //校验是否有符合的充值产品金额
//                        for(int i = 0; i < prodList.length; i++){
//                            if(prodList[i].equals(product.toString())){
//                                fee = Integer.valueOf(prodList[i]);
//                                index = i;
//                                break;
//                            }
//                        }
//                    }
//                    if(index != -1){
//                        String retail_price_list = productMap.get("wholesale_price_list");//获取零售价格  单位美元  改为成本价
//                        if(StringUtils.isNotBlank(retail_price_list)){
//                            String[] retailPriceList = retail_price_list.split(",");
//                            if(retailPriceList.length > index){
//                                feeUsd = new BigDecimal(retailPriceList[index]);//冻结资金账户金额  单位美元
//                            }
//                        }
//                    }else{
//                        return  ResultGenerator.genFailResult("Pengisian gagal");//充值失败
//                    }
//                }
//            }else{//接口调用失败
//                return  ResultGenerator.genFailResult(message);
//            }
//        }
        List<Map<String,Object>> topUpFeeList = this.topUpService(userId,phoneNumberType);
        for(Map<String,Object> feeMap : topUpFeeList){
            if(product.intValue() == (int)feeMap.get("product")){
                feeUsd = sExchangeRateService.indoneslaToUsd(new BigDecimal((Integer) feeMap.get("transaction_price")));
                fee = (Integer)feeMap.get("transaction_price");
                break;
            }
        }
        if(feeUsd==null){
            return  ResultGenerator.genFailResult("Pengisian gagal");
        }
        //判断账户资金是否可用
        SUserAccount userAccount=userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            return  ResultGenerator.genFailResult("Pengisian gagal");
        }
        //冻结资金,印尼盾转美元
        BigDecimal availableAmout = userAccount.getAvailableAmount();
        if(feeUsd.compareTo(availableAmout) > 0){
            return  ResultGenerator.genFailResult("Saldo tidak cukup");
        }
        userAccountBussService.freezeAccountMoney(Constants.BUSS_TYPE_TOPUP,Constants.BUSS_STEP_TOPUP_CASH_FREEZE,userId,feeUsd);
        //执行充话费
        Map<String,String> resultMap = transferToUtil.topUp(userId, phoneNumber, product, msisdn, yesOrNo,  smsTxt, sendYesOrNo, sSmsTxt,fee.toString());
        if(resultMap != null && ("Transaction successful".equals(resultMap.get("error_txt")) || "0".equals(resultMap.get("error_code")))){
            //冻结金额划出(美元)
            userAccountBussService.freezeOutAccountMoney(Constants.BUSS_TYPE_TOPUP, Constants.BUSS_STEP_TOPUP_CASH_FREEZE,userId,feeUsd);
            return  ResultGenerator.genSuccessResult("Pengisian berhasil");
        }else{
            //返还冻结金额(美元)
            userAccountBussService.freezBackAccountMoney(Constants.BUSS_TYPE_TOPUP, Constants.BUSS_STEP_TOPUP_CASH_FREEZE,userId,feeUsd);
            String errorTxt = this.getErrorText(resultMap.get("error_code"));
            return  ResultGenerator.genFailResult(errorTxt);
        }
    }

    public List<Map<String,Object>> topUpService(Integer userId, Integer type){
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("phoneNumberType",type);
        return sTransfertoTopupHisMapper.topUpService(paramMap);
    }

    public List<Map<String,Object>> topUpCheck(Map<String,Object> paramMap){
        return sTransfertoTopupHisMapper.findByUserId(paramMap);
    }

    /**
     * 当日充值是否达到上限
     * @param userId
     * @param product
     * @return
     */
    public boolean isLimitTopUp(Integer userId,Integer product){
        BigDecimal productB = new BigDecimal(product);
        BigDecimal productLimitB = new BigDecimal(productLimit);
        boolean result = false;
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId",userId);
        List<Map<String,Object>> list = sTransfertoTopupHisMapper.getProductSum(paramMap);
        if(list != null && list.size() > 0 && list.get(0) != null){
            Map<String,Object> map = list.get(0);
            BigDecimal productSumB = new BigDecimal(map.get("productSum").toString());
            if(productSumB.add(productB).compareTo(productLimitB) > 0){
                result = true;
            }
        }
        return result;
    }

    private String getErrorText(String code){
        //默认充值是失败
        String errorTxt = "";
        Map<String,String> tipMap = Maps.newHashMap();
        tipMap.put("101","Nomor ponsel anda salah");
        tipMap.put("224","Nomor ponsel anda salah");
        tipMap.put("204","Nomor yg dituju tidak diisi sebelumnya atau telah kedaluwarsa");
        tipMap.put("214","Penukaran ditolak");
        tipMap.put("222","Nomor dilarang melakukan pengisian");
        tipMap.put("104","Pengisian gagal");
        tipMap.put("221","Pengisian gagal");
        tipMap.put("232","Pengisian gagal");
        tipMap.put("242","Pengisian gagal");
        tipMap.put("244","Pengisian gagal");
        tipMap.put("246","Pengisian gagal");
        tipMap.put("252","Pengisian gagal");
        tipMap.put("254","Pengisian gagal");
        tipMap.put("256","Pengisian gagal");
        tipMap.put("301","Pengisian gagal");
        tipMap.put("310","Pengisian gagal");
        tipMap.put("312","Pengisian gagal");
        tipMap.put("207","Pengisian telah mencapai batas maximal");
        tipMap.put("230","Pengisian telah mencapai batas maximal");
        tipMap.put("231","Pengisian telah mencapai batas maximal");
        tipMap.put("233","Pengisian telah mencapai batas maximal");
        tipMap.put("241","Pengisian telah mencapai batas maximal");
        tipMap.put("243","Pengisian telah mencapai batas maximal");
        tipMap.put("245","Pengisian telah mencapai batas maximal");
        tipMap.put("251","Pengisian telah mencapai batas maximal");
        tipMap.put("253","Pengisian telah mencapai batas maximal");
        tipMap.put("255","Pengisian telah mencapai batas maximal");
        errorTxt = tipMap.get(code);
        if(StringUtils.isEmpty(errorTxt)){
            errorTxt = "";
        }
        return errorTxt;
    }

    //获取话费充值产品列表
    public Result msisdn_info(Integer userId){
        String phoneNumber = UserUtils.getCurrentMobile();
//        String phoneNumber = "9668951527";
        if(StringUtils.isEmpty(phoneNumber)){
            return ResultGenerator.genFailResult("phoneNumber is null");
        }
        //目前只允许菲律宾号码充值
        if(!phoneNumber.startsWith("+63")){
            if(phoneNumber.indexOf("0") == 0){
                phoneNumber = phoneNumber.substring(1,phoneNumber.length());
            }
            phoneNumber = "+63"+phoneNumber.trim();
        }
        //根据手机号获取话费充值列表
        Map<String,String> resultMap = transferToUtil.msisdn_info(phoneNumber);
        return ResultGenerator.genSuccessResult(resultMap);
    }
}
