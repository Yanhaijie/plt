package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRepotCountMapper;
import com.supermoney.loan.mg.entity.SRepotCount;
import com.supermoney.loan.mg.service.SRepotCountService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/18.
 */
@Service
@Transactional
public class SRepotCountServiceImpl extends AbstractService<SRepotCount> implements SRepotCountService {
    @Resource
    private SRepotCountMapper sRepotCountMapper;

    /**
     * 获取统计信息
     * @param param
     * @return
     */
    @Override
    public Result getCountInfo(Map<String,Object> param){
        List<SRepotCount> list=getCanSqlCountList();
        Map<String,String> data=new HashMap<>();
        for (SRepotCount item:list){
            
        }
        return ResultGenerator.genSuccessResult(data);
    }
    /**
     *
     *可用的SQL统计
     * @return
     */
    public List<SRepotCount> getCanSqlCountList(){
        SRepotCount param=new SRepotCount();
        param.setReportStatus(0);
        param.setReportType(0);
        return  sRepotCountMapper.select(param);
    }




}
