package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.service.SUserService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SActivityWhiteList;
import com.supermoney.loan.mg.service.SActivityWhiteListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/04/21.
*/
@RestController
@RequestMapping("/s/activity/white/list")
@Api(value = "/s/activity/white/list",description = "")
public class SActivityWhiteListController {
    @Resource
    private SActivityWhiteListService sActivityWhiteListService;

    @Resource
    private SUserService userService;

    @PostMapping("/add")
    public Result add(SActivityWhiteList sActivityWhiteList) {
        sActivityWhiteListService.save(sActivityWhiteList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sActivityWhiteListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SActivityWhiteList sActivityWhiteList) {
        sActivityWhiteListService.update(sActivityWhiteList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SActivityWhiteList sActivityWhiteList = sActivityWhiteListService.findById(id);
        return ResultGenerator.genSuccessResult(sActivityWhiteList);
    }

    @PostMapping("/save")
    public Result save(SActivityWhiteList sActivityWhiteList) {
        if (sActivityWhiteList.getUserId() != null){
            SUser user = userService.findById(sActivityWhiteList.getUserId());
            if (user == null){
                return ResultGenerator.genFailResult("用户id错误");
            }
        }
        else {
            return ResultGenerator.genFailResult("未关联用户");
        }

        if (sActivityWhiteList.getId() != null){
            sActivityWhiteListService.update(sActivityWhiteList);
        }
        else {
            sActivityWhiteList.setActivityName("lottery_51");
            sActivityWhiteList.setIsSent((byte)0);
            sActivityWhiteListService.save(sActivityWhiteList);
        }
        return ResultGenerator.genSuccessResult();
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
        List<SActivityWhiteList> list = sActivityWhiteListService.selectWhiteListByMap(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
