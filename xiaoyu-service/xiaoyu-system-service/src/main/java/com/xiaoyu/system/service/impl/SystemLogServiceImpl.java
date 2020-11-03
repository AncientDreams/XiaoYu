package com.xiaoyu.system.service.impl;

import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.system.entity.SystemLog;
import com.xiaoyu.system.mapper.SystemLogMapper;
import com.xiaoyu.system.service.ISystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyu.user.feign.IUserClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-10-30
 */
@Service
@AllArgsConstructor
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {

    final IUserClient iUserClient;

    /**
     * 测试分布式事务
     */
    @GlobalTransactional
    @Override
    public R testSeataGlobalTransactional() throws Exception {
        SystemLog  systemLog = new SystemLog();
        systemLog.setRequestIp("0:0:0:0:0:0:0:1");
        systemLog.setServerIp("192.168.2.212");
        systemLog.setUrl("/testGlobalTransactional");
        systemLog.setRequestType("POST");
        systemLog.setIsAjax(1);
        systemLog.setRequestTime(LocalDateTime.now());
        systemLog.setRequestBy("123");
        systemLog.setFlag(0);
        //保存
        save(systemLog);
        R r  = iUserClient.updateUserEmail("admin");
        if("99".equals(r.getCode())){
            throw new Exception("分布式事务回滚");
        }
        return R.success("测试");
    }
}
