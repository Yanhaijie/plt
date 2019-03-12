package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SRepotCountMapper;
import com.supermoney.loan.mg.dao.SUserMapper;
import com.supermoney.loan.mg.entity.vo.*;
import com.supermoney.loan.mg.service.ReportBussService;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.NomalUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportBussServiceImpl implements ReportBussService {

    @Autowired
    private SUserMapper userMapper;
    @Autowired
    private SRepotCountMapper sRepotCountMapper;

    /**
     * 正常统计
     * @param startTime
     * @param endTime
     * @return
     */
    public Result nomalCount(String startTime,String endTime){
        Map<String,Object> param=defaultParam(startTime,endTime);
        List<Map<String,Object>> data=userMapper.reportNomalCount(param);
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 订单统计
     * @param startTime
     * @param endTime
     * @return
     */
    public Result orderCount(String startTime,String endTime){
        Map<String,Object> param=defaultParam(startTime,endTime);
        List<Map<String,Object>> orderCount=userMapper.reportOrderCount(param);
        List<Map<String,Object>> orderStatus=userMapper.reportOrderStatus(param);
        Map<String,Object> data=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        data.put("orderCount",orderCount.get(0));
        data.put("orderStatus",orderStatus);
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 分销统计
     * @param pid  父类id，如果为空则默认查询下级代理统计，如果传入值，则查询当前用户下级的推广总数
     * @param id   根据用户id搜索
     * @param mobile   根据用户手机号搜索
     * @return 查询结果
     */
    @Override
    public Result distribution(String pid, String id, String mobile, Integer pageNo, Integer pageSize) {
        if(pageNo == null || pageNo == 0){
            pageNo = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        Integer pageIndex = (pageNo-1) * pageSize;

        //拼接参数
        Map<String,Object> param = new HashMap<>();
        param.put("pid",pid);
        param.put("id",id);
        param.put("mobile",mobile);
        param.put("pageIndex",pageIndex);
        param.put("pageSize",pageSize);
        List<DistributionVo> distributionVos = userMapper.getDistributions(param);
        Integer distributionCount = userMapper.getDistributionCount(param);

        //返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("distributions",distributionVos);
        resultMap.put("pageNo",pageNo);
        resultMap.put("pageSize",pageSize);
        resultMap.put("dataTotalCount",distributionCount);
        resultMap.put("pageTotalCount",distributionCount % pageSize > 0 ? (distributionCount/pageSize)+1 : (distributionCount/pageSize));
        return ResultGenerator.genSuccessResult(resultMap);
    }

    @Override
    public Result bountyReport(String userId, String mobile, Integer pageNo, Integer pageSize) {
        if(pageNo == null || pageNo == 0){
            pageNo = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        Integer pageIndex = (pageNo-1) * pageSize;

        //拼接参数
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("mobile",mobile);
        param.put("pageIndex",pageIndex);
        param.put("pageSize",pageSize);
        List<RewardVO>  reportData= sRepotCountMapper.bountyReport(param);
        Integer reportCount = sRepotCountMapper.bountyReportCount(param);
        //获取总计
        SBountyReportVo sBountyReportVo = sRepotCountMapper.getTransferToTotalAmount();
        if(sBountyReportVo == null){
            sBountyReportVo = new SBountyReportVo();
        }
        SBountyReportVo sBountyReportVo2 = sRepotCountMapper.getBountyTotalAmount();
        sBountyReportVo2.setRetailTotalAmount(sBountyReportVo.getRetailTotalAmount());
        sBountyReportVo2.setWholeSaleTotalAmount(sBountyReportVo.getWholeSaleTotalAmount());
        //返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("reportData",reportData);
        resultMap.put("pageNo",pageNo);
        resultMap.put("pageSize",pageSize);
        resultMap.put("dataTotalCount",reportCount);
        resultMap.put("pageTotalCount",reportCount % pageSize > 0 ? (reportCount/pageSize)+1 : (reportCount/pageSize));
        //总计
        resultMap.put("sBountyTotalReport",sBountyReportVo2);
        return ResultGenerator.genSuccessResult(resultMap);
    }


    /**
     * 默认参数
     * @param startTime
     * @param endTime
     * @return
     */
    public  Map<String,Object> defaultParam(String startTime,String endTime){
        Map<String,Object> param=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        if(StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)){
            Date d=new Date();
            startTime= NomalUntil.DateChangeToStr(d, Calendar.DATE,-7);
            endTime= NomalUntil.DateToStr(d,"");
        }
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return  param;
    }

    /**
     * 注册来源统计
     * @param appPackage app包名
     * @param version app版本
     * @param channel app渠道
     * @param share 分享渠道
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public Result regReport(String appPackage, String version, String channel, String share, Integer pageNo, Integer pageSize) {
        if(pageNo == null || pageNo == 0){
            pageNo = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        Integer pageIndex = (pageNo-1) * pageSize;
        //默认app开头
        Map<String,Object> param = new HashMap<>();
        if(StringUtils.isNotBlank(appPackage)){
            param.put("PACKAGE",appPackage);
        }
        if(StringUtils.isNotBlank(version)){
            param.put("VERSION",version);
        }
        if(StringUtils.isNotBlank(channel)){
            param.put("CHANNEL",channel);
        }
        if(StringUtils.isNotBlank(share)){
            param.put("share",share);
        }
        param.put("pageIndex",pageIndex);
        param.put("pageSize",pageSize);
        List<RegReportVo> regReportVos = sRepotCountMapper.getRegReport(param);
        List<RegVo> regVos = new ArrayList<>();
        for(RegReportVo regReportVo : regReportVos){
            RegVo regVo = new RegVo();
            String regSource = regReportVo.getRegSource();
            if(StringUtils.isNotBlank(regSource)){
                String[] ars=regSource.split(" ");
                for(String ar:ars){
                    String[] item=ar.split("/");
                    if(item.length<2){
                        continue;
                    }
                    String name = item[0];
                    if(name.contains("PACKAGE")){
                        regVo.setStrPackage(item[1]);
                    }else if("VERSION".equals(name)){
                        regVo.setVersion(item[1]);
                    }else if("CHANNEL".equals(name)){
                        regVo.setChannel(item[1]);
                    }else if("COUNTRYID".equals(name)){
                        regVo.setCountryId(item[1]);
                    }else if("shar".equals(name)){
                        regVo.setShare(item[1]);
                    }
                }
            }
            regVo.setRegCount(regReportVo.getRegCount()+"");
            regVos.add(regVo);
        }
        Integer regReportCount = sRepotCountMapper.getRegReportCount(param);
        //返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("reportData",regVos);
        resultMap.put("pageNo",pageNo);
        resultMap.put("pageSize",pageSize);
        resultMap.put("dataTotalCount",regReportCount);
        resultMap.put("pageTotalCount",regReportCount % pageSize > 0 ? (regReportCount/pageSize)+1 : (regReportCount/pageSize));
        return ResultGenerator.genSuccessResult(resultMap);
    }
}
