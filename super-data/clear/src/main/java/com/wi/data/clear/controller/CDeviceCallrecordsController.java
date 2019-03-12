package com.wi.data.clear.controller;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultGenerator;
import com.wi.data.clear.entity.CDeviceCallrecords;
import com.wi.data.clear.service.CDeviceCallrecordsService;
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
@RequestMapping("/c/device/callrecords")
public class CDeviceCallrecordsController {
    @Resource
    private CDeviceCallrecordsService cDeviceCallrecordsService;

    @PostMapping("/add")
    public Result add(CDeviceCallrecords cDeviceCallrecords) {
        cDeviceCallrecordsService.save(cDeviceCallrecords);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        cDeviceCallrecordsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CDeviceCallrecords cDeviceCallrecords) {
        cDeviceCallrecordsService.update(cDeviceCallrecords);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CDeviceCallrecords cDeviceCallrecords = cDeviceCallrecordsService.findById(id);
        return ResultGenerator.genSuccessResult(cDeviceCallrecords);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CDeviceCallrecords> list = cDeviceCallrecordsService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
