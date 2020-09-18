package com.xiaoyu.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.mapper.SystemUserMapper;
import com.xiaoyu.user.service.ISystemUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-15
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

}
