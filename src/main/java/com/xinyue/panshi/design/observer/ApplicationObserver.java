package com.xinyue.panshi.design.observer;

import com.xinyue.panshi.common.util.PrintUtil;

import java.util.Observable;
import java.util.Observer;

/**
 * @author hxy
 * @time 2018/1/22
 * @desc
 */
public class ApplicationObserver implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        ApplicationObservable applicationObservable = (ApplicationObservable)o;
        PrintUtil.printString((String) arg);
    }
}
