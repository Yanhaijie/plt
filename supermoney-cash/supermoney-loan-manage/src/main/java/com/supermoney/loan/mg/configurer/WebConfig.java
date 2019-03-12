package com.supermoney.loan.mg.configurer;

import com.alibaba.fastjson.JSON;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.ResultCode;
import org.apache.commons.lang.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2017-11-21.
 */

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    @Autowired
//    private  GwtOauthClient gwtOauthClient;


    @Bean
    public RestTemplate restTemplate(){
        return  restTemplateBuilder.build();
    }

//    /**
//     * oauth-client
//     * @return
//     */
//    @Bean
//    public GwtOauthClient gwtOauthClient(){
//        return  new GwtOauthClientImpl();
//    }


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
                    result.setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL.code);
                    result.setMessage(e.getMessage());

                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR.code);
                    result.setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");

                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
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
        //!"dev".equals(env)
        if (true) { //开发环境忽略签名认证
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    //验证是否登录
                    logger.info("url:" + request.getRequestURI());
                   // UserInfo userInfo=(UserInfo)request.getSession().getAttribute(Constants.ADMIN_USERINFO);
                    if("OPTIONS".equals(request.getMethod())){
                        return true;
                    }
                    Boolean canAccess=hasOauthToken(request);
                    if (canAccess) {
                        return true;
                    } else {
                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                        Result result = new Result();
                        result.setCode(ResultCode.UNAUTHORIZED.code);
                        result.setMessage("签名认证失败");
                        responseResult(response, result);
                        return false;
                    }
                }
            });

        }
    }

    /**
     * 是否有oauth权限
     * @param request
     * @return
     */
    public boolean hasOauthToken(HttpServletRequest request){
//        String accessToken=request.getParameter("access_token");
//        if(StringUtils.isBlank(accessToken))return  false;
//        OauthUserInfo userInfo=(OauthUserInfo)request.getSession().getAttribute(Constants.SESSION_USERINFO);
//        if(StringUtils.isNotBlank(accessToken) && userInfo==null ){
//            if(gwtOauthClient.getOauthUserInfo(accessToken)){
//                userInfo=gwtOauthClient.getUserInfo();
//                request.getSession().setAttribute(Constants.SESSION_USERINFO,userInfo);
//            }
//        }
       // String path=request.getServletPath().substring(1,request.getServletPath().length());
        //gwtOauthClient.CanAccessRoute(accessToken,path);
        return  true;
    }

    /**
     * 校验用户是否有权限
     * @param userInfo
     * @return
     */
    private List<String> pathsToSkip = Arrays.asList(
            "login",
            "users/check",
            "swagger-resources/configuration/ui",
            "swagger-resources",
            "v2/api-docs",
            "swagger-resources/configuration/security");
    private  boolean validUser(HttpServletRequest request){
        //不需要验证的控制器
        String path=request.getServletPath().substring(1,request.getServletPath().length());
        if(pathsToSkip.contains(path)){
            return  true;
        }
        //用户不存在
//        if(userInfo==null){
//            return  false;
//        }
        //没有权限
//        if(userInfo.getAccess().contains(path)){
//            return  true;
//        }
        return  false;
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
        //开发环境下开启跨域
//        if ("dev".equals(env)) {
//            registry.addMapping("/**")
//                    .allowedOrigins("*")
//                    .allowedMethods("GET", "POST", "DELETE", "PUT")
//                    .allowCredentials(true)
//                    .maxAge(3600);
//        }
    }



}