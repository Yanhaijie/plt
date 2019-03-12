package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.SMessage;
import com.supermoney.loan.api.service.SMessageService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
* Created by wenyuhao on 2018/04/10.
*/
@RestController
@RequestMapping("/s/message")
@Api(value = "/s/message",description = "")
public class SMessageController {
    @Resource
    private SMessageService sMessageService;

    @PostMapping("/detail")
    @ApiOperation("获取消息详情")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "消息详情",response = SMessage.class)
    public Result detail(@RequestParam Integer id,String appSecret) {
        SMessage sMessage = sMessageService.findById(id);
        return ResultGenerator.genSuccessResult(sMessage);
    }

}
