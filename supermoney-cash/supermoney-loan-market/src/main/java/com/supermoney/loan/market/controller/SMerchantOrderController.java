package com.supermoney.loan.market.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.service.SMerchantOrderService;
import com.supermoney.loan.market.utils.RequestUntil;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by bear on 2018/08/06.
*/
@RestController
@RequestMapping("/market/buss")
public class SMerchantOrderController {
    @Resource
    private SMerchantOrderService sMerchantOrderService;

    @PostMapping("/findClearUser")
    public Result findClearUser() {
        List<SMerchantOrder> list = sMerchantOrderService.findNeedClearUser();
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/updateClearResult")
    public Result updateClearResult() {
        Map<String,Object> param= RequestUntil.getParams();
        sMerchantOrderService.updateClearResult(param);
        return ResultGenerator.genSuccessResult();
    }
}
