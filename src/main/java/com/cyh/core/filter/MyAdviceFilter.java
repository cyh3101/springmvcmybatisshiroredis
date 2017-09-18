package com.cyh.core.filter;

import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by cyh on 2017/9/15.
 */
public class MyAdviceFilter extends AdviceFilter{
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("=========前置处理==========");
        return true;
        //return super.preHandle(request, response);
    }

    /**
     * 后置处理，执行完拦截链以后，正常返回
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("===========后置返回处理=============");
        super.postHandle(request, response);
    }

    /**
     * 后置最终处理，不管是不是有抛出异常都会知行，处理如资源释放的功能/清理资源
     * @param request
     * @param response
     * @param exception
     * @throws Exception
     */
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        System.out.println("=============后置最终处理==================");
        super.afterCompletion(request, response, exception);
    }
}
