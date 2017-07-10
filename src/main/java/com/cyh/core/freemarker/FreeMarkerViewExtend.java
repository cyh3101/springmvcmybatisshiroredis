package com.cyh.core.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by cai on 2017/7/10.
 */
public class FreeMarkerViewExtend extends FreeMarkerView{
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        model.put("basePath" , request.getContextPath());
        super.exposeHelpers(model, request);
    }
}
