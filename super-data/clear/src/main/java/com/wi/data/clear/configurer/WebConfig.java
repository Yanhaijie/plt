package  com.wi.data.clear.configurer;


import com.alibaba.fastjson.JSON;
import com.wi.data.clear.utils.Result;
import com.wi.data.clear.utils.ResultCode;
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
import java.util.*;


/**
 * Created by admin on 2018-06-21
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
                    result.setMessage("api [" + request.getRequestURI() + "] nothing");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL.code);
                    result.setMessage(e.getMessage());

                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR.code);
                    result.setMessage("api [" + request.getRequestURI() + "] error，plase later try again!");

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
        //!"dev".equals(env)
        if (true) { //开发环境忽略签名认证
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    if (true) {
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
     * 打印参数
     * @param request
     */
    public void  printParam(HttpServletRequest request){
        Map<String,String> parmMap=new HashMap<String,String>();
        //方式一：getParameterMap()，获得请求参数map
        Map<String,String[]> map= request.getParameterMap();
        //参数名称
        Set<String> key=map.keySet();
        //参数迭代器
        Iterator<String> iterator = key.iterator();
        while(iterator.hasNext()){
            String k=iterator.next();
            parmMap.put(k, map.get(k)[0]);
        }
        logger.info("parmMap:"+parmMap.toString());

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
        //开启跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600);

    }



}