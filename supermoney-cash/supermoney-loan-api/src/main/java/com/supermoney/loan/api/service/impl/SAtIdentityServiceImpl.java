package com.supermoney.loan.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.dao.SAtIdentityMapper;
import com.supermoney.loan.api.dao.SUserMapper;
import com.supermoney.loan.api.entity.SAtIdentity;
import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.CreditVo;
import com.supermoney.loan.api.service.SAtCreditInformationService;
import com.supermoney.loan.api.service.SAtIdentityService;
import com.supermoney.loan.api.service.SBussLabelService;
import com.supermoney.loan.api.service.SCertRecordService;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
@Service
@Transactional
public class SAtIdentityServiceImpl extends AbstractService<SAtIdentity> implements SAtIdentityService {
    @Resource
    private SAtIdentityMapper sAtIdentityMapper;

    @Resource
    private SCertRecordService sCertRecordService;

    @Resource
    private SBussLabelService bussLabelService;

    @Resource
    private SUserMapper sUserMapper;

    @Resource
    private SAtCreditInformationService sAtCreditInformationService;

    @Resource
    private AppProperties appProperties;

    @Value("${baidu.apiKey}")
    private String baiduApiKey;

    @Value("${baidu.secretKey}")
    private String baiduSecretKey;

    @Value("${baidu.living.url}")
    private String baiduLivingUrl;

    @Value("${baidu.compare.url}")
    private String baiduCompareUrl;


    /**
     *提交身份认证信息
     * @param userId
     * @param name
     * @param identity
     * @param imgFront
     * @param imgBack
     * @return
     */
    public Result filingIdentity(Integer userId, String name, String identity, String imgFront, String imgBack){

        if(userId==null){
            return ResultGenerator.genFailResult("userid is null");
        }
        if(StringUtils.isBlank(name)){
            return ResultGenerator.genFailResult("name is null");
        }
        if(StringUtils.isBlank(imgFront)){
            return ResultGenerator.genFailResult("imgFront is null");
        }
        if(StringUtils.isBlank(imgBack)){
            return ResultGenerator.genFailResult("imgBack is null");
        }
        SUser user=sUserMapper.selectByPrimaryKey(userId);
        if(user==null){
            return ResultGenerator.genFailResult("user is null");
        }
        //过滤符号
        name=name.replaceAll("\"","");
        identity=identity.replaceAll("\"","");
        Map<String,Object> param=new HashMap();
        param.put("userId",userId);
        Integer hasIdentity=sAtIdentityMapper.hasIdentityTotal(param);
        if(hasIdentity.compareTo(0)>0){
            return ResultGenerator.genFailResult("has been submitted!");
        }

        SAtIdentity atIdentity=new SAtIdentity();
        atIdentity.setUserId(userId);
        atIdentity.setRealName(name);
        atIdentity.setIdNumber(identity);
        atIdentity.setImgFront(imgFront);
        atIdentity.setImgBack(imgBack);
        atIdentity.setIdentityStatus(0);//待审核
        atIdentity.setInfoSource("supermoney-app");
        sAtIdentityMapper.insert(atIdentity);

        return  ResultGenerator.genSuccessResult(atIdentity.getId());
    }


