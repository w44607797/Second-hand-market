package com.mar.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author guokaifeng
 * @createDate: 2022/4/23
 **/

@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        pushUserInfo2Body(request, handlerMethod);
//
//        return true;
//    }
//
//    private void pushUserInfo2Body(HttpServletRequest request, HandlerMethod handlerMethod) {
//        try {
//            String phone = request.getHeader("phone");
//            String permission = request.getHeader("permission");
//            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//            if (ArrayUtils.isEmpty(methodParameters)) {
//                return;
//            }
//            for (MethodParameter methodParameter : methodParameters) {
//                Class clazz = methodParameter.getParameterType();
////                if (ClassUtils.isAssignable(UserInfoParam.class, clazz)) {
//                System.out.println(request instanceof CustomHttpServletRequestWrapper);
////                    if (request instanceof CustomHttpServletRequestWrapper) {
//                        CustomHttpServletRequestWrapper requestWrapper = (CustomHttpServletRequestWrapper) request;
//                        String body = requestWrapper.getBody();
//                        JSONObject param = JSONObject.parseObject(body);
//                        param.put("phone", phone);
//                        param.put("permission", Objects.isNull(permission) ? null : URLDecoder.decode(permission, "UTF-8"));
//                        requestWrapper.setBody(JSON.toJSONString(param));
//                    }
////                }
////            }
//        } catch (Exception e) {
//            log.warn("fill userInfo to request body Error ", e);
//        }
//    }
}
