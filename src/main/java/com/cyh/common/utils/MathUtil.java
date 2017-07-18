package com.cyh.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cai on 2017/7/15.
 */
public class MathUtil {
    public static String getMD5(String str){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5Buffer = new StringBuffer();
        for (int i = 0 ; i < byteArray.length ; i++){
            if(Integer.toHexString(0xFF & byteArray[i]).length() == 1){
                md5Buffer.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            }else {
                md5Buffer.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5Buffer.toString();
    }
}
