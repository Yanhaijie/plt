package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.controller.UtController;
import com.supermoney.loan.api.dao.SBountySignInMapper;
import com.supermoney.loan.api.entity.SBountySignIn;
import com.supermoney.loan.api.service.SBountySignInService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by @author on 2018/10/31.
 */
@Service
@Transactional
public class SBountySignInServiceImpl extends AbstractService<SBountySignIn> implements SBountySignInService {

    private static final Logger logger = LoggerFactory.getLogger(SBountySignInServiceImpl.class);
    @Resource
    private SBountySignInMapper sBountySignInMapper;

    @Value("${facebook.accountkit}")
    private String accountkitUrl;
    @Value("${facebook.version}")
    private String version;
    @Value("${facebook.appId}")
    private String appId;
    @Value("${facebook.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<SBountySignIn> find(SBountySignIn sBountySignIn) {
        return sBountySignInMapper.find(sBountySignIn);
    }


    //获取facebook中登记的用户信息，并做登记处理
    public JSONObject getUserInfoAndSign(String userAccessToken,Integer bountyId,String appSecret){
       /*获取用户信息格式
       {
            "id": "1932290080219401",
                "phone": {
            "number": "+8617512097915",
                    "country_prefix": "86",
                    "national_number": "17512097915"
        },
            "application": {
            "id": "462525834236236"
        }
        }*/
        String meEndpointUrl = accountkitUrl+"/"+version+"/me?"+"access_token="+userAccessToken;
        JSONObject userInfoObject = this.sendGet(meEndpointUrl);
        if(userInfoObject != null){
            logger.info("signIn===>>userInfoObject="+userInfoObject.toJSONString());
            JSONObject phoneInfo=(JSONObject)userInfoObject.get("phone");
            String phone = phoneInfo.get("national_number").toString().trim();
            SBountySignIn sBountySignIn = new SBountySignIn();
            sBountySignIn.setBountyId(bountyId);
            sBountySignIn.setMobile(phone);
            sBountySignIn.setStatus(1);
            List<SBountySignIn> list= this.find(sBountySignIn);
            //没登记成功过
            if(list == null || list.size() < 1){
                if(appSecret !=null){
                    //登录过了，判断是否伪造，伪造就返回异常
                    String phone1 = UserUtils.getCurrentMobile().trim();
                    logger.info("signIn===>>phone1="+phone1);
                    logger.info("signIn===>>phone="+phone);
                    if(!phone.equals(phone1)){
                        return null;
                    }
                }
                this.save(sBountySignIn);
            }
        }
        return userInfoObject;
    }

    //获取userAccessToken
    //获取token返回值格式
          /*     {
                "id": "1932290080219401",
                    "access_token": "EMAWcdQ1bHAVeoHJoLKaHcNeJ8dfkSRt7rjF9E9rzXsIQe11vgKqaJZBaf8rTXGn7ZBVz6gdARqCtoVQFmQq10a27BkZAKacAuCSheoS1SrkBHpbNKempINHZCEJIpR5vcbBtO73h6Vccmgnw09SBICxb3jhQQaZCMZD",
                    "token_refresh_interval_sec": 2592000
            }*/
    public JSONObject getAccessToken(String code){
        String getTokenUrl = accountkitUrl+"/"+ version+"/access_token?grant_type=authorization_code&code="+code+"&access_token=AA|"+appId+"|"+secret;
        JSONObject tokenObject = this.sendGet(getTokenUrl);
        return tokenObject;
    }

    private JSONObject sendGet(String url){
        try {
            String respon=restTemplate.getForObject(url,String.class);
            JSONObject json= JSON.parseObject(respon);
            return  json;
        }catch (Exception ex){
            logger.info("=====================sendGet error"+url);
            System.out.println(ex.getStackTrace());
            return  null;
        }
    }


}
