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
 * 用户服务远程调用异常统一处理，这里是指执行完业务异常后的返回消息处理，sentinel 熔断是异常到了一定的阈值
 * 后，直接快速返回异常信息，并不会执行相关业务逻辑。
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

            @Override
            public R updateUserEmail(String userName) {
                return R.fail(throwable.getMessage());
            }
        };
    }
}
