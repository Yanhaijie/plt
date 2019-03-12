package com.supermoney.loan.mg.controller;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SBountyRecord;
import com.supermoney.loan.mg.service.SBountyRecordService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/01/06.
*/
@RestController
@RequestMapping("/s/bounty/record")
@Api(value = "/s/bounty/record",description = "")
public class SBountyRecordController {
    @Resource
    private SBountyRecordService sBountyRecordService;

    @PostMapping("/add")
    public Result add(SBountyRecord sBountyRecord) {
        sBountyRecordService.save(sBountyRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBountyRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBountyRecord sBountyRecord) {
        sBountyRecordService.update(sBountyRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBountyRecord sBountyRecord = sBountyRecordService.findById(id);
        return ResultGenerator.genSuccessResult(sBountyRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sBountyRecordService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/audit")
    public Result audit(@RequestParam Integer status,String[] ids){
        return  sBountyRecordService.auditTask(status,ids);
    }
}
