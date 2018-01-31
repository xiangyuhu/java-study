package com.xinyue.panshi.design.proxy.simple;

import com.xinyue.panshi.design.proxy.dynamic.Gamer;
import com.xinyue.panshi.design.proxy.dynamic.WZRYGamer;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class GamerProxy {
    private Gamer gamer;

    public GamerProxy(String userName, String password){
        gamer = new WZRYGamer(userName, password);
    }

    public void play () {
        gamer.paly();
    }
}
