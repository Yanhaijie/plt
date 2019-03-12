package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.dao.SUserBindMapper;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.entity.vo.XenditCallBackVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by xionghuifeng on 2018/1/13.
 */
@Service
@Transactional
public class SUserAccountServiceImpl extends AbstractService<SUserAccount> implements SUserAccountService {

    private static final Logger logger = LoggerFactory.getLogger(SUserAccountServiceImpl.class);

    @Resource
    private SUserBindService userCardService;

    @Resource
    private SUserBindService sUserBindService;

    @Resource
    private SXenditPayService xenditPayService;

    @Resource
    private SAccountBalanceService balanceService;

    @Resource
    private UserAccountBussService userAccountBussService;

    @Resource
    private  AppProperties appProperties;

    @Resource
    private  SExchangeRateService sExchangeRateService;

    @Resource
    private SAtIdentityService sAtIdentityService;

    @Resource
    private SUserService sUserService;

    @Resource
    private  SUserCashService sUserCashService;

    @Resource
    private  SUserPayService sUserPayService;

    @Resource
    private SLoanOrderService sLoanOrderService;

    @Resource
    private    SBussErrorService sBussErrorService;

    /**
     * 全部提现
     * @param userId
     * @return
     */
    @Override
    public  Result moneyAllCash(Integer userId){
        SUserAccount userAccount=userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            return  ResultGenerator.genFailResult(" user is null");
        }
        //C-1按国家选择相应的提现金额校验
        Result checkLimitResult=cashCheckLimit(Constants.Country.INDONESIA,userAccount.getAvailableAmount());
        if(checkLimitResult.getCode()!=ResultCode.SUCCESS.code){
            return  checkLimitResult;
        }
        //C-2用户是否已经实名
        if(sAtIdentityService.countByStatus(userId,3).compareTo(0)<1){
            return  ResultGenerator.genFailResult("Anda belum mengisi Informasi KTP, Sebelum melakukan penarikan, Mohon mengisi Informasi KTP terlebih dahulu.");
        }
        //C-3用户是否已经绑卡
        SUserBind userCard=sUserBindService.getUserUseCard(userId);
        if(userCard==null){
            return  ResultGenerator.genFailResult("Anda belum menyambungkan kartu bank, Sebelum melakukan penarikan, Mohon mengisi Informasi KTP terlebih dahulu.");
        }

        //C-4是否有提现在待审核中
        if(sUserCashService.countByStatus(userId,0).compareTo(0)>0){
            return  ResultGenerator.genFailResult(" Sedang menarik.");
        }

