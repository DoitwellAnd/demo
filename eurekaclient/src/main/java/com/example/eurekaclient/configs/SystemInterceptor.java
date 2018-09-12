package com.example.eurekaclient.configs;

import com.alibaba.fastjson.JSON;
import com.example.eurekaclient.log.SystemLogger;
import com.example.eurekaclient.log.SystemLoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器。
 */
public class SystemInterceptor implements HandlerInterceptor {
    private final SystemLogger logger = SystemLoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
            Object handler) throws Exception {
        // 处理请求前的内容
        if(logger.isInfoEnabled()){
            logger.infoURL(request.getRequestURL().toString());
            logger.infoArgs(JSON.toJSONString(request.getParameterMap()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,
            Object handler,ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,
            Object handler,Exception ex) throws Exception {
    }
}
