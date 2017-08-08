package com.cyh.common.utils;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 日志工具类
 * Created by cai on 2017/8/2.
 */
public class LoggerUtils {
    public static boolean isDebug = Logger.getLogger(LoggerUtils.class).isDebugEnabled();

    /**
     * debug
     * @param clazz
     * @param message
     */
    public static void debug(Class<? extends Object> clazz, String message){
        if(!isDebug)return;
        Logger logger = Logger.getLogger(clazz);
        logger.debug(message);
    }

    /**
     * error
     * @param clazz
     * @param message
     * @param e
     */
    public static void error(Class<? extends Object> clazz, String message, Exception e){
        if(!isDebug)return;
        Logger logger = Logger.getLogger(clazz);
        if(null == e){
            logger.error(message);
            return;
        }
        logger.error(message, e);
    }

    /**
     * error重载
     * @param clazz
     * @param message
     */
    public static void error(Class<? extends Object> clazz, String message){
        error(clazz, message, null);
    }

    /**
     * 格式化输出debug
     * @param clazz
     * @param fmtString
     * @param value
     */
    public static void fmtDebug(Class<? extends Object> clazz, String fmtString,String...value){
        if(!isDebug)return;
        if(StringUtils.isBlank(fmtString)){
            return;
        }
        if(null != value && value.length != 0){
            fmtString = String.format(fmtString, value);
        }
        debug(clazz, fmtString);
    }

    /**
     * 格式化输出error
     * @param clazz
     * @param e
     * @param fmtString
     * @param value
     */
    public static void fmtError(Class<? extends Object> clazz, Exception e, String fmtString, Object...value){
        if(!isDebug)return;
        if(StringUtils.isBlank(fmtString)){
            return;
        }
        if(null != value && value.length !=0){
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString, e);
    }

    /**
     * 异常填充值输出
     * @param clazz 目标.class
     * @param fmtString 输出信息key
     * @param value 输出信息value
     */
    public static void fmtError(Class<? extends Object> clazz, String fmtString, Object... value){
        if(!isDebug)return;
        if(StringUtils.isBlank(fmtString)){
            return;
        }
        if(null != value && value.length != 0){
            fmtString = String.format(fmtString, value);
        }
        error(clazz, fmtString);
    }
}
