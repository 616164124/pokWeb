package com.pokweb.common.utils;


import org.junit.Test;

import java.util.Random;
import java.util.UUID;

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
     * 获取指定长度的随机数
     *
     * @param nums
     * @return
     */
    public static String getNum(int nums) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < nums; i++) {
            sb.append(random.nextInt(10) + "");
        }
        return sb.toString();
    }

    @Test
    public void test(){
        System.out.println(getStr(6));
    }
}
