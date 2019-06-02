package com.core.web.mvc;

import com.core.commons.util.CollectionUtils;
import com.core.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * <p>
 *  * 优化 Spring webmvc 对于 restful URL 使用 {@link PathVariable } 注解性能低下的问题
 *  * </p>
 *  *
 *  * <pre>
 *  * 在请求参数或请求头中添加 {@value #requestMappingParameterName }}} 信息，
 *  * 当参数值等于 {@link RequestMapping}} 或 {@link GetMapping}} 或 {@link PostMapping}} 或 {@link DeleteMapping}} 等的name 属性值时 ，
 *  * 使用自定义的 {@link #lookupHandlerMethod(String, HttpServletRequest)}}方法。
 *  * </pre>
 *  *
 *  * <pre>
 *  * 	使用方式 ：
 *  * 		如  定义的 mapping 如下：
 *  * 		&#64;RequestMapping(path = "{id}",name = "test")
 *  * 		public String test(@PathVariable String id) {
 *  * 			return "hello," + id;
 *  *      }
 *  *
 *  * 		则请求方式：https://localhost:${port}/test?path_variable_event=test
 *  * 		或在请求头添加 path_variable_event=test ：https://localhost:${port}/test
 *  *
 *  * </pre>
 * @author AGbetrayal
 * @date 2019/6/2 20:54
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRequestMappingHandlerMapping.class);

    private static final Map<HandlerMethod, RequestMappingInfo> METHOD_REQUEST_MAPPING_INFO_MAP = new HashMap<>(64);

    private String requestMappingParameterName = "path_variable_event";

    public void setRequestMappingParameterName(String requestMappingParameterName) {
        this.requestMappingParameterName = requestMappingParameterName;
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        METHOD_REQUEST_MAPPING_INFO_MAP.put(createHandlerMethod(handler, method), mapping);
        super.registerHandlerMethod(handler, method, mapping);
    }

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        String requestMappingName = request.getParameter(requestMappingParameterName);
        final boolean debug = LOGGER.isDebugEnabled();
        if (StringUtils.isEmpty(requestMappingName)) {
            requestMappingName = request.getHeader(requestMappingParameterName);
            if (StringUtils.isEmpty(requestMappingName)) {
                if (debug) {
                    LOGGER.debug("request parameter [" + requestMappingParameterName + "] is null");
                }
                return super.lookupHandlerMethod(lookupPath, request);
            }
        }
        List<HandlerMethod> handlerMethods = super.getHandlerMethodsForMappingName(requestMappingName);
        long size = CollectionUtils.size(handlerMethods);
        if (size != 1) {
            if (debug) {
                LOGGER.debug("path_variable_event :{},handlerMethods 匹配数:{}", requestMappingName, size);
            }
            return super.lookupHandlerMethod(lookupPath, request);
        }
        HandlerMethod handlerMethod = handlerMethods.get(0);
        RequestMappingInfo mappingInfo = METHOD_REQUEST_MAPPING_INFO_MAP.get(handlerMethod);
        if (null == mappingInfo) {
            if (debug) {
                LOGGER.debug("mappingInfo is null.");
            }
            return super.lookupHandlerMethod(lookupPath, request);
        }
        super.handleMatch(mappingInfo, lookupPath, request);
        return handlerMethod;
    }
}
