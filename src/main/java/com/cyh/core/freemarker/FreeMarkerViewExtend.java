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
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getLocalPort();
        String contextPath = request.getContextPath();
        String basePath = scheme + "://" + host + ":" + port + contextPath;
        model.put("basePath" , basePath);
        System.out.println("basePath: "  + basePath);
        super.exposeHelpers(model, request);
    }
}
