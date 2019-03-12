package com.supermoney.loan.market.controller;
import com.supermoney.loan.market.service.impl.NIKBussService;
import com.supermoney.loan.market.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* Created by bear on 2018/10/18.
*/
@RestController
@RequestMapping("/api/nik")
public class SNikController {
    @Resource
    private NIKBussService nikBussService;

    @PostMapping("/check")
    public Result check(String name, String nik, HttpServletRequest request) {
       return nikBussService.check(name,nik,request.getAttribute("merchantId").toString());
    }
}

