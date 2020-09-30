package com.xiaoyu.common.security.custom;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.common.core.constant.CommonConstants;
import lombok.SneakyThrows;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * <p>
 * AuthenticationEntryPoint  是Spring Security Web一个概念模型接口，顾名思义，他所建模的概念是:“认证入口点”。
 * 它在用户请求处理过程中遇到认证异常时，被ExceptionTranslationFilter用于开启特定认证方案(authentication schema)的认证流程。
 *
 *
 * 当用户请求了一个受保护的资源，但是用户没有通过认证，那么抛出异常，AuthenticationEntryPoint. Commence(..)就会被调用。
 * 这个对应的代码在ExceptionTranslationFilter中，当ExceptionTranslationFilter catch到异常后，就会间接调用AuthenticationEntryPoint。
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/23 13:54
 */
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException
    ) {
        int code = 401;
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(CommonConstants.CONTENT_TYPE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        if (authException != null) {
            jsonObject.put("msg", authException.getMessage());
        }
        response.setStatus(code);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(jsonObject.toJSONString());
    }
}
