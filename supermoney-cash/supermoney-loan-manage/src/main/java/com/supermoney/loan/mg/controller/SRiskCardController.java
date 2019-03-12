package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SRiskCard;
import com.supermoney.loan.mg.service.SRiskCardService;
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
* Created by bear on 2018/07/30.
*/
@RestController
@RequestMapping("/s/risk/card")
@Api(value = "/s/risk/card",description = "")
public class SRiskCardController {
    @Resource
    private SRiskCardService sRiskCardService;

    @PostMapping("/add")
    public Result add(SRiskCard sRiskCard) {
        sRiskCardService.save(sRiskCard);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sRiskCardService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRiskCard sRiskCard) {
        sRiskCardService.update(sRiskCard);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SRiskCard> list = sRiskCardService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/savenodes")
    public Result savenodes(@RequestParam Integer id) {
        SRiskCard sRiskCard = sRiskCardService.findById(id);
        return ResultGenerator.genSuccessResult(sRiskCard);
    }

}
