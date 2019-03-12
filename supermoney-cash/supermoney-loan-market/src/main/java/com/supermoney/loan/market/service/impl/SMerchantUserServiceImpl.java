package com.supermoney.loan.market.service.impl;

import com.supermoney.loan.market.dao.SMerchantUserMapper;
import com.supermoney.loan.market.entity.SMerchantUser;
import com.supermoney.loan.market.service.SMerchantUserService;
import com.supermoney.loan.market.utils.AbstractService;
import com.supermoney.loan.market.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


/**
 * Created by bear on 2018/08/06.
 */
@Service
@Transactional
public class SMerchantUserServiceImpl extends AbstractService<SMerchantUser> implements SMerchantUserService {

    private static final Logger logger = LoggerFactory.getLogger(SMerchantUserService.class);

    @Resource
    private SMerchantUserMapper sMerchantUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private  int MAXREQ=10000;

    /**
     * 校验秘钥
     * @param scretkey
     * @return
     */
    @Override
    public boolean checkScretKey(String scretkey, HttpServletRequest request){
        String scretAndId=getMerchantScretkeyAndId(scretkey);
        logger.info(" scretAndId:"+scretAndId);
        //验证失败
        if(StringUtils.isBlank(scretAndId)){
            return  false;
        }
        //计数验证
        if(!countCheck(scretkey)){
            return  false;
        }
        //写入merchantId
        request.setAttribute("merchantId",scretAndId.split("\\|")[1]);

        return true;
    }

    /**
     * 计数校验
     * @param scretkey
     * @return
     */
    public boolean countCheck(String scretkey){
        String countRedisKey = "market_scs:" + scretkey;
        Long addVal = redisTemplate.opsForValue().increment(countRedisKey, 1);
        //计数初始化
        if (addVal == 1) {
            redisTemplate.expire(countRedisKey, 1, TimeUnit.DAYS);
        }
        //计数校验
        if (addVal > MAXREQ) {
            logger.info("pass limit max 10000,that val :"+addVal+" ,that key:"+scretkey);
            return false;
        }
        return  true;
    }

    /**
     * 校验商户密钥
     * @param scretkey
     * @return
     */
    public String  getMerchantScretkeyAndId(String scretkey){
        String redisKey="market_scretkey:"+scretkey;
        Object hasSend=redisTemplate.opsForValue().get(redisKey);
        if(hasSend!=null){
            return  hasSend.toString();
        }

        SMerchantUser merchan=getByScret(scretkey);
        if(merchan!=null){
            String redisVal=merchan.getSecretKey()+"|"+merchan.getMerchantId();
            redisTemplate.opsForValue().set(redisKey,redisVal,30, TimeUnit.DAYS);
            return  redisVal;
        }

        return  "";
    }

    /**
     * 根据秘钥获取
     * @param scretkey
     * @return
     */
    public SMerchantUser  getByScret(String scretkey){
        SMerchantUser merchan=new SMerchantUser();
        merchan.setSecretKey(scretkey);
        merchan=sMerchantUserMapper.selectOne(merchan);
        return  merchan;
    }

}
