package com.xiaoyu.auth.service.impl;

import com.xiaoyu.auth.entity.SystemPermission;
import com.xiaoyu.auth.mapper.SystemPermissionMapper;
import com.xiaoyu.auth.service.ISystemPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-14
 */
@Service
@AllArgsConstructor
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission> implements ISystemPermissionService {


    private SystemPermissionMapper systemPermissionMapper;

    @Override
    public List<SystemPermission> findByUerName(String userName, String exhibition) {
        return systemPermissionMapper.findByUerName(userName, exhibition);
    }
}
