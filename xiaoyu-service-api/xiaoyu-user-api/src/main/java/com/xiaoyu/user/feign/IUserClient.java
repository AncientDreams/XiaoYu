/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.xiaoyu.user.feign;


import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.common.core.constant.AppConstant;
import com.xiaoyu.user.entity.SystemRole;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.fallback.UserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * <p>
 * User Feign接口类
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/15 11:14
 */
@FeignClient(
        value = AppConstant.APPLICATION_USER_NAME, path = "/user", fallbackFactory = UserFallbackFactory.class
)
public interface IUserClient {


    /**
     * 根据账号获取用户信息
     *
     * @param username 账号
     * @return ResultBody<SystemUser>
     */
    @GetMapping("/user")
    R<SystemUser> userByUsername(@RequestParam("username") String username);

    /**
     * 根据账号获取用户所拥有权限信息
     *
     * @param userName 账号
     * @return ResultBody<SystemPermission>
     */
    @GetMapping("/getSystemPermissions")
    R<List<String>> getSystemPermissions(@RequestParam("userName") String userName);

    /**
     * 根据账号获取用户角色信息
     *
     * @param userName 用户名称
     * @return ResultBody<SystemUser>
     */
    @GetMapping("/getRoles")
    R<List<SystemRole>> queryUserRolesByUserId(@RequestParam("userName") String userName);

    /**
     * xiufu
     *
     * @param userName 用户名称
     * @return ResultBody<SystemUser>
     */
    @GetMapping("/updateUser")
    R updateUserEmail(@RequestParam("userName") String userName) throws Exception;
}
