package com.xiaoyu.common.security.filter;

import com.xiaoyu.common.security.config.TokenConfig;
import com.xiaoyu.user.entity.SystemRole;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 微服务认证过滤器，验证token
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/21 11:43
 */
@Component
@Import(TokenConfig.class)
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;

    private final IUserClient iSystemUserService;

    @SuppressWarnings("all")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        //检查token令牌是否正确.
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenStore.readAccessToken(request.getHeader("Authorization"));
            Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
            //取出用户名称
            String principal = additionalInformation.get("user_name").toString();
            //远程调用获取用户角色信息
            List<SystemRole> roleList = iSystemUserService.queryUserRolesByUserId(principal).getData();

            //远程调用获取用户详细信息
            SystemUser systemUser = iSystemUserService.userByUsername(principal).getData();
            String[] authorities = roleList.stream().map(SystemRole::getRoleCode).toArray(String[]::new);

            //身份信息、权限信息填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null,
                    AuthorityUtils.createAuthorityList(authorities));
            //创建details
            authenticationToken.setDetails(systemUser);
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, httpServletResponse);
    }
}
