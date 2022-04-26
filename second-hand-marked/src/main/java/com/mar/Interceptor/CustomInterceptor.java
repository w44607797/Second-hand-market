package com.mar.Interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mar.bean.vo.UserInfoParam;
import com.mar.wrapper.CustomHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * @author guokaifeng
 * @createDate: 2022/4/23
 **/

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        pushUserInfo2Body(request, handlerMethod);

        return true;
    }

    private void pushUserInfo2Body(HttpServletRequest request, HandlerMethod handlerMethod) {
        try {
            String phone = request.getHeader("phone");
            String permission = request.getHeader("permission");
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            if (ArrayUtils.isEmpty(methodParameters)) {
                return;
            }
            for (MethodParameter methodParameter : methodParameters) {
                Class clazz = methodParameter.getParameterType();
//                if (ClassUtils.isAssignable(UserInfoParam.class, clazz)) {
                System.out.println(request instanceof CustomHttpServletRequestWrapper);
//                    if (request instanceof CustomHttpServletRequestWrapper) {
                        CustomHttpServletRequestWrapper requestWrapper = (CustomHttpServletRequestWrapper) request;
                        String body = requestWrapper.getBody();
                        JSONObject param = JSONObject.parseObject(body);
                        param.put("phone", phone);
                        param.put("permission", Objects.isNull(permission) ? null : URLDecoder.decode(permission, "UTF-8"));
                        requestWrapper.setBody(JSON.toJSONString(param));
                    }
//                }
//            }
        } catch (Exception e) {
            log.warn("fill userInfo to request body Error ", e);
        }
    }
}
