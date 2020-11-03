package com.xiaoyu.system.controller;


import com.xiaoyu.common.core.bo.R;
import com.xiaoyu.system.service.ISystemLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-10-30
 */
@RestController
@AllArgsConstructor
public class SystemLogController {


    private final ISystemLogService iSystemLogService;


    @RequestMapping("/test")
    public R addLog() throws Exception {
        return iSystemLogService.testSeataGlobalTransactional();
    }
}
