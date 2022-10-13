package com.pokweb.common.utils;

import com.pokweb.common.response.WebResponse;
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
@Component
public class JwtUtil {
    //key最好为40位
    @Value("${JWT_WEB_KEY}")
    public String JWT_WEB_KEY;
    private static final String JWT_THRID_KEY = "eyJzdWIiOiJ7aWQ9MTIzMTMxfSIsImlkIjoiMTIzMTMxIiwiZXhwIjoxNjYyNDMyMzkxfQ";

    /**
     * 生成jwt 用HS256加密
     */
    public String JWTBuild(Map<String, Object> params) {
        String jwt = Jwts.builder().setClaims(params).setSubject(params.toString()).setExpiration(new Date(System.currentTimeMillis() + 600000L)).signWith(SignatureAlgorithm.HS256, JWT_THRID_KEY).compact();
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
//            SignatureAlgorithm.RS256
            Claims body = Jwts.parser().setSigningKey(JWT_THRID_KEY).parseClaimsJws(token).getBody();
            webResponse.setResultCode("000000");
            webResponse.setResultObj(body.getSubject());
        } catch (Exception e) {
            webResponse.setResultCode("999999");
            webResponse.setResultMsg("token错误");
        } finally {
            return webResponse;
        }
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", "12313hfjkshfkhfuksjfh1");
        objectMap.put("name", "会黑科技和复活甲看");
        objectMap.put("sfz", "1231421321423889747326478");
        objectMap.put("dz", "会黑科技和复活甲看会黑科技和复活甲看会黑科技和复活甲看会黑科技和复活甲看会黑科技和复活甲看会黑科技和复活甲看会黑科技和复活甲看");
        objectMap.put("sjh", "124213412314213");
        objectMap.put("mz", "发接口返回好喝哦好好考试");
        String s = jwtUtil.JWTBuild(objectMap);
        System.out.println("s=" + s);
        WebResponse webResponse = jwtUtil.parserJwt(s);
        System.out.println(webResponse.getResultObj());
    }

}
