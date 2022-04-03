package com.mar.filter;

/**
 * @author guokaifeng
 * @createDate: 2022/4/4
 **/

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFiter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");
        String userTempId = httpServletRequest.getHeader("token");
        if(token!=null || userTempId!=null){

        }else {

        }
    }
}
