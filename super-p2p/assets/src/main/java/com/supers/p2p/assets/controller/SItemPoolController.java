package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SItemPool;
import com.supers.p2p.assets.service.SItemPoolService;
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
* Created by wenyuhao on 2018/05/07.
*/
@RestController
@RequestMapping("/s/item/pool")
@Api(value = "/s/item/pool",description = "")
public class SItemPoolController {
    @Resource
    private SItemPoolService sItemPoolService;

    @PostMapping("/add")
    public Result add(SItemPool sItemPool) {
        sItemPoolService.save(sItemPool);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sItemPoolService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SItemPool sItemPool) {
        sItemPoolService.update(sItemPool);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SItemPool sItemPool = sItemPoolService.findById(id);
        return ResultGenerator.genSuccessResult(sItemPool);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SItemPool> list = sItemPoolService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
