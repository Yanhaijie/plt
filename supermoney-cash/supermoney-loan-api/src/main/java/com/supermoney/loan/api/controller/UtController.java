package com.supermoney.loan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SBountySignIn;
import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import com.wi.cloud.oauthclient.vo.OauthUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bear on 2018/1/13.
 */
@RestController
@RequestMapping("/ut")
@Api(value = "/ut", description = "未登录用户相关接口")
public class UtController {

    private static final Logger logger = LoggerFactory.getLogger(UtController.class);
    @Value("${facebook.accountkit}")
    private String accountkitUrl;
    @Value("${facebook.version}")
    private String version;
    @Value("${facebook.appId}")
    private String appId;
    @Value("${facebook.secret}")
    private String secret;

    @Resource
    private SUserService sUserService;

    @Resource
    private SUserBindService userBindService;

    @Resource
    private SUserAccountService accountService;
    @Resource
    private SUserAccountBookService accountBookService;
    @Resource
    private SAccountBalanceService balanceService;

    @Resource
    private SBountyService sBountyService;

    @Resource
    private SBountySignInService sBountySignInService;

    @Resource
    private SBussLabelService sBussLabelService;

    @Resource
    private  UserAccountBussService userAccountBussService;

    @Resource
    private  SExchangeRateService sExchangeRateService;

    @Resource
    private SBannerService sBannerService;

    @Resource
    private  SLoanOrderService sLoanOrderService;

    @Resource
    private SXenditVirtualAccountService sXenditVirtualAccountService;

    @Resource
    private NomalBussService nomalBussService;



    @ApiOperation("测试调试-信息")
    @GetMapping("info")
    public  Result info(){
        return ResultGenerator.genSuccessResult( "superapi info");
    }

