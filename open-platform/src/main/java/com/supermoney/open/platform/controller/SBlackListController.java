package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SBlackList;
import com.supermoney.open.platform.service.SBlackListService;
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
@RequestMapping("/mg/black/list")
@Api(value = "/mg/black/list",description = "")
public class SBlackListController {
    @Resource
    private SBlackListService sBlackListService;

    @PostMapping("/add")
    public Result add(SBlackList sBlackList) {
        sBlackListService.save(sBlackList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBlackListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBlackList sBlackList) {
        sBlackListService.update(sBlackList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBlackList sBlackList = sBlackListService.findById(id);
        return ResultGenerator.genSuccessResult(sBlackList);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SBlackList> list = sBlackListService.selectBlackListByParam((Map<String,Object>) param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
