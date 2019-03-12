package com.supermoney.loan.mg.controller;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.service.SAtIdentityService;
import com.supermoney.loan.mg.service.SUserService;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
/**
* Created by xionghuifeng on 2018/01/22.
*/
@RestController
@RequestMapping("/s/at/identity")
@Api(value = "/s/at/identity",description = "")
public class SAtIdentityController {
    @Resource
    private SAtIdentityService sAtIdentityService;

    @Resource
    private SUserService userService;

    @PostMapping("/add")
    public Result add(SAtIdentity sAtIdentity) {
        sAtIdentityService.save(sAtIdentity);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SAtIdentity sAtIdentity) {
        if (sAtIdentity.getIdentityStatus() == 3 || sAtIdentity.getIdentityStatus() == 4){
            //征信资料修改标记位
            Map<String, Object> param = new HashMap<>();
            param.put("userId",sAtIdentity.getUserId());
            param.put("creditModify",1);
            userService.updateCreditInfo(param);
        }
        return  sAtIdentityService.updateInfo(sAtIdentity);
    }

    @PostMapping("/status")
    public Result status(Integer identifyId,Integer status) {
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sAtIdentityService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sAtIdentityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/audits")
    public Result audits(@RequestParam Integer status,String[] ids){
        return sAtIdentityService.audits(status,ids);
    }
}
