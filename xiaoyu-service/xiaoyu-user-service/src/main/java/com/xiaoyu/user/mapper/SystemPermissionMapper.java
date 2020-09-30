package com.xiaoyu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyu.user.entity.SystemPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-14
 */
public interface SystemPermissionMapper extends BaseMapper<SystemPermission> {

    /**
     * 通过用户名查询拥有的权限url
     *
     * @param userName   用户名
     * @param exhibition 侧边栏是否显示
     * @return List<SystemPermission>
     */
    List<String> findByUerName(@Param("userName") String userName, @Param("exhibition") String exhibition);

}
