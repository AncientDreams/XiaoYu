package com.xiaoyu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyu.user.entity.SystemPermission;

import java.util.List;

/**
 * <p>
 * 系统用户权限服务接口
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/18 14:01
 */
public interface ISystemPermissionService extends IService<SystemPermission> {

    /**
     * 通过用户名查询拥有的权限URL
     *
     * @param userName 用户名
     * @return List<SystemPermission>
     */
    List<String> findByUerName(String userName);
}
