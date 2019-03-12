package com.supermoney.loan.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.dao.SBountyMapper;
import com.supermoney.loan.api.dao.SBountyRecordMapper;
import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.entity.vo.SBountyUserRecordVo;
import com.supermoney.loan.api.service.SBountyRecordService;
import com.supermoney.loan.api.service.SExchangeRateService;
import com.supermoney.loan.api.service.SSendSmsRecordService;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.supermoney.loan.api.utils.NomalUntil.getString;


/**
 * Created by xionghuifeng on 2018/01/06.
 */
@Service
@Transactional
public class SBountyRecordServiceImpl extends AbstractService<SBountyRecord> implements SBountyRecordService {

    private static final Logger logger = LoggerFactory.getLogger(SBountyRecordServiceImpl.class);

        @Resource
        private SBountyRecordMapper sBountyRecordMapper;
        @Resource
        private SBountyMapper sBountyMapper;
        @Resource
        private SUserMapper sUserMapper;
        @Resource
        private SExchangeRateService sExchangeRateService;



    /**
     * 查询记录
     *
     * @param param
     * @return
     */
    public List<SBountyRecord> getList(Map<String, Object> param) {
        return sBountyRecordMapper.selectList(param);
    }

    /**
     * 查询用户记录
     * @param param
     * @return
     */
    public  List<SBountyUserRecordVo> getUserList(Map<String,Object> param){return  sBountyRecordMapper.selectUserList(param);}

    /**
     * 查询用户赏金记录
     * @param page
     * @param size
     * @param param
     * @return
     */
    public Result getUserRecordByPage(int page, int size, Map<String, Object> param){
        if(param.get("userId")==null){
            return  ResultGenerator.genFailResult("userId is faild");
        }
        //汇率转换
        SExchangeRate rate=sExchangeRateService.getUsdByCountry(Constants.Country.INDONESIA_CR);
        PageHelper.startPage(page, size,true,false);
        List<SBountyUserRecordVo> list = getUserList(param);
        List<Map<String,Object>> voMap=new ArrayList<Map<String, Object>>();
        for(SBountyUserRecordVo item:list){
            item.setBountyMoney(sExchangeRateService.valByRate(rate,item.getBountyMoney()));
            Map<String,Object>  itemMap=new HashMap<>(Constants.App.MAP_MIN_SIZE);
            itemMap.put("id",item.getId());
            itemMap.put("userId",item.getUserId());
            itemMap.put("bountyId",item.getBountyId());
            itemMap.put("bountyName",item.getBountyName());
            itemMap.put("bountyMoney",sExchangeRateService.valToInt(item.getBountyMoney()));
            itemMap.put("agencyDown",item.getAgencyDown());
            itemMap.put("recordStatus",item.getRecordStatus());
            itemMap.put("createTime",NomalUntil.getDateStirng(item.getCreateTime()));
            itemMap.put("adsIco",item.getAdsIco());
            itemMap.put("adsPic",item.getAdsPic());
            itemMap.put("cbEventName",item.getCbEventName());
            voMap.add(itemMap);
        }
       return ResultGenerator.genSuccessResult(voMap);
    }
    /**
     * 分页获取
     *
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String, Object> param) {

        PageHelper.startPage(page, size);
        List<SBountyRecord> list = getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 用户赏金任务统计信息
     * @param userId
     * @return
     */
    public  Result userBountyTotal(Integer userId){
        Map<String,Object> result=new HashMap<>();
        //已结算
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userId);
        BigDecimal clearCount=sBountyRecordMapper.userRecordTotal(param);
        result.put("total",clearCount);
        //未提取-未实现
        result.put("notCash",clearCount);

        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 赏金任务回调记录处理
     * @param param
     * @return
     */
    @Override
    public  Result  taskCallBack(Map<String,Object> param){

        String clickId=getString(param,"click_id");
        if(clickId.isEmpty()){
            logger.debug("callback faild param!");
            return  ResultGenerator.genFailResult(" faild param!");
        }
        String site= getString(param,"site");
        Map<String,Object> info=NomalUntil.decoderClickId(clickId);
        if(info==null){
            logger.debug("info is null");
            return  ResultGenerator.genFailResult("info is null");
        }
        logger.info("callback info:"+info.toString());
        String userId=getString(info,"userId");
        String bountyId=getString(info,"bountyId");
        //扩张参数处理
        Integer isDown=0;
        String useDsc="";
        if(StringUtils.isNotBlank(site)){
            String[] exs=site.split("_");
            if(exs.length>0){
                isDown=exs[0].equals("shar")? 1:0;
            }
            if(exs.length>1){
                useDsc=exs[1];
            }
        }
        if(userId.isEmpty() || bountyId.isEmpty()){
            logger.debug("callback faild site!");
            return  ResultGenerator.genFailResult(" faild site!");
        }
        //用户不存在
        SUser user=sUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if(user==null){
            logger.debug("callback faild user!");
            return  ResultGenerator.genFailResult(" faild user!");
        }
        //任务不存在
        SBounty bounty=sBountyMapper.selectByPrimaryKey(Integer.valueOf(bountyId));
        if(bounty==null){
            logger.debug("callback faild bounty!");
            return  ResultGenerator.genFailResult(" faild bounty!");
        }
        //判断此记录是否已新增
        String symbolClickId=NomalUntil.symbolDencoder(clickId);
        SBountyRecord  hasRecord=new SBountyRecord();
        hasRecord.setClickId(symbolClickId);
        hasRecord=sBountyRecordMapper.selectOne(hasRecord);
        if(hasRecord!=null){
            return  ResultGenerator.genFailResult("completed");
        }

        //新增完成记录
        SBountyRecord record=new SBountyRecord();
        record.setUserId(user.getId());
        record.setBountyId(bounty.getId());
        record.setBountyName(bounty.getBountyName());
        record.setBountyMoney(bounty.getBountyMoney());
        record.setAdsId(bounty.getAdsId());

        record.setAgencyDown(isDown);
        record.setClickId(symbolClickId);
        record.setGaid(getString(param,"gaid"));
        record.setIdfa(getString(param,"idfa"));
        record.setIp(getString(param,"ip"));
        record.setCbEventName(getString(param,"event_name"));
        record.setUseDsc(useDsc);
        //任务完成
        record.setRecordStatus(Constants.BountyRecord.STATUS_FINSH);
        sBountyRecordMapper.insert(record);
        return  ResultGenerator.genSuccessResult();
    }


}
