package com.software.eventplanning.utils;

import com.software.eventplanning.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

public class JwtUtil {
    final static String base64EncodedSecretKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//私钥
    final static long   TOKEN_EXP              = 1000 * 60 * 20;//过期时间,测试使用二十分钟

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * 检查token,只要不正确就会抛出异常
     **/
    public static Claims checkToken(String token) throws ServletException {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e1) {
            throw new ServiceException(401, "登录信息过期，请重新登录");
        } catch (Exception e) {
            throw new ServiceException(401, "用户未登录，请重新登录");
        }
    }
}
