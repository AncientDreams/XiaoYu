package com.xiaoyu.user.sentinel.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiaoyu.common.core.bo.R;


/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/30 9:22
 */
public class UserFallback {

    public static R listBlock(BlockException e) {
        return R.success("已被限流" + e.getMessage());
    }

    public static R listBack(Throwable e) {
        return R.success("服务已熔断" + e.getMessage());
    }

}
