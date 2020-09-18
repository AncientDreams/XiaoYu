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


import bo.ResultBody;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoyu.user.entity.SystemPermission;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.entity.SystemUserRole;
import com.xiaoyu.user.service.ISystemPermissionService;
import com.xiaoyu.user.service.ISystemUserService;
import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户服务Feign实现类
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/15 10:16
 */
@RestController
@AllArgsConstructor
public class UserClient implements IUserClient {

    private ISystemUserService iSystemUserService;

    private final ISystemPermissionService iSystemPermissionService;

    @Override
    @Cacheable(value = "userName", key = "#username")
    public ResultBody<SystemUser> userByUsername(String username) {
        return ResultBody.success(iSystemUserService.getOne(Wrappers.<SystemUser>lambdaQuery()
                .select(SystemUser::getUserId, SystemUser::getUserName, SystemUser::getPassword).eq(SystemUser::getUserName, username)));
    }

    @Override
    @Cacheable(value = "systemPermission",key = "#userName")
    public ResultBody<SystemPermission> getSystemPermissions(String userName) {
        return ResultBody.success(iSystemPermissionService.findByUerName(userName));
    }

    @Override
    @Cacheable(value = "userRoles", key = "#userName")
    public ResultBody<SystemUserRole> queryUserRolesByUserId(String userName) {
        return null;
    }


}
