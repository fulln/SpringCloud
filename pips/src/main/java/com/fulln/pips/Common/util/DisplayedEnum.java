package com.fulln.pips.Common.util;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public interface DisplayedEnum {

    String DEFAULT_VALUE_NAME = "code";

    String DEFAULT_LABEL_NAME = "label";

    default Integer getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return Integer.parseInt(field.get(this).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonValue
    default String getLabel() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer code) {
        if (code == null)
            throw  new IllegalArgumentException("DisplayedEnum value should not be null");
        if (enumClass.isAssignableFrom(DisplayedEnum.class))
            throw new IllegalArgumentException("illegal DisplayedEnum type");
        T[] enums = enumClass.getEnumConstants();
        for (T t: enums) {
            DisplayedEnum displayedEnum = (DisplayedEnum)t;
            if (displayedEnum.getValue().equals(code))
                return (T) displayedEnum;
        }
        throw new IllegalArgumentException("cannot parse integer: " + code + " to " + enumClass.getName());
    }
}