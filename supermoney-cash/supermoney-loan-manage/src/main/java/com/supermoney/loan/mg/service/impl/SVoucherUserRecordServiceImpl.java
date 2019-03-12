package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SVoucherMapper;
import com.supermoney.loan.mg.dao.SVoucherUserRecordMapper;
import com.supermoney.loan.mg.entity.SVoucher;
import com.supermoney.loan.mg.entity.SVoucherUserRecord;
import com.supermoney.loan.mg.service.SVoucherUserRecordService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/15.
 */
@Service
@Transactional
public class SVoucherUserRecordServiceImpl extends AbstractService<SVoucherUserRecord> implements SVoucherUserRecordService {
    @Resource
    private SVoucherUserRecordMapper sVoucherUserRecordMapper;

    @Resource
    private SVoucherMapper sVoucherMapper;
    /**
     * 用户抵用券发放
     * @param method 0 全部用戶  1 选中用户
     * @param voucherId
     * @param ids
     * @return
     */
    public Result userVoucher(Integer method, Integer voucherId, String[] ids){
        if(method==null || voucherId ==null){
            return  ResultGenerator.genFailResult("param faild!");
        }
        if(method.compareTo(1)==0 && ids.length<1){
            return  ResultGenerator.genSuccessResult();
        }
        SVoucher voucher=sVoucherMapper.selectByPrimaryKey(voucherId);
        if(voucher==null){
            return  ResultGenerator.genFailResult("voucher is null");
        }

        Map<String,Object> param=new HashMap();
        param.put("voucherId",voucher.getId());
        param.put("voucherName",voucher.getVoucherName());
        param.put("voucherDsc",voucher.getVoucherDsc());
        param.put("startTime",voucher.getStartTime());
        param.put("endTime",voucher.getEndTime());
        param.put("voucherBussType",voucher.getVoucherBussType());
        param.put("voucherType",voucher.getVoucherType());
        param.put("voucherVal",voucher.getVoucherVal());
        param.put("bountyIds",voucher.getBountyIds());
        String inserSql="INSERT INTO `s_voucher_user_record`(`user_id`,`voucher_id`,`voucher_name`,`voucher_dsc`,`start_time`,`end_time`," +
                "`voucher_buss_type`,`voucher_type`,`voucher_val`,`record_status`, `bounty_ids`)" +
                "select id,#{voucherId},#{voucherName},#{voucherDsc},#{startTime},#{endTime}," +
                "#{voucherBussType},#{voucherType},#{voucherVal},0,#{bountyIds}  " +
                "from s_user where user_status=0 ";

        //全部用户
        if(method.compareTo(0)==0){
            param.put("sql",inserSql);
            sVoucherUserRecordMapper.executeSql(param);
        }
        //选中用户
        if(method.compareTo(1)==0){
            String str=" and id in(";
           for(String item:ids){
               str+=item+",";
           }
           str=str.substring(0,str.length()-1)+")";
            inserSql=inserSql+str;
            param.put("sql",inserSql);
            sVoucherUserRecordMapper.executeSql(param);
        }

        return ResultGenerator.genSuccessResult();
    }


    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SVoucherUserRecord> getList(Map<String,Object> param)
    {
        return  sVoucherUserRecordMapper.selectList(param);
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
        List<SVoucherUserRecord> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }




}
