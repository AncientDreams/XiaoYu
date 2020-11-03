package com.xiaoyu.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.common.security.annotation.PermissionAuthorize;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.sentinel.block.UserBlock;
import com.xiaoyu.user.sentinel.fallback.UserFallback;
import com.xiaoyu.user.service.ISystemUserService;
import io.swagger.annotations.Api;
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
@Api(value = "用户服务接口")
@AllArgsConstructor
public class UserController {

    private final ISystemUserService iSystemUserService;

    @RequestMapping(value = "/list")
    @SentinelResource(value = "list", blockHandlerClass = UserBlock.class, blockHandler = "listBlock",
            fallbackClass = UserFallback.class, fallback = "listBack")
    public R list() {
        return R.success("yes");
    }

    @RequestMapping(value = "/user/view")
    @PermissionAuthorize
    public R<List<SystemUser>> test() {
        return R.success(iSystemUserService.list());
    }
}
