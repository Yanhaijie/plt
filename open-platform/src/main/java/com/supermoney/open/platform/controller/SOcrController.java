package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SOcr;
import com.supermoney.open.platform.service.SOcrService;
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
@RequestMapping("/mg/ocr")
@Api(value = "/mg/ocr",description = "")
public class SOcrController {
    @Resource
    private SOcrService sOcrService;

    @PostMapping("/add")
    public Result add(SOcr sOcr) {
        sOcrService.save(sOcr);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sOcrService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SOcr sOcr) {
        sOcrService.update(sOcr);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SOcr sOcr = sOcrService.findById(id);
        return ResultGenerator.genSuccessResult(sOcr);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SOcr> list = sOcrService.selectOcrListByParam((Map<String,Object>)param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
