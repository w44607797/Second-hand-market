package com.mar.config;


import com.mar.Interceptor.CustomInterceptor;
import com.mar.filter.UserInfoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guokaifeng
 * @createDate: 2022/4/8
 **/

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CustomInterceptor customInterceptor= new CustomInterceptor();
        registry.addInterceptor(customInterceptor);
    }

    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
        UserInfoFilter userInfoFilter = new UserInfoFilter();
        FilterRegistrationBean<UserInfoFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(userInfoFilter);
        bean.setName("userParamFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);

        return bean;
    }
}
