package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.service.SExchangeRateService;
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
* Created by xionghuifeng on 2018/04/01.
*/
@RestController
@RequestMapping("/s/exchange/rate")
@Api(value = "/s/exchange/rate",description = "")
public class SExchangeRateController {
    @Resource
    private SExchangeRateService sExchangeRateService;

    @PostMapping("/add")
    public Result add(SExchangeRate sExchangeRate) {
        sExchangeRateService.save(sExchangeRate);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sExchangeRateService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SExchangeRate sExchangeRate) {
        sExchangeRateService.update(sExchangeRate);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SExchangeRate sExchangeRate = sExchangeRateService.findById(id);
        return ResultGenerator.genSuccessResult(sExchangeRate);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        PageInfo pageInfo=sExchangeRateService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
