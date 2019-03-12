package com.supermoney.loan.mg.controller;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SUserPay;
import com.supermoney.loan.mg.service.SUserPayService;
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
* Created by wenyuhao on 2018/04/26.
*/
@RestController
@RequestMapping("/s/user/pay")
@Api(value = "/s/user/pay",description = "")
public class SUserPayController {
    @Resource
    private SUserPayService sUserPayService;

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sUserPayService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/check")
    public Result check() {
        Map<String,Object> param=RequestUntil.getParams();
        try {
            String ids = (String) param.get("ids");
            List<SUserPay> payList = sUserPayService.findByIds(ids);
            for (SUserPay cash: payList) {
                if (cash.getPayStatus() != 0){
                    throw new RuntimeException("审核失败，存在已审核条目");
                }
            }
            param.put("ids",ids.split(","));
            sUserPayService.doPayCheck(param);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult("审核成功");
    }
}
