package com.pokweb.common.utils.sm2;


import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * 用sm2加密
 */
public class JwtToken {

    //2个秘钥不能改,成对出现
    private static final String publicKey = "047afdc3d660ddd3d0df2c8f9954b0d2bff7a8628c59ac668c394ae1fda55c6b318f23926d24cbee5324b80f4e9bcbc6483cdb4d4fa466c9a885fb2a60917d6dc1";
    private static final String privateKey = "94c39bd37682456e4a44e67454c78f4cae63b6316a385fdabe2da69e9a12d963";

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
        long l = System.currentTimeMillis();
        String tks = "{\n" +
                "\"id\":\"12efsdfewg12efsdfewg12efsdfewg12efsdfewg\";\n" +
                "\"name\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\",\n" +
                "\"sfz\":\"2231231312412312314213x\";\n" +
                "\"mz\":\"汉族\"，\n" +
                "\"sjh\":\"12413132134123\",\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +
                "\"dz\":\"会黑科技和复活甲看算法和数据恢复可视化卡生活费静安寺开发和UI阿克苏就返回科技啥黑胡椒咖啡粉爱看就返回发挥科技啊饭卡就返回爱看就返回就好卡交电话费\"\n" +

                "}";
        //加密
        String sign = JwtToken.generateJwt(tks);
        System.out.println(sign);
        //解密
        String s = JwtToken.validataJwt(sign);
        System.out.println(s);
        System.out.println(System.currentTimeMillis()-l);

    }


}
