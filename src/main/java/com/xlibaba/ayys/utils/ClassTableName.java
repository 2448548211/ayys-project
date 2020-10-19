package com.xlibaba.ayys.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChenWang
 * @annotationName ClassTableName
 * @date 2020/10/15 20:19
 * @since JDK 1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassTableName {
    String value() default "";
}
