package com.wi.data.clear.controller;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultGenerator;
import com.wi.data.clear.entity.CDeviceLocation;
import com.wi.data.clear.service.CDeviceLocationService;
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
@RequestMapping("/c/device/location")
public class CDeviceLocationController {
    @Resource
    private CDeviceLocationService cDeviceLocationService;

    @PostMapping("/add")
    public Result add(CDeviceLocation cDeviceLocation) {
        cDeviceLocationService.save(cDeviceLocation);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        cDeviceLocationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CDeviceLocation cDeviceLocation) {
        cDeviceLocationService.update(cDeviceLocation);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CDeviceLocation cDeviceLocation = cDeviceLocationService.findById(id);
        return ResultGenerator.genSuccessResult(cDeviceLocation);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CDeviceLocation> list = cDeviceLocationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
