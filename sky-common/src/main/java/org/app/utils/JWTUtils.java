package org.app.utils;

/**
 * 作者:疏狂难除
 * 时间:2024-02-08
 */

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;

import java.util.Date;
import java.util.Map;

public class JWTUtils {



    public static String createJWT(String secretKey,long ttlMillis, Map<String, Object> claims) {
        String jwt = JWT.create()
                .setExpiresAt(DateUtil.offset(new Date(), DateField.SECOND, Math.toIntExact(ttlMillis / 1000)))
                .addPayloads(claims)
                .setKey(secretKey.getBytes())
                .sign();
        return jwt;
    }
    // 解密解析token
    public static Map<String, Object> parseJWT(String secretKey, String token) {
        return JWT.of(token).setKey(secretKey.getBytes()).getPayloads();
    }
}
