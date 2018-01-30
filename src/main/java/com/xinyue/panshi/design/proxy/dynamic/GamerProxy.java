package com.xinyue.panshi.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class GamerProxy extends DynamicProxy {
    public static <T> T newProxyInstance(Gamer gamer) {
        ClassLoader loader = gamer.getClass().getClassLoader();
        Class<?>[] classes = gamer.getClass().getInterfaces();
        InvocationHandler handler = new GamerInvocationHandlerImpl(gamer);
        return newProxyInstance(loader, classes, handler);
    }
}
