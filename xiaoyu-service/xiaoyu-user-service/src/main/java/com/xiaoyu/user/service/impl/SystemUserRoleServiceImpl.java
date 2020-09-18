package com.xiaoyu.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.user.entity.SystemUserRole;
import com.xiaoyu.user.mapper.SystemUserRoleMapper;
import com.xiaoyu.user.service.ISystemUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色服务接口实现
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/18 0018 11:23
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {
}
