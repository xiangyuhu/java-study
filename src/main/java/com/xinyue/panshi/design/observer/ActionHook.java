package com.xinyue.panshi.design.observer;

/**
 * @author hxy
 * @time 2018/1/22
 * @desc
 */
public interface ActionHook {
    public void action(ActionCode actionCode, Object param);
}
