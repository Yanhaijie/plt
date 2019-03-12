package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SUserRole;
import com.supers.p2p.assets.service.SUserRoleService;
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
* Created by wenyuhao on 2018/05/14.
*/
@RestController
@RequestMapping("/s/user/role")
@Api(value = "/s/user/role",description = "")
public class SUserRoleController {
    @Resource
    private SUserRoleService sUserRoleService;

    @PostMapping("/add")
    public Result add(SUserRole sUserRole) {
        sUserRoleService.save(sUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sUserRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUserRole sUserRole) {
        sUserRoleService.update(sUserRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SUserRole sUserRole = sUserRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sUserRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SUserRole> list = sUserRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