        //新增提现审核
        SUserCash userCash=new SUserCash();
        userCash.setUserId(userId);
        userCash.setUserBindId(userCard.getId());
        userCash.setCashMoney(userAccount.getAvailableAmount());
        userCash.setAreaCode(Constants.Country.INDONESIA_CODE);
        userCash.setCashPlatform(Constants.Country.INDONESIA_PAY_PLATFORM);
        userCash.setRemark("app cash");
        sUserCashService.save(userCash);
        //冻结提现金额
        userAccountBussService.freezeAccountMoney(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_CASH_FREEZE,userId,userAccount.getAvailableAmount());
        return  ResultGenerator.genSuccessResult("Penarikan Sedang Diproses! Proses pencairan biasanya memerlukan waktu 2~3hari waktu kerja");
//        return  moneyAccountCash(userId,userCard.getCardNumber(),userAccount.getAvailableAmount(),Constants.Country.INDONESIA_CODE);
    }

    /**
     * 现金账户提现处理
     * @param userCash
     * @param userId
     * @param cardAccount
     * @param amount
     * @param areaCode
     * @return
     */
    @Override
    public  Result  moneyAccountCash(SUserCash userCash,Integer userId, String cardAccount , BigDecimal amount,Integer areaCode){

        if(StringUtils.isBlank(cardAccount)){
            return  ResultGenerator.genFailResult("cardAccount is null ");
        }

        //银行卡校验
        SUserBindVo userCard=userCardService.getUserBindByCardAccount(userId,cardAccount);
        if(userCard==null){
            return  ResultGenerator.genFailResult("cardAccount does not exist");
        }
        //账户校验
        SUserAccount userAccount=userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            return  ResultGenerator.genFailResult("userAccount does not exist");
        }
        //提现金额校验
        if(amount.compareTo(userAccount.getFreezeAmount())>0){
            logger.info("cashByIndonesia: freezeAmount");
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }

        //按国家选择相应的提现接口
        Result cashResult=ResultGenerator.genFailResult("have not find areaCode");
        try{
            //印尼
            if(userCash.getAreaCode().equals(Constants.Country.INDONESIA_CODE)){
                //cashResult=cashByIndonesia(userCash,userCard,userAccount,amount,userId);
                cashResult=disbursements(userCash.getId(),Constants.BussPay.DIS_BUSS_CASH,userCard,userAccount,amount,userId);
            }
            //越南

        }catch (Exception ex){
            logger.info("cash exception :" +ex.getMessage());
            logger.error("cash exception :" +ex.getMessage());
            cashResult.setCode(ResultCode.INTERNAL_SERVER_ERROR.code);
            cashResult.setMessage("cash exception");
        }

        //打款请求提交成功,更新状态为提现中
        if(cashResult.getCode()==ResultCode.SUCCESS.code){
            sUserCashService.updateCashStatus(userCash.getId(),Constants.Cash.STATUS_CASHING);
        }
        //提现异常，不能再次提交，需要人工去查
        if(cashResult.getCode()==ResultCode.INTERNAL_SERVER_ERROR.code){
            sUserCashService.updateCashStatus(userCash.getId(),Constants.Cash.STATUS_EXPECTION);
        }
        return  cashResult;
    }

    /**
     * 现金账户用户借款打款
     * @param userPay
     * @param userId
     * @param cardAccount
     * @param amount
     * @param areaCode
     * @return
     */
    @Override
    public  Result  moneyAccountLoanOrder(SUserPay userPay,Integer userId, String cardAccount , BigDecimal amount,Integer areaCode){

        if(StringUtils.isBlank(cardAccount)){
            return  ResultGenerator.genFailResult("cardAccount is null ");
        }

        //银行卡校验
        SUserBindVo userCard=userCardService.getUserBindByCardAccount(userId,cardAccount);
        if(userCard==null){
            return  ResultGenerator.genFailResult("cardAccount does not exist");
        }
        //账户校验
        SUserAccount userAccount=userAccountBussService.getUserAccount(userId,Constants.ACCOUNT_TYPE_MONEY);
        if(userAccount==null){
            return  ResultGenerator.genFailResult("userAccount does not exist");
        }
        //按国家选择相应的借款打款
        Result cashResult=ResultGenerator.genFailResult("have not find areaCode");
        try{
            //印尼
            if(userPay.getAreaCode().equals(Constants.Country.INDONESIA_CODE)){
                cashResult=disbursements(userPay.getId(),Constants.BussPay.DIS_BUSS_LOAN,userCard,userAccount,amount,userId);
            }
            //越南

        }catch (Exception ex){
            logger.info("loanOrder exception :" +ex.getMessage());
            logger.error("loanOrder exception :" +ex.getMessage());
            cashResult.setCode(ResultCode.INTERNAL_SERVER_ERROR.code);
            cashResult.setMessage("loanOrder exception");
        }

        //打款请求提交成功,更新状态为提现中
        if(cashResult.getCode()==ResultCode.SUCCESS.code){

            sUserPayService.updateStatus(userPay.getId(),Constants.BussPay.PAY_STATUS_TO_ING);
        }
        //提现异常，不能再次提交，需要人工去查
        if(cashResult.getCode()==ResultCode.INTERNAL_SERVER_ERROR.code){
            sUserPayService.updateStatus(userPay.getId(),Constants.BussPay.PAY_STATUS_TO_ERROR);
        }
        return  cashResult;
    }
    /**
     * 根据国家校验提现额度
     * @param country
     * @param amount
     * @return
     */
    public  Result cashCheckLimit(String country,BigDecimal amount){
        //印尼盾转美元
         amount=sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,amount,false);
        if(country.equals(Constants.Country.INDONESIA)){
            if(amount.compareTo(Constants.CASH_IDR_MIN)<0){
                //提现额度必须大于
                //Penarikan reward terendah sebesar Rp 50.000
                return  ResultGenerator.genFailResult(" Penarikan reward terendah sebesar Rp 50.000，“Tarik uang tunai” atau “Ambil bonus”");
            }
            if(amount.compareTo(Constants.CASH_IDR_MAX)>0){
                //提现额度不能超过 10000000
                return  ResultGenerator.genFailResult("Jumlah penarikan tidak boleh lebih dari  "+Constants.CASH_IDR_MAX.toString());
            }
        }
        else {

            return  ResultGenerator.genFailResult("unknow country");
        }

        return  ResultGenerator.genSuccessResult();
    }



    /**
     * 印尼国家提现处理-暂时不用
     * @param userCash
     * @param userCard
     * @param userAccount
     * @param cashUsd
     * @param userId
     * @return
     */
    @Override
    public  Result cashByIndonesia(SUserCash userCash,SUserBindVo userCard,SUserAccount userAccount, BigDecimal cashUsd,Integer userId){
        //印尼盾转美元

        //校验金额(美元)  冻结金额要大于等于提现金额
        if(cashUsd.compareTo(userAccount.getFreezeAmount())>0){
            logger.info("cashByIndonesia: freezeAmount");
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        BigDecimal cashIdr=sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,cashUsd,false);
        logger.info("cashByIndonesia-idr:"+cashIdr.toString());
        //提交提现转账
        String sn = NomalUntil.getMsCode();
        logger.info("cash-sn:"+sn);
        // NomalUntil.getMsCode();
        //UUID.randomUUID().toString().replace("-", "");
        //UUID.randomUUID().toString();
        if(StringUtils.isBlank(userCard.getBankCode())){
            return  ResultGenerator.genFailResult("bank_code is null");
        }
        if(StringUtils.isBlank(userCard.getHoldingName())){
            return  ResultGenerator.genFailResult("account_holder_name is null");
        }
        if(StringUtils.isBlank(userCard.getCardAccount())){
            return  ResultGenerator.genFailResult("account_number is null");
        }

        Map<String, String> cashMap= new HashMap<>();
        cashMap.put("external_id", sn);
        cashMap.put("bank_code", userCard.getBankCode());
        cashMap.put("account_holder_name", userCard.getHoldingName());
        cashMap.put("account_number", userCard.getCardAccount());
        cashMap.put("description", "cash");
        //注意amount 值为整型不能有小数点,最小额度10000
        cashMap.put("amount", String.valueOf( cashIdr.intValue()));

        Result withResult = xenditPayService.requestWithoutCert(cashMap);
        if(withResult.getCode()!= ResultCode.SUCCESS.code){
            logger.info("cashByIndonesia-withResult:"+withResult.getMessage());
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        JSONObject obj = JSONObject.parseObject(withResult.getData().toString());
        logger.info("withResult-json:"+obj.toJSONString());
        //判断支付请求是否提交成功
        if(!obj.get("status").toString().equals("PENDING")){
            //业务繁忙请稍后再试
            logger.info("withResult-status:"+obj.get("status").toString());
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        //冻结金额(美元) - 注释，在提现申请时冻结
        //userAccountBussService.freezeAccountMoney(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_CASH_FREEZE,userId,cashUsd);
        //记录转账
        SAccountBalance balance = new SAccountBalance();
        balance.setAmount(cashIdr);
        balance.setUsdAmount(cashUsd);
        balance.setUserId(userId);
        balance.setCashId(userCash.getId());
        balance.setCardNumber(userCard.getCardNumber());
        balance.setAccountId(userAccount.getAccountId());
        balance.setOrderSn(sn);
        balance.setBankCode(obj.get("bank_code").toString());
        balance.setAccountHolderName(obj.get("account_holder_name").toString());
        balance.setRemark("Dikirim ke pengguna :" + userCard.getHoldingName());
        balance.setStatus(obj.get("status").toString());
        balance.setCreateDate(new Date());
        balance.setUpdated(new Date());
        balance.setBussType(0);
        balanceService.save(balance);
        return  ResultGenerator.genSuccessResult(" Proses berhasil, menunggu penarikan selesai.");
    }

    /**
     * 印尼 xendit 打款业务
     * @param relateId 关联ID
     * @param bussType 业务类型
     * @param userCard
     * @param userAccount
     * @param usdAmount
     * @param userId
     * @return
     */
    public  Result  disbursements(Integer relateId,Integer bussType,SUserBindVo userCard,SUserAccount userAccount, BigDecimal usdAmount,Integer userId){
        BigDecimal cashIdr=sExchangeRateService.usdToByCountry(Constants.Country.INDONESIA_CR,usdAmount,false);
        logger.info("cashByIndonesia-idr:"+cashIdr.toString());
        //提交提现转账
        String sn = NomalUntil.getMsCode();
        logger.info("cash-sn:"+sn);
        if(StringUtils.isBlank(userCard.getBankCode())){
            return  ResultGenerator.genFailResult("disbursements :bank_code is null");
        }
        if(StringUtils.isBlank(userCard.getHoldingName())){
            return  ResultGenerator.genFailResult("disbursements:account_holder_name is null");
        }
        if(StringUtils.isBlank(userCard.getCardAccount())){
            return  ResultGenerator.genFailResult("disbursements:account_number is null");
        }

        Map<String, String> cashMap= new HashMap<>();
        cashMap.put("external_id", sn);
        cashMap.put("bank_code", userCard.getBankCode());
        cashMap.put("account_holder_name", userCard.getHoldingName());
        cashMap.put("account_number", userCard.getCardAccount());
        cashMap.put("description", "cash");
        //注意amount 值为整型不能有小数点,最小额度10000
        if(appProperties.getDiscallback().equals("true")){
            // 当金额为90000时,触发回调.
            cashMap.put("amount", "90000");
        }else {
            cashMap.put("amount", String.valueOf( cashIdr.intValue()));
        }

        Result withResult = xenditPayService.requestWithoutCert(cashMap);
        if(withResult.getCode()!= ResultCode.SUCCESS.code){
            logger.info("cashByIndonesia-withResult:"+withResult.getMessage());
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        JSONObject obj = JSONObject.parseObject(withResult.getData().toString());
        logger.info("withResult-json:"+obj.toJSONString());
        //判断支付请求是否提交成功
        if(!obj.get("status").toString().equals("PENDING")){
            //业务繁忙请稍后再试
            logger.info("withResult-status:"+obj.get("status").toString());
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        //冻结金额(美元) - 注释，在提现申请时冻结
        //userAccountBussService.freezeAccountMoney(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_CASH_FREEZE,userId,cashUsd);
        //记录转账
        SAccountBalance balance = new SAccountBalance();
        balance.setAmount(cashIdr);
        balance.setUsdAmount(usdAmount);
        balance.setUserId(userId);
        balance.setCashId(relateId);
        balance.setCardNumber(userCard.getCardNumber());
        balance.setAccountId(userAccount.getAccountId());
        balance.setOrderSn(sn);
        balance.setBankCode(obj.get("bank_code").toString());
        balance.setAccountHolderName(obj.get("account_holder_name").toString());
        balance.setRemark("Dikirim ke pengguna :" + userCard.getHoldingName());
        balance.setStatus(obj.get("status").toString());
        balance.setCreateDate(new Date());
        balance.setUpdated(new Date());
        balance.setBussType(bussType);
        balanceService.save(balance);
        return  ResultGenerator.genSuccessResult(" Proses berhasil, menunggu penarikan selesai.");
    }


    /**
     * 现金账户提现打款、借款订单打款 回调处理
     * @param obj
     * @return
     */
    @Override
    public  Result moneyAccountCashCallBack(JSONObject obj,String callBackToken){
        if(!callBackToken.equals(appProperties.getXenditValidationToken())){
            logger.info("CB-Token Error:"+callBackToken);
            return  ResultGenerator.genFailResult("Warning!");
        }
        XenditCallBackVo vo =  JSONObject.toJavaObject(obj, XenditCallBackVo.class);
        if (vo == null && vo.getExternal_id() == null){
            logger.error("回调结果格式化错误");
            throw new RuntimeException("callback disbursement callback");
        }
        //订单是否存在
        Map<String,Object> maps=new HashMap<>();
        maps.put("orderSn",vo.getExternal_id());
        SAccountBalance balance = balanceService.getAccountBalance( maps);
        if(balance==null){
          return  ResultGenerator.genFailResult("order is not find");
        }
        //订单是否已处理过
        if(StringUtils.isNotBlank(balance.getFailureCode())){
            return  ResultGenerator.genFailResult("order is done!");
        }

        Result bussResult=ResultGenerator.genSuccessResult();
        ///用户提现业务处理
        if(balance.getBussType()==null || balance.getBussType().compareTo(Constants.BussPay.DIS_BUSS_CASH)==0){
            bussResult=cashCallBackBuss(vo,balance);
        }
        //用户借款业务处理
        if(balance.getBussType().compareTo(Constants.BussPay.DIS_BUSS_LOAN)==0){
            bussResult=loanOrderCallBackBuss(vo,balance);
        }

        //无错误备注成功
        if(StringUtils.isBlank(vo.getFailure_code())){
            vo.setFailure_code("order-success");
        }
        String status=bussResult.getCode()==ResultCode.SUCCESS.code ? vo.getStatus():"bussError:"+vo.getStatus();
        //更新订单
        balance.setExtId(vo.getId());
        balance.setStatus(status);
        balance.setFailureCode(vo.getFailure_code());
        balance.setTransactionSequence(vo.getTransaction_sequence());
        balance.setUpdated(new Date());
        balanceService.update(balance);
        logger.info("cash-callback-success:"+balance.getId());
        return  ResultGenerator.genSuccessResult();
    }

    /**
     *  用户提现回调业务处理
     * @param vo
     * @param balance
     */

    public Result  cashCallBackBuss(XenditCallBackVo vo,SAccountBalance balance ){
        logger.info("callback-buss-cash");
        try {
            if(vo.getStatus().equals("COMPLETED")){
                //冻结金额划出(美元)
                userAccountBussService.freezeOutAccountMoney(Constants.BUSS_TYPE_BOUNTY,
                        Constants.BUSS_STEP_BOUNTY_CASH_FREEZEOUT,balance.getUserId(),balance.getUsdAmount());
                sUserCashService.updateCashStatus(balance.getCashId(),Constants.Cash.STATUS_CASHSUCCESS);
            }else {
                //失败
                //打回冻结金额(美元)
                userAccountBussService.freezBackAccountMoney(Constants.BUSS_TYPE_BOUNTY,
                        Constants.BUSS_STEP_BOUNTY_CASH_FREEZEBACK,balance.getUserId(),balance.getUsdAmount());
                sUserCashService.updateCashStatus(balance.getCashId(),Constants.Cash.STATUS_CASHFAILD);
            }
        }catch (Exception ex){
            logger.info("cashCallBackBuss ex:"+ex.getMessage());
            ResultGenerator.genFailResult("buss error!");
        }
        return  ResultGenerator.genSuccessResult();
    }

    /**
     *  用户借款订单回调业务处理
     * @param vo
     * @param balance
     */
    @Override
    public Result  loanOrderCallBackBuss(XenditCallBackVo vo,SAccountBalance balance ){
        logger.info("callback-buss-loanOrder");
        try {
            if(vo.getStatus().equals("COMPLETED")){
                    //更新借款订单的状态和还款生效时间
                    Result result = sLoanOrderService.orderCallBackBuss(balance.getUserId(), balance.getCashId());
                    if (result.getCode() != ResultCode.SUCCESS.code) {
                        throw new BussException(result.getMessage());
                    }
                    // 更新状态
                    sUserPayService.updateStatus(balance.getCashId(), Constants.BussPay.PAY_STATUS_TO_SUCCESS);

            }else {
                //更新状态
                sUserPayService.updateStatus(balance.getCashId(),Constants.BussPay.PAY_STATUS_TO_FAILD);
            }
        }catch (Exception ex){
            logger.info("loanOrderCallBackBuss ex:"+ex.getMessage());
            //记录业务异常
            sBussErrorService.addBussError(Constants.App.BUSS_ERROR_DIS,  String.valueOf(balance.getId()),"loanOrderCallBackBuss:"+ex.getMessage(), JSON.toJSONString(vo));
            ResultGenerator.genFailResult("buss error!");
        }
        return  ResultGenerator.genSuccessResult();
    }


    public  void  test(){
        Map<String, String> cashMap= new HashMap<>();
        String sn=NomalUntil.getMsCode();
                //UUID.randomUUID().toString().replace("-", "");

//        cashMap.put("external_id", sn);
//        cashMap.put("bank_code", "DANAMON");
//        cashMap.put("account_holder_name", "JONHANNA");
//        cashMap.put("account_number", "3614490880");
//        cashMap.put("description", "cash");
//        cashMap.put("amount", "10000");

        //Bank account does not exist:
//        cashMap.put("external_id", sn);
//        cashMap.put("bank_code", "MANDIRI");
//        cashMap.put("account_holder_name", "Rizky");
//        cashMap.put("account_number", "7654321");
//        cashMap.put("description", "cash");
//        cashMap.put("amount", "10000");

        //Switching network is experiencing :
//        cashMap.put("external_id", sn);
//        cashMap.put("bank_code", "MANDIRI");
//        cashMap.put("account_holder_name", "Siti");
//        cashMap.put("account_number", "12121212");
//        cashMap.put("description", "cash");
//        cashMap.put("amount", "71799");
//
//        Result withResult = xenditPayService.requestWithoutCert(cashMap);
    }


}
