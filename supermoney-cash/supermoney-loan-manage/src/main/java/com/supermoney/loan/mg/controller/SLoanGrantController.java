package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SLoanGrant;
import com.supermoney.loan.mg.service.SLoanGrantService;
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
* Created by xionghuifeng on 2018/06/04.
*/
@RestController
@RequestMapping("/s/loan/grant")
@Api(value = "/s/loan/grant",description = "")
public class SLoanGrantController {
    @Resource
    private SLoanGrantService sLoanGrantService;

    @PostMapping("/update")
    public Result update(SLoanGrant sLoanGrant) {
        sLoanGrantService.update(sLoanGrant);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sLoanGrantService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 放款审核
     * @param orderStatus
     * @param ids
     * @param remark
     * @return
     */
    @PostMapping("/audit")
    public Result audit(Integer orderStatus,String[] ids,String remark){
        return  sLoanGrantService.audit(ids,orderStatus,remark);
    }

}
