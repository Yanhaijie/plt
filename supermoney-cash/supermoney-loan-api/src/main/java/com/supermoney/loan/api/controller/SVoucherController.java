package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.vo.SVoucherVo;
import com.supermoney.loan.api.service.SVoucherUserRecordService;
import com.supermoney.loan.api.utils.Constants;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
* Created by xionghuifeng on 2018/01/14.
*/
@RestController
@RequestMapping("/s/voucher")
@Api(value = "/s/voucher",description = "抵用券")
public class SVoucherController {
    @Resource
    private SVoucherUserRecordService sVoucherUserRecordService;

    /**
     * 1未使用   2已用中    3过期
     * @param type
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("获取个人抵用券列表")
    public Result list(@ApiParam(name = "type", value = "类型: 0未使用  1已用中  2过期", required = true) @RequestParam(defaultValue = "1") int type, String appSecret) {
        return sVoucherUserRecordService.getUserVoucherList(type,UserUtils.getCurrentUserId());
    }

    @PostMapping("/use")
    @ApiOperation("使用抵用券")
    public Result useVoucher(@RequestParam Integer id,@RequestParam Integer bountyId,String appSecret) {
       return  sVoucherUserRecordService.userVoucher(id, UserUtils.getCurrentUserId(),bountyId);
    }

    @PostMapping("/new-voucher")
    @ApiOperation("获取新发放的抵用券")
    @ApiResponse( code = Constants.SUCCESS_CODE,message = "抵用券信息",response = SVoucherVo.class)
    public  Result getNewVoucher(String appSecret){
        return  sVoucherUserRecordService.getNewVoucher(UserUtils.getCurrentUserId());
    }

    @PostMapping("/viewed-voucher")
    @ApiOperation("已查看新抵用券")
    public  Result viewVoucher(String appSecret,String[] ids){
        return  sVoucherUserRecordService.viewNewVoucher(UserUtils.getCurrentUserId(),ids);
    }


}
