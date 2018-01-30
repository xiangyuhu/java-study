package com.xinyue.panshi.design.proxy.dynamic;

import com.xinyue.panshi.common.util.PrintUtil;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class WZRYGamer implements Gamer{
    @Override
    public void paly() {
        PrintUtil.printString("王者荣耀选手正在嗨");
    }
}
