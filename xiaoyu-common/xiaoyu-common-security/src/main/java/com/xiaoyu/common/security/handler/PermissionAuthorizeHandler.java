package com.xiaoyu.common.security.handler;

import com.xiaoyu.common.security.annotation.PermissionAuthorize;
import com.xiaoyu.common.security.exception.AccessDeniedException;
import com.xiaoyu.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 注解 @PermissionAuthorize{@link PermissionAuthorize} 处理器
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/27 10:08
 */
@AllArgsConstructor
@Aspect
public class PermissionAuthorizeHandler {

    private final IUserClient iSystemUserService;

    @Around("@annotation(permissionAuthorize)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, PermissionAuthorize permissionAuthorize) {
        try {
            Class<?> pointClass = proceedingJoinPoint.getTarget().getClass();
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = pointClass.getDeclaredMethod(signature.getName(),
                    signature.getMethod().getParameterTypes());

            String[] urls;
            //方法名称
            if (permissionAuthorize.value().length > 0) {
                urls = permissionAuthorize.value();
            } else {
                urls = getUrl(pointClass, method);
            }
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            List<String> url = iSystemUserService.getSystemPermissions(userName).getData();
            //检查权限
            for (String s : urls) {
                if (url.contains(s)) {
                    //放行
                    return proceedingJoinPoint.proceed();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        throw new AccessDeniedException();
    }


    /**
     * 重置URL
     *
     * @param url url
     * @return String
     */
    private String restUrl(String url) {
        return url.startsWith("/") ? url : "/".concat(url);
    }

    private String[] getUrl(Class<?> pointClass, Method method) throws Exception {
        String url = "";
        if (!pointClass.isAnnotationPresent(RestController.class) && !pointClass.isAnnotationPresent(Controller.class)) {
            throw new Exception("注解@PermissionAuthorize 只能使用在 controller ");
        }
        RequestMapping requestMapping = pointClass.getAnnotation(RequestMapping.class);
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            //取第一个
            url = restUrl(requestMapping.value()[0]);
        }
        String[] methodUrl = methodAnnotation.value();
        String[] urlArray = new String[methodUrl.length];
        for (int i = 0; i < methodUrl.length; i++) {
            urlArray[i] = url + (methodUrl[i]);
        }
        return urlArray;
    }
}
