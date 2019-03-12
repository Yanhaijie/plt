package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.entity.SBussLabel;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SAtCreditInformation;
import com.supermoney.loan.mg.service.SAtCreditInformationService;
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
* Created by wenyuhao on 2018/05/28.
*/
@RestController
@RequestMapping("/s/at/credit/information")
@Api(value = "/s/at/credit/information",description = "")
public class SAtCreditInformationController {
    @Resource
    private SAtCreditInformationService sAtCreditInformationService;

    @PostMapping("/update")
    public Result update(SAtCreditInformation sAtCreditInformation) {
        sAtCreditInformationService.update(sAtCreditInformation);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sAtCreditInformationService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        return  sAtCreditInformationService.getInfo(id);
    }

    @PostMapping("/audit")
    public Result audit(@RequestParam Integer status,String[] ids) {
        return sAtCreditInformationService.audits(status,ids);
    }
}