    /**
     * 征信身份认证
     * @return
     */
    @Override
    @Transactional
    public  Result  creditIdentity(Integer userId, String name, String identity, String idFrontImg, String peopleImg, InputStream front, InputStream hold){
        Result livingRs;

        try {
            Result tempResult = bussLabelService.getByNameNotSplit("identityCert");
            JSONObject temJsonObject = JSON.parseObject(tempResult.getData().toString());
            String checkLivePeople = (String) temJsonObject.get("checkLivePeople");
            String identityAndFaceContrast = (String) temJsonObject.get("identityAndFaceContrast");

            //认证资料
            String access_token = BaiduFaceCheckUtil.getAccessToken(baiduApiKey,baiduSecretKey);
            byte[] idFileByte = FileUtil.readFileByFileStream((FileInputStream) front);
            byte[] faceFileByte = FileUtil.readFileByFileStream((FileInputStream) hold);

            //活体验证
            Result tempRs;
            if (checkLivePeople.equals("1")){
                tempRs = null;
            }
            else {
                //活体检测
                tempRs = BaiduFaceCheckUtil.checkLivePeopleByByte(baiduLivingUrl,faceFileByte,access_token);
            }

            if (tempRs == null || tempRs.getCode() == ResultCode.SUCCESS.code){
                if (tempRs != null){
                    sCertRecordService.safeLivingCert(UserUtils.getCurrentUserId(),1);
                }

                //人证对比
                if (identityAndFaceContrast.equals("1")){
                    livingRs = null;
                }
                else {
                    livingRs = BaiduFaceCheckUtil.baiduCompareByByte(baiduCompareUrl,idFileByte,faceFileByte,access_token);
                }

                if (livingRs == null || livingRs.getCode()  == ResultCode.SUCCESS.code){
                    if (livingRs != null){
                        sCertRecordService.safeIdPeopleCompareCert(UserUtils.getCurrentUserId(),1);
                    }
                    else {
                        livingRs = ResultGenerator.genSuccessResult("");
                    }

                    //通过认证对比，添加身份资料认证
                    name=name.replaceAll("\"","");
                    identity=identity.replaceAll("\"","");

                    SAtIdentity sAtIdentity = new SAtIdentity();
                    sAtIdentity.setCertType(1);
                    sAtIdentity.setUserId(UserUtils.getCurrentUserId());
                    sAtIdentity.setRealName(name);
                    sAtIdentity.setIdNumber(identity);
                    sAtIdentity.setImgFront(idFrontImg);
                    sAtIdentity.setImgHold(peopleImg);
                    sAtIdentity.setIdentityStatus(0);//待审核
                    sAtIdentity.setInfoSource("supermoney-app");
                    sAtIdentity.setIdCheck(0);
                    this.save(sAtIdentity);

                    //更新征信档案
                    CreditVo vo=new CreditVo();
                    vo.setIdentityId(sAtIdentity.getId());
                    vo.setCurStep(2);
                    vo.setRealNamne(name);
                    vo.setIdNumber(identity);
                    vo.setIdFrontImg(idFrontImg);
                    vo.setIdHoldImg(peopleImg);
                    sAtCreditInformationService.comitCredit(vo,userId);
                }
                else {
                    sCertRecordService.safeIdPeopleCompareCert(UserUtils.getCurrentUserId(),0);
                }
            }
            else {
                livingRs = tempRs;
                sCertRecordService.safeLivingCert(UserUtils.getCurrentUserId(),0);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            livingRs = ResultGenerator.genFailResult("Check failure");
        }

        return  livingRs;
    }

    /**
     * 统计绑卡状态的数量
     * @param userId
     * @param status
     * @return
     */
    public  Integer countByStatus(Integer userId,Integer status){
        Map<String,Object> param=new HashMap(Constants.App.MAP_MIN_SIZE);
        param.put("userId",userId);
        param.put("status",status);
        return  sAtIdentityMapper.statusIdentityTotal(param);
    }

    @Override
    public SAtIdentity selectAtIdentityByUserId(Map<String, Object> param) {
        return sAtIdentityMapper.selectAtIdentityByUserId(param);
    }

    /**
     * 获取审核成功的实名认真
     * @param userId
     * @return
     */
    public  SAtIdentity getSuccessIdentity(Integer userId){
        SAtIdentity identity=new SAtIdentity();
        identity.setUserId(userId);
        identity.setIdentityStatus(3);
        return  sAtIdentityMapper.selectOne(identity);
    }

    /**
     * 获取认证信息
     * @param userId
     * @return
     */
    @Override
    public  Result getIdentityInfo(Integer userId){
        Result result = ResultGenerator.genSuccessResult();
        Map<String, Object> map = new HashMap<>();
        map.put("userId",UserUtils.getCurrentUserId());
        SAtIdentity sAtIdentity = selectAtIdentityByUserId(map);
        // sAtIdentity.getIdentityStatus() == 4 ||
        if (sAtIdentity != null && sAtIdentity.getIdentityStatus() != null && (sAtIdentity.getIdentityStatus() == 1 || sAtIdentity.getIdentityStatus() == 3 || (sAtIdentity.getIdentityStatus() == 0 && sAtIdentity.getCertType() != null && sAtIdentity.getCertType() == 1)) ){
            map.put("realName",sAtIdentity.getRealName());
            map.put("idNumber",sAtIdentity.getIdNumber());
            map.put("imgFront", sAtIdentity.getImgFront());
            map.put("imgHold", sAtIdentity.getImgHold());
            result.setData(map);
        }
        return result;
    }
}
