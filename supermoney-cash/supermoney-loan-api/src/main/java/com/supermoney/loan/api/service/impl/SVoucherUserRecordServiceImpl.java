package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBountyMapper;
import com.supermoney.loan.api.dao.SVoucherUserRecordMapper;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SVoucherUserRecord;
import com.supermoney.loan.api.entity.vo.SVoucherVo;
import com.supermoney.loan.api.service.SVoucherUserRecordService;
import com.supermoney.loan.api.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/14.
 */
@Service
@Transactional
public class SVoucherUserRecordServiceImpl extends AbstractService<SVoucherUserRecord> implements SVoucherUserRecordService {
    @Resource
    private SVoucherUserRecordMapper sVoucherUserRecordMapper;

    @Resource
    private SBountyMapper sBountyMapper;

    /**
     * 获取用户抵用券列表
     * @param type
     * @return
     */
    public Result getUserVoucherList(int type,Integer userID){
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userID);
        //未使用
        if(type==0){
            param.put("notUse","true");
            param.put("nowTime",new Date());
        }
        else  if(type==1){
            param.put("used","true");
        }else  if(type==2){
            param.put("passTime","true");
            param.put("nowTime",new Date());
        }else {
            return  ResultGenerator.genSuccessResult();
        }
        List<SVoucherVo> list=sVoucherUserRecordMapper.selectUserList(param);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 使用抵用券
     * @param id
     * @param userId
     * @param bountyId
     * @return
     */
    public  Result userVoucher(Integer id,Integer userId,Integer bountyId){

        SVoucherUserRecord voucher=sVoucherUserRecordMapper.selectByPrimaryKey(id);
        if(voucher==null){
            return  ResultGenerator.genFailResult("voucher is null");
        }
        if(!(voucher.getRecordStatus().compareTo(0)==0)){
            return  ResultGenerator.genFailResult("voucher is used");
        }
        Date nowTime=new Date();
        if(nowTime.getTime()>voucher.getEndTime().getTime()){
            return  ResultGenerator.genFailResult("voucher  effect");
        }
        //任务条件是否满足
        if(voucher.getBountyIds()!=null && voucher.getBountyIds().length()>0 && voucher.getBountyIds().indexOf(bountyId.toString())<0){
            return  ResultGenerator.genFailResult("not available");
        }
        //是否使用过
        SVoucherUserRecord bountyUsed=new SVoucherUserRecord();
        bountyUsed.setUseId(bountyId);
        bountyUsed.setVoucherBussType(Constants.Voucher.BUSS_BOUNTY);
        bountyUsed.setUserId(userId);
        bountyUsed=sVoucherUserRecordMapper.selectOne(bountyUsed);
        if(bountyUsed!=null){
            return  ResultGenerator.genFailResult("voucher only one for bounty");
        }
        //任务是否存在
        SBounty bounty=sBountyMapper.selectByPrimaryKey(bountyId);
        if(bounty==null){
            return  ResultGenerator.genFailResult("have not bounty");
        }
        //更新状态
        voucher.setUseId(bountyId);
        voucher.setUseVal(voucher.getVoucherVal());
        voucher.setRecordStatus(Constants.App.NUM_ONE);//使用中
        sVoucherUserRecordMapper.updateByPrimaryKey(voucher);
        return  ResultGenerator.genSuccessResult();
    }

    /**
     * 获取新发放的抵用券
     * @param userId
     * @return
     */
    public  Result getNewVoucher(Integer userId){
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userId);
        param.put("notUse","true");
        param.put("nowTime",new Date());
        param.put("viewed","0");
        List<SVoucherVo> list=sVoucherUserRecordMapper.selectUserList(param);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 已查看新发的抵用券
     * @param userId
     * @param ids
     * @return
     */
    public  Result viewNewVoucher(Integer userId,String[] ids){
        if(ids.length<1){
            return  ResultGenerator.genFailResult(" have not ids!");
        }
        Map<String,Object> param=new HashMap();
        String updateSql="update s_voucher_user_record set viewed=1 where user_id=#{userId} and "+ NomalUntil.inIdsStr("id",ids);
        param.put("sql",updateSql);
        sVoucherUserRecordMapper.executeSql(param);
        return  ResultGenerator.genSuccessResult();
    }

}
