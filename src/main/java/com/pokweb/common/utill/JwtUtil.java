package com.pokweb.common.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt
 */

public class JwtUtil {


    /**
     * 生成jwt
     */
    public String JWTBuild(Map<String, Object> params) {
        String jwt = Jwts.builder().setClaims(params).setSubject(params.toString()).setExpiration(new Date(System.currentTimeMillis()+600000L)).signWith(SignatureAlgorithm.HS256, "MTExMTIxZmFkc3dhZGR3").compact();
        System.out.println(jwt);
        return jwt;
    }

    /**
     * 解析jwt
     * @param jwt
     */
    public String parserJwt(String jwt){
        Claims body = Jwts.parser().setSigningKey("MTExMTIxZmFkc3dhZGR3").parseClaimsJws(jwt).getBody();
        System.out.println(body.getSubject());
        return body.getSubject();
    }


    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", "123131");
        String s = jwtUtil.JWTBuild(objectMap);
        String s1 = jwtUtil.parserJwt("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7aWQ9MTIzMTMxfSIsImlkIjoiMTIzMTMxIiwiZXhwIjoxNjMyNDY0MDk2fQ.rsJd65QrIQLl6VLARf2RpWm-d0U_H4bdb8c-YyfDrmM");


    }

}
