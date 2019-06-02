package com.core.commons.converters;

import com.core.commons.util.AssertUtils;
import com.core.commons.util.ObjectUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * @author AGbetrayal
 * @date 2019/6/2 19:12
 */
public abstract class StringGenericConverter<T> implements GenericConverter {
    /*
    * 为空时的默认值
    * */
    @Getter
    @Setter
    private T defaultValue;

    /*
    * 目标类型
    * */
    @Getter
    private final Class<T> targetType;

    protected StringGenericConverter(Class<T> targetType) {
        this(null, targetType);
    }

    protected StringGenericConverter(T defaultValue, Class<T> targetType) {
        AssertUtils.isTrue(Objects.nonNull(targetType), "target Type must not be null");
        this.defaultValue = defaultValue;
        this.targetType = targetType;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, targetType));
    }

    @Override
    public T convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (ObjectUtils.isEmpty(source)) {
            return defaultValue;
        }
        try {
            return doConvert(source.toString().trim());
        } catch (Exception e) {
            throw new ConversionFailedException(TypeDescriptor.forObject(source),
                    TypeDescriptor.valueOf(this.targetType), source, e);
        }
    }

    protected abstract T doConvert(String source);
}
