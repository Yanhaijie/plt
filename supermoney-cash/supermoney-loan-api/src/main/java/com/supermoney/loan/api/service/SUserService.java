package com.supermoney.loan.api.service;
import com.alibaba.fastjson.JSONObject;
import com.netflix.ribbon.proxy.annotation.Http;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/08.
 */
public interface SUserService extends Service<SUser> {
    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    public Result sendCodeMsg(String mobile);
    /**
     * 发送找回密码短信验证码
     * @param mobile
     * @return
     */
    public Result sendFindCodeMsg(String mobile);

    /**
     * 短信验证码登录
     * @param mobile
     * @param code
     * @return
     */
    public   Result codeLogin(String mobile, String code,String userId,String shareCode);
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public Result getUserInfo(Integer userId);
    /**
     * 获取用户信息2
     * @param userId
     * @return
     */
    public Result getUserInfoVo(Integer userId);

    /**
     * 获取用户统计信息
     * @param userId
     * @return
     */
    public  Result getUserCount(Integer userId);

    /**
     * 找回密码第一步
     * @param mobile
     * @param code
     * @return
     */
    public  Result findone(String mobile, String code);
    /**
     *找回密码第二步
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public  Result findtwo(String mobile, String code,String password);
    /**
     * 密码登录
     * @param mobile
     * @param password
     * @return
     */
    public   Result pwdlogin(String mobile, String password);
    /**
     * 保存用户信息
     * @param userinfo
     * @return
     */
    public  Result saveUserInfo(UserInfoVo userinfo);
    /**
     * 下级代理用户登陆
     * @param mobile
     * @param code
     * @param userId
     * @param bountyId
     * @return
     */
    public Result downUserLogin(String mobile,String code,String userId,String bountyId,String shareCode);
    /**
     *  获取GeeTest行为验证
     * @param request
     * @param pinCode
     * @return
     */
    public  String getStartCaptchaServlet(HttpServletRequest request, String pinCode);
    /**
     * 校验GeeTest行为
     * @param request
     * @param pinCode
     * @return
     */
    public Map<String,String> verifyLogin(HttpServletRequest request, String pinCode);
    /**
     * 第三方平台登陆
     * @param openId
     * @param platmfromName
     * @param nickName
     * @return
     */
    public Result openIdLogin(String openId,String platmfromName,String nickName);
    /**
     * 第三方账号绑定
     * @param openId
     * @param platformName
     * @param mobile
     * @param code
     * @return
     */
    public Result openBindUser(String openId,String platformName,String mobile,String code);

    /**
     * accountKit登录
     * @param authCode
     * @return
     */
    public   Result kitLogin( String authCode);

    /**
     * kit userinfo
     * @param accessToken
     * @return
     */
    public JSONObject getUserInfoByKit(String accessToken);

    /**
     * 用户登录首次业务处理
     * @param userId
     * @return
     */
    public  Result firstBuss(Integer userId);

    /**
     * 更新用户征信等级和分数
     * @return
     */
    public void updateCreditInfo(Map<String, Object> param);

    /**
     * 获取征信资料更改的用户
     * @return
     */
    public List<Map<String, Object>> selectCreditModifyUser();

    public  SUser getUserByMobile(String mobile);
    
    public  Result kitUserLogin(String mobile);

    /**
     * 是否包含在线测试号码
     * @param mobile
     * @return
     */
    public boolean hasLineTestNumber(String mobile);

}
