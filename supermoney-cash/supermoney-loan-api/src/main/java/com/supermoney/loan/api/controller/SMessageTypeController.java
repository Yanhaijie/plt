package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.entity.vo.SMessageTypeVo;
import com.supermoney.loan.api.service.SMessageTypeService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by wenyuhao on 2018/04/10.
*/
@RestController
@RequestMapping("/s/message/type")
@Api(value = "/s/message/type",description = "")
public class SMessageTypeController {
    @Resource
    private SMessageTypeService sMessageTypeService;


    /**
     * 获取消息个数详情
     * @return
     */
    @PostMapping("/getMessageType")
    @ApiOperation("获取消息类别及未读情况")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "消息类别及未读情况",response = List.class)
    public Result getMessageType(String appSecret) throws Exception{
        SMessageUser sMessageUser = new SMessageUser();
        sMessageUser.setUserId(UserUtils.getCurrentUserId());
        List<SMessageTypeVo> resultList = sMessageTypeService.findMessageType(sMessageUser);
        return ResultGenerator.genSuccessResult(resultList);
    }

}
