package com.xinyue.panshi.annotation;

import java.lang.annotation.*;

/**
 * @author hxy
 * @time 2018/1/26
 * @desc
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TestAnnotation {
    int id() default 1;
    String msg();
}
