package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SActivityGift;
import com.supermoney.loan.mg.service.SActivityGiftService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/04/21.
*/
@RestController
@RequestMapping("/s/activity/gift")
@Api(value = "/s/activity/gift",description = "")
public class SActivityGiftController {
    @Resource
    private SActivityGiftService sActivityGiftService;

    @PostMapping("/add")
    public Result add(SActivityGift sActivityGift) {
        sActivityGiftService.save(sActivityGift);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sActivityGiftService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SActivityGift sActivityGift) {
        sActivityGiftService.update(sActivityGift);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/save")
    public Result save(SActivityGift sActivityGift) {
        if (sActivityGift.getId() != null){
            sActivityGiftService.update(sActivityGift);
        }
        else {
            sActivityGift.setActivityName("lottery_51");
            sActivityGiftService.save(sActivityGift);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SActivityGift sActivityGift = sActivityGiftService.findById(id);
        return ResultGenerator.genSuccessResult(sActivityGift);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String, Object> tempParam = (Map<String,Object>)param.get("search");
        if (tempParam == null){
            tempParam = new HashMap<>();
        }
        tempParam.put("activityName","lottery_51");
        List<Map<String, Object>> list = sActivityGiftService.selectGiftByMap(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/searchAllList")
    public Result searchAllList() {
        Map<String, Object> tempParam = new HashMap<>();
        tempParam.put("activityName","lottery_51");
        List<Map<String, Object>> list = sActivityGiftService.selectSearchGiftByMap(tempParam);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/backList")
    public Result backList() {
        Map<String, Object> tempParam = new HashMap<>();
        tempParam.put("activityName","lottery_51");
        List<Map<String, Object>> list= sActivityGiftService.selectBackGiftByMap(tempParam);
        return ResultGenerator.genSuccessResult(list);
    }
}
