package com.supermoney.loan.api.controller;

import com.netflix.discovery.converters.Auto;
import com.supermoney.loan.api.entity.SAtCreditInformation;
import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.entity.SAtLivingInfo;
import com.supermoney.loan.api.entity.vo.CreditVo;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xionghuifeng on 2018/01/08.
 */
@RestController
@RequestMapping("/s/user")
@Api(value = "/s/user", description = "用户账号信息相关接口")
public class SUserController {
    private static final Logger logger = LoggerFactory.getLogger(SUserController.class);

    @Resource
    private SUserService sUserService;
    @Resource
    private SUserFundService userFundService;
    @Resource
    private SAtIdentityService sAtIdentityService;
    @Resource
    private SAppLogService sAppLogService;

    @Resource
    private SBountyService sBountyService;

    @Resource
    private SXenditPayService xenditPayService;
    @Resource
    private SUserBindService userCardService;
    @Resource
    private SUserAccountService accountService;
    @Resource
    private SAccountBalanceService balanceService;
    @Resource
    private  SAtCreditInformationService sAtCreditInformationService;

    @Autowired
    private FileServer fileServer;

    @Autowired
    private SAtLivingInfoService sAtLivingInfoService;

    @Autowired
    private SCertRecordService sCertRecordService;

    /**
     * 获取用户信息
     *
     * @param appSecret
     * @return
     */
    @PostMapping("/userinfo")
    @ApiOperation("获取用户信息")
    public Result userinfo(String appSecret) {
        return sUserService.getUserInfoVo(UserUtils.getCurrentUserId());
    }

    @PostMapping("/countinfo")
    @ApiOperation("获取用户统计信息")
    public Result countinfo(String appSecret) {
        return sUserService.getUserCount(UserUtils.getCurrentUserId());
    }

    /**
     * 获取用户信息
     *
     * @param userinfo
     * @return
     */
    @PostMapping("/save-userinfo")
    @ApiOperation("保存用户信息")
    public Result saveUserinfo(UserInfoVo userinfo) {
        return sUserService.saveUserInfo(userinfo);
    }



    @PostMapping("/identity")
    @ApiOperation("实名认证")
    public Result authentication(@RequestParam(value = "front")MultipartFile front ,@RequestParam(value = "back") MultipartFile back, @RequestParam String name, @RequestParam String identity, String appSecret) {
        return  ResultGenerator.genFailResult(" Tidak dapat mengunggah, harap update aplikasi!");
    }

    /**
     * 上传用户设备信息
     *
     * @param deviceInfo
     * @return
     */
    @PostMapping("/device-info")
    @ApiOperation("用户设备信息")
    public Result userDevice(String deviceInfo, String appSecret) {
        Integer userid=appSecret.isEmpty()? 0:UserUtils.getCurrentUserId();
        return sAppLogService.filingLog(userid, "device-info", deviceInfo);
    }

    /**
     * app 日志上传
     *
     * @param logInfo
     * @return
     */
    @PostMapping("/app-log")
    @ApiOperation("appLog信息上传")
    public Result appLog(String logInfo, String appSecret) {
        Integer userid=appSecret.isEmpty()? 0:UserUtils.getCurrentUserId();
        return sAppLogService.filingLog(userid, "app-log", logInfo);
    }

    /**
     * track 日志上传
     * @param trackInfo
     * @param appSecret
     * @return
     */
    @PostMapping("/track-log")
    @ApiOperation("track信息上传")
    public  Result trackLog(String trackInfo,String appSecret){

        return  null;
    }

    @ApiOperation("获取用户个人账户金额信息")
    @PostMapping("/funds")
    public Result getUserAccount(String appSecret) {
        return userFundService.getUserAccountEntity(UserUtils.getCurrentUserId());
    }

    @ApiOperation("分享superMoney")
    @GetMapping("/shar-supermoney")
    public  Result sharSuperMoney(String appSecret){
        Integer userId=StringUtils.isBlank(appSecret) ? null:UserUtils.getCurrentUserId();
        return   sBountyService.sharSuperMoney(userId);
    }

