package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SBanner;
import com.supermoney.loan.mg.service.SBannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/03.
*/
@RestController
@RequestMapping("/s/banner")
@Api(value = "/s/banner",description = "")
public class SBannerController {
    @Resource
    private SBannerService sBannerService;

    @PostMapping("/add")
    public Result add(SBanner sBanner) {
        if (sBanner.getId() != null){
            sBannerService.updateBanner(sBanner);
        }
        else {
            sBanner.setPutPosition("homepage");
            sBannerService.save(sBanner);
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBannerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBanner sBanner) {
        sBannerService.update(sBanner);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBanner sBanner = sBannerService.findById(id);
        return ResultGenerator.genSuccessResult(sBanner);
    }

    @PostMapping("/onShelves")
    public Result onShelves(@RequestParam Integer id) {
        SBanner sBanner = sBannerService.findById(id);
        sBanner.setShelves((byte) 1);
        sBannerService.update(sBanner);
        return ResultGenerator.genSuccessResult(sBanner);
    }

    @PostMapping("/downShelves")
    public Result downShelves(@RequestParam Integer id) {
        SBanner sBanner = sBannerService.findById(id);
        sBanner.setShelves((byte) 0);
        sBannerService.update(sBanner);
        return ResultGenerator.genSuccessResult(sBanner);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,String areaCode) {
        PageHelper.startPage(page, size);
        Map<String,Object> param=new HashMap<>();
        param.put("putPosition","homepage");
        if(StringUtils.isNotEmpty(areaCode)){
            param.put("areaCode",areaCode);
        }
        List<Map<String, Object>> list = sBannerService.selectBannerByMap(param);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
