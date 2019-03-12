package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAtCreditInformationMapper;
import com.supermoney.loan.api.entity.*;
import com.supermoney.loan.api.entity.vo.CreditVo;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.service.*;
import com.supermoney.loan.api.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/25.
 */
@Service
@Transactional
public class SAtCreditInformationServiceImpl extends AbstractService<SAtCreditInformation> implements SAtCreditInformationService {
    private static final Logger logger = LoggerFactory.getLogger(SAtCreditInformationServiceImpl.class);

    @Resource
    private SAtCreditInformationMapper sAtCreditInformationMapper;
    @Resource
    private SAtIdentityService sAtIdentityService;
    @Resource
    private SUserBindService sUserBindService;
    @Resource
    private SExchangeRateService sExchangeRateService;
    @Resource
    private SUserService sUserService;
    @Resource
    private SAtCreditGradeService sAtCreditGradeService;


    /**
     * 提交征信资料
     * @param vo
     * @return
     */
    public Result comitCredit(CreditVo vo, Integer userId){

        if(userId==null){
            return  ResultGenerator.genSuccessResult("user is null");
        }
        //用户是否已存在征信档案
        SAtCreditInformation eo=new SAtCreditInformation();

        eo.setCurStep(vo.getCurStep());
        //setp1-身份认证
        if (vo.getCurStep() == 2){
            eo.setRealName(vo.getRealNamne());
            eo.setIdNumber(vo.getIdNumber());
            eo.setIdFrontImg(vo.getIdFrontImg());
            eo.setIdHoldImg(vo.getIdHoldImg());
            eo.setIdentityId(vo.getIdentityId());
        }

        //setp2
        eo.setBirthday(vo.getBirthday());
        eo.setGender(vo.getGender());
        eo.setMarried(vo.getMarried());
        eo.setReligion(vo.getReligion());
        eo.setEducation(vo.getEducation());
        eo.setLiveAddress(vo.getLiveAddress());
        eo.setLiveAddressStreet(vo.getLiveAddressStreet());
        eo.setChildren(vo.getChildren());
        //setp3
        eo.setProfession(vo.getProfession());
        if(vo.getProfession() != null && vo.getProfession().compareTo(Constants.Credit.CREDIT_P_WHITE)==0){
            //setp3-1
            eo.setCompanyName(vo.getCompanyName());
            eo.setIndustry(vo.getIndustry());
            eo.setPosition(vo.getPosition());
            eo.setMonthIncome(vo.getMonthIncome());
            eo.setCompanyPhone(vo.getCompanyPhone());
            eo.setCompanyAddress(vo.getCompanyAddress());
            eo.setCompanyAddressStreet(vo.getCompanyAddressStreet());
        }
        if(vo.getProfession() != null && vo.getProfession().compareTo(Constants.Credit.CREDIT_P_BUSINESSMAN)==0){
            //setp3-2
            eo.setCompanyName(vo.getCompanyName());
            eo.setIndustry(vo.getIndustry());
            eo.setCompanyPersons(vo.getCompanyPersons());
            eo.setMonthIncome(vo.getMonthIncome());
            eo.setCompanyPhone(vo.getCompanyPhone());
            eo.setCompanyAddress(vo.getCompanyAddress());
            eo.setCompanyAddressStreet(vo.getCompanyAddressStreet());
        }
        if(vo.getProfession() != null && vo.getProfession().compareTo(Constants.Credit.CREDIT_P_WITHOUT)==0){
            //setp3-3
            eo.setNotJobTime(vo.getNotJobTime());
            eo.setLastIndustry(vo.getLastIndustry());
            eo.setLastIncome(vo.getLastIncome());
            eo.setNotJobSource(vo.getNotJobSource());
            eo.setNotJobIncome(vo.getNotJobIncome());
        }
        if(vo.getProfession() != null && vo.getProfession().compareTo(Constants.Credit.CREDIT_P_STUDENT)==0){
            //setp3-4
            eo.setSchoolName(vo.getSchoolName());
            eo.setSchoolCourse(vo.getSchoolCourse());
            eo.setSchoolIn(vo.getSchoolIn());
            eo.setSchoolOut(vo.getSchoolOut());
        }
        //setp4
        eo.setRelativeType(vo.getRelativeType());
        eo.setRelativeName(vo.getRelativeName());
        eo.setRelativePhone(vo.getRelativePhone());
        eo.setUrgentName(vo.getUrgentName());
        eo.setUrgentPhone(vo.getUrgentPhone());
        //setp5
        eo.setCardName(vo.getCardName());
        eo.setCardId(vo.getCardId());
        eo.setCardBank(vo.getCardBank());
        eo.setCardAccount(vo.getCardAccount());
        eo.setCurStep(vo.getCurStep());


        SAtCreditInformation creditInfo=getByUserId(userId);
        if(creditInfo!=null){
            eo.setCreditStatus(creditInfo.getCreditStatus());
            //暂存和审核不通过时-》更新到提交审核
            if( (eo.getCreditStatus().compareTo(0)==0 || eo.getCreditStatus().compareTo(2)==0) && vo.getIsComit()!=null &&vo.getIsComit().equals("true") ){
                eo.setCreditStatus(1);
            }
            eo.setId(creditInfo.getId());
            sAtCreditInformationMapper.updateByPrimaryKeySelective(eo);
        }else {
            Integer status=vo.getIsComit()!=null && vo.getIsComit().equals("true")? 1:0;
            eo.setUserId(userId);
            eo.setCreditStatus(status);
            Integer step=vo.getCurStep().compareTo(0)>0 ? vo.getCurStep():0;
            eo.setCurStep(step);
            sAtCreditInformationMapper.insert(eo);
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result comitWorkCredit(SAtCreditInformation information) {
        if (information.getId() == null){
            sAtCreditInformationMapper.insert(information);
        }
        else {
            sAtCreditInformationMapper.updateByPrimaryKeySelective(information);
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取用户征信资料
     * @param userId
     * @return
     */
    public Result  CreditInfo(Integer userId){
        SAtCreditInformation creditInfo=getByUserId(userId);
       if (creditInfo==null){
           creditInfo=new SAtCreditInformation();
       }
       else {
           if (StringUtils.isNotBlank(creditInfo.getIdFrontImg())){
               creditInfo.setIdFrontImg( creditInfo.getIdFrontImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getIdHoldImg())){
               creditInfo.setIdHoldImg(creditInfo.getIdHoldImg());
           }

           //工作资料图片信息
           if (StringUtils.isNotBlank(creditInfo.getBPJSImg())){
               creditInfo.setBPJSImg(creditInfo.getBPJSImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getKKImg())){
               creditInfo.setKKImg( creditInfo.getKKImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getCreditCardImg())){
               creditInfo.setCreditCardImg(creditInfo.getCreditCardImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getWorkCardImg())){
               creditInfo.setWorkCardImg(creditInfo.getWorkCardImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getSalaryCertImg())){
               creditInfo.setSalaryCertImg(creditInfo.getSalaryCertImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getCompanyAffidavitImg())){
               creditInfo.setCompanyAffidavitImg(creditInfo.getCompanyAffidavitImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getBankStatementImg())){
               creditInfo.setBankStatementImg(creditInfo.getBankStatementImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getOtherWorkImg())){
               creditInfo.setOtherWorkImg(creditInfo.getOtherWorkImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getAccountImg())){
               creditInfo.setAccountImg(creditInfo.getAccountImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getDayHistoryImg())){
               creditInfo.setDayHistoryImg( creditInfo.getDayHistoryImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getWeekHistoryImg())){
               creditInfo.setWeekHistoryImg(creditInfo.getWeekHistoryImg());
           }
           if (StringUtils.isNotBlank(creditInfo.getMonthHistoryImg())){
               creditInfo.setMonthHistoryImg( creditInfo.getMonthHistoryImg());
           }


           Map<String, Object> map = new HashMap<>();
           map.put("userId",UserUtils.getCurrentUserId());
           SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(map);
           if (sAtIdentity != null && sAtIdentity.getIdentityStatus() != null && (sAtIdentity.getIdentityStatus() == 1 || sAtIdentity.getIdentityStatus() == 3 || (sAtIdentity.getIdentityStatus() == 0 && sAtIdentity.getCertType() != null && sAtIdentity.getCertType() == 1)) ){
               creditInfo.setRealName(sAtIdentity.getRealName());
               creditInfo.setIdNumber(sAtIdentity.getIdNumber());
               creditInfo.setIdFrontImg(sAtIdentity.getImgFront());
               creditInfo.setIdHoldImg(sAtIdentity.getImgHold()==null? null:(sAtIdentity.getImgHold()));
           }
           //设置0 为可提交状态
           if (sAtIdentity != null){
               //审核中
               if (sAtIdentity.getIdentityStatus() == 0){
                   if (sAtIdentity.getCertType() != null && sAtIdentity.getCertType() == 1){
                       creditInfo.setCheckStatus(1);
                   }
                   else {
                       creditInfo.setCheckStatus(0);
                   }
               }
               //通过
               else if (sAtIdentity.getIdentityStatus() == 3){
                   creditInfo.setCheckStatus(2);
               }
               //拒绝
               else if (sAtIdentity.getIdentityStatus() == 4){
                   creditInfo.setCheckStatus(0);
               }
               else {
                   creditInfo.setCheckStatus(0);
               }
           }
           else {
               creditInfo.setCheckStatus(0);
           }
       }
       return  ResultGenerator.genSuccessResult(creditInfo);
    }
    /**
     * 是否征信通过
     * @param userId
     * @return
     */
    public Result  hasCredit(Integer userId){
        Map<String,String> data=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        SAtCreditInformation creditInfo=getByUserId(userId);
        String isCredit=creditInfo==null? "0":creditInfo.getCreditStatus().toString();
        data.put("isCredit",isCredit);
        return  ResultGenerator.genSuccessResult(data);
    }
    /**
     * 根据用户获取
     * @param userId
     * @return
     */
    @Override
    public SAtCreditInformation getByUserId(Integer userId){
        SAtCreditInformation param=new SAtCreditInformation();
        param.setUserId(userId);
        return   sAtCreditInformationMapper.selectOne(param);
    }

    /**
     * 用户评级
     * @return
     */
    @Override
    public void gradeForUser(Integer userId){
        UserInfoVo user = (UserInfoVo)sUserService.getUserInfo(userId).getData();
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        //Super Uang注册查询 F
        int creditGrade = Constants.CreditGrade.GRADE_F;

        //实名活体认证查询 E
        Map<String, Object> identityParam = new HashMap<>();
        identityParam.put("userId",userId);
        SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(identityParam);
        if (sAtIdentity != null && sAtIdentity.getIdentityStatus() == 3){
            creditGrade = Constants.CreditGrade.GRADE_E;
        }
        else {
            if (creditGrade != user.getCreditGrade()){
                param.put("creditGrade",creditGrade);
            }
            param.put("creditModify",0);
            sUserService.updateCreditInfo(param);
            return;
        }

        //完成绑卡查询 D
        Map<String, Object> bindParam = new HashMap<>();
        bindParam.put("userId", userId);
        List<SUserBindVo> bindList = sUserBindService.getUserBindList(bindParam);
        if (bindList != null || bindList.size() > 0){
            creditGrade = Constants.CreditGrade.GRADE_D;
        }
        else {
            if (creditGrade != user.getCreditGrade()){
                param.put("creditGrade",creditGrade);
            }
            param.put("creditModify",0);
            sUserService.updateCreditInfo(param);
            return;
        }

        //本期暂不完成
        //授权访问通讯录查询
        //命中黑名单查询

        if (creditGrade != user.getCreditGrade()){
            param.put("creditGrade",creditGrade);
        }
        param.put("creditModify",0);
        sUserService.updateCreditInfo(param);
    }

    /**
     * 获取等级认证信息
     * @return
     */
    @Override
    public Result getUserGrade(Integer userId){
        logger.info("SAtCreditInformationServiceImpl====getUserGrade=====userId"+userId);
        UserInfoVo user = (UserInfoVo)sUserService.getUserInfo(userId).getData();
        SAtCreditGrade creditGrade = sAtCreditGradeService.findById(user.getCreditGrade());
        List<SAtCreditGrade> list = sAtCreditGradeService.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("creditGrade",creditGrade.getGradeName());
        map.put("limitAmout",creditGrade.getLimitAmout());
        map.put("proportion",creditGrade.getRank() * 1.0/list.size());

        //实名认证
        Map<String, Object> identityParam = new HashMap<>();
        identityParam.put("userId",userId);
        SAtIdentity sAtIdentity = sAtIdentityService.selectAtIdentityByUserId(identityParam);
        if (sAtIdentity == null){
            map.put("identityStatus",-1);
        }
        else {
            if (sAtIdentity.getCertType() != null &&  sAtIdentity.getCertType() == 1){
                map.put("identityStatus",sAtIdentity.getIdentityStatus());
            }
            else {
                map.put("identityStatus",-1);
            }
        }

        //绑卡
        Map<String, Object> bindParam = new HashMap<>();
        bindParam.put("userId", userId);
        List<SUserBindVo> bindList = sUserBindService.getUserBindList(bindParam);
        if (bindList == null || bindList.size() == 0){ 
            map.put("bindCardStatus",-1);
        }
        else {
            SUserBindVo bindVo = bindList.get(0);
            SUserBind bind = sUserBindService.findById(bindVo.getId());
            if (bind.getCardStatus() == 0){
                map.put("bindCardStatus",1);
            }
            else if (bind.getCardStatus() == 1){
                map.put("bindCardStatus",0);
            }
            else {
                map.put("bindCardStatus",bind.getCardStatus());
            }
        }

        //资料填写百分比
        Integer secondPercent = 0;
        Integer thirdPercent = 0;
        Integer fourthPercent = 0;
        Integer fifthPercent = 0;
        SAtCreditInformation creditInfo=getByUserId(userId);

        if (creditInfo != null){

            //计算第2步百分比
            int secondFillCount = 0;
            if (StringUtils.isNotBlank(creditInfo.getBirthday())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getGender())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getMarried())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getReligion())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getEducation())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getLiveAddress())){secondFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getLiveAddressStreet())){secondFillCount++;}

