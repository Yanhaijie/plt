package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SBaiduCompare;
import com.supermoney.open.platform.service.SBaiduCompareService;
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
* Created by @author on 2018/10/22.
*/
@RestController
@RequestMapping("/mg/baidu/compare")
@Api(value = "/mg/baidu/compare",description = "")
public class SBaiduCompareController {
    @Resource
    private SBaiduCompareService sBaiduCompareService;

    @PostMapping("/add")
    public Result add(SBaiduCompare sBaiduCompare) {
        sBaiduCompareService.save(sBaiduCompare);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBaiduCompareService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBaiduCompare sBaiduCompare) {
        sBaiduCompareService.update(sBaiduCompare);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBaiduCompare sBaiduCompare = sBaiduCompareService.findById(id);
        return ResultGenerator.genSuccessResult(sBaiduCompare);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SBaiduCompare> list = sBaiduCompareService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
