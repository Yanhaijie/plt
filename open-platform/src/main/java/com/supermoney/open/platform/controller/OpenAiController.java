package com.supermoney.open.platform.controller;

import com.supermoney.open.platform.services.AdvanceAIService;
import com.supermoney.open.platform.services.BaiduAIService;
import com.supermoney.open.platform.services.NIKCheckService;
import com.supermoney.open.platform.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
@Api(value = "",description = "ai接口")
public class OpenAiController {

    @Autowired
    private BaiduAIService baiduAIService;
    @Autowired
    private AdvanceAIService advanceAIService;
    @Autowired
    private NIKCheckService nikCheckService;

    @PostMapping("living")
    public Result livingCheck(String living_img, String merchantId) throws IOException {
        return baiduAIService.checkLivePeople(living_img, merchantId);
    }

    @PostMapping("compare")
    public Result baiduCompare(String face1_img,String face2_img, String merchantId) throws IOException {
        return baiduAIService.baiduCompare(face1_img, face2_img, merchantId);
    }


    @RequestMapping("ocr")
    public Result identityOCR(String ocr_img, String merchantId) {
        return advanceAIService.identityOCR(ocr_img, merchantId);
    }

    @RequestMapping("blacklist")
    public Result blacklist(String name,String idNumber,String phoneNumber, String merchantId) {
        return advanceAIService.blacklistCheck(name, idNumber, phoneNumber, merchantId);
    }

    @RequestMapping("nik")
    public Result checkNik(String nik, String merchantId) {
        return nikCheckService.checkNIK(nik,merchantId);
    }
}