            if (secondFillCount != 0){ secondPercent = (int)((secondFillCount > 0 ? 7 : 0)/7.0*100);}

            //计算第3步百分比
            int thirdFillCount = 0;

            if(creditInfo.getProfession() != null && creditInfo.getProfession().compareTo(Constants.Credit.CREDIT_P_WHITE)==0){
                //setp3-1
                if (StringUtils.isNotBlank(creditInfo.getCompanyName())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getIndustry())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getPosition())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getMonthIncome())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyPhone())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyAddress())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyAddressStreet())){thirdFillCount++;}

                if (thirdFillCount != 0){ thirdPercent = (int)((thirdFillCount > 0 ? 7 : 0) /7.0*100);}
            }
            else if(creditInfo.getProfession() != null && creditInfo.getProfession().compareTo(Constants.Credit.CREDIT_P_BUSINESSMAN)==0){
                //setp3-2
                if (StringUtils.isNotBlank(creditInfo.getCompanyName())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getIndustry())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyPersons())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyPhone())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyAddress())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getCompanyAddressStreet())){thirdFillCount++;}

                if (thirdFillCount != 0){ thirdPercent = (int)((thirdFillCount > 0 ? 6 : 0) /6.0*100);}
            }
            else if(creditInfo.getProfession() != null && creditInfo.getProfession().compareTo(Constants.Credit.CREDIT_P_WITHOUT)==0){
                //setp3-3
                if (StringUtils.isNotBlank(creditInfo.getNotJobTime())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getLastIndustry())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getLastIncome())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getNotJobSource())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getNotJobIncome())){thirdFillCount++;}

                if (thirdFillCount != 0){ thirdPercent = (int)((thirdFillCount > 0 ? 5 : 0) /5.0*100);}
            }
            else if(creditInfo.getProfession() != null && creditInfo.getProfession().compareTo(Constants.Credit.CREDIT_P_STUDENT)==0){
                //setp3-4
                if (StringUtils.isNotBlank(creditInfo.getSchoolName())){thirdFillCount++;}
//                if (StringUtils.isNotBlank(creditInfo.getSchoolCourse())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getSchoolIn())){thirdFillCount++;}
                if (StringUtils.isNotBlank(creditInfo.getSchoolOut())){thirdFillCount++;}

                if (thirdFillCount != 0){ thirdPercent = (int)((thirdFillCount > 0 ? 3 : 0) /3.0*100);}
            }

            //计算第4步百分比
            int fourthFillCount = 0;
            if (StringUtils.isNotBlank(creditInfo.getRelativeType())){fourthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getRelativeName())){fourthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getRelativePhone())){fourthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getUrgentName())){fourthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getUrgentPhone())){fourthFillCount++;}

            if (fourthFillCount != 0){ fourthPercent = (int)((fourthFillCount > 0 ? 5 : 0)/5.0*100);}

            //计算第5步百分比
            int fifthFillCount = 0;
            if (StringUtils.isNotBlank(creditInfo.getBPJSImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getKKImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getCreditCardImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getWorkCardImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getSalaryCertImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getCompanyAffidavitImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getBankStatementImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getOtherWorkImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getAccountImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getDayHistoryImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getWeekHistoryImg())){fifthFillCount++;}
            if (StringUtils.isNotBlank(creditInfo.getMonthHistoryImg())){fifthFillCount++;}

            if (fifthFillCount != 0){ fifthPercent = (int)(fifthFillCount/5.0*100); fifthPercent = fifthPercent > 100 ? 100 : fifthPercent;}
        }


        map.put("secondPercent",secondPercent);
        map.put("thirdPercent",thirdPercent);
        map.put("fourthPercent",fourthPercent);
        map.put("fifthPercent",fifthPercent);
        return ResultGenerator.genSuccessResult(map);
    }

    @Override
    public void creditInfoModify(Integer userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("creditModify",1);
        sUserService.updateCreditInfo(param);
    }

    @Override
    public List<SAtCreditInformation> selectUnCheckCreditList() {
        return sAtCreditInformationMapper.selectUnCheckCreditList();
    }

}
