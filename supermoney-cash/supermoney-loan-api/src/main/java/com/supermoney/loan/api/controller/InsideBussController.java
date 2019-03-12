package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.service.SLoanOrderService;
import com.supermoney.loan.api.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i/buss")
@Api(value = "/i/buss",description = "对内服务")
public class InsideBussController {
    @Autowired
    private SLoanOrderService sLoanOrderService;

    @RequestMapping(value = "/fixrepay",method = {RequestMethod.GET,RequestMethod.POST})
    public Result fixRepayCallBack(Integer bussType,String relateId){
        return  sLoanOrderService.fixRepayCallBack(bussType,relateId);
    }
}
