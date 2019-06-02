package com.core.commons.converters;

import com.core.commons.util.BooleanUtils;

/**
 * @author AGbetrayal
 * @date 2019/6/2 19:11
 */
public class StringToBooleanConverter extends StringGenericConverter<Boolean>{
    /**
     * 默认为null 时转换为 false
     */
    public StringToBooleanConverter() {
        super(Boolean.FALSE, Boolean.class);
    }

    /**
     *
     */
    @Override
    protected Boolean doConvert(String source) {
        return BooleanUtils.toBoolean(source);
    }
}
