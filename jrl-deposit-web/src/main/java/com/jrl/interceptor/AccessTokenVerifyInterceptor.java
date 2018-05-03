package com.jrl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter{
    private final static Logger logger = LoggerFactory.getLogger(AccessTokenVerifyInterceptor.class);

    private long start;
    private String params;
    private String url;
    private String ip;
    private String method;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        url = request.getRequestURL().toString();
        ip = request.getRemoteAddr();
        method = request.getMethod();
        //解析请求参数
        StringBuffer sb = new StringBuffer("[");
        Enumeration<String> ps = request.getParameterNames();
        while(ps.hasMoreElements()){
            String p = ps.nextElement();
            sb.append(p).append(" = ").append(request.getParameter(p) + ",");
        }
        int i = sb.lastIndexOf(",");
        params = i == -1 ? sb.append("]").toString() : sb.replace(i,i + 1,"]").toString();
        //执行业务
        super.postHandle(request, response, handler, modelAndView);
        logger.info("[{}]-客户端IP:[{}],请求地址:{},请求参数:{},耗时:{}ms",method,ip,url,params,System.currentTimeMillis() - start);

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("这里验证用户accessToken是否有效");
        start = System.currentTimeMillis();
        return super.preHandle(request, response, handler);
    }
}
