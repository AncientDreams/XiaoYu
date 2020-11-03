package com.xiaoyu.user.sentinel.fallback;

import com.xiaoyu.common.core.bo.R;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 * 用户服务接口熔断处理
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/30 9:22
 */
@Slf4j
public class UserFallback {

    public static R listBack(Throwable e) {
        log.error(e.getMessage(), e);
        return R.fail("查询错误：" + e.getMessage());
    }

}
