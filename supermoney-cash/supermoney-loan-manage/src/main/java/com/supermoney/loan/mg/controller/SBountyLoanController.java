package com.supermoney.loan.mg.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SBountyLoan;
import com.supermoney.loan.mg.service.SBountyLoanService;
import com.supermoney.loan.mg.service.SBussLimitService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/02/27.
*/
@RestController
@RequestMapping("/s/bounty/loan")
@Api(value = "/s/bounty/loan",description = "")
public class SBountyLoanController {
    @Resource
    private SBountyLoanService sBountyLoanService;

    @Resource
    private SBussLimitService sBussLimitService;

    @PostMapping("/add")
    public Result add(SBountyLoan sBountyLoan) {
        String jsonStr=sBountyLoan.getOpt();
        sBountyLoan.setOpt(null);
        sBountyLoanService.save(sBountyLoan);
        sBussLimitService.saveLoanLimit(sBountyLoan.getId(),jsonStr);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBountyLoanService.deleteById(id);
        sBussLimitService.delLoanLimit(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBountyLoan sBountyLoan) {
        String jsonStr=sBountyLoan.getOpt();
        sBountyLoan.setOpt(null);
        sBountyLoanService.update(sBountyLoan);
        sBussLimitService.saveLoanLimit(sBountyLoan.getId(),jsonStr);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBountyLoan sBountyLoan = sBountyLoanService.findById(id);
        return ResultGenerator.genSuccessResult(sBountyLoan);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SBountyLoan> list = sBountyLoanService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/bountyloan")
    public Result bountyloan(@RequestParam(defaultValue = "0") Integer bountyId) {
        return  sBountyLoanService.getByBountyId(bountyId);
    }

}
