package com.xiaoyu.system.service;

import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.system.entity.SystemLog;
import com.baomidou.mybatisplus.extension.service.IService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-10-30
 */
public interface ISystemLogService extends IService<SystemLog> {

    /**
     * 测试分布式事务
     *
     * @return R
     * @throws Exception 异常
     */
    R testSeataGlobalTransactional() throws Exception;
}
