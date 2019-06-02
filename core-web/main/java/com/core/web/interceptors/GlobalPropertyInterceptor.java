package com.core.web.interceptors;

import com.core.commons.util.CollectionUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AGbetrayal
 * @date 2019/6/2 20:28
 */
public class GlobalPropertyInterceptor extends HandlerInterceptorAdapter {
    /**
     * request 中的属性
     *
     * @see RequestPropertyProperties#property
     */
    private Map<String, Object> property = new HashMap<>();

    public void setProperty(Map<String, Object> property) {
        if (CollectionUtils.isNotEmpty(property)) {
            this.property.putAll(property);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        property.forEach(request::setAttribute);
        return true;
    }

    /**
     * request属性
     */
    @Data
    @ConfigurationProperties(prefix = "request")
    public static class RequestPropertyProperties {

        private Map<String, Object> property;
    }
}
