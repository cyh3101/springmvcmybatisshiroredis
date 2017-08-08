package com.cyh.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by cyh on 2017/8/8.
 */
public class SpringContextUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        try {
            return applicationContext.getBean(name);
        }catch (Exception e){
            throw new RuntimeException("获取的Bean不存在");
        }
    }

    public static <T> T getBean(String name, Class<T> requredType){
        return applicationContext.getBean(name, requredType);
    }

    public static boolean containBean(String name){
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException{
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name)
            throws NoSuchBeanDefinitionException{
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException{
        return applicationContext.getAliases(name);
    }
}
