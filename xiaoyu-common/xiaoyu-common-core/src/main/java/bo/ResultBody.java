package bo;

import constant.ResultConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 全局统一的返回结果
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/15 0015 11:16
 */
@Data
public class ResultBody<T> implements Serializable {

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
     * 响应对象
     */
    private T data;

    /**
     * 响应集合
     */
    private Collection<T> collection;

    public ResultBody(String code, boolean result, String msg, T data) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public ResultBody(String code, boolean result, String msg, T data, Collection<T> collection) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.data = data;
        this.collection = collection;
    }

    public ResultBody(String code, boolean result, String msg, Collection<T> collection) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.collection = collection;
    }

    public ResultBody() {
    }

    public static <T> ResultBody<T> success(String msg, T data) {
        return new ResultBody<>(ResultConstant.SUCCESS_CODE, true, msg, data);
    }

    public static <T> ResultBody<T> success(String msg) {
        return new ResultBody<>(ResultConstant.SUCCESS_CODE, true, msg, null);
    }

    public static <T> ResultBody<T> success(T data) {
        return new ResultBody<>(ResultConstant.SUCCESS_CODE, true, ResultConstant.SUCCESS, data);
    }

    public static <T> ResultBody<T> success(Collection<T> collection) {
        return new ResultBody<>(ResultConstant.SUCCESS_CODE, true, ResultConstant.SUCCESS, collection);
    }

    public static <T> ResultBody<T> fail(String msg, T data) {
        return new ResultBody<>(ResultConstant.FAIL_CODE, false, msg, data);
    }

    public static <T> ResultBody<T> fail(String msg) {
        return new ResultBody<>(ResultConstant.FAIL_CODE, false, msg, null);
    }

    public static <T> ResultBody<T> fail(T data) {
        return new ResultBody<>(ResultConstant.FAIL_CODE, false, ResultConstant.FAIL, data);
    }

    public static <T> ResultBody<T> fail(Collection<T> collection) {
        return new ResultBody<>(ResultConstant.FAIL_CODE, false, ResultConstant.FAIL, collection);
    }
}
