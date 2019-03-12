package com.supermoney.loan.mg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.STransfertoTopupFee;
import com.supermoney.loan.mg.service.STransfertoTopupFeeService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/07/18.
*/
@RestController
@RequestMapping("/s/transferto/topup/fee")
@Api(value = "/s/transferto/topup/fee",description = "")
public class STransfertoTopupFeeController {
    @Resource
    private STransfertoTopupFeeService sTransfertoTopupFeeService;

    @PostMapping("/add")
    public Result add(STransfertoTopupFee sTransfertoTopupFee) {
        sTransfertoTopupFeeService.save(sTransfertoTopupFee);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sTransfertoTopupFeeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(STransfertoTopupFee sTransfertoTopupFee) {
        sTransfertoTopupFeeService.update(sTransfertoTopupFee);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        STransfertoTopupFee sTransfertoTopupFee = sTransfertoTopupFeeService.findById(id);
        return ResultGenerator.genSuccessResult(sTransfertoTopupFee);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        //List<STransfertoTopupFee> list = sTransfertoTopupFeeService.selectList(tempParam);
        List<STransfertoTopupFee> list =null;
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/topUpHisList")
    public Result topUpHisList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        Map<String,Object> tempParam = (Map<String,Object>)param.get("search");
        List< Map<String,Object>> list = sTransfertoTopupFeeService.topUpHistList(tempParam);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/save")
    public Result save(STransfertoTopupFee sTransfertoTopupFee) {
        if(sTransfertoTopupFee.getId() != null){
            sTransfertoTopupFeeService.update(sTransfertoTopupFee);
        }else{
            sTransfertoTopupFeeService.save(sTransfertoTopupFee);
        }
        return ResultGenerator.genSuccessResult();
    }
}
