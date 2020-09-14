package com.xiaoyu.auth.service;

import com.xiaoyu.auth.entity.SystemPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-14
 */
public interface ISystemPermissionService extends IService<SystemPermission> {

    /**
     * 获取当前用户的权限
     *
     * @param userName   用户名
     * @param exhibition 侧边栏是否显示
     * @return List<SystemPermission>
     */
    List<SystemPermission> findByUerName(String userName, String exhibition);

}
