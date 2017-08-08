package com.cyh.common.utils;

/**
 * Created by cyh on 2017/8/8.
 */
public class UtilPath {
    /**
     * 获得classs目录
     * @return
     */
    public static String getClassPath(){
        String sysName = System.getProperty("os.name");

        //判断当前环境，如果是Windows，截取路径的第一个'/'
        if(!StringUtils.isBlank(sysName) && sysName.indexOf("Windows") != -1){
            return UtilPath.class.getResource("/").getFile().toString().substring(1);
        }else {
            return UtilPath.class.getResource("/").getFile().toString();
        }
    }

    /**
     * 获取当前对象的路径
     * @param object
     * @return
     */
    public static String getObjectPath(Object object){
        return object.getClass().getResource(".").getFile().toString();
    }

    /**
     * 获得项目路径
     * @return
     */
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }

    public static String getWEB_INF(){
        return getClassPath().replace("classes/","");
    }
}
