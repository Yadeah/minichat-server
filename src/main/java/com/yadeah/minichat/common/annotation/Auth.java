package com.yadeah.minichat.common.annotation;

import com.yadeah.minichat.common.constant.system.MethodOperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    boolean auth() default true;

    MethodOperationType type() default MethodOperationType.QUERY;

}
