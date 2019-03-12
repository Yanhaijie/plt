package com.supermoney.loan.mg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.supermoney.loan.mg.entity.SBlackList;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.service.SBlackListService;
import com.supermoney.loan.mg.service.SLoanOrderService;
import com.supermoney.loan.mg.service.SUserService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by @author on 2018/07/16.
*/
@RestController
@RequestMapping("/s/black/list")
@Api(value = "/s/black/list",description = "")
public class SBlackListController {
    @Resource
    private SBlackListService sBlackListService;

    @Resource
    private SLoanOrderService sLoanOrderService;
    @Resource
    private SUserService sUserService;

    @PostMapping("/add")
    public Result add(SBlackList sBlackList) {
        System.out.println("=============");

        if (sBlackList.getUserName().startsWith("0") == false){
            sBlackList.setUserName("0" + sBlackList.getUserName());
        }
        if (sBlackListService.blackListCheck1(sBlackList.getUserName()) == 0){
            List<SBlackList> list = Lists.newArrayList();
            list.add(sBlackList);
            sBlackListService.saveOrUpdateBlackList(list);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/addblack")
    public Result addBlack(Integer id,String remark) {
        SLoanOrder order = sLoanOrderService.findById(id);
        SUser user = sUserService.findById(order.getUserId());

        if (user.getUserName().startsWith("0") == false){
            user.setUserName("0" + user.getUserName());
        }
        if (sBlackListService.blackListCheck1(user.getUserName()) == 0){
            SBlackList sBlackList = new SBlackList();
            sBlackList.setUserName(user.getUserName());
            sBlackList.setCreateTime(new Date());
            sBlackList.setSource(3);
            sBlackList.setRemark(remark);
            sBlackListService.save(sBlackList);
        }
        else {
            ResultGenerator.genFailResult("已经在黑名单中");
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBlackListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBlackList sBlackList) {
        sBlackListService.update(sBlackList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBlackList sBlackList = sBlackListService.findById(id);
        return ResultGenerator.genSuccessResult(sBlackList);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        List<SBlackList> list = sBlackListService.selectList(tempParam);
        //List<SBlackList> list = null;
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
