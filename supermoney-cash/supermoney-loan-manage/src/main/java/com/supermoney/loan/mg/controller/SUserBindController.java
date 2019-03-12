package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.service.SUserBindService;
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
* Created by xionghuifeng on 2018/04/04.
*/
@RestController
@RequestMapping("/s/user/bind")
@Api(value = "/s/user/bind",description = "")
public class SUserBindController {
    @Resource
    private SUserBindService sUserBindService;

    @PostMapping("/add")
    public Result add(SUserBind sUserBind) {
        sUserBindService.save(sUserBind);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sUserBindService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUserBind sUserBind) {
        sUserBindService.update(sUserBind);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SUserBind sUserBind = sUserBindService.findById(id);
        return ResultGenerator.genSuccessResult(sUserBind);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sUserBindService.getUserBindList((Map<String,Object>)param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
