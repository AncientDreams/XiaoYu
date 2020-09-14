package com.xiaoyu.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhangXianYu   Email: 1600501744@qq.com
 * @since 2020/9/2 0002 15:53
 */
public class JwtUtil {

    private final static String SIGN_KEY = "dev";
    private final static String BEARER = "Basic";
    private final static Integer AUTH_LENGTH = 7;
    private final static String BASE64_SECURITY;

    public static String getToken(String auth) {
        if (auth != null && auth.length() > AUTH_LENGTH) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo(BEARER) == 0) {
                auth = auth.substring(7);
            }

            return auth;
        } else {
            return null;
        }
    }

    public static Claims parseJwt(String jsonWebToken) {
        try {
            return Jwts.parser().setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY)).parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception var2) {
            return null;
        }
    }

    static {
        BASE64_SECURITY = Base64.getEncoder().encodeToString(SIGN_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
