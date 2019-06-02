package com.core.web.filter;

import com.core.web.XssHttpServletRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AGbetrayal
 * @date 2019/6/2 20:17
 */
public class XssFilter extends AbstractFilter {
    @Override
    protected void doInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper(request), response);
    }
}
