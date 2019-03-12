package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.dao.SBankMapper;
import com.supermoney.loan.api.dao.SUserBindMapper;
import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.entity.SBank;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tangwenchi on 2018/1/13.
 */
@Service
@Transactional
public class SUserBindServiceImpl extends AbstractService<SUserBind> implements SUserBindService {
    private static final Logger logger = LoggerFactory.getLogger(SUserBindServiceImpl.class);

    @Resource
    private SUserBindMapper bindMapper;
    @Resource
    private SBankMapper bankMapper;
    @Resource
    private SXenditPayService sXenditPayService;
    @Resource
    private  AppProperties appProperties;
    @Resource
    private SAtIdentityService sAtIdentityService;
    @Resource
    private SUserService sUserService;
    @Resource
    private SAtCreditInformationService sAtCreditInformationService;



    /**
     * 获取用户银行卡集合
     * @param maps
     * @return
     */
    @Override
    public List<SUserBindVo> getUserBindList(Map<String, Object> maps) {
        return bindMapper.getUserBindCardList(maps);
    }

    /**
     * 根据卡号获取用户绑卡银行信息
     * @param userId
     * @param cardAccount
     * @return
     */
    public  SUserBindVo getUserBindByCardAccount(Integer userId,String cardAccount){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if(StringUtils.isNotBlank(cardAccount)){
            map.put("cardAccount", cardAccount);
        }
        map.put("cardStatus",Constants.STATUS_USE);
        List<SUserBindVo> list=getUserBindList(map);
        return  list.size()>0 ? list.get(0):null;
    }




