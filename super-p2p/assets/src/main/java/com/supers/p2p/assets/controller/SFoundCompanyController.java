package com.supers.p2p.assets.controller;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.SBlackWhiteList;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.service.SBlackWhiteListService;
import com.supers.p2p.assets.service.SCompanyService;
import com.supers.p2p.assets.utils.*;
import com.supers.p2p.assets.entity.SFoundCompany;
import com.supers.p2p.assets.service.SFoundCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/08.
*/
@RestController
@RequestMapping("/s/found/company")
@Api(value = "/s/found/company",description = "")
public class SFoundCompanyController {
    @Resource
    private SFoundCompanyService sFoundCompanyService;

    @Resource
    private SBlackWhiteListService sBlackWhiteListService;

    @Resource
    private SCompanyService sCompanyService;

    @PostMapping("/save")
    @Transactional
    public Result save(SFoundCompany sFoundCompany) {

         if (sFoundCompany.getId() == null){
            CurrentInfo userInfo = UserUtils.getCurrentInfo();
            if (userInfo.getCompanyId() == null){
                return ResultGenerator.genFailResult("请先填写公司基本信息");
            }
            if (userInfo.getCompanyId().intValue() != -1){
                sFoundCompany.setCompanyId(userInfo.getCompanyId());
            }
            sFoundCompany = sFoundCompany.toPoEntity();
            sFoundCompanyService.save(sFoundCompany);
        }
        else {
            sFoundCompany = sFoundCompany.toPoEntity();
            sFoundCompanyService.update(sFoundCompany);
        }
        SCompany company = sCompanyService.findById(sFoundCompany.getCompanyId());
        company.setAuditStatus(1);
        sCompanyService.update(company);
        return ResultGenerator.genSuccessResult(sFoundCompany.getId());
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sFoundCompanyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SFoundCompany sFoundCompany) {
        sFoundCompanyService.update(sFoundCompany);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,String> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        List<SFoundCompany> list = sFoundCompanyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/foundCompanyDetail")
    public Result foundCompanyDetail() {
        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId",userInfo.getUserId());
        Map<String, Object> resultMap = sFoundCompanyService.selectFoundCompanyDetail(paramMap);
        //元转为亿
        BigDecimal forecastAssetDemand = (BigDecimal) resultMap.get("forecastAssetDemand");
   /*     //元转为亿
        BigDecimal foundCost = (Integer) resultMap.get("foundCost");*/
        //元转为亿
        BigDecimal unreceivedPrincipal = (BigDecimal) resultMap.get("unreceivedPrincipal");
        //元转为亿
        BigDecimal monthInvestmentAmount = (BigDecimal) resultMap.get("monthInvestmentAmount");
        //小数转为百分比
        BigDecimal monthTransactionRate = (BigDecimal) resultMap.get("monthTransactionRate");
        //小数转为百分比
        BigDecimal collectFailRate = (BigDecimal) resultMap.get("collectFailRate");

        //元转为亿
        BigDecimal monthTransactionAmount = (BigDecimal) resultMap.get("monthTransactionAmount");
        //小数转为百分比
        BigDecimal margin = (BigDecimal) resultMap.get("margin");
        if(forecastAssetDemand != null){
            resultMap.put("forecastAssetDemand",forecastAssetDemand.divide(new BigDecimal("100000000")));
        }
     /*   if(foundCost != null){
            resultMap.put("foundCost",foundCost.divide(new BigDecimal("100000000")));
        }*/
        if(unreceivedPrincipal != null){
            resultMap.put("unreceivedPrincipal",unreceivedPrincipal.divide(new BigDecimal("100000000")));
        }
        if(monthInvestmentAmount != null){
            resultMap.put("monthInvestmentAmount",monthInvestmentAmount.divide(new BigDecimal("100000000")));
        }
        if(monthTransactionRate != null){
            resultMap.put("monthTransactionRate",monthTransactionRate.multiply(new BigDecimal("100")));
        }
        if(collectFailRate != null){
            resultMap.put("collectFailRate",collectFailRate.multiply(new BigDecimal("100")));
        }
        if(monthTransactionAmount != null){
            resultMap.put("monthTransactionAmount",monthTransactionAmount.divide(new BigDecimal("100000000")));
        }
        if(margin != null){
            resultMap.put("margin",margin.multiply(new BigDecimal("100")));
        }
        return ResultGenerator.genSuccessResult(resultMap);
    }

    @PostMapping("/addMyFound")
    public Result addMyFound(Integer companyId) {
        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        SBlackWhiteList sBlackWhiteList = new SBlackWhiteList();
        sBlackWhiteList.setCompanyId(userInfo.getCompanyId());
        sBlackWhiteList.setTargetCompanyId(companyId);
        //资金方
        if(userInfo.getUserType() > 1){
            sBlackWhiteList.setOwnerType(1);
        }else{//资产方
            sBlackWhiteList.setOwnerType(0);
        }
        List<SBlackWhiteList> list = sBlackWhiteListService.getList(sBlackWhiteList);
        if(list != null && list.size() > 0){
            return ResultGenerator.genFailResult("企业已存在合作名单");
        }

        SCompany company = sCompanyService.findById(userInfo.getCompanyId());
        SCompany targetCompany = sCompanyService.findById(companyId);

        SBlackWhiteList whiteList = new SBlackWhiteList();
        whiteList.setCompanyId(company.getId());
        whiteList.setCompanyName(company.getFullName());
        whiteList.setTargetCompanyId(targetCompany.getId());
        whiteList.setTargetCompanyName(targetCompany.getFullName());
        whiteList.setRelationType(1);

        //资金方
        if(userInfo.getUserType()> 1){
            whiteList.setOwnerType(1);
        }else{//资产方
            whiteList.setOwnerType(0);
        }
        sBlackWhiteListService.save(whiteList);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/removeMyFound")
    public Result removeMyFound(Integer whiteListId) {
        sBlackWhiteListService.deleteById(whiteListId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/foundCompanyList")
    public Result foundCompanyList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param = (Map<String,Object>)RequestUntil.getParams().get("search");
        PageHelper.startPage(page, size);

        if (param == null){
            param = new HashMap<>();
        }

        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        param.put("exceptCompanyId",userInfo.getCompanyId());

        String businessArea = (String) param.get("businessArea");
        if (StringUtils.isNotBlank(businessArea)){
            String[] arr = businessArea.split(",");
            param.put("businessArea",arr);
        }

        //0 0-5, 1 5-10, 2 10-15, 3 15-20 ,4 20
        String foundCost = (String) param.get("foundCost");
        String foundCostMin = "";
        String foundCostMax = "";
        if (StringUtils.isNotBlank(foundCost)){
            if (foundCost.equals("0")){
                foundCostMin = "0";
                foundCostMax = "5";
            }
            else if (foundCost.equals("1")){
                foundCostMin = "5";
                foundCostMax = "10";
            }
            else if (foundCost.equals("2")){
                foundCostMin = "10";
                foundCostMax = "15";
            }
            else if (foundCost.equals("3")){
                foundCostMin = "15";
                foundCostMax = "20";
            }
            else if (foundCost.equals("4")){
                foundCostMin = "20";
            }
        }
        param.put("foundCostMin",foundCostMin);
        param.put("foundCostMax",foundCostMax);
        List<Map<String, Object>> list = sFoundCompanyService.selectFoundCompanyList(param);
        if(list != null && list.size() > 0){
            for(Map<String,Object> map:list){
                BigDecimal forecastAssetDemand = (BigDecimal)map.get("forecastAssetDemand");
                BigDecimal monthTransactionRate = (BigDecimal)map.get("monthTransactionRate");
                BigDecimal monthTransactionAmount = (BigDecimal)map.get("monthTransactionAmount");
                BigDecimal margin = (BigDecimal)map.get("margin");
                BigDecimal collectFailRate = (BigDecimal)map.get("collectFailRate");
                BigDecimal unreceivedPrincipal = (BigDecimal)map.get("unreceivedPrincipal");
                if(unreceivedPrincipal != null){
                    map.put("unreceivedPrincipal",unreceivedPrincipal.divide(new BigDecimal("100000000")));
                }
                if(forecastAssetDemand != null){
                    map.put("forecastAssetDemand",forecastAssetDemand.divide(new BigDecimal("100000000")));
                }
                if(monthTransactionRate != null){
                    map.put("monthTransactionRate",monthTransactionRate.multiply(new BigDecimal("100")));
                }
                if(collectFailRate != null){
                    map.put("collectFailRate",collectFailRate.multiply(new BigDecimal("100")));
                }
                if(monthTransactionAmount != null){
                    map.put("monthTransactionAmount",monthTransactionAmount.divide(new BigDecimal("100000000")));
                }
                if(margin != null){
                    map.put("margin",margin.multiply(new BigDecimal("100")));
                }

            }
        }

        //===简单包装过滤下已经加入合作关系的企业，做下标识
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("exceptCompanyId",userInfo.getCompanyId());
        List<Map<String, Object>> cooList = sFoundCompanyService.selectCoopFoundCompanyList(paramMap);
        Map<String,Map<String,Object>> cooMap = Maps.newHashMap();
        for(Map<String,Object> map:cooList){
            cooMap.put(map.get("companyId").toString(),map);
        }
        for(Map<String,Object> map:list){
          String targetCompanyId = map.get("companyId").toString();
          //已经加入了合作企业
          if(cooMap.get(targetCompanyId) != null){
              map.put("cooFlag","1");
          }else{
              map.put("cooFlag","0");
          }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/myFoundCompanyList")
    public Result myFoundCompanyList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param = (Map<String,Object>)RequestUntil.getParams().get("search");
        PageHelper.startPage(page, size);

        if (param == null){
            param = new HashMap<>();
        }

        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        param.put("exceptCompanyId",userInfo.getCompanyId());

        String businessArea = (String) param.get("businessArea");
        if (StringUtils.isNotBlank(businessArea)){
            String[] arr = businessArea.split(",");
            param.put("businessArea",arr);
        }

        //0 0-5, 1 5-10, 2 10-15, 3 15-20 ,4 20
        String foundCost = (String) param.get("foundCost");
        String foundCostMin = "";
        String foundCostMax = "";
        if (StringUtils.isNotBlank(foundCost)){
            if (foundCost.equals("0")){
                foundCostMin = "0";
                foundCostMax = "5";
            }
            else if (foundCost.equals("1")){
                foundCostMin = "5";
                foundCostMax = "10";
            }
            else if (foundCost.equals("2")){
                foundCostMin = "10";
                foundCostMax = "15";
            }
            else if (foundCost.equals("3")){
                foundCostMin = "15";
                foundCostMax = "20";
            }
            else if (foundCost.equals("4")){
                foundCostMin = "20";
            }
        }
        param.put("foundCostMin",foundCostMin);
        param.put("foundCostMax",foundCostMax);
        List<Map<String, Object>> list = sFoundCompanyService.selectCoopFoundCompanyList(param);
        if(list != null && list.size() > 0){
            for(Map<String,Object> map:list){
                BigDecimal forecastAssetDemand = (BigDecimal)map.get("forecastAssetDemand");
                BigDecimal monthTransactionRate = (BigDecimal)map.get("monthTransactionRate");
                BigDecimal unreceivedPrincipal = (BigDecimal)map.get("unreceivedPrincipal");
                BigDecimal collectFailRate = (BigDecimal)map.get("collectFailRate");
                BigDecimal monthTransactionAmount = (BigDecimal)map.get("monthTransactionAmount");
                BigDecimal margin = (BigDecimal)map.get("margin");
                if(forecastAssetDemand != null){
                    map.put("forecastAssetDemand",forecastAssetDemand.divide(new BigDecimal("100000000")));
                }
                if(monthTransactionRate != null){
                    map.put("monthTransactionRate",monthTransactionRate.multiply(new BigDecimal("100")));
                }
                if(collectFailRate != null){
                    map.put("collectFailRate",collectFailRate.multiply(new BigDecimal("100")));
                }
                if(monthTransactionAmount != null){
                    map.put("monthTransactionAmount",monthTransactionAmount.divide(new BigDecimal("100000000")));
                }
                if(unreceivedPrincipal != null){
                    map.put("unreceivedPrincipal",unreceivedPrincipal.divide(new BigDecimal("100000000")));
                }
                if(margin != null){
                    map.put("margin",margin.multiply(new BigDecimal("100")));
                }

            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @RequestMapping("/exportFoundCompany")
    public void exportFoundCompany(HttpServletResponse repsonse) {
        Map<String,Object> param = (Map<String,Object>)RequestUntil.getParams().get("search");

        if (param == null){
            param = new HashMap<>();
        }

        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        param.put("exceptCompanyId",userInfo.getCompanyId());

        String businessArea = (String) param.get("businessArea");
        if (StringUtils.isNotBlank(businessArea)){
            String[] arr = businessArea.split(",");
            param.put("businessArea",arr);
        }

        //0 0-5, 1 5-10, 2 10-15, 3 15-20 ,4 20
        String foundCost = (String) param.get("foundCost");
        String foundCostMin = "";
        String foundCostMax = "";
        if (StringUtils.isNotBlank(foundCost)){
            if (foundCost.equals("0")){
                foundCostMin = "0";
                foundCostMax = "5";
            }
            else if (foundCost.equals("1")){
                foundCostMin = "5";
                foundCostMax = "10";
            }
            else if (foundCost.equals("2")){
                foundCostMin = "10";
                foundCostMax = "15";
            }
            else if (foundCost.equals("3")){
                foundCostMin = "15";
                foundCostMax = "20";
            }
            else if (foundCost.equals("4")){
                foundCostMin = "20";
            }
        }
        param.put("foundCostMin",foundCostMin);
        param.put("foundCostMax",foundCostMax);

        List<Map<String, Object>> list;
        String exportType = (String) param.get("exportType");
        if (exportType != null && exportType.equals("0")){
            list = sFoundCompanyService.selectCoopFoundCompanyList(param);
        }
        else {
            list = sFoundCompanyService.selectFoundCompanyList(param);
        }

        Map<String,String> assetInfoMap = Maps.newHashMap();
        assetInfoMap.put("10","消费分期");
        assetInfoMap.put("11","融资租赁");
        assetInfoMap.put("12","车抵贷");
        assetInfoMap.put("13","房抵贷");
        assetInfoMap.put("14","信用贷");
        assetInfoMap.put("15","供应链金融");
        assetInfoMap.put("16","其他");
        assetInfoMap.put("1001","3C消费");
        assetInfoMap.put("1002","个人日常消费");
        assetInfoMap.put("1003","装修");
        assetInfoMap.put("1004","旅游");
        assetInfoMap.put("1005","教育");
        assetInfoMap.put("1006","医疗");
        assetInfoMap.put("1007","其他");

        Map<String,String> isStraightBackMap = Maps.newHashMap();
        isStraightBackMap.put("1","直还");
        isStraightBackMap.put("0","非直还");

        Map<String,String> isDirectInvestmentMap = Maps.newHashMap();
        isDirectInvestmentMap.put("0","非直投");
        isDirectInvestmentMap.put("1","直投");
        if(list != null){
            for(Map<String, Object> map : list){
                String assetInfo = (String) map.get("assetInfo");
                if(StringUtils.isNotEmpty(assetInfo)){
                    String[] assetInfoKey = assetInfo.split(",");
                    String assetInfos ="";
                    for(int i=0;i<assetInfoKey.length;i++){
                        assetInfos =assetInfos+assetInfoMap.get(assetInfoKey[i]+"")+",";
                    }
                    assetInfos = assetInfos.substring(0,assetInfos.length()-1);
                    map.put("assetInfo",assetInfos);
                }
                Integer isStraightBack = (Integer) map.get("isStraightBack");
                Integer isDirectInvestment = (Integer) map.get("isDirectInvestment");
                if(isStraightBack != null){
                    map.put("isDirectInvestment",isDirectInvestmentMap.get(isDirectInvestment.toString()));
                }
                if(isStraightBack != null){
                    map.put("isStraightBack",isStraightBackMap.get(isStraightBack.toString()));
                }
            }
        }


        if(list != null && list.size() > 0){
            for(Map<String,Object> map:list){
                BigDecimal forecastAssetDemand = (BigDecimal)map.get("forecastAssetDemand");
                BigDecimal monthTransactionRate = (BigDecimal)map.get("monthTransactionRate");
                BigDecimal collectFailRate = (BigDecimal)map.get("collectFailRate");
                BigDecimal monthTransactionAmount = (BigDecimal)map.get("monthTransactionAmount");
                BigDecimal margin = (BigDecimal)map.get("margin");
                BigDecimal unreceivedPrincipal = (BigDecimal)map.get("unreceivedPrincipal");
                if(forecastAssetDemand != null){
                    map.put("forecastAssetDemand",forecastAssetDemand.divide(new BigDecimal("100000000")));
                }
                if(monthTransactionRate != null){
                    map.put("monthTransactionRate",monthTransactionRate.multiply(new BigDecimal("100")));
                }
                if(collectFailRate != null){
                    map.put("collectFailRate",collectFailRate.multiply(new BigDecimal("100")));
                }
                if(monthTransactionAmount != null){
                    map.put("monthTransactionAmount",monthTransactionAmount.divide(new BigDecimal("100000000")));
                }
                if(margin != null){
                    map.put("margin",margin.multiply(new BigDecimal("100")));
                }
                if(unreceivedPrincipal != null){
                    map.put("unreceivedPrincipal",unreceivedPrincipal.divide(new BigDecimal("100000000")));
                }

            }
        }

      /*  String[] titles = {"公司ID","资金方简称","可接受资产类型","预测资产需求量（亿）","对资产的要求","资金年化利率范围","月平均资金成交利率(%)","募集失败率(%)"};
        String[] datakeys ={"companyId","abbreviationName","assetInfo","forecastAssetDemand","foundCondition","foundYearRateRand","monthTransactionRate","collectFailRate"};*/
        String[] titles = {"公司ID","资金方简称","待收本金（亿）","月交易额（亿）","放款方式","还款方式","保证金（%"};
        String[] datakeys ={"companyId","abbreviationName","unreceivedPrincipal","monthTransactionAmount","isDirectInvestment","isStraightBack","margin"};
        if (exportType != null && exportType.equals("0")){
            ExcelUtil.exportXlsx(repsonse, "我的合作资金公司", titles, list,datakeys);
        }else{
            ExcelUtil.exportXlsx(repsonse, "资金公司", titles, list,datakeys);
        }
    }
}
