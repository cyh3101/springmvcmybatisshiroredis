package com.cyh.common.utils;

/**
 * Created by cyh on 2017/8/8.
 */
public class UtilPath {
    public static String getClassPath(){
        String sysName = System.getProperty("os.name");

        //判断当前环境，如果是Windows，截取路径的第一个'/'
        if(!StringUtils.isBlank(sysName) && sysName.indexOf("Windows") != -1){
            return UtilPath.class.getResource("/").getFile().toString().substring(1);
        }else {
            return UtilPath.class.getResource("/").getFile().toString();
        }
    }
}
