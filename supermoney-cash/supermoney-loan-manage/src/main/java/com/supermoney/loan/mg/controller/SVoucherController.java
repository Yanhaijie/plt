package com.supermoney.loan.mg.controller;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SVoucher;
import com.supermoney.loan.mg.service.SVoucherService;
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
@RequestMapping("/s/voucher")
@Api(value = "/s/voucher",description = "")
public class SVoucherController {
    @Resource
    private SVoucherService sVoucherService;

    @PostMapping("/add")
    public Result add(SVoucher sVoucher) {
        sVoucherService.save(sVoucher);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sVoucherService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SVoucher sVoucher) {
        sVoucherService.update(sVoucher);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SVoucher sVoucher = sVoucherService.findById(id);
        return ResultGenerator.genSuccessResult(sVoucher);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sVoucherService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/drop")
    public Result drop() {
        return sVoucherService.getDrop();
    }
}
