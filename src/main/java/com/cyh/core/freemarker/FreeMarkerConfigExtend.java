package com.cyh.core.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Created by cai on 2017/9/4.
 */
public class FreeMarkerConfigExtend extends FreeMarkerConfigurer{
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
//        Configuration cfg = this.getConfiguration();
//        cfg.setDefaultEncoding("UTF-8");
//        putInitShared(cfg);
        this.getConfiguration().setSharedVariable("shiro",new ShiroTags());
    }

    public static void put(Configuration cfg, String k, Object v) throws TemplateModelException {
        cfg.setSharedVariable(k, v);
        cfg.setNumberFormat("#");//防止页面输出数字，变成2000
        //可以添加很多自己要传输到页面的[方法、对象、值]
    }

    public static void putInitShared(Configuration cfg) throws TemplateModelException {
        put(cfg, "shiro", new ShiroTags());//设置shiro标签
    }
}
