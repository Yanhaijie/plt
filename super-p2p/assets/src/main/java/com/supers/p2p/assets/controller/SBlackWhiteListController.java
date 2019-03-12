package com.supers.p2p.assets.controller;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.utils.*;
import com.supers.p2p.assets.entity.SBlackWhiteList;
import com.supers.p2p.assets.service.SBlackWhiteListService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/08.
*/
@RestController
@RequestMapping("/s/black/white/list")
@Api(value = "/s/black/white/list",description = "")
public class SBlackWhiteListController {
    @Resource
    private SBlackWhiteListService sBlackWhiteListService;


    /**
     * 加入合作名单
     * @return
     */
    @PostMapping("/addCooperation")
    public Result addCooperation(SBlackWhiteList sBlackWhiteList) {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        sBlackWhiteList.setCompanyId(currentInfo.getCompanyId());
        //资金方
        if(currentInfo.getUserType() > 1){
            sBlackWhiteList.setOwnerType(1);
        }else{//资产方
            sBlackWhiteList.setOwnerType(0);
        }

        List<SBlackWhiteList> list = sBlackWhiteListService.getList(sBlackWhiteList);
        if(list == null || list.size() < 1){
            sBlackWhiteListService.save(sBlackWhiteList);
        }else{
            return ResultGenerator.genFailResult("企业已存在合作名单");
        }
        Result reslut = ResultGenerator.genSuccessResult();
        reslut.setMessage("添加成功");
        return reslut;
    }


    /**
     * 移出合作名单
     * @param sBlackWhiteList
     * @return
     */
    @PostMapping("/deleteCooperation")
    public Result deleteCooperation(SBlackWhiteList sBlackWhiteList) {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        sBlackWhiteList.setCompanyId(currentInfo.getCompanyId());
        sBlackWhiteListService.deleteByObj(sBlackWhiteList);
        Result reslut = ResultGenerator.genSuccessResult();
        reslut.setMessage("移出成功");
        return reslut;
    }



    /**
     * 我的合作资产公司
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/getMyAssetCompanyList")
    public Result getMyAssetCompanyList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        searchMap.put("my_company_id",currentInfo.getCompanyId());
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = sBlackWhiteListService.getMyAssetCompanyList(searchMap);
        if(list != null && list.size() > 0){
            for(Map<String,Object> map:list){
                BigDecimal assetSize = (BigDecimal)map.get("asset_size");
                BigDecimal monthSize = (BigDecimal)map.get("month_size");
                BigDecimal everySum = (BigDecimal)map.get("every_sum");
                BigDecimal acceptFound = (BigDecimal)map.get("accept_found");
                if(assetSize != null){
                    map.put("asset_size",assetSize.divide(new BigDecimal("100000000")));
                }
                if(monthSize != null){
                    map.put("month_size",monthSize.divide(new BigDecimal("100000000")));
                }

                if(acceptFound != null){
                    map.put("accept_found",acceptFound.multiply(new BigDecimal("100")));
                }

            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取资产资金匹配列表
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/getAssetFoundRelationList")
    public Result getAssetFoundRelationList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        List<Map<String, Object>> list = sBlackWhiteListService.selectAssetFoundRelation(searchMap);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @RequestMapping("/exportAssetFoundRelation")
    public void exportAssetFoundRelation(HttpServletResponse repsonse) {
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        List<Map<String, Object>> list = sBlackWhiteListService.selectAssetFoundRelation(searchMap);
        String[] titles = {"资产方ID","资产方简称","资金方ID","资金方简称"};
        String[] datakeys ={"asset_id","asset_name","found_id","found_name"};
        ExcelUtil.exportXlsx(repsonse, "资产&资金匹配表", titles, list,datakeys);
    }



    @RequestMapping("/exportMyAssetCompany")
    public void exportMyAssetCompany(HttpServletResponse repsonse) {
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        searchMap.put("my_company_id",currentInfo.getCompanyId());
        List<Map<String, Object>> list = sBlackWhiteListService.getMyAssetCompanyList(searchMap);
        if(list != null && list.size() > 0){
            for(Map<String,Object> map:list){
                BigDecimal assetSize = (BigDecimal)map.get("asset_size");
                BigDecimal monthSize = (BigDecimal)map.get("month_size");
                BigDecimal everySum = (BigDecimal)map.get("every_sum");
                BigDecimal acceptFound = (BigDecimal)map.get("accept_found");
                if(assetSize != null){
                    map.put("asset_size",assetSize.divide(new BigDecimal("100000000")));
                }
                if(monthSize != null){
                    map.put("month_size",monthSize.divide(new BigDecimal("100000000")));
                }

                if(acceptFound != null){
                    map.put("accept_found",acceptFound.multiply(new BigDecimal("100")));
                }

            }
        }
        String[] titles = {"公司ID","资产方简称","资产规模(亿)","月新增规模(亿)","单笔资产期限","单笔资产额度","逾期情况","可接受资金成本（年化利率%）","业务区域","履约险(0:为无，1为有)","担保(0:为无，1为有)"};
        String[] datakeys ={"id","abbreviation_name","asset_size","month_size","every_deadline","every_sum","overdue","accept_found","business_area","is_performance_insurance","is_performance_insurance","is_guarantee"};
        ExcelUtil.exportXlsx(repsonse, "我的合作资产公司", titles, list,datakeys);
    }

}
