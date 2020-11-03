package com.xiaoyu.common.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 基于Redis锁的实现，如需满足更加复杂的业务，请自行实现
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/10/9 15:54
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    /**
     * 锁超时时间，单位毫秒，默认超时时间10s
     */
    long timeout() default 10000;
}
