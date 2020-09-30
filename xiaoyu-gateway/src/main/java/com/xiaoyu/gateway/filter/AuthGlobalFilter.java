package com.xiaoyu.gateway.filter;

import com.xiaoyu.common.core.constant.AuthConstant;
import com.xiaoyu.gateway.config.WhitelistPathConfig;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


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

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println(path);
        String logStr = "【网关认证服务】";

        log.info(logStr + "请求地址：" + path);

        //白名单路径放行
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String allowUrl : whitelistPathConfig.getUrls()) {
            if (pathMatcher.match(allowUrl, path)) {
                return chain.filter(exchange);
            }
        }

        //检查请求头是否有携带token令牌
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(headerToken)) {
            return errorResponse(resp, AuthConstant.LACK_TOKEN);
        }

        //3 判断是否是有效的token
        try {
            String bearer = "bearer";
            headerToken = headerToken.trim().replace(bearer, "");
            Claims claims = Jwts.parser().setSigningKey(AuthConstant.SALINGER.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(headerToken).getBody();
            if (claims != null) {
                return chain.filter(exchange);
            }
        } catch (ExpiredJwtException e) {
            return errorResponse(resp, AuthConstant.TOKEN_EXPIRED);
        } catch (Exception e) {
            return errorResponse(resp, AuthConstant.DECRYPTION_FAILURE);
        }
        return errorResponse(resp, AuthConstant.DECRYPTION_FAILURE);
    }

    private Mono<Void> errorResponse(ServerHttpResponse response, String message) {
        byte[] bits = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
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
