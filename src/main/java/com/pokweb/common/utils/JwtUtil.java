package com.pokweb.common.utils;

import com.pokweb.common.response.WebResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private static final String JWT_WEB_KEY = "MTExMTIxZmFkc3dhZGR1";
    private static final String JWT_THRID_KEY="MTExMTIxZmFkc3dhZGR2";
    /**
     * 生成jwt
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
            DigestUtils.md5Digest("sfsfsfs".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            webResponse.setResultCode("333333");
            webResponse.setResultMsg(e.toString());
        } finally {
            return webResponse;
        }

    }

    /**
     * 获取盐值
     */
    public static String getSalt() {
        String salt = "123";
        return salt;
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", "123131");
        String s = jwtUtil.JWTBuild(objectMap);
        String salt = jwtUtil.getSalt();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiO1iJ7aWQ9MTIzMTMxfSIsImlkIjoiMTIzMTMxIiwiZXhwIjoxNjMyNzkzNTIxfQ.Zd1RbiTvMeWGFa15ir2POqD1CNMby_pKokl3K7baDs8";
        System.out.println("s="+s);
        WebResponse webResponse = jwtUtil.parserJwt(s);
        System.out.println(webResponse.getResultObj());

    }

}
