package com.supermoney.loan.api.controller;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.service.SMessageUserService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.supermoney.loan.api.utils.NomalUntil.getPageMap;

/**
* Created by wenyuhao on 2018/04/10.
*/
@RestController
@RequestMapping("/s/message/user")
@Api(value = "/s/message/user",description = "")
public class SMessageUserController {
    @Resource
    private SMessageUserService sMessageUserService;

    @PostMapping("/readMessage")
    @ApiOperation("阅读消息")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "阅读消息",response = Result.class)
    public Result readMessage(@RequestParam Integer id,String appSecret) {
        SMessageUser sMessageUser = new SMessageUser();
        sMessageUser.setId(id);
        sMessageUser.setReadStatus(1);
        sMessageUserService.update(sMessageUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    @ApiOperation("获取推送的消息(用户没移除过的)")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "推送的消息",response = PageInfo.class)
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,String messageTypeId,String appSecret) {
        Map<String,Object> paramMap= getPageMap(page,size);
        paramMap.put("userId",UserUtils.getCurrentUserId());
        paramMap.put("messageTypeId",messageTypeId);
        paramMap.put("deleteStatus",0);
        List<SMessageUser> resultList =sMessageUserService.getList(paramMap);
        return ResultGenerator.genSuccessResult(resultList);
    }

    /**
     * 获取消息个数
     * @param appSecret
     * @return
     */
    @PostMapping("/getCount")
    @ApiOperation("获取消息个数")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "消息个数",response = Integer.class)
    public Result getCount(String appSecret) {
        SMessageUser sMessageUser = new SMessageUser();
        sMessageUser.setUserId(UserUtils.getCurrentUserId());
        //查未读消息且用户没有移除的消息
        sMessageUser.setReadStatus(0);
        sMessageUser.setDeleteStatus(0);
        int count =sMessageUserService.getCount(sMessageUser);
        return ResultGenerator.genSuccessResult(count);
    }

    /**
     * 获取消息个数详情
     * @return
     */
    @PostMapping("/getCountInfo")
    @ApiOperation("获取消息个数详情")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "消息个数详情",response = Map.class)
    public Result getCountInfo(String appSecret) throws Exception{
        SMessageUser sMessageUser = new SMessageUser();
        sMessageUser.setUserId(UserUtils.getCurrentUserId());
        //查未读消息且用户没有移除的消息
        sMessageUser.setReadStatus(0);
        sMessageUser.setDeleteStatus(0);
        Map<String,Object> resultMap =sMessageUserService.getCountInfo(sMessageUser);
        return ResultGenerator.genSuccessResult(resultMap);
    }
}
