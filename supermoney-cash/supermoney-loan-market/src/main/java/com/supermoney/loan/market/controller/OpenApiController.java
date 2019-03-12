package com.supermoney.loan.market.controller;

import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.entity.requestVo.CreditRequestVo;
import com.supermoney.loan.market.entity.requestVo.DemandRequestVo;
import com.supermoney.loan.market.entity.requestVo.OrderRequestVo;
import com.supermoney.loan.market.service.impl.MerchantOrderBussService;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultGenerator;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.util.RequestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class OpenApiController {

    @Autowired
    private MerchantOrderBussService merchantOrderBussService;

    /**
     * 3.1获取借款需求接口
     * @return
     */
    @PostMapping("/demand")
    public Result demand(DemandRequestVo requestVo,HttpServletRequest request) {
        requestVo.setMerchantId(request.getAttribute("merchantId").toString());
        return merchantOrderBussService.getDemand(requestVo);
    }


    /**
     * 3.2获取征信资料接口
     * @param requestVo
     * @return
     */
    @PostMapping("/credit")
    public Result credit(CreditRequestVo requestVo) {
        return merchantOrderBussService.getCredit(requestVo);
    }

    /**
     * 3.3获取采集数据接口
     * @param requestVo
     * @return
     */
    @PostMapping("/collection")
    public Result collection(CreditRequestVo requestVo) {
        return merchantOrderBussService.getCollection(requestVo);
    }


    /**
     * 3.4更新借款订单接口
     * @param requestVo
     * @return
     */
    @PostMapping("/orderupdate")
    public Result orderupdate(OrderRequestVo requestVo) {
        return merchantOrderBussService.orderupdate(requestVo);
    }

}
