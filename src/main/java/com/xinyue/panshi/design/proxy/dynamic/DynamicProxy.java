package com.xinyue.panshi.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by coffice on 2017-05-15.
 */
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {
        T result = (T) Proxy.newProxyInstance(loader, interfaces, handler);
        return result;
    }
}
