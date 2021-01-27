package com.skytower.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

@Slf4j
public class JwtUtil {

    public static long ttl = 1 * 12 * 3600 * 1000; // 一天
    private static String jwtSecret = "J14RN3NPPU91U9FIG8V7VD6CK7B5T0M";

    // 生成token
    public static String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder jwt = Jwts.builder()
                .setSubject(subject)  // 主题
                .setIssuedAt(now) // 签发时间
                .signWith(SignatureAlgorithm.HS256, jwtSecret);
        if (ttl > 0) {
            // 设置过期时间
            jwt.setExpiration(new Date(nowMillis + ttl));
        }
        return jwt.compact();
    }

    // 解析token
    public static JSONObject parseToken(String token) {
        JSONObject respData = new JSONObject();

        try {
            try {
                Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();

                respData.put("status", "success");
                respData.put("tokenStatus", "token校验成功");
                return respData;
            } catch (ExpiredJwtException e) {
                respData.put("status", "success");
                respData.put("tokenStatus", "token已过期");
                return respData;
            } catch (JwtException e) {
                respData.put("status", "success");
                respData.put("tokenStatus", "token解析失败");
                return respData;
            }
        } catch (JSONException e) {
            return respData;
        }
    }
}