package com.xiaoyu.common.core.bo;

import com.xiaoyu.common.core.constant.ResultConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 全局统一的返回结果
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/15 11:16
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应结果
     */
    private boolean result;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public R(String code, boolean result, String msg) {
        this.code = code;
        this.result = result;
        this.msg = msg;
    }

    public R(String code, boolean result, String msg, T data) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public R() {
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<>(ResultConstant.SUCCESS_CODE, true, msg, data);
    }

    public static <T> R<T> success(String msg) {
        return new R<>(ResultConstant.SUCCESS_CODE, true, msg);
    }

    public static <T> R<T> success(T data) {
        return new R<>(ResultConstant.SUCCESS_CODE, true, ResultConstant.SUCCESS, data);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R<>(ResultConstant.FAIL_CODE, false, msg, data);
    }

    public static <T> R<T> fail(String msg) {
        return new R<>(ResultConstant.FAIL_CODE, false, msg);
    }

    public static <T> R<T> fail(T data) {
        return new R<>(ResultConstant.FAIL_CODE, false, ResultConstant.FAIL, data);
    }
}
