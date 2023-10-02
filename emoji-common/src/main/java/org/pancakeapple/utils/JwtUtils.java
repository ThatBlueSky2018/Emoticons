package org.pancakeapple.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.pancakeapple.constant.RBACConstant;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private static final String signKey= RBACConstant.JWT_SIGN_KEY;

    private static final Long expire=RBACConstant.JWT_EXPIRE;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return jwt
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(signKey).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            log.info("无效的JWT标签");
        } catch (MalformedJwtException e) {
            log.info("无效的JWT令牌");
        } catch (ExpiredJwtException e) {
            log.info("过期的JWT令牌");
        } catch (UnsupportedJwtException e) {
            log.info("不支持的JWT令牌");
        } catch (IllegalArgumentException e) {
            log.info("处理程序的JWT令牌压缩无效");
        }
        return false;
    }
}
