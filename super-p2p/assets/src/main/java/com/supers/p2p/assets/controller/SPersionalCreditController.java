package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SPersionalCredit;
import com.supers.p2p.assets.service.SPersionalCreditService;
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
* Created by wenyuhao on 2018/05/04.
*/
@RestController
@RequestMapping("/s/persional/credit")
@Api(value = "/s/persional/credit",description = "")
public class SPersionalCreditController {
    @Resource
    private SPersionalCreditService sPersionalCreditService;

    @PostMapping("/add")
    public Result add(SPersionalCredit sPersionalCredit) {
        sPersionalCreditService.save(sPersionalCredit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sPersionalCreditService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SPersionalCredit sPersionalCredit) {
        sPersionalCreditService.update(sPersionalCredit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SPersionalCredit sPersionalCredit = sPersionalCreditService.findById(id);
        return ResultGenerator.genSuccessResult(sPersionalCredit);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SPersionalCredit> list = sPersionalCreditService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
