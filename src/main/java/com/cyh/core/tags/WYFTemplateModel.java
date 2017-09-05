package com.cyh.core.tags;

import com.cyh.core.freemarker.FreemarkerTagUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by cyh on 2017/9/5.
 */
public abstract class WYFTemplateModel implements TemplateDirectiveModel{
    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        /**
         * 模版方法模式，把变化委派下去，交给子类实现！
         */
        Map<String, TemplateModel> paramWrap = putValue(params);


        Map<String, TemplateModel> origMap = FreemarkerTagUtil.convertToTemplateModel(env, paramWrap);
        body.render(env.getOut());
        FreemarkerTagUtil.clearTempleModel(env, paramWrap, origMap);
    }

    /**
     * 子类实现
     * @param params
     * @return
     * @throws TemplateModelException
     */
    protected abstract Map<String, TemplateModel> putValue(Map params) throws TemplateModelException;
}
