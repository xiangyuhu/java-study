package com.xinyue.panshi.design.proxy.cligb;
import java.lang.reflect.Method;

import com.xinyue.panshi.design.proxy.dynamic.Gamer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class CglibProxy implements MethodInterceptor{
    private Gamer target;

    public Object getProxyInstance(Gamer target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Cligb 热身");
        Object result = proxy.invokeSuper(target, args);
        System.out.println("Cligb 结束");
        return result;
    }
}
