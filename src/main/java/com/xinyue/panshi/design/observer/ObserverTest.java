package com.xinyue.panshi.design.observer;

/**
 * @author hxy
 * @time 2018/1/22
 * @desc
 */
public class ObserverTest {
    public static void main (String [] args) {
        ApplicationObservable applicationObservable = new ApplicationObservable();
        applicationObservable.addObserver(new ApplicationObserver());
        applicationObservable.action(ActionCode.CLOSE, "test");
        applicationObservable.action(ActionCode.COMMIT, "test");
    }
}
