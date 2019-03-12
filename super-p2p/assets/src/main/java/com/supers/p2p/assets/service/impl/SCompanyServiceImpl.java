package com.supers.p2p.assets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.supers.p2p.assets.dao.SCompanyMapper;
import com.supers.p2p.assets.entity.SAssetCompany;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.SFoundCompany;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.service.SAssetCompanyService;
import com.supers.p2p.assets.service.SCompanyService;
import com.supers.p2p.assets.service.SFoundCompanyService;
import com.supers.p2p.assets.utils.AbstractService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/08.
 */
@Service
@Transactional
public class SCompanyServiceImpl extends AbstractService<SCompany> implements SCompanyService {
    @Resource
    private SCompanyMapper sCompanyMapper;
    @Resource
    private SAssetCompanyService sAssetCompanyService;
    @Resource
    private SFoundCompanyService sFoundCompanyService;
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SCompany> getList(Map<String,Object> param)
    {
        return  sCompanyMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    @Override
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SCompany> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<Map<String,Object>> selectCompanyExeclModel(){
        return  sCompanyMapper.selectCompanyExeclModel();
    }

    public List<Map<String,Object>> selectAssetCompanyExeclModel(){
        return  sCompanyMapper.selectAssetCompanyExeclModel();
    }

    public List<Map<String,Object>> selectFoundCompanyExeclModel(){
        return  sCompanyMapper.selectFoundCompanyExeclModel();
    }

    @Transactional
    public void importCompany(List<List<Object>> list,int type) throws Exception{
        if(list == null && list.size() < 1){
            return;
        }
        //导入公司认证信息
        if(type == 1){
            List<SCompany> companyList = Lists.newArrayList();
            for(int i = 1; i < list.size(); i++) {
                List<Object> objList = list.get(i);
                SCompany company = new SCompany();
                company.setAuditStatus(new Integer("2"));
                company.setId(Integer.parseInt((String) objList.get(0)));
                company.setAbbreviationName((String) objList.get(1));
                company.setFullName((String) objList.get(2));
                company.setDsc((String) objList.get(3));
                company.setLinkMan((String) objList.get(4));
                company.setMobile((String) objList.get(5));
                company.setEmail((String) objList.get(6));
                company.setAddress((String) objList.get(7));
                company.setIdNumber((String) objList.get(8));
                company.setIdCardFirstUrl((String) objList.get(9));
                company.setIdCardSecondUrl((String) objList.get(10));
                company.setUnifyCreditNum((String) objList.get(11));
                company.setUnifyCreditUrl((String) objList.get(12));
                company.setOpeningBankNum((String) objList.get(13));
                company.setOpeningBankUrl((String) objList.get(14));
                company.setBankCreditNum((String) objList.get(15));
                company.setBankCreditUrl((String) objList.get(16));
             //   companyList.add(company);
                this.update(company);
            }

        }
        //导入资产公司信息概况
        if(type == 2){
            List<SAssetCompany> assetCompanyList = Lists.newArrayList();
            for(int i = 1; i < list.size(); i++) {
                List<Object> objList = list.get(i);
                SAssetCompany assetCompany = new SAssetCompany();
                assetCompany.setCompanyId(Integer.parseInt((String) objList.get(0)));
                assetCompany.setAssetInfo((String) objList.get(3));
                if (objList.get(4) != null && StringUtils.isNotEmpty(objList.get(4).toString())) {
                    assetCompany.setAssetSize(new BigDecimal(objList.get(4).toString()));
                }
                if (objList.get(5) != null && StringUtils.isNotEmpty(objList.get(5).toString())) {
                    assetCompany.setAssetSize(new BigDecimal(objList.get(5).toString()));
                }
                assetCompany.setEveryDeadline((String) objList.get(6));
                if (objList.get(7) != null && StringUtils.isNotEmpty(objList.get(7).toString())) {
                    assetCompany.setAssetSize(new BigDecimal(objList.get(7).toString()));
                }
                assetCompany.setOverdueM0((String) objList.get(8));
                assetCompany.setOverdueM1((String) objList.get(9));
                assetCompany.setOverdueM2((String) objList.get(10));
                assetCompany.setOverdueM3((String) objList.get(11));
                if (objList.get(12) != null && StringUtils.isNotEmpty(objList.get(12).toString())) {
                    assetCompany.setAcceptFound(new BigDecimal(objList.get(12).toString()));
                }
                assetCompany.setBusinessArea((String) objList.get(13));
                if(StringUtils.isNotEmpty((String)objList.get(14))){
                    assetCompany.setIsPerformanceInsurance(Integer.parseInt((String) objList.get(14)));
                }
                if (objList.get(15) != null && StringUtils.isNotEmpty(objList.get(15).toString())) {
                    assetCompany.setIsGuarantee(Integer.parseInt((String) objList.get(15)));
                }
                if (objList.get(16) != null && StringUtils.isNotEmpty(objList.get(16).toString())) {
                    assetCompany.setInventory(new BigDecimal(objList.get(16).toString()));
                }
                if (objList.get(17) != null && StringUtils.isNotEmpty(objList.get(17).toString())) {
                    assetCompany.setStartingAmount(new BigDecimal(objList.get(17).toString()));
                }
                if(StringUtils.isNotEmpty((String)objList.get(18))){
                    assetCompany.setLoanForm((String) objList.get(18));
                }
                if(StringUtils.isNotEmpty((String)objList.get(19))){
                    assetCompany.setRemittanceForm((String) objList.get(19));
                }
                if (objList.get(20) != null && StringUtils.isNotEmpty(objList.get(20).toString())) {
                    assetCompany.setIsMargin(Integer.parseInt((String) objList.get(20)));
                }
                assetCompany.toPoEntity();
                assetCompanyList.add(assetCompany);
            }
            if(assetCompanyList != null && assetCompanyList.size() > 0){
                sAssetCompanyService.save(assetCompanyList);
            }
        }
        //导入资金公司信息概况
  /*      if(type == 3){
            List<SFoundCompany> foundCompanyList = Lists.newArrayList();
            for(int i = 1; i < list.size(); i++) {
                List<Object> objList = list.get(i);
                SFoundCompany foundCompany = new SFoundCompany();
                foundCompany.setCompanyId(Integer.parseInt((String) objList.get(0)));
                foundCompany.setAssetInfo((String) objList.get(3));
                if(StringUtils.isNotEmpty((String)objList.get(4))){
                    foundCompany.setForecastAssetDemand(new BigDecimal((String) objList.get(4)));
                }
                foundCompany.setFoundCondition((String) objList.get(5));
                foundCompany.setFoundYearRateRand((String) objList.get(6));
                if(StringUtils.isNotEmpty((String)objList.get(7))){
                    foundCompany.setMonthTransactionRate(new BigDecimal((String)objList.get(7)));
                }
                if(StringUtils.isNotEmpty((String)objList.get(8))){
                    foundCompany.setCollectFailRate(new BigDecimal((String)objList.get(8)));
                }
                foundCompany.setBusinessArea((String) objList.get(9));
                foundCompany.setPlatformName((String) objList.get(10));
                foundCompany.setUrl((String) objList.get(11));
                if(StringUtils.isNotEmpty((String)objList.get(12))){
                    foundCompany.setUnreceivedPrincipal(new BigDecimal((String) objList.get(12)));
                }
                if(StringUtils.isNotEmpty((String)objList.get(13))){
                    foundCompany.setMonthInvestmentAmount(new BigDecimal((String) objList.get(13)));
                }
                if(StringUtils.isNotEmpty((String)objList.get(14))){
                    foundCompany.setEscrow(Integer.parseInt((String) objList.get(14)));
                }
                if(StringUtils.isNotEmpty((String)objList.get(15))){
                    foundCompany.setIsDirectInvestment(Integer.parseInt((String) objList.get(15)));
                }
                if(StringUtils.isNotEmpty((String)objList.get(16))){
                    foundCompany.setIsStraightBack(Integer.parseInt((String) objList.get(16)));
                }
                foundCompany.toPoEntity();
                foundCompanyList.add(foundCompany);
            }
            if(foundCompanyList != null && foundCompanyList.size() > 0){
                sFoundCompanyService.save(foundCompanyList);
            }
        }
        */

      if(type == 3) {
          List<SFoundCompany> foundCompanyList = Lists.newArrayList();
          for (int i = 1; i < list.size(); i++) {
              List<Object> objList = list.get(i);
              SFoundCompany foundCompany = new SFoundCompany();
              foundCompany.setCompanyId(Integer.parseInt((String) objList.get(0)));
              if (objList.get(3) != null && StringUtils.isNotEmpty(objList.get(3).toString())) {
                  foundCompany.setUnreceivedPrincipal(new BigDecimal(objList.get(3).toString()));
              }

              if (objList.get(4) != null && StringUtils.isNotEmpty(objList.get(4).toString())) {
                  foundCompany.setMonthInvestmentAmount(new BigDecimal(objList.get(4).toString()));
              }
              if (objList.get(5) != null && StringUtils.isNotEmpty(objList.get(5).toString())) {
                  foundCompany.setIsDirectInvestment(Integer.parseInt(objList.get(5).toString().substring(0,1)));
              }
              if (objList.get(6) != null && StringUtils.isNotEmpty(objList.get(6).toString())) {
                  foundCompany.setIsStraightBack(Integer.parseInt(objList.get(6).toString().substring(0,1)));
              }
              if (objList.get(7) != null && StringUtils.isNotEmpty(objList.get(7).toString())) {
                  foundCompany.setMargin(new BigDecimal(objList.get(7).toString()));
              }
              foundCompany.toPoEntity();
              foundCompanyList.add(foundCompany);
          }
          if (foundCompanyList != null && foundCompanyList.size() > 0) {
              sFoundCompanyService.save(foundCompanyList);
          }
      }




    }



}
