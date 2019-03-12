package com.supermoney.loan.mg.Feign;

import com.supermoney.loan.mg.utils.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SUPERMONEY-API")
public interface SupermoneyApiClient {

    /**
     * 服务信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/info")
    public  String  info();

    /**
     * 还款回调失败解决
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/i/buss/fixrepay")
    public Result repayCallBackFix(@RequestParam("bussType") Integer bussType,@RequestParam("relateId") String relateId);

}
