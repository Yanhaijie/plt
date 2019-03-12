package com.supermoney.loan.mg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SMessage;
import com.supermoney.loan.mg.service.SMessageService;
import com.supermoney.loan.mg.service.SXenditVirtualAccountService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
/**
* Created by wenyuhao on 2018/04/04.
*/
@RestController
@RequestMapping("/s/message")
@Api(value = "/s/message",description = "")
public class SMessageController {
    @Resource
    private SMessageService sMessageService;
    @Resource
    private SXenditVirtualAccountService sXenditVirtualAccountService;


    @PostMapping("/add")
    public Result add(SMessage sMessage) {
        sMessageService.save(sMessage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sMessageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMessage sMessage) {
        sMessageService.update(sMessage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SMessage sMessage = sMessageService.findById(id);
        return ResultGenerator.genSuccessResult(sMessage);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        PageInfo pageInfo=sMessageService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 推送消息
     */
    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestParam Integer id) {
        return sMessageService.sendMessage(id);
    }
}
