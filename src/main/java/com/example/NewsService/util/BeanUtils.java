package com.example.NewsService.util;

import com.example.NewsService.aop.Loggable;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass
public class BeanUtils {
    @SneakyThrows
    @Loggable
    public void copyNonNullFields(Object source, Object destination) {
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);

            if (value != null) {
                field.set(destination, value);
            }
        }
    }
}