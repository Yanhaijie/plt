package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SMerchantOrder;
import com.supermoney.loan.mg.service.SMerchantOrderService;
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
* Created by bear on 2018/08/06.
*/
@RestController
@RequestMapping("/s/merchant/order")
@Api(value = "/s/merchant/order",description = "")
public class SMerchantOrderController {
    @Resource
    private SMerchantOrderService sMerchantOrderService;

    @PostMapping("/add")
    public Result add(SMerchantOrder sMerchantOrder) {
        sMerchantOrderService.save(sMerchantOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sMerchantOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMerchantOrder sMerchantOrder) {
        sMerchantOrderService.update(sMerchantOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SMerchantOrder sMerchantOrder = sMerchantOrderService.findById(id);
        return ResultGenerator.genSuccessResult(sMerchantOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sMerchantOrderService.selectByParam((Map)param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
