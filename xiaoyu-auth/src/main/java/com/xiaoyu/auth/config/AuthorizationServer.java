package com.xiaoyu.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/14 0014 11:49
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

//    ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），
//    客户端详情信息在 这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。

//    AuthorizationServerEndpointsConfigurer：用来配置令牌（token）的访问端点和  令牌服务(token services)。

//    AuthorizationServerSecurityConfigurer：用来配置令牌端点的安全约束 .

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private ClientDetailsService clientDetailsService;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    //将客户端信息存到数据库
    private ClientDetailsService clientDetailsService(DataSource dataSource) {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    //客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService(dataSource));
        // 使用in‐memory存储
//        clients.inMemory()
//                // client_id
//                .withClient("c1")
//                //客户端密钥
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                //资源列表
//                .resourceIds("res1")
//                // 该client允许的授权类型authorization_code, password, refresh_token, implicit, client_credentials
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
//                // 允许的授权范围
//                .scopes("all")
//                .autoApprove(false)
//                //加上验证回调地址
//                .redirectUris("http://www.baidu.com");
    }


    //令牌管理模式
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        //客户端信息服务
        service.setClientDetailsService(clientDetailsService(dataSource));
        //是否产生刷新令牌
        service.setSupportRefreshToken(true);
        //令牌的存储策
        service.setTokenStore(tokenStore);
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        // 令牌默认有效期2小时
        service.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }


    //配置端点信息     令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //密码模式需要 authenticationManager
        endpoints.authenticationManager(authenticationManager)
                //授权码模式需要 authorizationCodeServices
                .authorizationCodeServices(authorizationCodeServices)
                //设置令牌管理模式
                .tokenServices(tokenService()).
                //允许POST方式提交
                        allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        //设置授权码模式的授权码如何 存取，暂时采用内存方式
//        return new InMemoryAuthorizationCodeServices();
//    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        //设置授权码模式的授权码如何 存取，采用数据库
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    //令牌访问的安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.
                //tokenkey这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个 endpoint完全公开。
                        tokenKeyAccess("permitAll()").
                //checkToken这个endpoint完全公开
                        checkTokenAccess("permitAll()")
                //允许表单认证
                .allowFormAuthenticationForClients();
    }

}