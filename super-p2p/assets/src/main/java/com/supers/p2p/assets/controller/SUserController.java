package com.supers.p2p.assets.controller;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.entity.vo.SUserVo;
import com.supers.p2p.assets.entity.vo.UserInfo;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.service.SUserService;
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
* Created by wenyuhao on 2018/05/10.
*/
@RestController
@RequestMapping("/s/user")
@Api(value = "/s/user",description = "")
public class SUserController {
    @Resource
    private SUserService sUserService;

    @PostMapping("/add")
    public Result add(SUserVo sUser) {
        return  sUserService.addUser(sUser);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        SUser sUser = sUserService.findById(id);
        sUser.setUseStatus(1);
        sUserService.update(sUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SUserVo sUser) {
        return sUserService.addUser(sUser);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SUser sUser = sUserService.findById(id);
        return ResultGenerator.genSuccessResult(sUser);
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sUserService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 员工管理查询
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/staff-list")
    public Result listStaff(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=(Map<String,Object>)RequestUntil.getParams().get("search");
        param=param==null? new HashMap<>():param;
        CurrentInfo userinfo= UserUtils.getCurrentInfo();
        param.put("companyId",userinfo.getCompanyId());
        param.put("parentId",userinfo.getUserId());
        PageInfo pageInfo=sUserService.getByPage(page,size,param);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/staff-add")
    public Result addStaff(SUserVo sUser) {
        CurrentInfo userinfo= UserUtils.getCurrentInfo();
        sUser.setParentId(userinfo.getUserId());
        sUser.setCompanyId(userinfo.getCompanyId());
        return  sUserService.addUser(sUser);
    }

    @PostMapping("/staff-update")
    public Result updateStaff(SUserVo sUser) {
        sUser.setParentId(UserUtils.getCurrentInfo().getUserId());
        return sUserService.addUser(sUser);
    }
}
