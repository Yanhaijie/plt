package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SAtCreditInformationMapper;
import com.supermoney.loan.mg.entity.SAtCreditInformation;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.entity.vo.SAtCreditInformationVo;
import com.supermoney.loan.mg.service.SAtCreditInformationService;
import com.supermoney.loan.mg.service.SUserBindService;
import com.supermoney.loan.mg.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/28.
 */
@Service
@Transactional
public class SAtCreditInformationServiceImpl extends AbstractService<SAtCreditInformation> implements SAtCreditInformationService {
    @Resource
    private SAtCreditInformationMapper sAtCreditInformationMapper;

    @Resource
    private AppProperties appProperties;

    @Resource
    private SUserBindService sUserBindService;


    /**
     * 查询
     * @param param
     * @return
     */
    public List<SAtCreditInformationVo> getList(Map<String,Object> param)
    {
        return  sAtCreditInformationMapper.selectList(param);
    }

    /**
     * 获取
     * @param id
     * @return
     */
    public  SAtCreditInformationVo getById(Integer id){
        Map<String,Object> param=new HashMap<>();
        param.put("id",id);
        List<SAtCreditInformationVo> list =getList(param);
        return  list.get(0);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SAtCreditInformationVo> list =getList(param);
        for(SAtCreditInformationVo item :list){
            item.setImgBack(item.getImgBack());
            item.setImgFront(item.getImgFront());
            item.setImgHold(item.getImgHold());
            if(StringUtils.isNotBlank(item.getBPJSImg())){item.setBPJSImg(item.getBPJSImg());}
            if(StringUtils.isNotBlank(item.getKKImg())){item.setKKImg(item.getKKImg());}
            if(StringUtils.isNotBlank(item.getCreditCardImg())){item.setCreditCardImg(item.getCreditCardImg());}
            if(StringUtils.isNotBlank(item.getWorkCardImg())){item.setWorkCardImg(item.getWorkCardImg());}
            if(StringUtils.isNotBlank(item.getSalaryCertImg())){item.setSalaryCertImg(item.getSalaryCertImg());}
            if(StringUtils.isNotBlank(item.getCompanyAffidavitImg())){item.setCompanyAffidavitImg(item.getCompanyAffidavitImg());}
            if(StringUtils.isNotBlank(item.getBankStatementImg())){item.setBankStatementImg(item.getBankStatementImg());}
            if(StringUtils.isNotBlank(item.getOtherWorkImg())){item.setOtherWorkImg(item.getOtherWorkImg());}
            if(StringUtils.isNotBlank(item.getAccountImg())){item.setAccountImg(item.getAccountImg());}
            if(StringUtils.isNotBlank(item.getDayHistoryImg())){item.setDayHistoryImg(item.getDayHistoryImg());}
            if(StringUtils.isNotBlank(item.getWeekHistoryImg())){item.setWeekHistoryImg(item.getWeekHistoryImg());}
            if(StringUtils.isNotBlank(item.getMonthHistoryImg())){item.setMonthHistoryImg(item.getMonthHistoryImg());}
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    public  Result getInfo(Integer id){
        SAtCreditInformationVo item=getById(id);
        item.setImgBack(item.getImgBack());
        item.setImgFront(item.getImgFront());
        item.setImgHold(item.getImgHold());
        item.setBPJSImg(item.getBPJSImg());
        item.setKKImg(item.getKKImg());
        item.setCreditCardImg(item.getCreditCardImg());
        item.setWorkCardImg(item.getWorkCardImg());
        item.setSalaryCertImg(item.getSalaryCertImg());
        item.setCompanyAffidavitImg(item.getCompanyAffidavitImg());
        item.setBankStatementImg(item.getBankStatementImg());
        item.setOtherWorkImg(item.getOtherWorkImg());
        item.setAccountImg(item.getAccountImg());
        item.setDayHistoryImg(item.getDayHistoryImg());
        item.setWeekHistoryImg(item.getWeekHistoryImg());
        item.setMonthHistoryImg(item.getMonthHistoryImg());

        SUserBind sUserBind = sUserBindService.getUserUseCard(item.getUserId());
        if (sUserBind != null){
            item.setCardName(sUserBind.getHoldingName());
            item.setCardId(sUserBind.getHoldingCard());
            item.setCardBank(sUserBind.getCardNumber());
            item.setCardAccount(sUserBind.getCardAccount());
        }
        return  ResultGenerator.genSuccessResult(item);
    }
    /**
     * 批量审核
     * @param status
     * @param ids
     * @return
     */
    public Result audits(Integer status, String[] ids){

        for(String id:ids){
            SAtCreditInformation credit= sAtCreditInformationMapper.selectByPrimaryKey(Integer.valueOf(id));
            if(status.compareTo(1)==0){
                credit.setCreditStatus(Constants.Credit.STATUS_AUDITPASS);
            }
            if(status.compareTo(0)==0){
                credit.setCreditStatus(Constants.Credit.STATUS_AUDITFAILD);
            }
            sAtCreditInformationMapper.updateByPrimaryKeySelective(credit);
        }

        return ResultGenerator.genSuccessResult();
    }

    @Override
    public List<SAtCreditInformation> selectUnCheckCreditList() {
        return sAtCreditInformationMapper.selectUnCheckCreditList();
    }

}
