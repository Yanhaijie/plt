package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.service.SRightService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/11.
*/
@RestController
@RequestMapping("/s/right")
@Api(value = "/s/right",description = "")
public class SRightController {
    @Resource
    private SRightService sRightService;

    @PostMapping("/add")
    public Result add(SRight sRight) {
        if(sRight.getParentId()==null){
            sRight.setParentId(0);
        }
        sRightService.save(sRight);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sRightService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRight sRight) {
        if(sRight.getParentId()==null){
            sRight.setParentId(0);
        }
        sRightService.update(sRight);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SRight sRight = sRightService.findById(id);
        return ResultGenerator.genSuccessResult(sRight);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=(Map<String,Object>)RequestUntil.getParams().get("search");
        param=param==null? new HashMap<>():param;
        PageInfo pageInfo=sRightService.getByPage(page,size,param);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取所有权限
     * @return
     */
    @PostMapping("/all")
    public Result all() {
        Map<String,Object> param=new HashMap<>();
        param.put("visibleType","0");
        List<SRight> list= sRightService.getList(param);
        return  ResultGenerator.genSuccessResult(list);
    }



}
