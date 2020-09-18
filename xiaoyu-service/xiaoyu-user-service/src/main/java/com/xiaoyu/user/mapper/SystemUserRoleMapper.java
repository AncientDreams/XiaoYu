package com.xiaoyu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoyu.user.entity.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-14
 */
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {

    List<String> queryUserRole(@Param(value = "userId") String userId);
}
