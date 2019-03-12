package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SRepotCount;
import com.supermoney.loan.mg.entity.vo.DistributionVo;
import com.supermoney.loan.mg.entity.vo.RegReportVo;
import com.supermoney.loan.mg.entity.vo.RewardVO;
import com.supermoney.loan.mg.entity.vo.SBountyReportVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SRepotCountMapper extends Mapper<SRepotCount> {
    //赏金统计
    List<RewardVO> bountyReport(Map<String, Object> param);
    //赏金统计数据条数
    Integer bountyReportCount(Map<String, Object> param);
    //话费统计
    SBountyReportVo getTransferToTotalAmount();
    //赏金总额
    SBountyReportVo getBountyTotalAmount();
    //注册来源统计
    List<RegReportVo> getRegReport(Map<String, Object> param);

    Integer getRegReportCount(Map<String, Object> param);
}