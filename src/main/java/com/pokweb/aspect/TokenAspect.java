package com.pokweb.aspect;

import com.pokweb.common.utils.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;


@Aspect
@Component
public class TokenAspect {
//切点（com.pokweb.web）下的内容
    @Pointcut("execution(public * com.pokweb.web.*.controller.*.*(..))")
    public void doOperation() {
    }

    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable {
        JwtUtil jwtUtil = new JwtUtil();
//        Map target = (Map) joinPoint.getArgs()[0];
//        String token = target.get("token").toString();
//        if(!"login".equals(token) && !"register".equals(token)){
//            if(!"000000".equals(jwtUtil.parserJwt(token).getResultCode())){
//                throw  new Exception("token 验证不通过！！！");
//            }
//        }
    }


    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public Object doAfterReturning(Object object) {
        String str = null;
//        try {
//            str=base64EnStr(resultBody.getResult());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        resultBody.setResult(str);
//        System.out.println("ǰ��֪ͨ��Ӧ�Ĳ���:"+resultBody);
        return "iiiii";
    }

//
//    public  String base64EnStr(String str) throws UnsupportedEncodingException {
//        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
//    }
//
//
//    public static String base64DeStr(String encodeStr) throws UnsupportedEncodingException {
//        byte[] decodeStr = Base64.getDecoder().decode(encodeStr);
//        return new String(decodeStr, "UTF-8");
//    }

    public boolean checkToken(String token) {


        return true;
    }

}
