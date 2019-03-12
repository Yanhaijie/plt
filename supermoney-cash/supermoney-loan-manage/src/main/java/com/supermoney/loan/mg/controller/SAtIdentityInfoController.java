package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SAtIdentityInfo;
import com.supermoney.loan.mg.service.SAtIdentityInfoService;
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
* Created by xionghuifeng on 2018/04/10.
*/
@RestController
@RequestMapping("/s/at/identity/info")
@Api(value = "/s/at/identity/info",description = "")
public class SAtIdentityInfoController {
    @Resource
    private SAtIdentityInfoService sAtIdentityInfoService;

    @PostMapping("/add")
    public Result add(SAtIdentityInfo sAtIdentityInfo) {
        sAtIdentityInfoService.save(sAtIdentityInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sAtIdentityInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SAtIdentityInfo sAtIdentityInfo) {
        sAtIdentityInfoService.update(sAtIdentityInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SAtIdentityInfo sAtIdentityInfo = sAtIdentityInfoService.findById(id);
        return ResultGenerator.genSuccessResult(sAtIdentityInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SAtIdentityInfo> list = sAtIdentityInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
