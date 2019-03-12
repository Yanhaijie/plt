package com.wi.data.clear.controller;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultGenerator;
import com.wi.data.clear.entity.CMobile;
import com.wi.data.clear.service.CMobileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by CodeGenerator on 2018/06/22.
*/
@RestController
@RequestMapping("/c/mobile")
public class CMobileController {
    @Resource
    private CMobileService cMobileService;

    @PostMapping("/add")
    public Result add(CMobile cMobile) {
        cMobileService.save(cMobile);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        cMobileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CMobile cMobile) {
        cMobileService.update(cMobile);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CMobile cMobile = cMobileService.findById(id);
        return ResultGenerator.genSuccessResult(cMobile);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CMobile> list = cMobileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
