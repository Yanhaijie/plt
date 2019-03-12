package com.wi.data.clear.controller;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultGenerator;
import com.wi.data.clear.entity.CDeviceApp;
import com.wi.data.clear.service.CDeviceAppService;
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
@RequestMapping("/c/device/app")
public class CDeviceAppController {
    @Resource
    private CDeviceAppService cDeviceAppService;

    @PostMapping("/add")
    public Result add(CDeviceApp cDeviceApp) {
        cDeviceAppService.save(cDeviceApp);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        cDeviceAppService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CDeviceApp cDeviceApp) {
        cDeviceAppService.update(cDeviceApp);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CDeviceApp cDeviceApp = cDeviceAppService.findById(id);
        return ResultGenerator.genSuccessResult(cDeviceApp);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CDeviceApp> list = cDeviceAppService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
