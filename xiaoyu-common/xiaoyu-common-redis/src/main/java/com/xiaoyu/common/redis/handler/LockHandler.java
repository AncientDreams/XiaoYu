package com.xiaoyu.common.redis.handler;

import com.xiaoyu.common.redis.annotation.Lock;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 解析注解 {@link com.xiaoyu.common.redis.annotation.Lock}
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/10/9 16:45
 */
@AllArgsConstructor
@Aspect
public class LockHandler {

    private final RedissonClient redissonClient;

    @Around("@annotation(lock)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Lock lock) {
        RLock rLock = null;
        Object object = null;
        try {
            Class<?> pointClass = proceedingJoinPoint.getTarget().getClass();
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = pointClass.getDeclaredMethod(signature.getName(),
                    signature.getMethod().getParameterTypes());
            Lock methodLock = method.getAnnotation(Lock.class);
            //key 类的全类名加上方法名称
            String key = pointClass.getName().concat(method.getName());
            rLock = redissonClient.getLock(key);
            //加锁
            rLock.lock(methodLock.timeout(), TimeUnit.MILLISECONDS);
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //解锁
            if (rLock != null) {
                rLock.unlock();
            }
        }
        return object;
    }

}
