package com.supers.p2p.assets.controller;

import com.supers.p2p.assets.service.FileServer;
import com.supers.p2p.assets.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by admin on 2018-02-12.
 */
@RestController
@RequestMapping("/s/upload")
@Api(value = "/s/upload", description = "文件上传")
public class UploadFileController {

    @Autowired
    private FileServer fileServer;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/file", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("文件上传")
    public Result fileUpdate(@RequestParam(value = "file") MultipartFile file) {
        return  fileServer.fileUpdate(file,"img");
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
}
