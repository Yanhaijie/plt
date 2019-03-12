package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SPersonalInfo;
import com.supers.p2p.assets.service.SPersonalInfoService;
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
@RequestMapping("/s/personal/info")
@Api(value = "/s/personal/info",description = "")
public class SPersonalInfoController {
    @Resource
    private SPersonalInfoService sPersonalInfoService;

    @PostMapping("/add")
    public Result add(SPersonalInfo sPersonalInfo) {
        sPersonalInfoService.save(sPersonalInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sPersonalInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SPersonalInfo sPersonalInfo) {
        sPersonalInfoService.update(sPersonalInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SPersonalInfo sPersonalInfo = sPersonalInfoService.findById(id);
        return ResultGenerator.genSuccessResult(sPersonalInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SPersonalInfo> list = sPersonalInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
