package com.cyh.common.utils;


import net.sf.json.JSONObject;

import java.io.*;

/**
 * Created by cai on 2017/8/2.
 */
public class SerializeUtil {
    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object){
        if(null == object){
            throw new NullPointerException("can't serialize null");
        }
        ByteArrayOutputStream bo = null;
        ObjectOutputStream os = null;
        byte[] bt = null;

        try {
            bo = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bo);
            os.writeObject(object);
            bt = bo.toByteArray();
        } catch (Exception e) {
            LoggerUtils.fmtError(SerializeUtil.class, e,"serialize error %s",
                    JSONObject.fromObject(object));
        } finally {
            close(os);
            close(bo);
        }
        return bt;
    }

    /**
     * 反序列化
     * @param in
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] in, Class<T>... requiredType){
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        Object object = null;
        try {
            if(null != in){
                bi = new ByteArrayInputStream(in);
                oi = new ObjectInputStream(bi);
                object = oi.readObject();
            }
        } catch (Exception e) {
            LoggerUtils.fmtError(SerializeUtil.class, e, "serialize error %s",
                    in);
        }
        return (T)object;
    }

    /**
     * 反序列化方法重载
     * @param in
     * @return
     */
    public static Object deserialize(byte[] in){
        return deserialize(in, Object.class);
    }

    /**
     * 关闭流
     * @param closeable
     */
    public static void close(Closeable closeable){
        if(null != closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                LoggerUtils.fmtError(SerializeUtil.class,"close stream error");
            }
        }
    }
}
