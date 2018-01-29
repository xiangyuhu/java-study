package com.xinyue.panshi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @author hxy
 * @time 2018/1/26
 * @desc
 */

@TestAnnotation(msg="hello annotation")
public class Test {

    @Check(value="hi")
    private int abc;


    public static void main(String[] args) throws NoSuchFieldException {
        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
            //获取类的注解
            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }

        Field a = Test.class.getDeclaredField("abc");
        a.setAccessible(true);
        //获取一个成员变量上的注解
        Check check = a.getAnnotation(Check.class);

        if ( check != null ) {
            System.out.println("check value:" + check.value());
        }

    }
}