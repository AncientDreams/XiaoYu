package com.xiaoyu.user.fallback;

import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.user.entity.SystemRole;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.feign.IUserClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户服务远程调用异常熔断处理
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/30 10:19
 */
@Component
public class UserFallbackFactory implements FallbackFactory<IUserClient> {

    @Override
    public IUserClient create(Throwable throwable) {
        return new IUserClient() {
            @Override
            public R<SystemUser> userByUsername(String username) {
                return R.fail(throwable.getMessage());
            }

            @Override
            public R<List<String>> getSystemPermissions(String userName) {
                return R.fail(throwable.getMessage());
            }

            @Override
            public R<List<SystemRole>> queryUserRolesByUserId(String userName) {
                return R.fail(throwable.getMessage());
            }
        };
    }
}
