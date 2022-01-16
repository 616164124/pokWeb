package com.pokweb.common.utils;

import com.pokweb.common.response.WebResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt
 */
@Component
public class JwtUtil {
    //key最好为40位
    private static final String JWT_WEB_KEY = "yDwCNGrD6boGnBAHsjT2Ir8DdMjFdefLTES3RNVnQdLVUK2o2Ehg";
    private static final String JWT_THRID_KEY="MTExMTIxZmFkc3dhZGR2";
    /**
     * 生成jwt 用HS256加密
     */
    public String JWTBuild(Map<String, Object> params) {
        String jwt = Jwts.builder().setClaims(params).setSubject(params.toString()).setExpiration(new Date(System.currentTimeMillis() + 600000L)).signWith(SignatureAlgorithm.HS256, JWT_WEB_KEY).compact();
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
            Claims body = Jwts.parser().setSigningKey(JWT_WEB_KEY).parseClaimsJws(token).getBody();
            webResponse.setResultCode("000000");
            webResponse.setResultObj(body.getSubject());
        } catch (Exception e) {
            webResponse.setResultCode("999999");
            webResponse.setResultMsg(e.toString());
        } finally {
            return webResponse;
        }

    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", "123131");
        String s = jwtUtil.JWTBuild(objectMap);
        System.out.println("s="+s);
        WebResponse webResponse = jwtUtil.parserJwt(s);
        System.out.println(webResponse.getResultObj());

    }

}
