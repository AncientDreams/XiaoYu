package com.xiaoyu.user.sentinel.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiaoyu.common.core.bo.R;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户服务接口限流处理
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/10/9 9:53
 */
@Slf4j
public class UserBlock {

    public static R listBlock(BlockException e) {
        log.error(e.getMessage(), e);
        return R.fail("服务器繁忙，请稍后再试！");
    }
}
