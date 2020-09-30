package com.xiaoyu.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.common.security.annotation.PermissionAuthorize;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.sentinel.fallback.UserFallback;
import com.xiaoyu.user.service.ISystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/21 13:55
 */
@RestController
@AllArgsConstructor
public class UserController {

    ISystemUserService iSystemUserService;

    @RequestMapping(value = "/list")
    @SentinelResource(value = "list",blockHandlerClass = UserFallback.class,blockHandler = "listBlock",
            fallbackClass = UserFallback.class,fallback = "listBack")
    public R list() throws Exception {
        throw  new Exception("测试熔断");
//        return R.success("yes");
    }

    @RequestMapping(value = "/user/view")
    @PermissionAuthorize
    public R<List<SystemUser>> test() {
        return R.success(iSystemUserService.list());
    }
}
