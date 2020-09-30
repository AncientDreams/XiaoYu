package com.xiaoyu.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.user.entity.SystemPermission;
import com.xiaoyu.user.mapper.SystemPermissionMapper;
import com.xiaoyu.user.service.ISystemPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户权限服务接口实现
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/18 14:02
 */
@Service
@AllArgsConstructor
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission> implements ISystemPermissionService {

    final SystemPermissionMapper systemPermissionMapper;

    @Override
    public List<String> findByUerName(String userName) {
        return systemPermissionMapper.findByUerName(userName, "01");
    }

}
