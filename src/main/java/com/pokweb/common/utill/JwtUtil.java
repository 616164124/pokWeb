package com.pokweb.common.utill;

import com.pokweb.common.response.WebResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * jwt
 */
@Component
public class JwtUtil {

    private static final String JWT_KEY = "MTExMTIxZmFkc3dhZGR3";

    /**
     * 生成jwt
     */
    public String JWTBuild(Map<String, Object> params) {
        String jwt = Jwts.builder().setClaims(params).setSubject(params.toString()).setExpiration(new Date(System.currentTimeMillis() + 600000L)).signWith(SignatureAlgorithm.HS256, JWT_KEY).compact();
        System.out.println(jwt);
        return jwt;
    }

    /**
     * 解析jwt
     *
     * @param token
     */
    public WebResponse parserJwt(String token) {
        WebResponse webResponse = new WebResponse();
        try {
            Claims body = Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token).getBody();
            webResponse.ok(body.getSubject());
        } catch (Exception e) {
            webResponse.error(e.getMessage().toString(), e);
        } finally {
            return webResponse;
        }

    }

    /**
     * 获取盐值
     */

    public static String getSalt() {

        String salt = "";
        Properties properties = new Properties();
        Object o = properties.get("jwtsalt");
//        System.out.println(o.toString());
        return salt;
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", "123131");
        String s = jwtUtil.JWTBuild(objectMap);
        String salt = jwtUtil.getSalt();
        System.out.println(salt);
//        String s1 = jwtUtil.parserJwt("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7aWQ9MTIzMTMxfSIsImlkIjoiMTIzMTMxIiwiZXhwIjoxNjMyNDY0MDk2fQ.rsJd65QrIQLl6VLARf2RpWm-d0U_H4bdb8c-YyfDrmM");


    }

}
