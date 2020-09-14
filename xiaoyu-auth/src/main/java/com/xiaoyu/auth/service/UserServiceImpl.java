package com.xiaoyu.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyu.auth.entity.SystemPermission;
import com.xiaoyu.auth.entity.SystemUser;
import com.xiaoyu.auth.entity.SystemUserRole;
import com.xiaoyu.auth.mapper.SystemRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020-09-13 15:12
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final ISystemUserService iSystemUserService;

    private final ISystemUserRoleService iSystemUserRoleService;

    private final ISystemPermissionService iSystemPermissionService;

    private final SystemRoleMapper systemRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
        System.out.println("username=" + username);
        //根据账号去数据库查询
        SystemUser systemUser = iSystemUserService.
                getOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUserName, username));
        if (systemUser == null) {
            //查不到返回空
            return null;
        }
        //查询角色
        List<String> roleNames =  systemRoleMapper.queryUserRole(systemUser.getUserId().toString());
//        List<String> permissionList = new ArrayList<>();
//        List<SystemPermission> systemPermissions = iSystemPermissionService.findByUerName(systemUser.getUserName(), null);
//        systemPermissions.forEach(systemPermission -> permissionList.add(systemPermission.getUrl()));
        // 这里暂时使用静态数据
        return User.withUsername(username).
                password(systemUser.getPassword())
                //关于权限的话，是用传入一个url 地址的数组
                .authorities(roleNames.toArray(new String[0])).build();
    }
}
