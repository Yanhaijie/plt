package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SCallRecord;
import com.supermoney.open.platform.service.SCallRecordService;
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
* Created by @author on 2018/10/10.
*/
@RestController
@RequestMapping("/mg/call/record")
@Api(value = "/mg/call/record",description = "")
public class SCallRecordController {
    @Resource
    private SCallRecordService sCallRecordService;

    @PostMapping("/add")
    public Result add(SCallRecord sCallRecord) {
        sCallRecordService.save(sCallRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sCallRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SCallRecord sCallRecord) {
        sCallRecordService.update(sCallRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SCallRecord sCallRecord = sCallRecordService.findById(id);
        return ResultGenerator.genSuccessResult(sCallRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sCallRecordService.selectCallRecordByParam((Map<String,Object> )param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
