package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SCompanyInfo;
import com.supers.p2p.assets.service.SCompanyInfoService;
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
@RequestMapping("/s/company/info")
@Api(value = "/s/company/info",description = "")
public class SCompanyInfoController {
    @Resource
    private SCompanyInfoService sCompanyInfoService;

    @PostMapping("/add")
    public Result add(SCompanyInfo sCompanyInfo) {
        sCompanyInfoService.save(sCompanyInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sCompanyInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SCompanyInfo sCompanyInfo) {
        sCompanyInfoService.update(sCompanyInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SCompanyInfo sCompanyInfo = sCompanyInfoService.findById(id);
        return ResultGenerator.genSuccessResult(sCompanyInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SCompanyInfo> list = sCompanyInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
