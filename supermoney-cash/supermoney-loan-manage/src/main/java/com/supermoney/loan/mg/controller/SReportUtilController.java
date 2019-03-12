package com.supermoney.loan.mg.controller;

import com.supermoney.loan.mg.service.SReportUtilService;
import com.supermoney.loan.mg.utils.ExcelUtil;
import com.supermoney.loan.mg.utils.RequestUntil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
* Created by wenyuhao on 2018/04/13.
*/
@RestController
@RequestMapping("/s/report/util")
@Api(value = "/s/report/util",description = "")
public class SReportUtilController {
    @Resource
    private SReportUtilService sReportUtilService;

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse repsonse) {
        Map<String,Object> param= RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        String sqlStr = (String)searchMap.get("sql");
        //表格数据
        List<Map<String, Object>> list = sReportUtilService.executeSelect(searchMap);
        ExcelUtil.exportXlsx(repsonse, "report", null, list,searchMap);
    }
}
