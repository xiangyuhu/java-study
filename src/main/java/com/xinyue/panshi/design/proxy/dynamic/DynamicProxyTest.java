package com.xinyue.panshi.design.proxy.dynamic;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class DynamicProxyTest {

    public static void main (String [] args) {
        Gamer gamer = new WZRYGamer();
        Gamer proxy = GamerProxy.newProxyInstance(gamer);
        proxy.paly();
    }
}