    @ApiOperation("获取认证信息")
    @PostMapping("/get-identity-info")
    public Result getIdentityInfo(String appSecret){
        return  sAtIdentityService.getIdentityInfo(UserUtils.getCurrentUserId());
    }

    @PostMapping("/credit-identity")
    @ApiOperation("征信实名认证")
    public Result creditIdentity(@RequestParam(value = "front")MultipartFile front ,
                                 @RequestParam(value = "hold") MultipartFile hold,
                                 @RequestParam String name,
                                 @RequestParam String identity,
                                 String appSecret) {

        if(StringUtils.isBlank(identity)){return ResultGenerator.genFailResult("identity is null"); }
        if(StringUtils.isBlank(name)){return ResultGenerator.genFailResult("name is null");}
        if(front==null || hold==null){ return  ResultGenerator.genFailResult(" have two img!");}

        Map<String, Object> param = new HashMap<>();
        param.put("userId",UserUtils.getCurrentUserId());
        param.put("certType", 1);
        SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(param);
        if (sAtIdentity != null){
            if (sAtIdentity.getIdentityStatus() == 0){
                return ResultGenerator.genSuccessResult("审核中，请等待");
            }
            else if (sAtIdentity.getIdentityStatus() == 3){
                return ResultGenerator.genSuccessResult("已通过认证不需要重复提交");
            }
        }

        Result frontRs=fileServer.fileUpdate(front,"img");
        Result backRs=fileServer.fileUpdate(hold,"img");

        if(frontRs.getCode()!= ResultCode.SUCCESS.code){
            return  frontRs;
        }
        if(backRs.getCode()!=ResultCode.SUCCESS.code){
            return  backRs;
        }

        String idFrontImg =((Map<String,String>)frontRs.getData()).get("fileName");
        String peopleImg =((Map<String,String>)backRs.getData()).get("fileName");

        InputStream frontInputStream = null;
        InputStream holdInputStream = null;
        try {
            frontInputStream = front.getInputStream();
            holdInputStream = hold.getInputStream();
        }
        catch (Exception e){
            return ResultGenerator.genFailResult("check fail");
        }

        return sAtIdentityService.creditIdentity(UserUtils.getCurrentUserId(),name,identity,idFrontImg,peopleImg,frontInputStream,holdInputStream);
    }

    @ApiOperation("征信资料")
    @PostMapping("/credit-comit")
    public  Result creditComit( CreditVo vo, String appSecret){
        return  sAtCreditInformationService.comitCredit(vo,UserUtils.getCurrentUserId());
    }

    @ApiOperation(value = "征信资料")
    @PostMapping("/credit-info")
    public  ResultT<CreditVo> creditInfo(String appSecret){
        return  new ResultT<CreditVo>(sAtCreditInformationService.CreditInfo(UserUtils.getCurrentUserId()));
    }

