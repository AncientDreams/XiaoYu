package com.xiaoyu.common.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 注解功能： 标示在方法上表示次方法必须要有访问的URL权限
 * <p>
 * 注解使用范围: 在controller中
 * <p>
 * 注解的目的: 更加方便的通过Permission来控制权限
 * </p>
 *
 * 注解处理类：{@link com.xiaoyu.common.security.handler.PermissionAuthorizeHandler}
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/27 9:50
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAuthorize {

    /**
     * url 资源路径，表示拥有哪个url权限后能进行访问，默认会根据request mapping中的value中的去匹配
     * <p>
     * value 参数是一个数组，如果数组中的url 有其中一条拥有就可以访问
     */
    String[] value() default {};
}
