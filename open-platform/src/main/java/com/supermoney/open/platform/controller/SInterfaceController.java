package com.supermoney.open.platform.controller;
import com.supermoney.open.platform.utils.Result;
import com.supermoney.open.platform.utils.RequestUntil;
import com.supermoney.open.platform.utils.ResultGenerator;
import com.supermoney.open.platform.entity.SInterface;
import com.supermoney.open.platform.service.SInterfaceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by @author on 2018/10/10.
*/
@RestController
@RequestMapping("/mg/interface")
@Api(value = "/mg/interface",description = "")
public class SInterfaceController {
    @Resource
    private SInterfaceService sInterfaceService;

    @PostMapping("/save")
    public Result add(SInterface sInterface) {
        if (sInterface.getId() == null){
            sInterfaceService.save(sInterface);
        }
        else {
            sInterfaceService.update(sInterface);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sInterfaceService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SInterface sInterface = sInterfaceService.findById(id);
        return ResultGenerator.genSuccessResult(sInterface);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SInterface> list = sInterfaceService.selectInterfaceListByParam((Map<String,Object>)param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/list/all")
    public Result listAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SInterface> list = sInterfaceService.findAll();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (SInterface sInterface : list){
            Map<String, Object> map = new HashMap<>();
            map.put("label",sInterface.getInterfaceName());
            map.put("value",sInterface.getId());
            resultList.add(map);
        }
        return ResultGenerator.genSuccessResult(resultList);
    }
}
