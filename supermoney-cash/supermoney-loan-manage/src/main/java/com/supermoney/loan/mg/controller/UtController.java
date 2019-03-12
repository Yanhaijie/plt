package com.supermoney.loan.mg.controller;

import com.supermoney.loan.mg.service.UtService;
import com.supermoney.loan.mg.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.RegEx;
import javax.annotation.Resource;

@RestController
@RequestMapping("/s/ut")
@Api(value = "/s/ut",description = "")
public class UtController {

    @Resource
    private UtService utService;

    /**
     * 导入电话号码
     * @param file
     * @return
     */
    @RequestMapping(value = "/import-numbers", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("导入电话号码")
    public Result importNumbers(@RequestParam(value = "file") MultipartFile file) {
        return  utService.importNumbers(file);
    }

    /**
     * 发送营销短信
     * @param numbers
     * @param content
     * @return
     */
    @RequestMapping(value = "/pushmsg", produces = "application/json; charset=utf-8" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("发送营销短信")
    public Result pushMsg(@RequestParam String[] numbers,@RequestParam String content) {
        return  utService.pushMsg(numbers,content);
    }


}
