package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.service.SBountyService;
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
* Created by xionghuifeng on 2018/01/04.
*/
@RestController
@RequestMapping("/s/bounty")
@Api(value = "/s/bounty",description = "")
public class SBountyController {
    @Resource
    private SBountyService sBountyService;

    @PostMapping("/add")
    public Result add(SBounty sBounty) {
        sBountyService.save(sBounty);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBountyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBounty sBounty) {
        sBountyService.update(sBounty);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBounty sBounty = sBountyService.findById(id);
        return ResultGenerator.genSuccessResult(sBounty);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sBountyService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/drop")
    public Result items(String isApi){
        return ResultGenerator.genSuccessResult(sBountyService.getDrop(isApi));
    }


}
