package com.supers.p2p.assets.controller;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.SPersionalCredit;
import com.supers.p2p.assets.entity.SPersonalInfo;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.service.SPersionalCreditService;
import com.supers.p2p.assets.service.SPersonalInfoService;
import com.supers.p2p.assets.utils.*;
import com.supers.p2p.assets.entity.SItemInfo;
import com.supers.p2p.assets.service.SItemInfoService;
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
import java.sql.Date;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/07.
*/
@RestController
@RequestMapping("/s/item/info")
@Api(value = "/s/item/info",description = "")
public class SItemInfoController {
    @Resource
    private SItemInfoService sItemInfoService;

    @Resource
    private SPersonalInfoService sPersonalInfoService;

    @Resource
    private SPersionalCreditService sPersionalCreditService;

    /**
     * api首页
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageHelper.startPage(page, size);
        PageInfo pageInfo=sItemInfoService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }



    @PostMapping("/getDetail")
    public Result getDetail(@RequestParam Integer id) {
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("id",id);
        List<Map<String,Object>> result = sItemInfoService.getDetailList(paramMap);
        if(result != null && result.size() > 0){
            return ResultGenerator.genSuccessResult(result.get(0));
        }
        return ResultGenerator.genFailResult("无数据！");

    }
    
    @PostMapping("/add")
    public Result add(SItemInfo sItemInfo) {
        sItemInfoService.save(sItemInfo);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 资产项目池
     * @return
     */
    @PostMapping("/getAssetItemPoolList")
    public Result getAssetItemPoolList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sItemInfoService.search();
        if(list != null&& list.size()>0){
            for(Map<String,Object> map : list){
                BigDecimal borrowRate = (BigDecimal)map.get("borrow_rate");
                map.put("borrow_rate",borrowRate.multiply(new BigDecimal("100")));
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/auditAssetItem")
    public Result auditAssetItem(@RequestParam Integer id,@RequestParam Integer status) {
        SItemInfo sItemInfo = new SItemInfo();
        sItemInfo.setId(id);
        sItemInfo.setUseStatus(status);
        sItemInfoService.update(sItemInfo);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 添加个人资产项目
     * @return
     */
    @PostMapping("/addPersonItem")
    @Transactional
    public Result addPersonItem(SItemInfo itemInfo, SPersonalInfo personalInfo, SPersionalCredit persionalCredit,Integer itemId) {

        if (itemId == null){
            sPersonalInfoService.save(personalInfo);
            sPersionalCreditService.save(persionalCredit);

            CurrentInfo userInfo = UserUtils.getCurrentInfo();
            itemInfo.setBorrowerType(0);
            itemInfo.setBorrowerInfoId(personalInfo.getId());
            itemInfo.setBorrowerCreditId(persionalCredit.getId());
            itemInfo.setUseStatus(1);
            itemInfo.setAssetUserId(userInfo.getUserId());
            BigDecimal borrowRate = itemInfo.getBorrowRate();
            //转为%
            if(borrowRate != null){
                itemInfo.setBorrowRate(borrowRate.divide(new BigDecimal("100")));
            }
            sItemInfoService.save(itemInfo);
        }
        else {
            SItemInfo resultItemInfo = sItemInfoService.findById(itemId);
            resultItemInfo.setItemTitle(itemInfo.getItemTitle());
            resultItemInfo.setAssetInfo(itemInfo.getAssetInfo());
            resultItemInfo.setBorrowAmount(itemInfo.getBorrowAmount());
            resultItemInfo.setBorrowDeadline(itemInfo.getBorrowDeadline());
            resultItemInfo.setRepayWay(itemInfo.getRepayWay());
            resultItemInfo.setBorrowRate(itemInfo.getBorrowRate());
            BigDecimal borrowRate = resultItemInfo.getBorrowRate();
            //转为%
            if(borrowRate != null){
                resultItemInfo.setBorrowRate(borrowRate.divide(new BigDecimal("100")));
            }
            sItemInfoService.update(resultItemInfo);

            SPersonalInfo resultPersonalInfo = sPersonalInfoService.findById(resultItemInfo.getBorrowerInfoId());
            resultPersonalInfo.setName(personalInfo.getName());
            resultPersonalInfo.setIdNumber(personalInfo.getIdNumber());
            resultPersonalInfo.setMobile(personalInfo.getMobile());
            resultPersonalInfo.setSex(personalInfo.getSex());
            resultPersonalInfo.setEmail(personalInfo.getEmail());
            resultPersonalInfo.setAddress(personalInfo.getAddress());
            resultPersonalInfo.setProfessionType(personalInfo.getProfessionType());
            resultPersonalInfo.setSalary(personalInfo.getSalary());
            sPersonalInfoService.update(resultPersonalInfo);

            SPersionalCredit resultPersionalCredit = sPersionalCreditService.findById(resultItemInfo.getBorrowerCreditId());
            resultPersionalCredit.setIdCardFirstUrl(persionalCredit.getIdCardFirstUrl());
            resultPersionalCredit.setIdCardSecondUrl(persionalCredit.getIdCardSecondUrl());
            resultPersionalCredit.setBankCardFirstUrl(persionalCredit.getBankCardFirstUrl());
            resultPersionalCredit.setBankCardSecondUrl(persionalCredit.getBankCardSecondUrl());
            sPersionalCreditService.update(resultPersionalCredit);
        }
        return ResultGenerator.genSuccessResult("succeed");
    }


    @PostMapping("/addCar")
    public synchronized Result addCar(@RequestParam Integer id) {
        SItemInfo oldSItemInfo = sItemInfoService.findById(id);
        if(oldSItemInfo.getUseStatus() != 3){
            Result result = ResultGenerator.genSuccessResult();
            result.setMessage("抱歉！添加失败，项目已经被锁定！");
            return result;
        }
        CurrentInfo userInfo = UserUtils.getCurrentInfo();
        SItemInfo sItemInfo = new SItemInfo();
        sItemInfo.setId(id);
        sItemInfo.setFoundUserId(userInfo.getUserId());
        sItemInfo.setUseStatus(4);
        sItemInfo.setLockDuration(new Integer(600));
        sItemInfo.setLockTime(new java.util.Date());
        sItemInfoService.update(sItemInfo);
        Result result = ResultGenerator.genSuccessResult();
        result.setMessage("添加成功");
        return result;
    }

    /**
     * 导出资产项目
     * @param repsonse
     */
    @RequestMapping("/exportAssetItem")
    public void exportAssetItem(HttpServletResponse repsonse) {
        List<Map<String, Object>> list = sItemInfoService.search();
        if(list != null&& list.size()>0){
            for(Map<String,Object> map : list){
                BigDecimal borrowRate = (BigDecimal)map.get("borrow_rate");
                map.put("borrow_rate",borrowRate.multiply(new BigDecimal("100")));
            }
        }
        sItemInfoService.exportAssetItem(repsonse,list);
    }

    //导出购物车中的资产项目，中标的，把购物车的资产项目全部中标
    @RequestMapping("/exportAssetItemCar")
    @Transactional
    public void exportAssetItemCar(HttpServletResponse repsonse) {
        List<Map<String, Object>> list = sItemInfoService.searchCar();
        if(list != null && list.size() > 0){
            String[] idArray = new String[list.size()];
            for(int i=0;i<list.size();i++){
                Map map = list.get(i);
                map.put("use_status",5);
                idArray[i] = map.get("id").toString();
                BigDecimal borrowRate = (BigDecimal)map.get("borrow_rate");
                map.put("borrow_rate",borrowRate.multiply(new BigDecimal("100")));
            }
            Map<String,Object> map = Maps.newHashMap();
            map.put("idArray",idArray);
            sItemInfoService.exportCar(map);
        }
        sItemInfoService.exportAssetItem(repsonse,list);
    }


}
