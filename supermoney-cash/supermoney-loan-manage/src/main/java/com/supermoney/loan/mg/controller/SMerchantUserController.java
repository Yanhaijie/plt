package com.supermoney.loan.mg.controller;
import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.service.SBountyService;
import com.supermoney.loan.mg.utils.BussCodeGenerate;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.RequestUntil;
import com.supermoney.loan.mg.utils.ResultGenerator;
import com.supermoney.loan.mg.entity.SMerchantUser;
import com.supermoney.loan.mg.service.SMerchantUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by bear on 2018/08/06.
*/
@RestController
@RequestMapping("/s/merchant/user")
@Api(value = "/s/merchant/user",description = "")
public class SMerchantUserController {
    @Resource
    private SMerchantUserService sMerchantUserService;

    @Resource
    private SBountyService sBountyService;

    @PostMapping("/addOrUpdate")
    public Result add(SMerchantUser sMerchantUser) {
        if (sMerchantUser.getId() == null){
            //todo MerchantId 和 key 及 token
            sMerchantUser.setMerchantId(UUID.randomUUID().toString().replace("-",""));
            sMerchantUser.setSecretKey(BussCodeGenerate.generateSecret());
            sMerchantUser.setTestSecretKey(BussCodeGenerate.generateSecret());
            sMerchantUser.setValidationoToken(BussCodeGenerate.generateToken());
            sMerchantUser.setTestValidationoToken(BussCodeGenerate.generateToken());
            sMerchantUserService.save(sMerchantUser);
        }
        else {
            sMerchantUserService.update(sMerchantUser);
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        if (id != null) {
            Map<String, Object> param = new HashMap<>();
            param.put("merchantId", id);
            List<SBounty> list = sBountyService.getList(param);
            if (list != null && list.size() > 0){
                return ResultGenerator.genFailResult("该商户存在借款产品， 不能删除");
            }

            sMerchantUserService.deleteById(id);
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SMerchantUser sMerchantUser) {
        sMerchantUserService.update(sMerchantUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        SMerchantUser sMerchantUser = sMerchantUserService.findById(id);
        return ResultGenerator.genSuccessResult(sMerchantUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SMerchantUser> list = sMerchantUserService.selectByParam((Map) param.get("search"));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/drop")
    public Result drop() {
        return ResultGenerator.genSuccessResult(sMerchantUserService.selectDrop());
    }
}
