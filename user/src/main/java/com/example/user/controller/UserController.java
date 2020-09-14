package com.example.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/14 0014 16:00
 */
@RestController
public class UserController {

    @GetMapping("/name")
    public String getName(){
        return "nam,e";
    }

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAnyAuthority('管理员')")
    public Object get(Authentication authentication){
        authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        return details.getTokenValue() + "访问资源一";
    }

}
