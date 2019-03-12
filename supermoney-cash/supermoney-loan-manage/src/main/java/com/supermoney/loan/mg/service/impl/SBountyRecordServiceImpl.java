package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SBountyRecordMapper;
import com.supermoney.loan.mg.dao.SUserPayMapper;
import com.supermoney.loan.mg.dao.SVoucherUserRecordMapper;
import com.supermoney.loan.mg.entity.*;
import com.supermoney.loan.mg.entity.vo.SBountyRecordVo;
import com.supermoney.loan.mg.service.*;
import com.supermoney.loan.mg.timer.Scheduler;
import com.supermoney.loan.mg.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


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
    private SVoucherUserRecordMapper sVoucherUserRecordMapper;
    @Resource
    private UserAccountBussService userAccountBussService;
    @Resource
    private SUserService sUserService;

    @Resource
    private SUserPayMapper sUserPayMapper;

    @Resource
    private SSendSmsRecordService sSendSmsRecordService;
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SBountyRecordVo> getList(Map<String,Object> param)
    {
        return  sBountyRecordMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    @Override
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SBountyRecordVo> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 赏金结算定时任务
     */
    @Override
    public void bountyCountTask(){
        Map<String,Object> param=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        param.put("recordStatus",Constants.BountyRecord.STATUS_FINSH.toString());
        List<SBountyRecordVo> list =getList(param);
        List<String> ars=new ArrayList<>();
        list.forEach(item-> ars.add(item.getId().toString()));
        String[] ids=ars.toArray(new String[ars.size()]);
        logger.info("bounty count size :"+ids.length);
        auditTask(Constants.BountyRecord.STATUS_COUNT,ids);
    }
    /**
     * 审核任务
     * @param status
     * @param ids
     * @return
     */
    @Override
    public Result auditTask(Integer status, String[] ids){

        if(status.equals(Constants.BountyRecord.STATUS_UNFINSH)){
            //不通过-未完成
            updateRecordStatus(ids,status);
        }
        if(status.equals(Constants.BountyRecord.STATUS_COUNT)){
            //通过-结算
            for(String id:ids){
                SBountyRecord record=new SBountyRecord();
                record.setId(Integer.valueOf(id));
                record=sBountyRecordMapper.selectOne(record);
                //空或者已結算
                if(record==null || record.getRecordStatus().equals(Constants.BountyRecord.STATUS_COUNT)){
                    continue;
                }
                record.setRecordStatus(Constants.BountyRecord.STATUS_COUNT);
                BigDecimal gotMoney=hasVoucherMoney(record.getBountyId(),record.getUserId(),record.getBountyMoney());
                //通过增加奖金金额
                userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_COUNT_INMONEY,record.getUserId(),gotMoney);
                sBountyRecordMapper.updateByPrimaryKeySelective(record);
                //给上级代理分成
                parentAgencyCount(record);
                //完成任务者，给上级推广人人头费，只给一次。
                regPerpsonMoneyCount(record.getUserId());

                if(sSendSmsRecordService.getBountyRecordByUserId(record.getUserId())== null){
                    SSendSmsRecord sSendSmsRecord=new SSendSmsRecord();
                    sSendSmsRecord.setUserId(record.getUserId());
                    SUser suser=sUserService.getById(record.getUserId());
                    if(suser != null){
                        sSendSmsRecord.setMobile(suser.getMobile());
                        sSendSmsRecord.setStatus(1);
                        sSendSmsRecord.setType(3);
                        String smsRecord="Hi:  (Trump), supermoney has earned you "+record.getBountyMoney()+" dollars, you can withdraw it when it is 5 dollars," +
                                " please log in and recommend it to your friend which makes your friend your agent and works for you!" +
                                " The more agent you have the, more you earn! ";
                        sSendSmsRecord.setSmsRecord(smsRecord);
                        List<SSendSmsRecord> list=new ArrayList<>();
                        list.add(sSendSmsRecord);
                        logger.info("首次赏金发送短信内容===================================:Hi:  (Trump), supermoney has earned you "+record.getBountyMoney()+
                                " dollars, you can withdraw it when it is 5 dollars,"+
                                "please log in and recommend it to your friend which makes your friend your agent and works for you! The more agent you have the, more you earn! ");
                        sSendSmsRecordService.insertSendSmsRecord(list);
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 是否奖券加成奖金
     * @param bountyId
     * @param userId
     * @param gotMoney
     * @return
     */
    public BigDecimal hasVoucherMoney(Integer bountyId,Integer userId ,BigDecimal gotMoney){
        BigDecimal bountyMoney=new BigDecimal(gotMoney.doubleValue());
        SVoucherUserRecord userVoucher=new SVoucherUserRecord();
        userVoucher.setUseId(bountyId);
        userVoucher.setVoucherBussType(Constants.VOUCHERS.BUSS_BOUNTY);
        userVoucher.setUserId(userId);
        userVoucher=sVoucherUserRecordMapper.selectOne(userVoucher);
        if(userVoucher==null){
            return  bountyMoney;
        }
        //非使用中，不能结算
        if(!userVoucher.getRecordStatus().equals(Constants.VOUCHERS.STATUS_DOING)){
            return  bountyMoney;
        }

        if(userVoucher.getVoucherType().equals(Constants.VOUCHERS.TYPE_SINGLE)){
        //单次翻倍,使用完更新。
            bountyMoney=bountyMoney.multiply(new BigDecimal(2));
            userVoucher.setRecordStatus(Constants.VOUCHERS.STATUS_DONE);
            sVoucherUserRecordMapper.updateByPrimaryKeySelective(userVoucher);
            return  bountyMoney;

        }else  if(userVoucher.getVoucherType().equals(Constants.VOUCHERS.TYPE_PROJECT)){
        //项目翻倍
            if(voucherExpired(userVoucher)>0){
                return bountyMoney;
            }
            bountyMoney=bountyMoney.multiply(new BigDecimal(2));
            return  bountyMoney;
        }else  if(userVoucher.getVoucherType().equals(Constants.VOUCHERS.TYPE_LIMIT)){
         //期限翻倍
            if(voucherExpired(userVoucher)>0){
                return bountyMoney;
            }
            bountyMoney=bountyMoney.multiply(new BigDecimal(2));
            return  bountyMoney;
        }else  if(userVoucher.getVoucherType().equals(Constants.VOUCHERS.TYPE_RANDOM)){
           //随机红包,使用完更新。 奖励0.002-0.01 美金 （20-137印尼盾）
            BigDecimal rmMoney=NomalUntil.randomDecimal(0.002,0.01);
            bountyMoney=bountyMoney.add(rmMoney);
            userVoucher.setRecordStatus(Constants.VOUCHERS.STATUS_DONE);
            sVoucherUserRecordMapper.updateByPrimaryKeySelective(userVoucher);
            return  bountyMoney;
        }
        return  bountyMoney;
    }

    /**
     * 过期处理
     * @param userVoucher
     */
    public int  voucherExpired(SVoucherUserRecord userVoucher){
        Date nowTime=new Date();
        if(nowTime.getTime()>userVoucher.getEndTime().getTime()){
            userVoucher.setRecordStatus(Constants.VOUCHERS.STATUS_DONE);
           return sVoucherUserRecordMapper.updateByPrimaryKeySelective(userVoucher);
        }
        return  0;
    }

    /**
     * 更新状态
     * @param ids
     * @param status
     */
    public void updateRecordStatus(String[] ids,Integer status){
        Map<String,Object> param=new HashMap();
        String sql="update s_bounty_record set record_status="+status.toString()+" where "+ NomalUntil.inIdsStr("id",ids);
        param.put("sql",sql);
        sBountyRecordMapper.executeSql(param);
    }

    /**
     * 给上级代理结算
     * @param bountyRecord
     */
    public void parentAgencyCount(SBountyRecord bountyRecord){
       //给上一级代理结算
        SUser user=sUserService.findBy("id",bountyRecord.getUserId());
        if(user.getParentId()==null){
            return;
        }
        if( user.getParentId().compareTo(0)>0){
            //如果有上一级代理，给上一级代理分层 1比1 的利润
            userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_AGENCY_COUNT_INMONEY,user.getParentId(),bountyRecord.getBountyMoney());
        }
        //給上二级代理结算
         SUser userTwo=sUserService.findBy("id",user.getParentId());
        if(userTwo.getParentId()==null){
            return;
        }
        if(userTwo.getParentId().compareTo(0)>0){
            //如果有上二级代理，给上二级代理分层 1比1 的利润
            userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_BOUNTY,Constants.BUSS_STEP_BOUNTY_AGENCY_COUNT_INMONEY,userTwo.getParentId(),bountyRecord.getBountyMoney());
        }
    }

    /**
     * 给上级代理结算人头费(推荐注册),只给一次.
     * @param userId
     */
    public  void  regPerpsonMoneyCount(Integer userId){
        SUser user=sUserService.findBy("id",userId);
        if(user.getParentId()==null){
            //logger.info(" regPerpsonMoneyCount parentUser isnull!");
            return;
        }
        //结算过的不能再次  status:待审核 payType:代理推广结算  userID:代理人  opt 被推广人
        logger.info("agencycount param:"+user.getParentId()+"|ll"+userId.toString());
        SUserPay param=new SUserPay();
        param.setUserId(user.getParentId());
        param.setOpt(userId.toString());
        param.setPayStatus(Constants.BussPay.PAY_STATUS_WAIT);
        param.setPayType(Constants.BussPay.PAYTYPE_AGENCY);
        SUserPay userPay=sUserPayMapper.selectOne(param);
        if(userPay==null){
            logger.info("regPerpsonMoneyCount  userPay isnull");
            return;
        }
        userAccountBussService.inMoenyAccount(Constants.BUSS_TYPE_AGENCY,Constants.BUSS_TYPE_AGENCY_PERSON,userPay.getUserId(),userPay.getPayMoney());
        userPay.setPayStatus(Constants.BussPay.PAY_STATUS_AUDIT);
        sUserPayMapper.updateByPrimaryKeySelective(param);
    }
}
