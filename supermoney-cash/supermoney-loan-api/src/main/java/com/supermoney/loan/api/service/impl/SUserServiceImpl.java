package com.supermoney.loan.api.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.dao.SBountyMapper;
import com.supermoney.loan.api.dao.SUserBindMapper;
import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.entity.vo.UserCountVo;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import com.supermoney.sms.SmsClient;
import com.wi.cloud.oauthclient.GwtOauthClient;
import com.wi.cloud.oauthclient.vo.OauthUserInfo;
import com.wi.cloud.oauthclient.vo.RegUserInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
/**
 * Created by xionghuifeng on 2018/01/08.
 */
@Service
@Transactional
public class SUserServiceImpl extends AbstractService<SUser> implements SUserService {

    private static final Logger logger = LoggerFactory.getLogger(SUserServiceImpl.class);

    @Resource
    private SUserMapper sUserMapper;

    @Autowired
    private SUserBindMapper sUserBindMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GwtOauthClient gwtOauthClient;

    @Autowired
    private  AppProperties appProperties;

    @Resource
    private UserAccountBussService userAccountBussService;

    @Resource
    private SBountyMapper sBountyMapper;

    @Resource
    private SUserOpenService sUserOpenService;

    @Resource
    private SExchangeRateService sExchangeRateService;

    @Resource
    private SUserPayService sUserPayService;

    @Resource
    private  SBountyService sBountyService;

    @Resource
    private SAtIdentityService sAtIdentityService ;


    /**
     * 发送登录短信验证码
     * @param mobile
     * @return
     */
    public Result sendCodeMsg(String mobile){
        String rkey=com.supermoney.loan.api.utils.Constants.REDIS_MSGCODE_KEY+Md5Util.md5Hex(mobile);
        String tmp=appProperties.getSmsTmp();// "【无限云】您的验证码为：%s，请在一分钟内使用，谢谢！";
        Result result=new Result();
        logger.info("sendCode==============rkey"+rkey);
        //是否在20分钟内超过10次
        Object hasSend=redisTemplate.opsForValue().get(rkey);
        if((hasSend !=null)) {
            if (Integer.parseInt(hasSend.toString()) >= 10) {
                result.setMessage("fail :phone More than 10 times");
                result.setCode(ResultCode.FAIL.code);
                return result;
            }
            redisTemplate.opsForValue().increment(rkey,1);
        }else{
            redisTemplate.opsForValue().increment(rkey,1);
            redisTemplate.expire(rkey,20*60,TimeUnit.SECONDS);
        }
        Object isExpired=redisTemplate.opsForValue().get(Constants.REDIS_MSGCODE_ISEXPIRED+mobile);
        //验证已发送
        if(isExpired != null){
            result.setMessage("fail :sms has send");
            result.setCode(ResultCode.FAIL.code);
            return result;
        }
        redisTemplate.opsForValue().set(Constants.REDIS_MSGCODE_ISEXPIRED+mobile,"1",60, TimeUnit.SECONDS);
        return  sendSmsCodeMsg(mobile,Constants.REDIS_MSGCODE_KEY+mobile,tmp,60*5);
    }

