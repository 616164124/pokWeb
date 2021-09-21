package com.pokweb.aspect.action;

import com.pokweb.common.response.WebResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 */

@Aspect
@Component
public class TokenAspect {

    @Pointcut("execution(public * com.pokweb.web.*.controller.*.*(..))")
    public void doOperation() {

    }


    @Before("doOperation()")
    public void before(JoinPoint joinPoint) throws Throwable{
        Object[] objs =joinPoint.getArgs();
        for (Object obj : objs) {
            Map object =(Map) obj;
            if (object.containsKey("token")) {
                String token = object.get("token").toString();
                if(token=="" ||(object.get("id").toString()=="" && object.get("password").toString()=="")){
                return;
                }
            }
            System.out.println("前置通知参数通知"+object.toString());


        }
    }


    @AfterReturning(returning = "object", pointcut = "doOperation()")
    public void doAfterReturning(Object object) {
        WebResponse resultBody = (WebResponse) object;
        String str =null;
//        try {
//            str=base64EnStr(resultBody.getResult());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        resultBody.setResult(str);
//        System.out.println("ǰ��֪ͨ��Ӧ�Ĳ���:"+resultBody);
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
}
