package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SActivityLotteryRecord;
import com.supermoney.loan.mg.service.SActivityLotteryRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/04/21.
*/
@RestController
@RequestMapping("/s/activity/lottery/record")
@Api(value = "/s/activity/lottery/record",description = "")
public class SActivityLotteryRecordController {
    @Resource
    private SActivityLotteryRecordService sActivityLotteryRecordService;

    @PostMapping("/add")
    public Result add(SActivityLotteryRecord sActivityLotteryRecord) {
        sActivityLotteryRecordService.save(sActivityLotteryRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sActivityLotteryRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SActivityLotteryRecord sActivityLotteryRecord) {
        sActivityLotteryRecordService.update(sActivityLotteryRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SActivityLotteryRecord sActivityLotteryRecord = sActivityLotteryRecordService.findById(id);
        return ResultGenerator.genSuccessResult(sActivityLotteryRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String, Object> tempParam = (Map<String,Object>)param.get("search");
        tempParam.put("activityName","lottery_51");
        List<SActivityLotteryRecord> list = sActivityLotteryRecordService.selectLotteryRecordListByMap(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/user/lottery/count")
    public Result userLotteryCount(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String, Object> tempParam = (Map<String,Object>)param.get("search");
        List<Map<String, Object>> list = sActivityLotteryRecordService.selectUserLotteryCount(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
