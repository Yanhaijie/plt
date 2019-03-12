package com.supermoney.loan.mg.controller;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SVoucherUserRecord;
import com.supermoney.loan.mg.service.SVoucherUserRecordService;
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
* Created by xionghuifeng on 2018/01/15.
*/
@RestController
@RequestMapping("/s/voucher/user/record")
@Api(value = "/s/voucher/user/record",description = "")
public class SVoucherUserRecordController {
    @Resource
    private SVoucherUserRecordService sVoucherUserRecordService;


    @PostMapping("/update")
    public Result update(SVoucherUserRecord sVoucherUserRecord) {
        sVoucherUserRecordService.update(sVoucherUserRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
     /*   Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SVoucherUserRecord> list = sVoucherUserRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);*/
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sVoucherUserRecordService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
