package com.mar.filter;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

import com.mar.bean.vo.ResponseResult;
import com.mar.exception.TotalException;
import com.mar.service.RedisService;
import com.mar.utils.RedisUtils;
import com.mar.utils.StateEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@WebFilter(urlPatterns = {"/api/user/passport/logout/*","/api/cart/*"},filterName = "LoginFiter")
@Slf4j
public class LoginFiter implements Filter{

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisService redisService;


    public void init(FilterConfig config) throws ServletException {
//        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");
        String userTempId = httpServletRequest.getHeader("token");
        System.out.println(url);
        if(token==null && userTempId==null) {
            TotalException totalException = new TotalException(StateEnum.USER_ERROR_NOLOGIN.getCode(),
                    StateEnum.USER_ERROR_NOLOGIN.getMessage(),
                    StateEnum.USER_ERROR_NOLOGIN.getMessage());
            httpServletRequest.setAttribute("filter.error", totalException);
            httpServletRequest.getRequestDispatcher("/api/user/nologin").forward(request, response);
            return;
            }else if (!redisTemplate.hasKey(token)) {
                TotalException totalException = new TotalException(StateEnum.USER_ERROR_WRONGJWT.getCode(),
                        StateEnum.USER_ERROR_WRONGJWT.getMessage(),
                        StateEnum.USER_ERROR_WRONGJWT.getMessage());
                httpServletRequest.setAttribute("filter.error", totalException);
                httpServletRequest.getRequestDispatcher("/api/user/nologin").forward(request, response);
                return;
            }
        //刷新过期时间
        redisTemplate.expire(token,1800, TimeUnit.SECONDS);

        filterChain.doFilter(request,response);
    }
}
