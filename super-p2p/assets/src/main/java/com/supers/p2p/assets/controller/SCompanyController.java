package com.supers.p2p.assets.controller;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.entity.vo.SAssetCompanyVo;
import com.supers.p2p.assets.service.SAssetCompanyService;
import com.supers.p2p.assets.service.SFoundCompanyService;
import com.supers.p2p.assets.utils.*;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.service.SCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
* Created by wenyuhao on 2018/05/08.
*/
@RestController
@RequestMapping("/s/company")
@Api(value = "/s/company",description = "")
public class SCompanyController {
    @Resource
    private SCompanyService sCompanyService;

    @Resource
    private SFoundCompanyService sFoundCompanyService;

    @Resource
    private SAssetCompanyService sAssetCompanyService;

    @PostMapping("/add")
    public Result add(SCompany sCompany) {
        sCompanyService.save(sCompany);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sCompanyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SCompany sCompany) {
        sCompanyService.update(sCompany);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id,@RequestParam Integer companyType) {
        Object detailCompanyMap = null;
        //资产
        if (companyType == 0){
            Map<String,Object> paramMap = Maps.newHashMap();
            paramMap.put("id",id);
            List<SAssetCompanyVo> list = sAssetCompanyService.findByParam(paramMap);
            detailCompanyMap = list.get(0).toVoEntity();
        }
        //资金
        else if (companyType == 1){
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("companyId",id);
            Map<String, Object> foundInfoMap = sFoundCompanyService.selectFoundCompanyDetail(paramMap);
            BigDecimal unreceivedPrincipal = (BigDecimal)foundInfoMap.get("unreceivedPrincipal");
            BigDecimal monthTransactionAmount = (BigDecimal)foundInfoMap.get("monthTransactionAmount");
            BigDecimal margin = (BigDecimal)foundInfoMap.get("margin");
            if(monthTransactionAmount != null){
                foundInfoMap.put("monthTransactionAmount",monthTransactionAmount.divide(new BigDecimal("100000000")));
            }
            if(unreceivedPrincipal != null){
                foundInfoMap.put("unreceivedPrincipal",unreceivedPrincipal.divide(new BigDecimal("100000000")));
            }
            if(margin != null){
                foundInfoMap.put("margin",margin.multiply(new BigDecimal("100")));
            }
            detailCompanyMap = foundInfoMap;
        }
        return ResultGenerator.genSuccessResult(detailCompanyMap);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        Map<String,Object> param=RequestUntil.getParams();
        PageInfo pageInfo=sCompanyService.getByPage(page,size,(Map<String,Object>)param.get("search"));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/drop")
    public Result drop() {
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 加载公司信息
     * @return
     */
    @PostMapping("/getCompany")
    public SCompany getCompany() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        return sCompanyService.findById(currentInfo.getCompanyId());
    }

    /**
     * 保存公司信息
     * @return
     */
    @PostMapping("/saveCompany")
    public Result saveCompany(SCompany sCompany) {
        //修改完数据后，把公司只为待审核状态
        sCompany.setAuditStatus(1);
        sCompanyService.update(sCompany);
        return ResultGenerator.genSuccessResult();
    }



    @PostMapping("/audit")
    public Result audit(@RequestParam Integer id,@RequestParam Integer status) {
        SCompany company = sCompanyService.findById(id);
        company.setAuditStatus(status);
        sCompanyService.update(company);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping("/exportCompany")
    public void exportCompany(HttpServletResponse repsonse) {
        Map<String,String> companyTypeMap = Maps.newHashMap();
        Map<String,String> auditStatusMap = Maps.newHashMap();
        companyTypeMap.put("0","资产方");
        companyTypeMap.put("1","资金方");
        auditStatusMap.put("0","待资质认证");
        auditStatusMap.put("1","待审核资质");
        auditStatusMap.put("2","完成资质认证");
        auditStatusMap.put("3","审核不通过");
        Map<String,Object> param=RequestUntil.getParams();
        List<SCompany> list = sCompanyService.getList((Map<String,Object>)param.get("search"));
        List<Map<String,Object>> resultList = Lists.newArrayList();
        if(list != null){
            for(SCompany company: list){
                Map<String,Object> map = Maps.newHashMap();
                map.put("id",company.getId().toString());
                map.put("abbreviationName",company.getAbbreviationName());
                map.put("fullName",company.getFullName());
                int companyTypeKey = company.getCompanyType()==null ? null:company.getCompanyType().intValue();
                int aditStatusKey = company.getAuditStatus()==null ? null:company.getAuditStatus().intValue();
                map.put("companyType",companyTypeMap.get(companyTypeKey+""));
                map.put("auditStatus",auditStatusMap.get(aditStatusKey+""));
                resultList.add(map);
            }
        }
        String[] titles = {"公司ID","简称","全称","账号类型","账号状态"};
        String[] datakeys ={"id","abbreviationName","fullName","companyType","auditStatus"};
        ExcelUtil.exportXlsx(repsonse, "合作伙伴", titles, resultList,datakeys);
    }


    /**
     *  导出公司相关模板
     * @param repsonse
     * @param type 1 导出带资质认证的企业，2 导出资产公司信息模板，3 导出资金公司信息模板
     */
    @RequestMapping("/exportCompanyInfoModel")
    public void exportCompanyInfoModel(HttpServletResponse repsonse,@RequestParam Integer type) {
        if(type.intValue() == 1){
            String[] titles = {"公司ID","简称","全称","公司介绍","联系人","电话","邮箱","通讯地址","企业法人身份证","身份证正面url","身份证反面url","社会信用代码证","社会信用代码证url","银行开户许可证","银行开户许可证url","银行信用证","银行信用证url"};
            String[] datakeys ={"id","abbreviation_name","full_name","dsc","link_man","mobile","email","address","id_number","id_card_first_url","id_card_second_url","unify_credit_num","unify_credit_url","opening_bank_num","opening_bank_url","bank_credit_num","bank_credit_url"};
            List<Map<String,Object>> resultList = sCompanyService.selectCompanyExeclModel();
            ExcelUtil.exportXlsx(repsonse, "待资质认证合作伙伴", titles, resultList,datakeys);
        }
        if(type.intValue() == 2){
            String[] titles = {"公司ID","简称","全称","可提供资产类型（如11，22 用英文都号码隔开）","资产规模（单位亿，请输入数字）","月新增规模（单位亿，请输入数字）","单笔资产期限","单笔资产额度（单位元，请输入数字）","逾期情况M0","逾期情况M1","逾期情况M2","逾期情况M3","可接受资金成本（单位%，请输入数字）","公司业务区域","是否有履行约(0 否 1 是)","是否有担保人(0 否 1 是)","存量（单元亿，请输入数字）","起投量（单位亿，请输入数字）","放款形式","汇款形式","保证金（0：无，1：有）"};
            String[] datakeys ={"company_no","abbreviation_name","full_name","asset_info","asset_size","month_size","every_deadline","every_sum","overdue_M0","overdue_M1","overdue_M2","overdue_M3","accept_found","business_area","is_performance_insurance","is_guarantee","inventory","starting_amount","loan_form","remittance_form","is_margin"};
            List<Map<String,Object>> resultList = sCompanyService.selectAssetCompanyExeclModel();
            ExcelUtil.exportXlsx(repsonse, "资产公司信息模板", titles, resultList,datakeys);
        }
      /*  if(type.intValue() == 3){
            String[] titles = {"公司ID","简称","全称","接受资产类型（如11，22 用英文都号码隔开）","预测资产需求量（单位亿，请输入数字）","对资产的要求(0~100个字符)","资金年化利率范围","月平均资金成交利率（单位%，请输入数字）","募集失败率（单位%，请输入数字）","公司业务区域（如深圳，佛山 用英文逗号隔开）","平台名称","网址","待收本金(单位亿，请输入数字)","月投金额(单位亿，请输入数字)","有无存管 0无，1有","是否直投 0 否 1是","是否直还 0否，1是"};
            String[] datakeys ={"company_no","abbreviation_name","full_name","asset_info","forecast_asset_demand","found_condition","found_year_rate_rand","month_transaction_rate","collect_fail_rate","business_area","platform_name","url","unReceived_principal","month_investment_amount","escrow","is_direct_investment","is_straight_back"};
            List<Map<String,Object>> resultList = sCompanyService.selectFoundCompanyExeclModel();
            ExcelUtil.exportXlsx(repsonse, "资金公司信息模板", titles, resultList,datakeys);
        }*/
        if(type.intValue() == 3){
            String[] titles = {"公司ID","简称","全称","待收本金（单位亿，请输入数字）","月交易额(单位亿，请输入数字)","放款方式（请输入数字 0非直投，1直投）","还款方式（请输入数字 0非直投，1直投）","保证金（单位%，请输入数字）"};
            String[] datakeys ={"company_no","abbreviation_name","full_name","unReceived_principal","month_transaction_amount","is_direct_investment","is_straight_back","margin"};
            List<Map<String,Object>> resultList = sCompanyService.selectFoundCompanyExeclModel();
            ExcelUtil.exportXlsx(repsonse, "资金公司信息模板", titles, resultList,datakeys);
        }
    }


    /**
     * 导入合作伙伴认证信息
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadCompanyExcel", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("文件上传")
    public Result uploadCompanyExcel(@RequestParam(value = "file") MultipartFile file,HttpServletResponse repsonse,@RequestParam Integer type){
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        try {
            List<List<Object>> list = ExcelUtil.getDataByExcel(file);
            sCompanyService.importCompany(list,type.intValue());
        }catch (Exception e){
            Result result = ResultGenerator.genFailResult("fail");
            result.setMessage("导入失败！请检查数据格式或重新导出模板后重试");
            return result;
        }
        Result result = ResultGenerator.genSuccessResult();
        result.setMessage("导入成功！");
        return result;
    }

}
