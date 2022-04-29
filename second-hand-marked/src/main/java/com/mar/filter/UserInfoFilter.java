package com.mar.filter;

import com.mar.wrapper.CustomHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * @author guokaifeng
 * @createDate: 2022/4/23
 **/
//@WebFilter(urlPatterns = {"/*"},filterName = "UserInfoFilter")
@Slf4j
public class UserInfoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        CustomHttpServletRequestWrapper customHttpServletRequestWrapper = null;
//        try {
//            HttpServletRequest req = (HttpServletRequest)request;
//            customHttpServletRequestWrapper = new CustomHttpServletRequestWrapper(req);
//        }catch (Exception e){
//            log.warn("customHttpServletRequestWrapper Error:", e);
//        }
        chain.doFilter((Objects.isNull(customHttpServletRequestWrapper) ? request : customHttpServletRequestWrapper), response);
    }
}
