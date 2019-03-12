package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SRole;
import com.supers.p2p.assets.service.SRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.utils.UserUtils;
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
@RequestMapping("/s/role")
@Api(value = "/s/role",description = "")
public class SRoleController {
    @Resource
    private SRoleService sRoleService;

    @PostMapping("/add")
    public Result add(SRole sRole) {
        if(sRole.getParentId()==null){
            sRole.setParentId(0);
        }
        sRole.setCompanyId(UserUtils.getCurrentInfo().getCompanyId());
        sRoleService.save(sRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SRole sRole) {
        sRoleService.update(sRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SRole sRole = sRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=(Map<String,Object>)RequestUntil.getParams().get("search");
        param=param==null? new HashMap<>():param;
        param.put("companyId",UserUtils.getCurrentInfo().getCompanyId());
        PageInfo pageInfo=sRoleService.getByPage(page,size,param);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/company-role")
    public Result companyRole() {
        return sRoleService.companyRole(UserUtils.getCurrentInfo().getCompanyId());
    }

}
