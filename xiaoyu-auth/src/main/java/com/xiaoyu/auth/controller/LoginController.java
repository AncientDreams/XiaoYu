package com.xiaoyu.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020-09-13 14:08
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login-view")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login‐success")
    public String loginSuccess() {
        return " 登录成功" + getUserName();
    }

    /*** 测试资源1 * @return */
    @GetMapping(value = "/r/r1")
    public String r1() {
        return " 访问资源1";
    }

    /*** 测试资源2 * @return */
    @GetMapping(value = "/r/r2")
    public String r2() {
        return " 访问资源2";
    }


    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) principal;
        return userDetails.getUsername();
    }
}