    /**
     * 用户绑卡
     * @param userId
     * @param bankId
     * @param cardNumber
     * @param cardAccount
     * @param holdingName
     * @param holdingCard
     * @param holdPhone
     * @return
     */
    @Transactional
    public Result userBindCard(Integer userId,Integer bankId,String cardNumber,String cardAccount,String holdingName,String holdingCard,String holdPhone){
        //
        if( StringUtils.isBlank(cardAccount)){
            return      ResultGenerator.genFailResult(" holdingCard is null!");
        }
        //校验参数是否有特殊符号
        //银行是否存在
        SBank bank=bankMapper.selectByPrimaryKey(bankId);
        if(bank==null){
            return  ResultGenerator.genFailResult("bank define!");
        }
        //是否已绑定
        SUserBind hasUserBind=new SUserBind();
        hasUserBind.setCardAccount(cardAccount.trim());
        hasUserBind.setBankId(bankId);
        hasUserBind.setUserId(userId);
        hasUserBind.setCardStatus(Constants.STATUS_USE);
        hasUserBind=bindMapper.selectOne(hasUserBind);
        if(hasUserBind!=null){
            return ResultGenerator.genFailResult("Kartu bank saat ini telah terikat oleh pengguna.");
        }
        //提交到xendit验证信息: status: SUCCESS 直接绑定    PENDING 等待回调绑定   FAILURE 验证失败
        String sn = UUID.randomUUID().toString().replace("-", "");
        Map<String,String> bindMap=new HashMap<>();
        bindMap.put("external_id", sn);
        bindMap.put("bank_account_number",cardAccount);
        bindMap.put("bank_code",bank.getBankCode());
        Result bindResult=sXenditPayService.nameValidator(bindMap);
        logger.info("bind-2:"+bindResult.getData().toString());
        if(bindResult.getCode()!= ResultCode.SUCCESS.code){
            return  ResultGenerator.genFailResult("Busy please try again later!");
        }
        logger.info("bind-3:"+bindResult.getData().toString());
        JSONObject obj = JSONObject.parseObject(bindResult.getData().toString());
        //FAILURE 校验错误返回错误信息
        if(obj.get("status").toString().equals("FAILURE")){
            return  ResultGenerator.genFailResult(sXenditPayService.NameValidatorErrors(obj.get("failure_reason").toString()));
        }
        //PENDING 直接返回等待回调处理
        //SUCCESS 成功直接绑定
        if(obj.get("status").toString().equals("SUCCESS") ||  obj.get("status").toString().equals("PENDING")) {
            Integer status=obj.get("status").toString().equals("SUCCESS") ? Constants.STATUS_USE:Constants.STATUS_WAIT;
            //绑定
            SUserBind userBind = new SUserBind();
            userBind.setUserId(UserUtils.getCurrentUserId());
            userBind.setBankId(bankId);
            userBind.setCardNumber(cardNumber.trim());
            userBind.setCardAccount(cardAccount.trim());
            userBind.setHoldingCard("");
            userBind.setHoldingName("");
            userBind.setHoldingPhone("");
            SAtIdentity identity=sAtIdentityService.getSuccessIdentity(userId);
            if(identity!=null){
                userBind.setHoldingCard(identity.getIdNumber());
                userBind.setHoldingName(identity.getRealName());
            }
            SUser gotUser=sUserService.findById(userId);
            if(gotUser!=null){
                userBind.setHoldingPhone(gotUser.getUserName());
            }
            userBind.setCountry("Indonesia"); //默认印尼
            userBind.setCardType(0); //默认绑定的为储蓄卡
            userBind.setCardStatus(status);
            userBind.setXenditAccountId(obj.get("id").toString());
            userBind.setXenditReference(obj.get("reference").toString());
            userBind.setBindTime(new Date());
            userBind.setAreaCode(Constants.Country.INDONESIA_CODE);
            bindMapper.insertSelective(userBind);
            //开启征信资料修改 标记位
            sAtCreditInformationService.creditInfoModify(UserUtils.getCurrentUserId());
            return  ResultGenerator.genSuccessResult();
        }
        return   ResultGenerator.genFailResult("Busy please try again later!");
    }
    /**
     * 绑定银行卡回调处理
     * ==============FAILURE===============
     * {
     *   "bank_account_number": "1234567899",
     *   "failure_reason": "RECIPIENT_NOT_FOUND_ERROR",
     *  "bank_code": "MANDIRI",
     *  "reference": "58cd618ba0464eb64acdb246",
     *  "status": "FAILURE",
     *  "updated": "2017-03-24T08:11:07.624Z",
     *  "id": "59e608887eb26d005d44aeb8"
     * }
     *==============SUCCESS================
     *{
     *  "bank_account_number": "1234567899",
     *  "bank_account_holder_name": "JOE CONTINI",
     *  "bank_code": "MANDIRI",
     *  "status": "SUCCESS",
     *  "updated": "2017-03-24T08:11:07.624Z",
     *  "id": "59e608887eb26d005d44aeb8"
     *  }
     *
     * @param obj
     * @param callBackToken
     * @return
     */
    public  Result bindAccountCallBack(JSONObject obj,String callBackToken){
        logger.info("CB-Token :"+callBackToken);
        logger.info("VA-Token :"+appProperties.getXenditValidationToken());
        if(!callBackToken.equals(appProperties.getXenditValidationToken())){
            logger.info("CB-Token Error:"+callBackToken);
            return  ResultGenerator.genFailResult("Warning!");
        }
        SUserBind userBind=new SUserBind();
        //userBind.setCardAccount(obj.get("bank_account_number").toString());
        userBind.setXenditAccountId(obj.get("id").toString());
        userBind=bindMapper.selectOne(userBind);
        if(userBind==null){
          logger.info("bind callback userbind is null:"+obj.get("id").toString());
          return  ResultGenerator.genFailResult("have not request");
        }
        //已处理过 (取消和绑定不处理)
        if(!userBind.getCardStatus().equals(Constants.STATUS_WAIT))
        {
            return   ResultGenerator.genSuccessResult();
        }
        //更新状态
        Integer status= Constants.STATUS_STOP;
        if(obj.get("status").equals("SUCCESS")){
            status= Constants.STATUS_USE;
            String holdingName=obj.get("bank_account_holder_name")==null? "":obj.get("bank_account_holder_name").toString();
           if(!userBind.getHoldingName().equals(holdingName)){
                userBind.setRemark(userBind.getHoldingName()+"|"+holdingName);
           }
        }else {
            //回调失败,记录失败原因.
            String reason=obj.get("failure_reason")==null? "unknow reason":
                    sXenditPayService.NameValidatorErrors(obj.get("failure_reason").toString());
            userBind.setRemark(reason);
        }

        userBind.setCardStatus(status);
        bindMapper.updateByPrimaryKey(userBind);

        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 取消银行卡绑定
     * @param id
     * @param userId
     * @return
     */
    public Result cancelUserBind(Integer id,Integer userId){
        SUserBind userBind=new SUserBind();
        userBind.setId(id);
        userBind.setUserId(userId);
        userBind=bindMapper.selectOne(userBind);
        if(userBind==null){
            return  ResultGenerator.genFailResult("user bind is null");
        }
        userBind.setCancelTime(new Date());
        userBind.setCardStatus(Constants.STATUS_STOP);
        bindMapper.updateByPrimaryKey(userBind);

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取支持的银行卡列表
     * @param search
     * @param country
     * @return
     */
    @Override
    public Result getBackList(String search, String country) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("search", search);
        if (country == null) {
            maps.put("country", "Indonesia");
        } else {
            maps.put("country", country);
        }
        return ResultGenerator.genSuccessResult(bankMapper.getBankList(maps));
    }

    /**
     * 获取用户使用的默认卡
     * @param userId
     * @return
     */
    public  SUserBind getUserUseCard(Integer userId){
        SUserBind param=new SUserBind();
        param.setUserId(userId);
        param.setCardStatus(Constants.STATUS_USE);
        List<SUserBind> userbindList=   bindMapper.select(param);
        return  userbindList.size()==0 ? null:userbindList.get(0);
    }
}
