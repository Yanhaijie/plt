package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SBaiduLiving;
import com.supermoney.open.platform.service.SBaiduLivingService;
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
@RequestMapping("/mg/baidu/living")
@Api(value = "/mg/baidu/living",description = "")
public class SBaiduLivingController {
    @Resource
    private SBaiduLivingService sBaiduLivingService;

    @PostMapping("/add")
    public Result add(SBaiduLiving sBaiduLiving) {
        sBaiduLivingService.save(sBaiduLiving);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBaiduLivingService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBaiduLiving sBaiduLiving) {
        sBaiduLivingService.update(sBaiduLiving);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBaiduLiving sBaiduLiving = sBaiduLivingService.findById(id);
        return ResultGenerator.genSuccessResult(sBaiduLiving);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SBaiduLiving> list = sBaiduLivingService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
