package com.xiaoyu.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.auth.entity.SystemUser;
import com.xiaoyu.auth.mapper.SystemUserMapper;
import com.xiaoyu.auth.service.ISystemUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxy
 * @since 2020-09-13
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

}
