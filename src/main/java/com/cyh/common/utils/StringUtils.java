package com.cyh.common.utils;

/**
 * Created by cai on 2017/7/20.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
    /**
     * 判断多个或一个对象为空
     * @param objects
     * @return
     */
    public static  boolean isBlank(Object...objects){
        Boolean result = false;
        for (Object object:objects
             ) {
            if(null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())){
                result = true;
                break;
            }
        }
        return result;
    }

    public static  boolean isBlank(String str){
        Object object = str;
        return isBlank(object);
    }

    public static boolean isBlank(Object object){
        return isBlank(object);
    }

    public static boolean isBlank(String...strings){
        Object[] objects = strings;
        return isBlank(objects);
    }

    /**
     * 判断一个字符串在字符串数组中出现的次数
     * @param baseStr
     * @param strings
     * @return
     */
    public static int indexOf(String baseStr , String[] strings){
        int count = 0;
        if(null == baseStr || baseStr.length() == 0 || null == strings || strings.length == 0){
            return 0;
        }
        for (String str:strings
             ) {
            boolean result = str.equals(baseStr);
            count = result ? ++count : count;
        }
        return count;
    }
}
