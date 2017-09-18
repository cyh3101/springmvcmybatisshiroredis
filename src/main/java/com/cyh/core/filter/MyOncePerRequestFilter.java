package com.cyh.core.filter;

import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by cyh on 2017/9/15.
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("=============once per request filter===============");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
