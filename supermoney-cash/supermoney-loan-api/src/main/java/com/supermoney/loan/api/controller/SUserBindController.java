package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.service.SUserBindService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import com.supermoney.loan.api.utils.UserUtils;
import com.supermoney.loan.api.utils.Verification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangwenchi on 2018/1/13.
 */
@RestController
@RequestMapping("/s/user-card-bind")
@Api(value = "/s/user-card-bind", description = "用户银行卡绑定相关接口")
public class SUserBindController {

    @Resource
    private SUserBindService userBindService;

    @ApiOperation("用户绑定银行卡列表")
    @PostMapping("/list")
    public Result getUserCardList(String appSecret) {
        Map<String, Object> map = new HashMap();
        map.put("userId", UserUtils.getCurrentUserId());
        List<SUserBindVo> lst = userBindService.getUserBindList(map);
        return ResultGenerator.genSuccessResult(lst);
    }

    @ApiOperation("取消绑定")
    @PostMapping("/user-card-cancel")
    public Result cancelUserCard(String appSecret, @RequestParam Integer id) {
        return userBindService.cancelUserBind(id,UserUtils.getCurrentUserId());
    }


    @ApiOperation("新增用户绑卡")
    @PostMapping("/user-card-add")
    public Result addUserCard(String appSecret,
                              @ApiParam(name = "bankId", value = "银行列表中银行的Id", required = true) @RequestParam Integer bankId,
                              @ApiParam(name = "cardNumber", value = "银行卡号", required = false) @RequestParam String cardNumber,
                              @ApiParam(name = "cardAccount", value = "银行账户", required = true) @RequestParam String cardAccount,
                              @ApiParam(name = "holdingName", value = "持卡人名称", required = false) @RequestParam String holdingName,
                              @ApiParam(name = "holdingCard", value = "持卡人身份证号码", required = false) @RequestParam String holdingCard,
                              @ApiParam(name = "holdPhone", value = "持卡人电话号码", required = false) @RequestParam String holdPhone) {

          return userBindService.userBindCard(UserUtils.getCurrentUserId(),bankId,cardNumber,cardAccount,holdingName,holdingCard,holdPhone);
    }


}
