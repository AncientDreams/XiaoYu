package com.xiaoyu.common.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyu.common.security.config.TokenConfig;
import com.xiaoyu.common.security.config.WhitelistPathConfig;
import com.xiaoyu.user.entity.SystemRole;
import com.xiaoyu.user.entity.SystemUser;
import com.xiaoyu.user.feign.IUserClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;

    private final IUserClient iSystemUserService;

    private final WhitelistPathConfig whitelistPathConfig;

    @SuppressWarnings("all")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
       String path = request.getRequestURI();
        //白名单
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String allowUrl : whitelistPathConfig.getUrls()) {
            if (pathMatcher.match(allowUrl, path)) {
                filterChain.doFilter(request, httpServletResponse);
                return;
            }
        }
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
            filterChain.doFilter(request, httpServletResponse);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", HttpStatus.FORBIDDEN.value());
            jsonObject.put("meg","无权访问");
            byte[] bytes = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            httpServletResponse.getOutputStream().write(bytes);
            log.error(e.getMessage(), e);
        }
    }
}
