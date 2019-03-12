package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SRiskSource;
import com.supermoney.loan.mg.service.SRiskSourceService;
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
@RequestMapping("/s/risk/source")
@Api(value = "/s/risk/source",description = "")
public class SRiskSourceController {
    @Resource
    private SRiskSourceService sRiskSourceService;

    @PostMapping("/add")
    public Result add(SRiskSource sRiskSource) {
        sRiskSourceService.save(sRiskSource);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sRiskSourceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRiskSource sRiskSource) {
        sRiskSourceService.update(sRiskSource);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SRiskSource sRiskSource = sRiskSourceService.findById(id);
        return ResultGenerator.genSuccessResult(sRiskSource);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SRiskSource> list = sRiskSourceService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 下拉数据源
     * @param keyword
     * @return
     */
    @PostMapping("/drop")
    public Result drop(String keyword) {
        return sRiskSourceService.getDrop(keyword);
    }
}
