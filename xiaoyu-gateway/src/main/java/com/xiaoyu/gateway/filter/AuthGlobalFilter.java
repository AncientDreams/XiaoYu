package com.xiaoyu.gateway.filter;

import com.xiaoyu.gateway.config.JwtUtil;
import com.xiaoyu.gateway.config.WhitelistPathConfig;
import io.jsonwebtoken.Claims;
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
        if (whitelistPathConfig.getUrls().contains(path)) {
            return chain.filter(exchange);
        }

        //检查请求头是否有携带token令牌
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(headerToken)) {
            return errorResponse(resp, "令牌缺失，拒绝访问！");
        }

        //检查令牌是否正确
        String token = JwtUtil.getToken(headerToken);
        Claims claims = JwtUtil.parseJwt(token);
        if (claims == null) {
            return errorResponse(resp, "解析令牌错误！");
        }

        //获取请求用户对应的角色
        String roles = claims.get("authorities").toString();
        roles = roles.substring(1, roles.length() - 1);

        return chain.filter(exchange);
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
