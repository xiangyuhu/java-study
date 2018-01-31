package com.xinyue.panshi.design.proxy;

import com.xinyue.panshi.design.proxy.cligb.CglibProxy;
import com.xinyue.panshi.design.proxy.dynamic.Gamer;
import com.xinyue.panshi.design.proxy.dynamic.GamerProxy;
import com.xinyue.panshi.design.proxy.dynamic.WZRYGamer;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class ProxyTest {

    public static void main (String [] args) {
        Gamer gamer = new WZRYGamer();
        Gamer proxy = GamerProxy.newProxyInstance(gamer);
        proxy.paly();

        CglibProxy cglibProxy = new CglibProxy();
        Gamer gamerProxy = (Gamer) cglibProxy.getProxyInstance(new WZRYGamer());
        gamerProxy.paly();

        com.xinyue.panshi.design.proxy.simple.GamerProxy proxySimple = new com.xinyue.panshi.design.proxy.simple.GamerProxy("1", "2");
        proxySimple.play();
    }
}
