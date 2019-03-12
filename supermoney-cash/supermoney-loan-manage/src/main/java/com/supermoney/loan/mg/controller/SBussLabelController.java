package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SBussLabel;
import com.supermoney.loan.mg.service.SBussLabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/02/11.
*/
@RestController
@RequestMapping("/s/buss/label")
@Api(value = "/s/buss/label",description = "")
public class SBussLabelController {
    @Resource
    private SBussLabelService sBussLabelService;

    @PostMapping("/add")
    public Result add(SBussLabel sBussLabel) {
        return  sBussLabelService.saveBussLable(sBussLabel);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sBussLabelService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SBussLabel sBussLabel) {
        return  sBussLabelService.saveBussLable(sBussLabel);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SBussLabel sBussLabel = sBussLabelService.findById(id);
        return ResultGenerator.genSuccessResult(sBussLabel);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SBussLabel> list = sBussLabelService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取lable
     * @param bussName
     * @return
     */
    @PostMapping("/bussname")
    public  Result getBussLable(@RequestParam String bussName){
        SBussLabel buss=sBussLabelService.findBy("bussName",bussName);
        return  ResultGenerator.genSuccessResult(buss);
    }

    /**
     * 获取app banner
     * @return
     */
    @PostMapping("/banners")
    public  Result getBanners(){
        return  sBussLabelService.getBanner();
    }
    /**
     * 设置app banner
     * @return
     */
    @PostMapping("/banner")
    public  Result  banner(@RequestParam String bannerStr){
        System.out.println(bannerStr);
        SBussLabel oldBussLable = sBussLabelService.getByName("banner");
        if (oldBussLable != null);
        SBussLabel bussLabel = new SBussLabel();
        bussLabel.setId(oldBussLable.getId());
        bussLabel.setBussName("banner");
        bussLabel.setBussVal(bannerStr);
        return  sBussLabelService.saveBussLable(bussLabel);
    }


}
