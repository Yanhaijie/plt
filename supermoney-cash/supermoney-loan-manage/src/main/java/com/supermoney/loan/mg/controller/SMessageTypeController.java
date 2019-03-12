package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SMessageType;
import com.supermoney.loan.mg.service.SMessageTypeService;
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
* Created by wenyuhao on 2018/04/04.
*/
@RestController
@RequestMapping("/s/message/type")
@Api(value = "/s/message/type",description = "")
public class SMessageTypeController {
    @Resource
    private SMessageTypeService sMessageTypeService;

    @PostMapping("/add")
    public Result add(SMessageType sMessageType) {
        sMessageTypeService.save(sMessageType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sMessageTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMessageType sMessageType) {
        sMessageTypeService.update(sMessageType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SMessageType sMessageType = sMessageTypeService.findById(id);
        return ResultGenerator.genSuccessResult(sMessageType);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        PageInfo pageInfo=sMessageTypeService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/drop")
    public Result items(){
        return ResultGenerator.genSuccessResult(sMessageTypeService.getDrop());
    }
}
