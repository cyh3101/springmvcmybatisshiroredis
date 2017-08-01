package com.cyh.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Arrays;
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

    public static void outputImage(int w, int h, OutputStream os, String code) {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//消除线段的锯齿状边缘
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY,
                Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
            fractions[i] = random.nextFloat();
        }
        Arrays.sort(fractions);

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);//设置边框

        g2.setColor(getRandomColor(200, 250));
        g2.fillRect(0, 2, w, h - 4);
    }

    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
