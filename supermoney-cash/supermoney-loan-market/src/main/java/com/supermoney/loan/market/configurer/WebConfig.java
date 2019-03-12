package com.supermoney.loan.market.configurer;

import com.alibaba.fastjson.JSON;
import com.supermoney.loan.market.service.SMerchantUserService;
import com.supermoney.loan.market.utils.BussCodeGenerate;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017-11-21.
 */

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate(){
        return  restTemplateBuilder.build();
    }

    @Autowired
    private SMerchantUserService sMerchantUserService;

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(ResultCode.FAIL.code);
                    result.setMessage(e.getMessage());
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {

                    result.setCode(ResultCode.NOT_FOUND.code);
                    result.setMessage("api [" + request.getRequestURI() + "] does not exist!");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL.code);
                    result.setMessage(e.getMessage());

                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR.code);
                    result.setMessage("api [" + request.getRequestURI() + "] faild!!");

                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("api [%s] error，method：%s.%s，info：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                //验证是否登录
                logger.info("url:" + request.getRequestURI());
                if (apiSignCheck(request)) {
                    return true;
                } else {
                    logger.warn("sign check faild!，api：{}，IP：{}，parameters：{}",
                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                    Result result = new Result();
                    result.setCode(ResultCode.UNAUTHORIZED.code);
                    result.setMessage("sign check faild!");
                    responseResult(response, result);
                    return false;
                }
            }
        });
    }

    /**
     * 请求签名校验
     * @param request
     * @return
     */
    public boolean apiSignCheck(HttpServletRequest request){
        //是否HTTPS请求
        //过滤不校验
        String notcheckStr="/market/buss/";
        String uri=request.getRequestURI();
        if(uri.length()>notcheckStr.length() && uri.substring(0,notcheckStr.length()).equals(notcheckStr)){
            return  true;
        }
        //参数校验
        String authorization=request.getHeader("APISIGN");
        String punique=request.getHeader("S-PUNIQUE-KEY");
        logger.info("APISIGN:"+authorization+" S-PUNIQUE-KEY:"+punique);

        if(authorization==null || StringUtils.isBlank(authorization)){
            return  false;
        }
        if(punique==null || StringUtils.isBlank(punique)){
            return  false;
        }
        //scretKey校验
        String scretkey= BussCodeGenerate.authorizationToScretkey(authorization);
        if( scretkey==null || StringUtils.isBlank(scretkey)){
            return  false;
        }
        if(!sMerchantUserService.checkScretKey(scretkey,request)){
            return false;
        }


        return  true;
    }


    /**
     * 获取IP
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
    /**
     * 返回结果处理
     * @param response
     * @param result
     */
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "DELETE", "PUT")
                    .allowCredentials(true)
                    .maxAge(3600);

    }


    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = StringUtils.isNotBlank(source) ? sdf.parse((String) source): null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}