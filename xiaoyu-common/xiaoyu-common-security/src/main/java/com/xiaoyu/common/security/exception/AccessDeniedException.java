package com.xiaoyu.common.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统拒绝访问抛出此异常
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/27 14:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

}
