package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SLoanDamaged;
import com.supermoney.loan.mg.service.SLoanDamagedService;
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
* Created by bear on 2018/07/25.
*/
@RestController
@RequestMapping("/s/loan/damaged")
@Api(value = "/s/loan/damaged",description = "")
public class SLoanDamagedController {
    @Resource
    private SLoanDamagedService sLoanDamagedService;

    @PostMapping("/add")
    public Result add(SLoanDamaged sLoanDamaged) {
        sLoanDamagedService.save(sLoanDamaged);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sLoanDamagedService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SLoanDamaged sLoanDamaged) {
        sLoanDamagedService.update(sLoanDamaged);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SLoanDamaged sLoanDamaged = sLoanDamagedService.findById(id);
        return ResultGenerator.genSuccessResult(sLoanDamaged);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SLoanDamaged> list = sLoanDamagedService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
