package com.supermoney.loan.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017-12-26.
 */
@Controller
public class ViewRouterController {

    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "super-money-api-info";
    }
}
