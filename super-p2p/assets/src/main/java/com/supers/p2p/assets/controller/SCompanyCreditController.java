package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SCompanyCredit;
import com.supers.p2p.assets.service.SCompanyCreditService;
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
* Created by wenyuhao on 2018/05/04.
*/
@RestController
@RequestMapping("/s/company/credit")
@Api(value = "/s/company/credit",description = "")
public class SCompanyCreditController {
    @Resource
    private SCompanyCreditService sCompanyCreditService;

    @PostMapping("/add")
    public Result add(SCompanyCredit sCompanyCredit) {
        sCompanyCreditService.save(sCompanyCredit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sCompanyCreditService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SCompanyCredit sCompanyCredit) {
        sCompanyCreditService.update(sCompanyCredit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SCompanyCredit sCompanyCredit = sCompanyCreditService.findById(id);
        return ResultGenerator.genSuccessResult(sCompanyCredit);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SCompanyCredit> list = sCompanyCreditService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
