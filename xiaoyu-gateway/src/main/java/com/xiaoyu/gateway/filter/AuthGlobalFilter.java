package com.xiaoyu.gateway.filter;

import com.nimbusds.jose.JWSObject;
import com.xiaoyu.gateway.config.WhitelistPathConfig;
import com.xiaoyu.user.entity.SystemPermission;
import com.xiaoyu.user.feign.IUserClient;
import constant.AuthConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>
 * XiaoYu 微服务统一入口
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/1 0001 9:19
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthGlobalFilter implements Ordered, GlobalFilter {

    final WhitelistPathConfig whitelistPathConfig;

    final IUserClient userClient;

    private RedisCacheManager redisCacheManager;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println(path);
        String logStr = "【网关认证服务】";

        log.info(logStr + "请求地址：" + path);

        //白名单路径放行
        if (whitelistPathConfig.getUrls().contains(path)) {
            return chain.filter(exchange);
        }

        //检查请求头是否有携带token令牌
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(headerToken)) {
            return errorResponse(resp, AuthConstant.LACK_TOKEN);
        }

        //检查token令牌是否正确
        Claims claims = jwtDecode(headerToken);
        if (claims == null) {
            return errorResponse(resp, AuthConstant.DECRYPTION_FAILURE);
        }

        //鉴权，校验当前用户是否有访问资源的权限
//        Authentication(path)
        //从token中解析用户信息并设置到Header中去
        JWSObject jwsObject = JWSObject.parse(headerToken);
        String userStr = jwsObject.getPayload().toString();
        log.info("AuthGlobalFilter.filter() user:{}", userStr);
        exchange.getRequest().mutate().header("user", userStr).build();

        return chain.filter(exchange);
    }


    private Mono<Void> errorResponse(ServerHttpResponse response, String message) {
        byte[] bits = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }


    /**
     * 验签，解密失败返回null
     *
     * @param jwtString jwt加密串
     * @return Claims
     */
    private Claims jwtDecode(String jwtString) {
        String base64Security = Base64.getEncoder().encodeToString(AuthConstant.SALINGER.getBytes(StandardCharsets.UTF_8));
        try {
            return Jwts.parser().setSigningKey(Base64.getDecoder().decode(base64Security)).
                    parseClaimsJws(jwtString).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("all")
    private boolean Authentication(String url, String userName) {
        //用户所拥有的访问权限
        Collection<SystemPermission> systemPermissionList;
        final String permissionCache = "systemPermission";
        Cache cache = redisCacheManager.getCache(permissionCache);
        if (cache != null) {
            try {
                systemPermissionList = cache.get(userName, Collection.class);
            } catch (Exception ignored) {
            }
        }
        systemPermissionList =  userClient.getSystemPermissions(userName).getCollection();
        List<String> urlList = new ArrayList<>(systemPermissionList.size());
        systemPermissionList.forEach(permission -> {
            String permissionUrl = permission.getUrl();
            if (!StringUtils.isEmpty(permissionUrl)) {
                urlList.add(permission.getUrl());
            }
        });
        return urlList.contains(url);
    }

    /**
     * 执行优先级
     *
     * @return 数字越大优先级越低
     */
    @Override
    public int getOrder() {
        return 0;
    }


}
