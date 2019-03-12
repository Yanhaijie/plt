package com.supermoney.loan.mg.controller;

import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.service.SUserService;
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
* Created by xionghuifeng on 2018/01/13.
*/
@RestController
@RequestMapping("/s/user")
@Api(value = "/s/user",description = "")
public class SUserController {
    @Resource
    private SUserService sUserService;

    @Resource
    private SVoucherUserRecordService sVoucherUserRecordService;

    @PostMapping("/add")
    public Result add(SUser sUser) {
        sUserService.save(sUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUser sUser) {
        sUserService.update(sUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SUser sUser = sUserService.findById(id);
        return ResultGenerator.genSuccessResult(sUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
/*        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SUser> list = sUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);*/
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sUserService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/voucher")
    public Result voucher(@RequestParam Integer method,@RequestParam Integer voucherId,String[] ids){
        return sVoucherUserRecordService.userVoucher(method,voucherId,ids);
    }
}
