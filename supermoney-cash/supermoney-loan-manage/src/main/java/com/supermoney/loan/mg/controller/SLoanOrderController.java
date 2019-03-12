package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SLoanOrder;
import com.supermoney.loan.mg.service.SLoanOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/05/30.
*/
@RestController
@RequestMapping("/s/loan/order")
@Api(value = "/s/loan/order",description = "")
public class SLoanOrderController {
    @Resource
    private SLoanOrderService sLoanOrderService;

    @PostMapping("/update")
    public Result update(SLoanOrder sLoanOrder) {
        sLoanOrderService.update(sLoanOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SLoanOrder sLoanOrder = sLoanOrderService.findById(id);
        return ResultGenerator.genSuccessResult(sLoanOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sLoanOrderService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    /**
     * 订单审核
     * @param id
     * @param orderStatus
     * @return
     */
    @PostMapping("/audit")
    public Result audit(Integer id,Integer orderStatus,String remark){
        return  sLoanOrderService.audit(id,orderStatus,remark);
    }
    /**
     * 测试还款打款
     * @param id
     * @return
     */
    @PostMapping("/testrepay")
    public  Result testRepay(Integer id,boolean isOverdue,Integer amount){
        return  sLoanOrderService.testRepay(id,isOverdue,amount);
    }

    /**
     * 订单转API
     * @param start
     * @param end
     * @return
     */
    @PostMapping("/toapi")
    public  Result toapi(String start,String end,Integer orderStatus,Integer toApiBountyId){
        return  sLoanOrderService.allToApi(start,end,orderStatus,toApiBountyId);
    }

}
