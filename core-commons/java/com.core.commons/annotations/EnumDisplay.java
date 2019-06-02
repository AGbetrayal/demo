package com.core.commons.annotations;

import java.lang.annotation.*;

/**
 * @author AGbetrayal
 * @date 2019/6/2 21:30
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDisplay {

    /**
     * value
     *
     * @return
     */
    String value();

    /**
     * Order
     *
     * @return
     */
    int order() default 0;
}