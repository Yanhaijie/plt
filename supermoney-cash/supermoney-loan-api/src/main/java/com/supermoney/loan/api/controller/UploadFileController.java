package com.supermoney.loan.api.controller;

import com.supermoney.loan.api.service.FileServer;
import com.supermoney.loan.api.service.SBussLabelService;
import com.supermoney.loan.api.utils.AppProperties;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bear on 2018/1/14.
 */
@RestController
@RequestMapping("/s/upload")
@Api(value = "/s/upload", description = "文件上传")
public class UploadFileController {


    @Autowired
    private FileServer fileServer;

    @Resource
    private SBussLabelService sBussLabelService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/file", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("文件上传")
    public Result fileUpdate(@RequestParam(value = "file") MultipartFile file,@RequestParam(value="type:img,pdf,txt,apk") String type) {
        return  fileServer.fileUpdate(file,type);
    }

    /**
     * 上传base64图片
     * @param baseStr
     * @return
     */
    @RequestMapping(value = "/base64-img", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("base64Img上传")
    public Result imgUpdate(String  baseStr) {
       return  fileServer.imgUpdate(baseStr);
    }

    /**
     * 上传业务标签，内部使用.
     * @param pwd
     * @param labelName
     * @param lableVal
     * @return
     */
    @ApiOperation("上传业务标签")
    @PostMapping("/busslable")
    public Result addBanner(@RequestParam String pwd ,@RequestParam  String labelName,@RequestParam String lableVal)
    {
        return   sBussLabelService.saveLable(pwd,labelName,lableVal);
    }

}
