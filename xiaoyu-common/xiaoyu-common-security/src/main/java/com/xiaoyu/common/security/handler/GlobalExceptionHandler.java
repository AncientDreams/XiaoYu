package com.xiaoyu.common.security.handler;


import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.common.security.exception.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * controller 全局异常处理,针对性处理AccessDeniedException 异常
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/27 14:15
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public String exceptionHandler() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", HttpStatus.FORBIDDEN.value());
        jsonObject.put("msg", "Permission denied,Please authorize");
        return jsonObject.toJSONString();
    }

}
