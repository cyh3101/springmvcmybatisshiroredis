package com.cyh.common.utils;

import java.util.Random;

/**
 * Created by cai on 2017/7/24.
 */
public class VerifyCodeUtils {
    //可以显示的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNOPQRSTUVWXYZ";

    //验证码key
    public static final String V_CODE = "_CODE";
    private static Random random = new Random();

    //验证码对象,内部类
    public static class Verify{
        private String code;
        private Integer value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    /**
     * 使用系统默认字符源生产验证码
     * @return
     */
    public static Verify generateVerify(){
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        Verify entity = new Verify();
        entity.setCode(num1 + " X " + num2);
        entity.setValue(num1 + num2);
        return entity;
    }

    /**
     * 生产指定长度的验证码
     * @param verifySize
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize , VERIFY_CODES);
    }

    /**
     * 根据指定的字符串生产一定长度的验证码
     * @param verifySize
     * @param sources
     * @return
     */
    public static String generateVerifyCode(int verifySize , String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        StringBuilder verifyCode = new StringBuilder(verifySize);
        Random random = new Random(System.currentTimeMillis());
        int codeLen = sources.length();
        for (int i = 0;i < verifySize;i++){
            verifyCode.append(sources.charAt(random.nextInt(codeLen - 1)));
        }
        return verifyCode.toString();
    }


}
