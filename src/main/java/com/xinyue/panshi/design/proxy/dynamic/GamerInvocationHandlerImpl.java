package com.xinyue.panshi.design.proxy.dynamic;

import javafx.beans.InvalidationListener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class GamerInvocationHandlerImpl implements InvocationHandler {

    private Gamer gamer;

    public GamerInvocationHandlerImpl(Gamer gamer){
        this.gamer = gamer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //doBefore
        System.out.println("王者荣耀选手正在热身");

        //processing
        Object returnValue = method.invoke(gamer, args);

        //doAfter
        System.out.println("比赛结束发送朋友圈");

        return returnValue;
    }
}
