package com.pokweb.common.utils;


import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStrUtil {

    /**
     * 获取指定长度的随机字符串
     *
     * @param length 长度(不能大于32)
     * @return string
     */
    public static String getStr(int length) {
        if (length > 32) {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }

    /**
     * 随机生产33位字符串
     *
     * @return string
     */
    public static String getStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取指定长度的伪随机数(int)
     *
     * @param nums
     * @return
     */
    public static String getNum(int nums) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nums; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(0,9));
        }
        return sb.toString();
    }

    @Test
    public void test(){
        System.out.println(getStr(6));
        ThreadLocalRandom current = ThreadLocalRandom.current();
        System.out.println(getNum(6)+"------");
    }
}