    @PostMapping("/credit-commit-work1")
    @ApiOperation("征信资料-工作资料")
    public Result creditCommitWork1(
                                 @RequestParam(value = "workCardImg", required=false) MultipartFile workCardImg,
                                 @RequestParam(value = "salaryCertImg", required=false)MultipartFile salaryCertImg,
                                 @RequestParam(value = "companyAffidavitImg", required=false) MultipartFile companyAffidavitImg,
                                 @RequestParam(value = "bankStatementImg", required=false)MultipartFile bankStatementImg,
                                 @RequestParam(value = "otherWorkImg", required=false) MultipartFile otherWorkImg,
                                 @RequestParam(value = "accountImg", required=false)MultipartFile accountImg,
                                 @RequestParam(value = "dayHistoryImg", required=false) MultipartFile dayHistoryImg,
                                 @RequestParam(value = "weekHistoryImg", required=false)MultipartFile weekHistoryImg,
                                 @RequestParam(value = "monthHistoryImg", required=false) MultipartFile monthHistoryImg,
                                 String isComit,
                                 String appSecret) {
        SAtCreditInformation information = sAtCreditInformationService.getByUserId(UserUtils.getCurrentUserId());
        if (information == null){
            information = new SAtCreditInformation();
            information.setUserId(UserUtils.getCurrentUserId());
            information.setCreditStatus(0);
            information.setCurStep(6);
            isComit = "false";
        }
        if (workCardImg == null && salaryCertImg == null && companyAffidavitImg == null && bankStatementImg == null && otherWorkImg == null && accountImg == null && dayHistoryImg == null && weekHistoryImg == null && monthHistoryImg == null){
            return ResultGenerator.genFailResult("Surat Keterangan Bekerja belum diunggah!");
        }

        if (workCardImg != null){Result result=fileServer.fileUpdate(workCardImg,"img");information.setWorkCardImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (salaryCertImg != null){Result result=fileServer.fileUpdate(salaryCertImg,"img");information.setSalaryCertImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (companyAffidavitImg != null){Result result=fileServer.fileUpdate(companyAffidavitImg,"img");information.setCompanyAffidavitImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (bankStatementImg != null){Result result=fileServer.fileUpdate(bankStatementImg,"img");information.setBankStatementImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (otherWorkImg != null){Result result=fileServer.fileUpdate(otherWorkImg,"img");information.setOtherWorkImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (accountImg != null){Result result=fileServer.fileUpdate(accountImg,"img");information.setAccountImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (dayHistoryImg != null){Result result=fileServer.fileUpdate(dayHistoryImg,"img");information.setDayHistoryImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (weekHistoryImg != null){Result result=fileServer.fileUpdate(weekHistoryImg,"img");information.setWeekHistoryImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (monthHistoryImg != null){Result result=fileServer.fileUpdate(monthHistoryImg,"img");information.setMonthHistoryImg(((Map<String,String>)result.getData()).get("fileName"));}

        if (isComit.equals("true")){
            Map<String, Object> identityParam = new HashMap<>();
            identityParam.put("userId",UserUtils.getCurrentUserId());
            SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(identityParam);
            if (sAtIdentity == null || sAtIdentity.getCertType() == 0){
                information.setCreditStatus(0);
            }
            else {
                information.setCreditStatus(6);
            }
        }

        return sAtCreditInformationService.comitWorkCredit(information);
    }

    @PostMapping("/credit-commit-work2")
    @ApiOperation("征信资料-工作资料")
    public Result creditCommitWork2(
            @RequestParam(value = "BPJSImg", required=false)MultipartFile BPJSImg,
            @RequestParam(value = "creditCardImg", required=false)MultipartFile creditCardImg,
            @RequestParam(value = "KKImg", required=false) MultipartFile KKImg,
            String appSecret) {
        SAtCreditInformation information = sAtCreditInformationService.getByUserId(UserUtils.getCurrentUserId());
        if (information == null){
            return ResultGenerator.genFailResult("Surat Keterangan Bekerja belum diunggah!");
        }

        if (information.getWorkCardImg() == null && information.getSalaryCertImg() == null && information.getCompanyAffidavitImg() == null && information.getBankStatementImg() == null && information.getOtherWorkImg() == null && information.getAccountImg() == null && information.getDayHistoryImg() == null && information.getWeekHistoryImg() == null && information.getMonthHistoryImg() == null){
            return ResultGenerator.genFailResult("Surat Keterangan Bekerja belum diunggah!");
        }

        if (BPJSImg == null && creditCardImg == null && KKImg == null){
            return ResultGenerator.genSuccessResult();
        }

        if (BPJSImg != null){Result result=fileServer.fileUpdate(BPJSImg,"img");information.setBPJSImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (creditCardImg != null){Result result=fileServer.fileUpdate(creditCardImg,"img");information.setCreditCardImg(((Map<String,String>)result.getData()).get("fileName"));}
        if (KKImg != null){Result result=fileServer.fileUpdate(KKImg,"img");information.setKKImg(((Map<String,String>)result.getData()).get("fileName"));}

        return sAtCreditInformationService.comitWorkCredit(information);
    }

    @ApiOperation(value = "是否征信通过")
    @PostMapping("/has-credit")
    public  Result hasCredit(String appSecret){
        return  sAtCreditInformationService.hasCredit(UserUtils.getCurrentUserId());
    }

    @ApiOperation(value = "征信信用等级和状态")
    @PostMapping("/credit-grade-status")
    public  Result creditGradeStatus(String appSecret){
        return  sAtCreditInformationService.getUserGrade(UserUtils.getCurrentUserId());
    }

}
