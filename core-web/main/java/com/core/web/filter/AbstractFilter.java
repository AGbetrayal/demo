package com.core.web.filter;

/**
 * @author AGbetrayal
 * @date 2019/6/2 20:12
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter{
    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doInternal((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    protected abstract void doInternal(HttpServletRequest request, HttpServletResponse response,
                                       FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
