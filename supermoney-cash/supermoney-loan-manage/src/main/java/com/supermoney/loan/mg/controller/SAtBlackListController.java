package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SAtBlackList;
import com.supermoney.loan.mg.service.SAtBlackListService;
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
* Created by wenyuhao on 2018/06/01.
*/
@RestController
@RequestMapping("/s/at/black/list")
@Api(value = "/s/at/black/list",description = "")
public class SAtBlackListController {
    @Resource
    private SAtBlackListService sAtBlackListService;

    @PostMapping("/add")
    public Result add(SAtBlackList sAtBlackList) {
        sAtBlackListService.save(sAtBlackList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sAtBlackListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SAtBlackList sAtBlackList) {
        sAtBlackListService.update(sAtBlackList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SAtBlackList sAtBlackList = sAtBlackListService.findById(id);
        return ResultGenerator.genSuccessResult(sAtBlackList);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SAtBlackList> list = sAtBlackListService.selectBlackListByParam((Map<String, Object>) param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
