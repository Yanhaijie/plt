package com.wi.data.clear.controller;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultGenerator;
import com.wi.data.clear.entity.CDeviceMsg;
import com.wi.data.clear.service.CDeviceMsgService;
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
@RequestMapping("/c/device/msg")
public class CDeviceMsgController {
    @Resource
    private CDeviceMsgService cDeviceMsgService;

    @PostMapping("/add")
    public Result add(CDeviceMsg cDeviceMsg) {
        cDeviceMsgService.save(cDeviceMsg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        cDeviceMsgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CDeviceMsg cDeviceMsg) {
        cDeviceMsgService.update(cDeviceMsg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        CDeviceMsg cDeviceMsg = cDeviceMsgService.findById(id);
        return ResultGenerator.genSuccessResult(cDeviceMsg);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CDeviceMsg> list = cDeviceMsgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
