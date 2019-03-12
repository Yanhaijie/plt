package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.service.SLoanOrderService;
import com.supermoney.loan.api.service.SXenditVirtualAccountService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/s/loan")
@Api(value = "/s/loan",description = "现金贷接口")
public class LoanController {

    @Resource
    private SLoanOrderService sLoanOrderService;

    @Resource
    private SXenditVirtualAccountService sXenditVirtualAccountService;


    @ApiOperation("借款下单")
    @PostMapping("/to-order")
    public Result list(@RequestParam Integer bountyId,
                       @ApiParam(name = "needAmount", value = "需要借款的金额", required = true) @RequestParam Integer needAmount,
                       @ApiParam(name = "limit", value = "借款周期", required = true) @RequestParam Integer limit,
                       @ApiParam(name = "unit", value = "周期单位", required = true) @RequestParam String unit,
                       @ApiParam(name = "reason", value = "借款理由", required = true) @RequestParam String reason,
                       @ApiParam(name = "countryCode", value = "国家区域编码", required = true) @RequestParam String countryCode,
                       String appSecret) {

        return sLoanOrderService.toOrder(bountyId,needAmount,limit,unit,reason,countryCode,UserUtils.getCurrentUserId());
    }

    @ApiOperation("订单列表")
    @PostMapping("/order-list")
    public Result orderList(@ApiParam(name = "orderType", value = "订单类型（0 全部， 1 待完成，2 待还款，3 已结清）", required = true) @RequestParam Integer orderType ,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size,String appSecret) {
        return sLoanOrderService.orderList(orderType ,UserUtils.getCurrentUserId(),page,size);
    }

    @ApiOperation("订单详情")
    @PostMapping("/order-detail")
    public Result orderDetail(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam Integer orderId ,String appSecret) {
        return sLoanOrderService.orderDetail(orderId ,UserUtils.getCurrentUserId());
    }


    @ApiOperation("订单还款账户")
    @PostMapping("/order-virtualAccount")
    public Result orderVirtualAccount(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam Integer orderId ,String appSecret) {
        Map<String, Object>  resultMap = sLoanOrderService.orderVirtualAccount(orderId ,UserUtils.getCurrentUserId());
        if (resultMap== null){
            ResultGenerator.genFailResult("不存在对应订单的还款账户");
        }
        return ResultGenerator.genSuccessResult(resultMap.get("account_number"));
    }
}
