package com.mar.filter;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

import com.mar.bean.vo.ResponseResult;
import com.mar.exception.TotalException;
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
import java.io.IOException;

@WebFilter(urlPatterns = "/*",filterName = "LoginFiter")
@Slf4j
public class LoginFiter implements Filter{

    @Autowired
    RedisTemplate redisTemplate;

    public void init(FilterConfig config) throws ServletException {
//        log.info("过滤器初始化");
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");
        String userTempId = httpServletRequest.getHeader("token");
        if(token==null || userTempId==null){
            TotalException totalException = new TotalException(StateEnum.USER_ERROR_NOLOGIN.getCode(),
                    StateEnum.USER_ERROR_NOLOGIN.getMessage(),
                    StateEnum.USER_ERROR_NOLOGIN.getMessage());
//            httpServletRequest.getRequestDispatcher("/nologin").forward(request,response);
        }
        if(redisTemplate.hasKey(token)==null){
            throw new TotalException(StateEnum.USER_ERROR_WRONGJWT.getCode(),
                    StateEnum.USER_ERROR_WRONGJWT.getMessage(),
                    StateEnum.USER_ERROR_WRONGJWT.getMessage());
        }
        filterChain.doFilter(request,response);

    }
}
