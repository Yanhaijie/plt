package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.service.UserAccountBussService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SUserCash;
import com.supermoney.loan.mg.service.SUserCashService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/04/02.
*/
@RestController
@RequestMapping("/s/user/cash")
@Api(value = "/s/user/cash",description = "")
public class SUserCashController {
    @Resource
    private SUserCashService sUserCashService;

    @PostMapping("/add")
    public Result add(SUserCash sUserCash) {
        sUserCashService.save(sUserCash);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sUserCashService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUserCash sUserCash) {
        sUserCashService.update(sUserCash);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SUserCash sUserCash = sUserCashService.findById(id);
        return ResultGenerator.genSuccessResult(sUserCash);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = sUserCashService.getCashCheckListByStatus((Map<String, Object>) param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/check")
    public Result check() {
        Map<String,Object> param=RequestUntil.getParams();
        try {
            String ids = (String) param.get("ids");
            List<SUserCash> cashList = sUserCashService.findByIds(ids);

            for (SUserCash cash: cashList) {
                if (cash.getCashStatus() != 0){
                    throw new RuntimeException("审核失败，存在已审核条目");
                }
            }

            param.put("ids",ids.split(","));

            sUserCashService.doCashCheck(param,cashList);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult("审核成功");
    }
}
