package com.supermoney.loan.api.service;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
public interface SUserBindService extends Service<SUserBind> {

    /**
     * 用户绑定列表
     */
    List<SUserBindVo> getUserBindList(Map<String, Object> maps);
    /**
     * 根据银行账号获取用户绑卡银行信息
     * @param userId
     * @param cardAccount
     * @return
     */
    public  SUserBindVo getUserBindByCardAccount(Integer userId,String cardAccount);

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
    public Result userBindCard(Integer userId,Integer bankId,String cardNumber,String cardAccount,String holdingName,String holdingCard,String holdPhone);
    /**
     * 绑定银行卡回调处理
     * @param obj
     * @param callBackToken
     * @return
     */
    public  Result bindAccountCallBack(JSONObject obj, String callBackToken);
    /**
     * 取消绑卡
     *
     * @param
     * @return
     */
    Result cancelUserBind(Integer id,Integer userId);
    /**
     * 获取支持的银行卡列表
     * @param search
     * @param country
     * @return
     */
    Result getBackList(String search, String country);
    /**
     * 获取用户使用的默认卡
     * @param userId
     * @return
     */
    public  SUserBind getUserUseCard(Integer userId);

}
