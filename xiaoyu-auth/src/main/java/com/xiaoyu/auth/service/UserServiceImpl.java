package com.xiaoyu.auth.service;

import bo.ResultBody;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * <p>
 * UserDetailsService
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020-09-13 15:12
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    private IUserClient iSystemUserService;

    private RedisCacheManager redisCacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("------------- 登录人 : {}", username);

        SystemUser systemUser = null;
        final String userNameCache = "userName";
        //先查询缓存
        Cache cache = redisCacheManager.getCache(userNameCache);
        if (cache != null) {
            try {
                systemUser = (SystemUser) Objects.requireNonNull(cache.get(username, ResultBody.class)).getData();
            } catch (Exception ignored) {
            }
        }

        //根据账号去数据库查询
        if (systemUser == null) {
            systemUser = iSystemUserService.userByUsername(username).getData();
            if (systemUser == null) {
                return null;
            }
        }

        //如果需要扩展信息，将 信息转为json保存到withUsername  JSONObject.toJSONString(systemUser)
        return User.withUsername(systemUser.getUserName()).
                password(systemUser.getPassword())
                //关于权限的话，是用传入一个url 地址的数组，此处 不传，到网关后统一鉴权
                .authorities("1").build();
    }
}
