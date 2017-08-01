package com.cyh.common.utils;

import com.cyh.core.shiro.token.manager.TokenManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * 清除验证码
     */
    public static void clearVerifyCode(){
        TokenManager.getSession().removeAttribute(V_CODE);
    }
    /**
     * 对比验证码
     * @param code
     * @return
     */
    public static boolean verifyCode(String code){
        String v = (String)TokenManager.getValue2Session(V_CODE);
        return StringUtils.equals(v, StringUtils.lowerCase(code));
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

    /**
     * 生成指定的验证码到图片文件中，并返回验证码字符串
     * @param w
     * @param h
     * @param outputFile
     * @param verifySize
     * @return
     */
    public static String outputVerifyImage(int w, int h, File outputFile, int verifySize){
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, outputFile, verifyCode);
        return verifyCode;
    }
    /**
     * 生成指定的验证到图片输出流中，并返回验证码字符串
     * @param w
     * @param h
     * @param os
     * @param verifySize
     * @return
     */
    public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, os, verifyCode);
        return verifyCode;
    }
    /**
     * 生成指定验证码图像文件
     * @param w
     * @param h
     * @param outputFile
     * @param code
     */
    public static void outputImage(int w, int h, File outputFile, String code){
        if(null == outputFile){
            return;
        }
        File dir = outputFile.getParentFile();
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            outputFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            outputImage(w, h, fileOutputStream, code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 生成验证码图片流，写入输出流中;
     * @param w
     * @param h
     * @param os
     * @param code
     * @throws IOException
     */
    public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
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
        g2.fillRect(0, 2, w, h - 4);//设置背景

        //绘制干扰线
        g2.setColor(getRandomColor(160,200));
        for (int i = 1;i < 20;i++){
            int x = random.nextInt(w - 1);
            int y = random.nextInt(h - 1);
            int x1 = random.nextInt(6) + 1;
            int y1 = random.nextInt(12) + 1;
            g2.drawLine(x, y, x + x1 + 40, y + y1 + 20);
        }

        //绘制噪点
        float yawpRate = 0.05f;//噪声率
        int area = (int)(yawpRate * w * h);
        for (int i = 0;i < area;i++){
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int rgb = getRandomIntColor();
            image.setRGB(x, y, rgb);
        }

        //扭曲图片
        //TODO

        g2.setColor(getRandomColor(100,160));
        int fontSize = h - 4;
        Font font = new Font("Algerian" , Font.ITALIC,fontSize);
        g2.setFont(font);//设置字体
        char[] chars = code.toCharArray();
        for (int i = 0;i < verifySize;i++){
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize/2, h/2);
            g2.setTransform(affine);//设置2d仿射变换
            //设置字母
            g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
        }
        g2.dispose();
        ImageIO.write(image, "jpg" ,os);
    }

    /**
     * 生成随机的颜色
     * @param fc
     * @param bc
     * @return
     */
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

    private static int getRandomIntColor(){
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c:rgb
             ) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb(){
        int[] rgb = new int[3];
        for (int i = 0;i < 3;i++){
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }
}