    /**
     * 发送找回密码短信验证码
     * @param mobile
     * @return
     */
    @Override
    public Result sendFindCodeMsg(String mobile){
        String tmp=appProperties.getSmsTmp();//"【无限云】您的验证码为：%s，请在一分钟内使用，谢谢！";
        String rkey= Constants.REDIS_FINDCODE_KEY+mobile;
        return  sendSmsCodeMsg(mobile,rkey,tmp,60);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param redisKey
     * @param contentTmp
     * @param seconds
     * @return
     */
    public  Result sendSmsCodeMsg(String mobile,String redisKey,String contentTmp,int seconds){
        // true :发送验证码 false : 不发送验证码 验证码为默认验证码
        boolean isSendSms= appProperties.getOpenSmsSend()==null? false:appProperties.getOpenSmsSend().equals("true");
        Result result=new Result();
        result.setCode(ResultCode.FAIL.code);
        if(StringUtils.isBlank(mobile)){
            result.setMessage("number is empty!");
            return result;
        }
        //isSendSms &&
        if(!NomalUntil.checkYnNumber(mobile)){
            result.setMessage("number format error!");
            return result;
        }
        //如果存在则删除
        Object hasIspired=redisTemplate.opsForValue().get(redisKey);
        if(hasIspired!=null){
           redisTemplate.delete(redisKey);
        }


        String defaulSmsCode=appProperties.getDefaulSmsCode();
        String code=isSendSms? NomalUntil.generateRandomNum(5):defaulSmsCode;
        String content=String.format(contentTmp,code);
        //将来接入第三方短信厂商
        //mobile=addAreaCodeMobile(mobile);
        logger.info("send sms code :"+code+" mobile:"+mobile+"isSendSms:"+isSendSms);
        boolean sendResult= isSendSms ? SmsClient.sendMsg(mobile,content, com.supermoney.sms.Constants.PRODUCT_PAASOO):true;
        if(sendResult){
            redisTemplate.opsForValue().set(redisKey,code,seconds, TimeUnit.SECONDS);
            result.setCode(ResultCode.SUCCESS.code);
            result.setMessage("send sms success!");
        }else {
            result.setMessage("send sms faild!");
        }
        return  result;
    }

    /**
     * 是否开发者账号
     * @param mobile
     * @param redisKey
     * @return
     */
    public boolean hasDevNumber(String mobile,String redisKey){
        boolean result=hasLineTestNumber(mobile);
        if(result){
//           原 String code=mobile.equals("081234567880")? "770880":"10086";
            String code=appProperties.getDefaulSmsCode();
            redisTemplate.opsForValue().set(redisKey,code,60, TimeUnit.SECONDS);
        }

        return  result;
    }

    @Override
    public boolean hasLineTestNumber(String mobile){
        String[] ars=new String[]{"081234567880","081234567881","081234567882","081234567883","081234567884",
                "081234567885","081234567886","081234567887","081234567888","081234567889","081234567890",
                "082213685706"};
        boolean result=false;
        for (String item:ars) {
            if(item.equals(mobile)){
                result=true;
                break;
            }
        }
        return  result;
    }

    /**
     * 号码添加区号
     * @param mobile
     * @returnhao
     */
//    public  String  addAreaCodeMobile(String mobile){
//        String code="63";
//        //08开头，印尼
//        if(mobile.indexOf("08")==0){
//            mobile=mobile.substring(1,mobile.length());
//            mobile=code+mobile;
//        }
//        return  mobile;
//    }

    /**
     * 密码登录
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public   Result pwdlogin(String mobile, String password){
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(password)){
            return  ResultGenerator.genFailResult("number or password is empty!");
        }
        SUser user=getUserByMobile(mobile);
        if(user==null){
            return ResultGenerator.genFailResult("login faild: have not user 101");
        }
        if(!user.getUserPassword().equals(password)){
            return ResultGenerator.genFailResult("login faild: username or password faild!");
        }

        OauthUserInfo userinfo=loginOauth(user.getId(),mobile,password);
        return  userinfo!=null ?   ResultGenerator.genSuccessResult(userinfo):
                ResultGenerator.genFailResult("login faild: have not oauth user !");
    }

    /**
     * 短信验证码登录
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public   Result codeLogin(String mobile, String code,String userId,String shareCode){
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
            return  ResultGenerator.genFailResult("code is empty!");
        }
        String rkey=com.supermoney.loan.api.utils.Constants.REDIS_MSGCODE_KEY+mobile;
        Object rcode=redisTemplate.opsForValue().get(rkey);
        logger.info("rcode got ");
        if(rcode==null || !rcode.toString().equals(code)){
            return  ResultGenerator.genFailResult("sms code faild!");
        }

        return  hasUser(mobile)? appUserDefaultLogin(mobile):noneUserLogin(mobile,userId,shareCode);
    }

    /**
     * app用户默认密码登录
     * @param mobile
     * @return
     */
    public Result appUserDefaultLogin(String mobile){
        SUser user=getUserByMobile(mobile);
        if(user==null){
           return ResultGenerator.genFailResult("login faild: have not user 100 ");
        }

        OauthUserInfo userinfo=loginOauth(user.getId(),mobile,user.getUserPassword());
        if(userinfo != null){
            sUserMapper.updatelastLoginTime(user.getId());
            return ResultGenerator.genSuccessResult(userinfo);
        }else{
            return ResultGenerator.genFailResult("login faild: have not oauthuser !");
        }
    }

    /**
     * kit 用户登录
     * @param mobile
     * @
     */
    public  Result kitUserLogin(String mobile){
        //印尼手机号如果没有加0,则要补上
//        if(mobile.substring(0,1).equals("8"))
//        {
//            mobile="0"+mobile;
//        }
        return  hasUser(mobile)? appUserDefaultLogin(mobile):noneUserLogin(mobile,"","");
    }


//    public static void main(String[] args) {
//       String mobile="123456789";
//       mobile.substring(0,1).equals("8");
//    }
    /**
     *  不存在用户登录
     * @param mobile
     * @param parentUserId
     * @return
     */
   public  Result noneUserLogin(String mobile,String parentUserId,String shareCode){
        //如果是下级代理登录，要检查父级用户是否存在
       if(!StringUtils.isBlank(parentUserId)){
           SUser  parentUser= sUserMapper.selectByPrimaryKey(Integer.valueOf(parentUserId));
           if(parentUser==null){
               return ResultGenerator.genFailResult("login faild: have not parentUser");
           }
       }
       //先注册后获取用户信息
       String password=NomalUntil.generateRandomNum(6);
       Result regResult=appUserDefaultReg(mobile,password,parentUserId,shareCode);
       if(regResult.getCode()==ResultCode.SUCCESS.code){
           OauthUserInfo userinfo=loginOauth((Integer)regResult.getData(),mobile,password);
           return  userinfo!=null ?   ResultGenerator.genSuccessResult(userinfo):
                   ResultGenerator.genFailResult("sign faild: have not oauthuser !");

       }else {
           return  ResultGenerator.genFailResult("sign faild:reg is  faild!");
       }
   }
    /**
     * 用户是否存在
     * @param username
     * @return
     */
    public  boolean hasUser(String username){
        return   gwtOauthClient.hasUserName(username);
    }

    /**
     * 登录到鉴权服务，并返回用户信息
     * @param username
     * @param password
     * @return
     */
    public OauthUserInfo loginOauth(Integer appUserId,String username, String password){
        boolean hasUser=false;
        boolean login=gwtOauthClient.toLogin(username,password);
        if(login){
            hasUser=gwtOauthClient.getOauthUserInfo(gwtOauthClient.getUserInfo().getAccess_token());
        }
        if(hasUser){
            String appStr=UserUtils.toAppSecret(appUserId,username);
            gwtOauthClient.getUserInfo().setAppSecret(appStr);
            return  gwtOauthClient.getUserInfo();
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public Result getUserInfo(Integer userId){
         SUser user=sUserMapper.selectByPrimaryKey(userId);
         if(user==null){
             return  ResultGenerator.genFailResult("have not user");
         }
         UserInfoVo userinfo=new UserInfoVo();
        userinfo.setUserId(user.getId());
        userinfo.setUserLevel(user.getUserLevel());
        userinfo.setAge(NomalUntil.objString(user.getAge()));
        userinfo.setSex(NomalUntil.objString(user.getSex()));
        userinfo.setUserName(NomalUntil.noneString(user.getUserName()));
        userinfo.setRealName(NomalUntil.noneString(user.getRealName()));
        userinfo.setEmail(NomalUntil.noneString(user.getEmail()));
        userinfo.setMobile(NomalUntil.noneString(user.getMobile()));
        //绑卡数量
        userinfo.setBindCardNum(sUserBindMapper.userBindTotal(userId));
        userinfo.setCountry(NomalUntil.noneString(user.getCountry()));
        userinfo.setCreditScore(NomalUntil.objString(user.getCreditScore()));
        userinfo.setCreditGrade(user.getCreditGrade());
        userinfo.setCreditModify(user.getCreditModify());
        userinfo.setPic("default");
        return  ResultGenerator.genSuccessResult(userinfo);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public Result getUserInfoVo(Integer userId){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        UserInfoVo userinfo=sUserMapper.getUserInfo(map);
        map.put("certType",1);
        SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(map);
        if (sAtIdentity == null){
            userinfo.setRealNameStatus(3);
        }
        else {
            if(sAtIdentity.getIdentityStatus() == 0) {
                userinfo.setRealNameStatus(0);
            }
            else if(sAtIdentity.getIdentityStatus() == 3) {
                userinfo.setRealNameStatus(1);
            }
            else if(sAtIdentity.getIdentityStatus() == 4) {
                userinfo.setRealNameStatus(2);
            }
        }
        if(userinfo==null){
            return  ResultGenerator.genFailResult("user is null!");
        }
        userinfo.setBindCardNum(sUserBindMapper.userBindTotal(userId));
        //转换汇率
        SExchangeRate rate=sExchangeRateService.getUsdByCountry(Constants.Country.PHP_CR);
        logger.info("当前汇率为："+Constants.Country.PHP_CR+"rate: "+rate.getExchangeVal());
        userinfo.setAvailableAmount(sExchangeRateService.valByRate(rate,userinfo.getAvailableAmount()));
        logger.info("用户可用金额为："+userinfo.getAvailableAmount()+"换算之后为："+userinfo.getAvailableAmount());
        userinfo.setCashAmount(sExchangeRateService.valByRate(rate,userinfo.getCashAmount()));
        userinfo.setFreezeAmount(sExchangeRateService.valByRate(rate,userinfo.getFreezeAmount()));
        return  ResultGenerator.genSuccessResult(userinfo);
    }

    /**
     * 获取用户统计信息
     * @param userId
     * @return
     */
    public  Result getUserCount(Integer userId){
        Map<String,Object> param=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        param.put("userId",userId);
        UserCountVo countVo=sUserMapper.getUserCount(param);

        SExchangeRate rate=sExchangeRateService.getUsdByCountry(Constants.Country.PHP_CR);
        countVo.setAvailableAmount(sExchangeRateService.valByRate(rate,countVo.getAvailableAmount()));
        countVo.setCashAmount(sExchangeRateService.valByRate(rate,countVo.getCashAmount()));
        countVo.setFreezeAmount(sExchangeRateService.valByRate(rate,countVo.getFreezeAmount()));
        countVo.setAllAmount(sExchangeRateService.valByRate(rate,countVo.getAllAmount()));

        Map<String,Object> result=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        result.put("availableAmount",sExchangeRateService.valToInt(countVo.getAvailableAmount()));
        result.put("cashAmount",sExchangeRateService.valToInt(countVo.getCashAmount()));
        result.put("freezeAmount",sExchangeRateService.valToInt(countVo.getFreezeAmount()));
        result.put("allAmount",sExchangeRateService.valToInt(countVo.getAllAmount()));

        return  ResultGenerator.genSuccessResult(result);
    }

    /**
     * 保存用户信息
     * @param userinfo
     * @return
     */
    @Override
    public  Result saveUserInfo(UserInfoVo userinfo){
        if(userinfo==null){
            return  ResultGenerator.genFailResult(" user param is null");
        }
        SUser user=sUserMapper.selectByPrimaryKey(userinfo.getUserId());
        if(user==null){
            return  ResultGenerator.genFailResult("have not user");
        }
        user.setEmail(userinfo.getEmail());
        sUserMapper.updateByPrimaryKey(user);
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * app用户默认注册
     * @param mobile
     * @param password
     * @param parentUserId
     * @return
     */
    public  Result appUserDefaultReg(String mobile,String password,String parentUserId,String shareCode){
        //获取userAgent
        Map<String,String> usrAgent=RequestUntil.getUserAgentParams();
        //sUserMapper
        //oauth注册
        RegUserInfo reguser=new RegUserInfo();
        reguser.setUserName(mobile);
        reguser.setPassword(password);
        reguser.setNickName(mobile);
        reguser.setGender("0");
        reguser.setUserType(0);//对外用户
        reguser.setRegSource("Hipeso-app");//pulant-app注册
        logger.info("gwtOauthClient.regUser go");
       com.wi.cloud.oauthclient.vo.Result oauthRegResult=gwtOauthClient.regUser(reguser);
        logger.info("gwtOauthClient.regUser end");
       if(oauthRegResult.getCode()!=ResultCode.SUCCESS.code){
           return  ResultGenerator.genFailResult("reg faild!");
       }
       //supermoney新增用户资料
       SUser user=new SUser();
       user.setUserCode(oauthRegResult.getData().toString());
       user.setUserName(mobile);
       user.setUserPassword(password);
       user.setMobile(mobile);
       user.setUserLevel(0);//普通用户
       user.setSex(0);//男
       user.setUserStatus(0);//启用
       user.setLotteryCount(0);
       logger.info("解密前share为："+shareCode);
       //注册来源处理
        String regSource = "";
        if(StringUtils.isNotBlank(shareCode)){
            regSource = NomalUntil.decoderShareCode(shareCode);
        }
        String share = usrAgent.get("shar");
        logger.info("share: "+share);
        logger.info("regSource: "+regSource);
        if(StringUtils.isNotBlank(regSource) && StringUtils.isNotBlank(share)){
            regSource = regSource.replace("shar/null","shar/"+share);
            user.setRegSource(regSource);
        }else if(StringUtils.isNotBlank(regSource)){
            user.setRegSource(regSource);
        }else{
            user.setRegSource("app:"+usrAgent.get("PARAMS"));
        }
       user.setRegIp(usrAgent.get("IP"));
       user.setCreditGrade(Constants.CreditGrade.GRADE_F);
       user.setCreditModify(0);
       if(StringUtils.isNotBlank(parentUserId)){
           user.setParentId(Integer.valueOf(parentUserId));
       }
       sUserMapper.insert(user);
       //初始化 资金账户 和 资金信息
        userAccountBussService.firstInitAcocountAndFund(user.getId());
        //给新注册的用户充值0.133108美元
        SUserPay  userPay=new SUserPay();
        userPay.setUserId(user.getId());
        userPay.setPaySourceId(Constants.PLATFORM_USERID);
        userPay.setPayMoney(new BigDecimal(0.133108));
        userPay.setAreaCode(Constants.Country.PHP_CODE);
        userPay.setRemark("firstReg");
        userPay.setPayType(Constants.BussPay.PAYTYPE_LOTTERY);
        sUserPayService.save(userPay);
        //代理推广人头结算
        if(StringUtils.isNotBlank(parentUserId)) {
            agencyCountPerson(user.getId(),Integer.valueOf(parentUserId));
        }
        logger.info("new user reg success");
       return   ResultGenerator.genSuccessResult(user.getId());
    }

    /**
     * 代理人头结算
     * @param regUserId
     * @param parentUserId
     */
    public void  agencyCountPerson(Integer regUserId,Integer parentUserId){
        try {
            countParentUserAgency(regUserId,parentUserId,1);
        }catch (Exception ex){
            logger.info("agency count person ex:"+ex.getMessage());
        }
    }

    /**
     * 递归统计上级代理推广人头
     * @param parentUserId
     * @param level
     * @return
     */
    public void countParentUserAgency(Integer regUserId,Integer parentUserId,int level){
        //只递归到当前结算用户的上3级
        if(level>3){
             return ;
         }
        SUser  parentUser= sUserMapper.selectByPrimaryKey(Integer.valueOf(parentUserId));
        if(parentUser==null){
            return ;
        }
        //人头结算充值
        SUserPay userPay=new SUserPay();
        //userPay.set
        userPay.setUserId(parentUser.getId());
        userPay.setPaySourceId(Constants.PLATFORM_USERID);
        //代理人头结算费用 0.05 - 0.01
        userPay.setPayMoney(new BigDecimal(0.01));
        userPay.setAreaCode(Constants.Country.INDONESIA_CODE);
        userPay.setRemark("agencyPersonCount");
        userPay.setPayType(Constants.BussPay.PAYTYPE_AGENCY);
        userPay.setOpt(regUserId.toString());
        sUserPayService.save(userPay);
        //上级不为空结算
       // userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_AGENCY,Constants.BUSS_TYPE_AGENCY_PERSON,parentUser.getId(),new BigDecimal(0.05));
        level=level+1;
        if(parentUser.getParentId()==null){
            return;
        }
        countParentUserAgency(regUserId,parentUser.getParentId(),level);
    }

    /**
     * 找回密码第一步
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public  Result findone(String mobile, String code){
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
            return  ResultGenerator.genFailResult("mobile and code not empty!");
        }
        String rkey=Constants.REDIS_FINDCODE_KEY+mobile;
        Object rcode=redisTemplate.opsForValue().get(rkey);
        if(rcode==null || !rcode.toString().equals(code)){
            return  ResultGenerator.genFailResult("code err!");
        }
        if(!hasUser(mobile)){
            return  ResultGenerator.genFailResult("have not user!");
        }
        return  ResultGenerator.genSuccessResult();
    }

    /**
     *找回密码第二步
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @Override
    public  Result findtwo(String mobile, String code,String password){
        //再次校验
        Result oneResult=findone(mobile,code);
        if(oneResult.getCode()!=ResultCode.SUCCESS.code){
            return  oneResult;
        }
        SUser user=getUserByMobile(mobile);
        if(user==null){
            return ResultGenerator.genFailResult("have not userinfo");
        }
        //修改oauth密码
        com.wi.cloud.oauthclient.vo.Result gwtResult= gwtOauthClient.editPwd(mobile,password);
        if(gwtResult.getCode()!=ResultCode.SUCCESS.code){
            return ResultGenerator.genFailResult("oauth err!");
        }
        //修改用户信息密码
        user.setUserPassword(password);
        sUserMapper.updateByPrimaryKey(user);
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 根据手机号获取用户
     * @param mobile
     * @return
     */
    public  SUser getUserByMobile(String mobile){
        logger.info("getUserByMobile:"+mobile);
        SUser user=new SUser();
        user.setUserName(mobile);
        user=sUserMapper.selectOne(user);
        if(user==null){
            user=sUserMapper.getUserByMobile(mobile);
        }
        return  user;
    }

    /**
     * 根据用户ID获取用户
     * @param userId
     * @return
     */
    public  SUser getUserByUserId(Integer userId){
        SUser user=new SUser();
        user.setId(userId);
        return  sUserMapper.selectOne(user);
    }
    /**
     * 下级代理用户登陆
     * @param mobile
     * @param code
     * @param userId
     * @param bountyId
     * @return
     */
    @Override
    public Result downUserLogin(String mobile,String code,String userId,String bountyId,String shareCode) {
        logger.info("手机号"+mobile+"_"+code+"_"+userId+"_"+bountyId+"_"+shareCode);
        //如果赏金ID为空则用SUPERMONEY任务
        if(StringUtils.isBlank(bountyId)){
            SBounty  spBounty=sBountyService.getSuperMoneyBounty();
            bountyId=spBounty!=null? spBounty.getId().toString():"";
            logger.info("bountyId is none use hipeso bounty");
        }

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(bountyId)){
            return  ResultGenerator.genFailResult("param ia faild!");
        }

         Result loginResult=codeLogin(mobile,code,userId,shareCode);
        logger.info("用户信息====================="+loginResult.toString());
         if(loginResult.getCode()!=ResultCode.SUCCESS.code){
             return  loginResult;
         }

        OauthUserInfo userinfo=(OauthUserInfo)loginResult.getData();

        SBounty bounty=sBountyMapper.selectByPrimaryKey(Integer.valueOf(bountyId));
         if(bounty==null){
             return  ResultGenerator.genFailResult("bounty is null");
         }
        logger.info("用户信息====================="+userinfo.getAppSecret());
        String[] ary=UserUtils.deAppSecret(userinfo.getAppSecret());
        logger.info("ary size :"+ary);
        Integer regUserId= Integer.valueOf(ary[0]);
        logger.info("regUserId"+regUserId);
        String clickId=NomalUntil.encodeClickId(regUserId,bounty.getId(),
                bounty.getAdsId(),appProperties.getAdsChId(),"hipeso-app");
        logger.info("clickId :"+clickId);
        String adsUrl=bounty.getAdsUrl()+"&ch_subid="+regUserId+"&click_id="+clickId;
        String sharUrl= appProperties.getH5Sharesms()+"?userId="+regUserId+"&bountyId="+bounty.getId()+"&site=shar";

        Map<String,Object> data=new HashMap<>();
        data.put("userInfo",loginResult.getData());
        data.put("sharUrl",sharUrl);

        return  ResultGenerator.genSuccessResult(data);
    }

    /**
     *  获取GeeTest行为验证
     * @param request
     * @param pinCode
     * @return
     */
    @Override
    public  String getStartCaptchaServlet(HttpServletRequest request, String pinCode){
        logger.info("pinCode:"+pinCode);
        if(StringUtils.isNotBlank(pinCode)){
            logger.info("send pinCode:"+pinCode);
            Result sendResult=sendCodeMsg(pinCode);
            if(sendResult.getCode()!=ResultCode.SUCCESS.code){
                logger.info("geetest send code msg faild:"+sendResult.getMessage());
            }else {
                logger.info("pinCode success");
            }
            String tipMsg="Silakan download versi terbaru Superpinjam di Google. Dengan ersi baru, pencairan dana lebih cepat!";
            return  tipMsg;
        }

        GeetestLib gtSdk = new GeetestLib(appProperties.getGeetestId(),appProperties.getGeetestKey(),appProperties.getGeetestFailBack().equals("true"));
        //扩张参数,可选填
        HashMap<String, String> param = new HashMap<String, String>();
        String ip=request.getRemoteAddr();
        param.put("client_type", "app");
        if(StringUtils.isNotBlank(pinCode)) {
            param.put("user_id", pinCode);
        }
        if(StringUtils.isNotBlank(ip)){
            param.put("ip_address", ip);
        }
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到缓
        String gtKey=Constants.REDIS_GEETEST+pinCode;
        redisTemplate.opsForValue().set(gtKey,gtServerStatus,300, TimeUnit.SECONDS);//5分钟
        String responStr=gtSdk.getResponseStr();
        return  responStr;
    }

    /**
     * 校验GeeTest行为
     * @param request
     * @param pinCode
     * @return
     */
    @Override
    public  Map<String,String> verifyLogin(HttpServletRequest request, String pinCode){
        GeetestLib gtSdk = new GeetestLib(appProperties.getGeetestId(),appProperties.getGeetestKey(),appProperties.getGeetestFailBack().equals("true"));
        //获取参数
        String ip=request.getRemoteAddr();
        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
        //读取服务器状态
        String gtKey=Constants.REDIS_GEETEST+pinCode;
        Object statusCode=redisTemplate.opsForValue().get(gtKey);
        int gt_server_status_code =statusCode==null ? 0:Integer.valueOf(statusCode.toString());
        //扩张参数
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("client_type", "app");
        if(StringUtils.isNotBlank(pinCode)) {
            param.put("user_id", pinCode);
        }
        if(StringUtils.isNotBlank(ip)){
            param.put("ip_address", ip);
        }
        //校验行为
        int result=0;
        if (gt_server_status_code == 1){
            //gt-server正常，向gt-server进行二次验证
            result = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
        }
        else {
            // gt-server非正常情况下，进行failback模式验证
            result = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }
        if(result!=0){
            Result sendResult=sendCodeMsg(pinCode);
            if(sendResult.getCode()!=ResultCode.SUCCESS.code){
                logger.info("geetest send code msg faild:"+sendResult.getMessage());
            }
        }

        Map<String,String> map=new HashMap<>();
        map.put("status",result==0? "fail":"success");
        map.put("version",gtSdk.getVersionInfo());

        return   map;
    }

    /**
     * 第三方平台登陆
     * @param openId
     * @param platmfromName
     * @param nickName
     * @return
     */
    @Override
    public Result openIdLogin(String openId,String platmfromName,String nickName){
        if(StringUtils.isBlank(openId) || StringUtils.isBlank(platmfromName)){
            return  ResultGenerator.genFailResult("openId or platmfromName is null! ");
        }
        Map<String,Object> result=new HashMap<>();
        boolean isBindUser=false;
        SUserOpen  open=sUserOpenService.getOpen(openId,platmfromName);
        if(open==null){
            //新增
            sUserOpenService.addOpen(openId,platmfromName,nickName,"");
        }else {
            //是否已经绑定账号
            Map<String,Object> param=new HashMap<>();
            param.put("openId",openId);
            param.put("platformName",platmfromName);
            SUser user=sUserMapper.getUserByOpenId(param);
            if(user!=null){
                //登陆
                Result loginResult= pwdlogin(user.getMobile(),user.getUserPassword());
                if(loginResult.getCode()!=ResultCode.SUCCESS.code){
                    return loginResult;
                }
                isBindUser=true;
                result=NomalUntil.objectToMap(loginResult.getData());
            }
        }
        result.put("isBindUser",isBindUser);
        return  ResultGenerator.genSuccessResult(result);
    }

    /**
     * 第三方账号绑定
     * @param openId
     * @param platformName
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public Result openBindUser(String openId,String platformName,String mobile,String code){
        if(StringUtils.isBlank(code)){
            return  ResultGenerator.genFailResult("code is null");
        }
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(openId)){
            return  ResultGenerator.genFailResult("openId or mobile is not null");
        }
        String rkey=com.supermoney.loan.api.utils.Constants.REDIS_MSGCODE_KEY+mobile;
        Object rcode=redisTemplate.opsForValue().get(rkey);
        if(rcode==null || !rcode.toString().equals(code)){
            return  ResultGenerator.genFailResult("code faild!");
        }

        Result loginResult=new Result();
        SUser user=getUserByMobile(mobile);
        if(user!=null){
            //用户虽然存在，验证openid是否已绑定过，绑定过不能操作，以防恶意修改。
            SUserOpen  open=sUserOpenService.getOpen(openId,platformName);
            if(open!=null && open.getUserId()!=null){
                return  ResultGenerator.genFailResult("openid has bind");
            }
            //用户已存在登陆
            loginResult= pwdlogin(user.getMobile(),user.getUserPassword());
            if(loginResult.getCode()!=ResultCode.SUCCESS.code){
                return  loginResult;
            }
        }else {
            //新增用户并登陆
            loginResult=noneUserLogin(mobile,"","");
            if(loginResult.getCode()!=ResultCode.SUCCESS.code){
                return  loginResult;
            }
            user=getUserByMobile(mobile);
        }
        //绑定
        sUserOpenService.userBindOpen(openId,platformName,user.getId());
        return  loginResult;
    }


    /**
     * accountKit登录
     * @param authCode
     * @return
     */
    public   Result kitLogin(String authCode){
        logger.info("authCode:"+authCode);
        Map<String,String> param=RequestUntil.getUserAgentParams();
        String app=StringUtils.isBlank(param.get("PACKAGE"))? "": param.get("PACKAGE");
        logger.info("kit app:"+app);
        //根据包名获取相应 id 和 key
        String appId="";
        String key="";
        app="Hipeso";
        if(app.equals(appProperties.getMoneyshopKitApp())){
            appId=appProperties.getMoneyshopKitId();
            key=appProperties.getMoneyshopKitKey();
        }else {
            appId=appProperties.getMoneyshopKitId();
            key=appProperties.getMoneyshopKitKey();
        }
        boolean mc=app.equals(appProperties.getMoneycashKitApp());

        logger.info("test mc:"+mc+" getMoneycashKitApp:"+appProperties.getMoneycashKitApp()+" app:"+app);

        //取accessToken
        JSONObject accessJson=getAccessTokenByKit(appId,key,authCode);
        if(accessJson==null){
            return  ResultGenerator.genFailResult("100 try again later!");
        }
        if(accessJson.get("access_token")==null || StringUtils.isEmpty(accessJson.get("access_token").toString())){
            return  ResultGenerator.genFailResult("get accessToken  err !");
        }
        //取用户信息-手机号
        JSONObject userJson= getUserInfoByKit(accessJson.get("access_token").toString());
        if(userJson==null){
            return  ResultGenerator.genFailResult("101 try again later!");
        }
        //手机号-KEY,authCode-VALUE
        JSONObject phoneInfo=(JSONObject)userJson.get("phone");
        logger.info("phone params:"+phoneInfo.toJSONString());
        String monile="63"+phoneInfo.get("national_number").toString();
        return  kitUserLogin(monile);
    }

    /**
     * 获取kit-AccessToken
     * GET
     * {
     *   "id" : <account_kit_user_id>,
     *   "access_token" : <user_access_token>,
     *    "token_refresh_interval_sec" : <refresh_interval>
     *  }
     * @param id
     * @param key
     * @param authCode
     * @return
     */
    public JSONObject getAccessTokenByKit(String id,String key,String authCode) {
        try {

            String url = "http://graph.accountkit.com/v1.3/access_token?grant_type=authorization_code&";
            String data="code=" +authCode+"&access_token=AA%7c"+id+"%7c"+key;

            logger.info("kit-token-url:" + url+data);
           // String respon = HttpUtil.doHttpsPost(url,data);

            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod(url+data);
            client.executeMethod(method);
            System.out.println(method.getResponseBodyAsString());
            String respon=method.getResponseBodyAsString();
            method.releaseConnection();
            logger.info("getAccessTokenByKit-respon:" + respon);
            JSONObject json= JSON.parseObject(respon);
            return  json;
        } catch (Exception ex) {
            logger.error("getAccessTokenByKit:" + ex.getMessage());
            return null;
        }
    }


    /**
     * 获取kit-用户信息
     * GET
     * {
     *    id: "1234512345123451",
     *    phone: {
     *    number: "+15551234567"
     *    country_prefix: "1",
     *    national_number: "5551234567"
     *    },
     *    application: {
     *    id: "5432154321543210"
     *    }
     *  }
     * @param accessToken
     * @return
     */
    public  JSONObject getUserInfoByKit(String accessToken){
        //"https://graph.accountkit.com/v1.3/me/?access_token=<access_token>";
        try {
            String url="http://graph.accountkit.com/v1.3/me/?access_token=%s";
            url=String.format(url,accessToken);
            logger.info("kit-info-url:"+url);
            String respon=restTemplate.getForObject(url,String.class);
            logger.info("getUserInfoByKit-respon:"+respon);
            JSONObject json= JSON.parseObject(respon);
            return  json;
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  null;
        }
    }

//    public static void main(String[] args) {
//        SUserServiceImpl s=new SUserServiceImpl();
//        JSONObject j=s.getUserInfoByKit("EMAWd1ZCg3kSy1qzdx0hqFmy7aZA1QmBtYcsMvQzxg7ZA2NqMilM99od3Dm1lCLk8mZBuHoB9IOdxTGZCOUOYebNOYToZAaoRqS7afNsIeRe5ZCvorTAdIhBs014HwkEUI5J0ImdwQ3ZAiiM41EvrgGfiMXK71jSsyVUMZD");
//
//        System.out.println(j);
//    }

    /**
     * 用户登录首次业务处理
     * @param userId
     * @return
     */
    public  Result firstBuss(Integer userId){

        //代理人头结算
       //giveMoneyAgencyCount(userId);
        return  ResultGenerator.genSuccessResult();
    }

    @Override
    public void updateCreditInfo(Map<String, Object> param) {
        sUserMapper.updateCreditInfo(param);
    }

    @Override
    public List<Map<String, Object>> selectCreditModifyUser() {
        return sUserMapper.selectCreditModifyUser();
    }

    /**
     * 给推广有效的代理结算
     * @param userId
     * @return
     */
    public  Result giveMoneyAgencyCount(Integer userId){
        //代理人头结算
        SUserPay param=new SUserPay();
        param.setOpt(userId.toString());
        param.setPayStatus(Constants.BussPay.PAY_STATUS_WAIT);
        param.setPayType(Constants.BussPay.PAYTYPE_AGENCY);
        List<SUserPay> userPays= sUserPayService.getList(param);
         logger.info("agency count:"+userPays.size());
        for(SUserPay pay:userPays){
            userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_AGENCY,Constants.BUSS_TYPE_AGENCY_PERSON,pay.getUserId(),pay.getPayMoney());
            pay.setPayStatus(Constants.BussPay.PAY_STATUS_AUDIT);
            sUserPayService.update(pay);

        }
        return  ResultGenerator.genSuccessResult();
    }

}