    @ApiOperation("测试返回-401信息")
    @PostMapping("test401")
    public  String test(HttpServletRequest request, HttpServletResponse respon){
        respon.setStatus(401);
        return  "";
    }
    /**
     * 发送短信验证码-暂时关闭此接口，以极验的方式发送短信。
     *
     * @param mobile
     * @return
     */
    @ApiOperation("发送登录短信验证码")
    @PostMapping("/sendcode")
    public Result sendcode(@RequestParam String mobile) {
//        原代码
//        是否包含在线测试号码
//        if(sUserService.hasLineTestNumber(mobile)){
//            return  sUserService.senndCodeMsg(mobile);
//        }else {
//            return  ResultGenerator.genFailResult("channel is close");
//        }
        return  sUserService.sendCodeMsg(mobile);
    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation("发送找回密码短信验证码")
    @PostMapping("/sendfindcode")
    public Result sendfindcode(@RequestParam String mobile) {
        return sUserService.sendFindCodeMsg(mobile);
    }

    /**
     * 短信验证码登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @ApiOperation("短信验证码登录")
    @PostMapping("/codelogin")
    public Result codelogin(@RequestParam String mobile, @RequestParam String code) {
        return sUserService.codeLogin(mobile, code, "","");
    }

    /**
     * accountKit登陆
     * @param
     * @param authCode
     * @return
     */
    @ApiOperation("accountKit登陆")
    @PostMapping("/kitlogin")
    public  Result kitLogin(@RequestParam String authCode){
        return  sUserService.kitLogin(authCode);
    }

    /**
     * 分享下级用户验证码登录
     *
     * @param mobile
     * @param code
     * @param userId
     * @return
     */
    @ApiOperation("分享下级用户验证码登录")
    @PostMapping("/down-codelogin")
    public Result downCodeLogin(HttpServletRequest request,@RequestParam String mobile, @RequestParam String code, @RequestParam String userId,
                                @RequestParam(defaultValue = "",required = false) String bountyId,
                                @RequestParam(defaultValue = "",required = false) String source,
                                @RequestParam(defaultValue = "",required = false) String shareCode
    ) {
        source=source==null? "shareWeb":"shareWeb|"+source.toString();
        request.setAttribute("share",source);

        return sUserService.downUserLogin(mobile, code, userId, bountyId,shareCode);
    }

    /**
     * 分享获取赏金任务介绍
     *
     * @param userId
     * @param bountyId
     * @return
     */
    @ApiOperation("分享获取赏金任务介绍")
    @PostMapping("/share-bounty")
    public Result getSharesms(@RequestParam String userId, @RequestParam String bountyId) {
        return sBountyService.getSharesms(userId, bountyId);
    }

    @ApiOperation("获取banner图片")
    @PostMapping("/bussLable")
    public  Result getBanners(String lableName){
        return  sBussLabelService.getByBanner();
    }

    /**
     * 获取app配置
     * @return
     */
    @ApiOperation("app配置信息")
    @PostMapping("/app-config")
    public  Result  getAppConfig(){
        return  sBussLabelService.getByLabelToJson("appconfig");
    }

    @ApiOperation("计算借款配置信息")
    @PostMapping("/loan-config")
    public  Result loanConfig(Integer bountyId){ return  sLoanOrderService.getloanSet(bountyId);}

    @ApiOperation("获取banner(新)")
    @PostMapping("/banner")
    public  Result getBannerList(){
        Map<String, Object> param = new HashMap<>();
        param.put("putPosition","homepage");
        param.put("nowDate", new Date());
        //加入国家编码查询
        Map<String,String> reqHeaders= RequestUntil.getUserAgentParams();
        String areaCode=reqHeaders.get("COUNTRYID");
        if(StringUtils.isNotEmpty(areaCode)){
            param.put("areaCode",areaCode);
        }else{
            param.put("areaCode","899");
        }
        
        return  ResultGenerator.genSuccessResult(sBannerService.selectBannerByParam(param));
    }

    /**
     * 密码登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @ApiOperation("密码登录")
    @PostMapping("/pwdlogin")
    public Result pwdlogin(@RequestParam String mobile, @RequestParam String password) {
        return sUserService.pwdlogin(mobile, password);
    }

    /**
     * 找回密码第一步，验证短信验证码
     * @param mobile
     * @param code
     * @return 返回密钥
     */
    @ApiOperation("找回密码第一步，验证手机和验证码")
    @PostMapping("/findone")
    public Result findone(@RequestParam String mobile, @RequestParam String code) {
        return sUserService.findone(mobile, code);
    }

    /**
     * 找回密码第二步,修改密码
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @ApiOperation("找回密码第二步,修改密码")
    @PostMapping("/findtwo")
    public Result findtwo(@RequestParam String mobile, @RequestParam String code, @RequestParam String password) {
        return sUserService.findtwo(mobile, code, password);
    }


    @ApiOperation("获取支持的银行卡列表")
    @GetMapping("/get-bank-list")
    public Result getBankList(@RequestParam String search) {
        return userBindService.getBackList(search, "Indonesia");
    }

    @ApiOperation("获取行为验证参数")
    @RequestMapping(value = "/get-behavior",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  String getBehavior(HttpServletRequest request,String pin){
     return  sUserService.getStartCaptchaServlet(request,pin);
    }

    @ApiOperation("验证行为验证参数")
    @RequestMapping(value = "/verify-behavior",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,String> verifyBehavior(HttpServletRequest request, String pin){
        return  sUserService.verifyLogin(request, pin);
    }

    @ApiOperation("借款计算")
    @PostMapping("/calculation")
    public  Result calculation( @ApiParam(name = "bountyId", value = "任务编号",  required = true)  @RequestParam Integer bountyId,
                                @ApiParam(name = "money", value = "借款金额", required = true)   @RequestParam Integer money,
                                @ApiParam(name = "limit", value = "借款期限", required = true)  @RequestParam Integer limit ,
                                @ApiParam(name = "limitUnit", value = "期限单位") @RequestParam(defaultValue = "0") Integer limitUnit){
        return  sBountyService.calculation(bountyId,money,limit,limitUnit);
    }

    @ApiOperation("第三方平台登陆")
    @PostMapping("/openid-login")
    public Result openIdLogin( @ApiParam(name = "openId", value = "第三方平台唯一用户编号",  required = true)  @RequestParam String openId,
                               @ApiParam(name = "platmfromName", value = "第三方平台名称",  required = true)  @RequestParam  String platmfromName,
                               @ApiParam(name = "nickName", value = "昵称",  required = false)  @RequestParam String nickName){
        return  sUserService.openIdLogin(openId,platmfromName,nickName);
    }

    @ApiOperation("第三方账号绑定")
    @PostMapping("/openid-bind")
    public Result openBindUser(@ApiParam(name = "openId", value = "第三方平台唯一用户编号",  required = true)  @RequestParam String openId,
                               @ApiParam(name = "platmfromName", value = "第三方平台名称",  required = true)  @RequestParam  String platmfromName,
                               @ApiParam(name = "mobile", value = "绑定手机号",  required = true)  @RequestParam String mobile,
                               @ApiParam(name = "code", value = "验证码",  required = true) @RequestParam   String code){
        return  sUserService.openBindUser(openId,platmfromName,mobile,code);
    }
    @ApiOperation("获取国家国币信息")
    @PostMapping("/currency")
    public  Result getCurrency(String country){
        return  sExchangeRateService.getCurrency(country);
    }

    @ApiOperation("登录首次触发业务")
    @PostMapping("/first-buss")
    public  Result userLoginFirstBuss(String appSecret){
        if(StringUtils.isBlank(appSecret)){
            return ResultGenerator.genFailResult("have not appSecret");
        }
        return  sUserService.firstBuss(UserUtils.getCurrentUserId());
    }


    @ApiOperation("测试创建虚拟账户")
    @PostMapping("testCreateAccount")
    public  Result testCreateAccount(Integer userId,Integer order,String name){
        return  sXenditVirtualAccountService.createVirtualAccount(userId,order,name,"BNI",null,1);
    }

    @ApiOperation("测试创建制定还款金额虚拟账户")
    @PostMapping("testCreateAccountExpectAmmount")
    public  Result testCreateAccount1(Integer userId,Integer order,String name,Integer ammount){
        return  sXenditVirtualAccountService.createVirtualAccount(userId,order,name,"BNI",ammount,1);
    }


    @ApiOperation("测试更新还款金额虚拟账户")
    @PostMapping("testUpdateAccount")
    public  Result testCreateAccount1(Integer ammout,String id ){
        return  sXenditVirtualAccountService.updateVirtualAccount(id,ammout);
    }

    @ApiOperation("测试超时")
    @PostMapping("/testtime")
    public  Result testTimeOut(Integer val){
        try {
            long s=val.intValue()*1000;
            Thread.sleep(s);
            SUserBind bind=new SUserBind();
            bind.setRemark("test time out:"+String.valueOf(s));
            return  ResultGenerator.genSuccessResult(bind);
        }catch (Exception ex){
            ex.printStackTrace();
            return  ResultGenerator.genFailResult(" testTimeOut error! ");
        }
    }

    @ApiOperation("弹窗消息")
    @PostMapping("/dialog-msg")
    public  Result userDialogMsg(String appSecret){
        Integer userId=StringUtils.isBlank(appSecret) ? 0:UserUtils.getCurrentUserId();
        return  nomalBussService.getDialogMsg(userId);
    }

    @ApiOperation("APP版本")
    @PostMapping("/appversion")
    public  Result appversion(String appSecret){
          return  sBussLabelService.appVersion("");
    }

    @ApiOperation("APP下载地址")
    @PostMapping("/appDownLoad")
    public  Result appDownLoad(String appSecret){
        return  sBussLabelService.getByNameNotSplit("appDownLoad");
    }

    @ApiOperation("APP模块功能开关")
    @PostMapping("/appmoudles")
    public  Result appmoudles(String appSecret){
        return  sBussLabelService.appModules();
    }

    /**
     * facebook上的登录验证
     * @param code
     * @param bountyId
     * @param appSecret
     * @param userAccessToken
     * @return
     */
    @ApiOperation("赏金任务登记")
    @PostMapping("/signIn")
    public Result signIn(String code,String bountyId,String appSecret,String userAccessToken) {
        logger.info("signIn====================start  code="+code+"||bountyId="+bountyId+"||appSecret="+appSecret+"||userAccessToken"+userAccessToken);
        if(StringUtils.isEmpty(bountyId)){
            return ResultGenerator.genFailResult("bountyId is null");
        }else{
            bountyId = bountyId.trim();
        }
        Map resultMap = Maps.newHashMap();
        //验证是否登录过
        if(StringUtils.isNotEmpty(appSecret) && StringUtils.isNotEmpty(userAccessToken)){
            //获取用户信息并登记
            JSONObject userInfoObject = sBountySignInService.getUserInfoAndSign(userAccessToken,Integer.parseInt(bountyId),appSecret);
            if(userInfoObject == null){
                logger.info("signIn===>>accessToken or appSecret error and userInfoObject is null");
                return ResultGenerator.genFailResult("accessToken or appSecret error");
            }
        }else if(StringUtils.isEmpty(code)){
            return ResultGenerator.genFailResult("code is null");
        }else{
            JSONObject tokenObject = sBountySignInService.getAccessToken(code);
            if(tokenObject == null){
                logger.info("signIn===>>tokenObject is null");
                return ResultGenerator.genFailResult("get userAccessToken request error");
            }
            userAccessToken = tokenObject.getString("access_token");
            //获取用户信息并登记
            JSONObject userInfoObject = sBountySignInService.getUserInfoAndSign(userAccessToken,Integer.parseInt(bountyId),null);
            if(userInfoObject == null){
                logger.info("signIn===>>userInfoObject is null");
                return ResultGenerator.genFailResult("get userInfo request error");
            }
            //注册并登录返回用户数据
            JSONObject phoneInfo=(JSONObject)userInfoObject.get("phone");
            String phone = phoneInfo.get("national_number").toString().trim();
            logger.info("signIn===>>phone ="+phone);
            Result result = sUserService.kitUserLogin(phone);
            OauthUserInfo userinfo = (OauthUserInfo)result.getData();
            appSecret = userinfo.getAppSecret();
        }
        resultMap.put("userAccessToken",userAccessToken);
        resultMap.put("appSecret",appSecret);
        logger.info("signIn====================end");
        return ResultGenerator.genSuccessResult(resultMap);
    }

}
