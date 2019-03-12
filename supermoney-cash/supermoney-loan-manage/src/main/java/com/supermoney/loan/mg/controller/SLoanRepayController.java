package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.Feign.SupermoneyApiClient;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SLoanRepay;
import com.supermoney.loan.mg.service.SLoanRepayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/06/04.
*/
@RestController
@RequestMapping("/s/loan/repay")
@Api(value = "/s/loan/repay",description = "")
public class SLoanRepayController {
    @Resource
    private SLoanRepayService sLoanRepayService;

    @Autowired
    private SupermoneyApiClient supermoneyApiClient;

    /**
     * 还款账单
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sLoanRepayService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 用户还款记录
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/record")
    public Result record(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sLoanRepayService.getRecordByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 还款业务重新执行
     * @param bussType
     * @param relateId
     * @return
     */
    @PostMapping("/fixrepayerror")
    public Result fixrepayerror(@RequestParam(defaultValue = "2") Integer bussType, @RequestParam(defaultValue = "") String relateId) {
        if(StringUtils.isBlank(relateId)){
            return  ResultGenerator.genFailResult("relateId is null");
        }
        return supermoneyApiClient.repayCallBackFix(bussType,relateId);
    }

    /**
     * 还款报损
     * @param orderCode
     * @param damagedAmountType
     * @param damagedType
     * @param orderStatus
     * @return
     */
    @PostMapping("/damaged")
    public Result damaged(   @RequestParam(defaultValue = "") String orderCode,
                               @RequestParam Integer damagedAmountType,
                               @RequestParam Integer damagedType,
                               @RequestParam Integer orderStatus
                           ) {
        return sLoanRepayService.damaged(orderCode,damagedAmountType,damagedType,orderStatus);
    }


}
