package com.supers.p2p.assets.controller;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.service.SBlackWhiteListService;
import com.supers.p2p.assets.service.SCompanyService;
import com.supers.p2p.assets.utils.*;
import com.supers.p2p.assets.entity.SAssetCompany;
import com.supers.p2p.assets.service.SAssetCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/s/asset/company")
@Api(value = "/s/asset/company",description = "")
public class SAssetCompanyController {
    @Resource
    private SAssetCompanyService sAssetCompanyService;
    @Resource
    private SCompanyService sCompanyService;
    @Resource
    private SBlackWhiteListService sBlackWhiteListService;

    /**
     * 资产池列表
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/getAssetCompanyPoolList")
    public Result getAssetCompanyPoolList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = search();
        PageInfo pageInfo = new PageInfo(list);
        //===简单包装过滤下已经加入合作关系的企业，做下标识
        Map<String,Object> paramMap = Maps.newHashMap();
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        paramMap.put("my_company_id",currentInfo.getCompanyId());
        List<Map<String, Object>> cooList =  sBlackWhiteListService.getMyAssetCompanyList(paramMap);
        Map<String,Map<String,Object>> cooMap = Maps.newHashMap();
        for(Map<String,Object> map:cooList){
            cooMap.put(map.get("id").toString(),map);
        }
        for(Map<String,Object> map:list){
            String targetCompanyId = map.get("id").toString();
            //已经加入了合作企业
            if(cooMap.get(targetCompanyId) != null){
                map.put("cooFlag","1");
            }else{
                map.put("cooFlag","0");
            }
        }




        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getAssetCompany")
    public SAssetCompanyVo getAssetCompany() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("id",currentInfo.getCompanyId()+"");
        List<SAssetCompanyVo> list = sAssetCompanyService.findByParam(paramMap);
        if (list != null && list.size() >0){
            return list.get(0).toVoEntity();
        }
        return null;
    }



    @PostMapping("/saveAssetCompany")
    public Result saveAssetCompany(SAssetCompany assetCompany) {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        if(currentInfo.getCompanyId().intValue() != -1){
            assetCompany.setCompanyId(currentInfo.getCompanyId());

        }
        assetCompany.setOpt(currentInfo.getUserId().toString());
        //金额单位转换
        assetCompany = assetCompany.toPoEntity();
        if(assetCompany.getId() == null){
            sAssetCompanyService.save(assetCompany);
        }else{
            sAssetCompanyService.update(assetCompany);
        }

        //修改完数据后，把公司只为待审核状态
        SCompany sCompany = new SCompany();
        sCompany.setId(assetCompany.getCompanyId());
        sCompany.setAuditStatus(1);
        sCompanyService.update(sCompany);
        Result result = ResultGenerator.genSuccessResult();
        result.setMessage("操作成功！");
        return result;
    }


    /**
     * 导出
     * @param repsonse
     */
    @RequestMapping("/exportAssetCompany")
    public void exportAssetCompany(HttpServletResponse repsonse) {
        List<Map<String, Object>> list = search();
        String[] titles = {"公司ID","资产方简称","资产规模(亿)","月新增规模(亿)","单笔资产期限","单笔资产额度（元）","逾期情况","可接受资金成本（年化利率%）","业务区域","履约险（0为无，1为有）","担保（0为无，1为有）"};
        String[] datakeys ={"id","abbreviation_name","asset_size","month_size","every_deadline","every_sum","overdue","accept_found","business_area","is_performance_insurance","is_guarantee"};
        Map map = null;
        ExcelUtil.exportXlsx(repsonse, "资产公司", titles, list,datakeys);
    }

    /**
     * 查询和导出共用
     * @return
     */
    private List search() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        if(searchMap == null){
            searchMap = Maps.newHashMap();
        }
        String businessArea = (String) searchMap.get("business_area");
        if (org.apache.commons.lang.StringUtils.isNotBlank(businessArea)){
            String[] arr = businessArea.split(",");
            param.put("businessArea",arr);
        }
        searchMap.put("my_company_id",currentInfo.getCompanyId());
        List<Map<String, Object>> list = sAssetCompanyService.getAssetCompanyPoolList(searchMap);
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
        return list;
    }


}
