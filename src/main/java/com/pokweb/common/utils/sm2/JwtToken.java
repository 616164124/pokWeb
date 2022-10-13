package com.pokweb.common.utils.sm2;


import com.alibaba.fastjson.JSONObject;

import java.util.Base64;

/**
 * 用sm2加密
 */
public class JwtToken {

    //2个秘钥不能改成对出现
    private static final String publicKey = "04bd133a440cd5ebaeeb68c958953a7794134c3992d2c3fd006571c7f1c7b3ebef484595ecd298d6c22fd1c0f84ba7ca8595761c271c959dc1370d7528c55b1ccb";
    private static final String privateKey = "835b53881c3e391ba430022119bf6b0f7fd7764ec9808d305ea5b620c531363e";

    public static String sign(String sourceData) {
        String signstr = "";
        try {
            SM2Sign sign = SM2SignVerify.sign(SM2Util.hexToByte(privateKey), sourceData.getBytes());
            signstr = sign.getSm2_sign();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return signstr;
    }

    public static boolean verify(String sourceData, String signstr) {
        SM2Sign sign = SM2SignVerify.validateSign(SM2Util.hexToByte(publicKey), sourceData.getBytes(), SM2Util.hexToByte(signstr));
        return sign.isVerify();
    }


    public static String generateJwt(String tks) {
        String jwt = "";
        JSONObject headerjson = new JSONObject();
        headerjson.put("typ", "JWT");
        headerjson.put("alg", "SM2");

        String headerb64 = Base64.getEncoder().encodeToString(headerjson.toJSONString().getBytes());

        JSONObject payloadjson = new JSONObject();
        payloadjson.put("iss", "pokweb");
        payloadjson.put("sub", "mingshfk");
        payloadjson.put("tks", tks);

        String payloadb64 = Base64.getEncoder().encodeToString(payloadjson.toJSONString().getBytes());

        StringBuffer buf = new StringBuffer();
        buf.append(headerb64).append(".").append(payloadb64);

        String signdata = sign(buf.toString());

        buf.append(".").append(signdata);

        jwt = buf.toString();
        return jwt;
    }

    public static String validataJwt(String jwt) throws Exception {
        boolean flag = false;
        String[] jwtarr = jwt.split("\\.");
        if (jwtarr == null || jwtarr.length != 3) {
            throw new Exception("jwt错误");
        }

        String header = jwtarr[0];
        String payload = jwtarr[1];
        String sign = jwtarr[2];

        flag = verify(header + "." + payload, sign);

        byte[] b = Base64.getDecoder().decode(header);
        header = new String(b);

        b = Base64.getDecoder().decode(payload);
        payload = new String(b);

        JSONObject json = JSONObject.parseObject(payload);

        return json.getString("tks");
    }

    public static void main(String[] args) throws Exception {

        JwtToken jwtToken = new JwtToken();
        String tks = "{\n" +
                "\"id\":\"12efsdfewg12efsdfewg12efsdfewg12efsdfewg\";\n" +
                "\"name\":\"会黑科技和复活甲看\",\n" +
                "\"sfz\":\"2231231312412312314213x\";\n" +
                "\"mz\":\"汉族\"，\n" +
                "\"sjh\":\"12413132134123\",\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "}";
        //加密
        String sign = jwtToken.generateJwt(tks);
        System.out.println(sign);
        //解密
        String s = jwtToken.validataJwt(sign);
        System.out.println(s);


    }


}
