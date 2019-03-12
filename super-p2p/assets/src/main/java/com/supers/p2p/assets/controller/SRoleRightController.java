package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SRoleRight;
import com.supers.p2p.assets.service.SRoleRightService;
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
* Created by wenyuhao on 2018/05/11.
*/
@RestController
@RequestMapping("/s/role/right")
@Api(value = "/s/role/right",description = "")
public class SRoleRightController {
    @Resource
    private SRoleRightService sRoleRightService;

    @PostMapping("/add")
    public Result add(SRoleRight sRoleRight) {
        sRoleRightService.save(sRoleRight);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sRoleRightService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRoleRight sRoleRight) {
        sRoleRightService.update(sRoleRight);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SRoleRight sRoleRight = sRoleRightService.findById(id);
        return ResultGenerator.genSuccessResult(sRoleRight);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SRoleRight> list = sRoleRightService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/user-right")
    public Result userRight(Integer roleId) {
        return sRoleRightService.userright(roleId);
    }

    @PostMapping("/save-right")
    public Result saveright(Integer roleId,String[] ids) {
        return  sRoleRightService.saveright(roleId,ids);
    }
}
