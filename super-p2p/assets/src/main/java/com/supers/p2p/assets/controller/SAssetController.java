package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SAsset;
import com.supers.p2p.assets.service.SAssetService;
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
* Created by wenyuhao on 2018/05/03.
*/
@RestController
@RequestMapping("/s/asset")
@Api(value = "/s/asset",description = "")
public class SAssetController {
    @Resource
    private SAssetService sAssetService;

    @PostMapping("/add")
    public Result add(SAsset sAsset) {
        sAssetService.save(sAsset);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sAssetService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SAsset sAsset) {
        sAssetService.update(sAsset);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SAsset sAsset = sAssetService.findById(id);
        return ResultGenerator.genSuccessResult(sAsset);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SAsset> list = sAssetService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
