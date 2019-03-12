package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SMessageUser;
import com.supermoney.loan.mg.service.SMessageUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/04/04.
*/
@RestController
@RequestMapping("/s/message/user")
@Api(value = "/s/message/user",description = "")
public class SMessageUserController {
    @Resource
    private SMessageUserService sMessageUserService;

    @PostMapping("/add")
    public Result add(SMessageUser sMessageUser) {
        sMessageUserService.save(sMessageUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sMessageUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMessageUser sMessageUser) {
        sMessageUserService.update(sMessageUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SMessageUser sMessageUser = sMessageUserService.findById(id);
        return ResultGenerator.genSuccessResult(sMessageUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        PageInfo pageInfo=sMessageUserService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 批量删除
     * @param idStr
     * @return
     */
    @PostMapping("/deleteByIds")
    public Result deleteByIds(@RequestParam String idStr){
        sMessageUserService.deleteByIds(idStr);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 移除，逻辑删除
     * @param sMessageUser
     * @return
     */
    @PostMapping("/remove")
    public Result remove(SMessageUser sMessageUser) {
        if(sMessageUser != null && StringUtils.isNotEmpty(sMessageUser.getId().toString())){
            sMessageUser.setDeleteStatus(1);
            sMessageUserService.update(sMessageUser);
        }
        return ResultGenerator.genSuccessResult();
    }
}
