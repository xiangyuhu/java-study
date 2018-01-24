package com.xinyue.panshi.design.observer;

import java.util.Observable;

/**
 * @author hxy
 * @time 2018/1/22
 * @desc
 */
public class ApplicationObservable extends Observable implements ActionHook  {

    private void close(){
        super.setChanged();
        super.notifyObservers("应用关闭成功");
    }
    private void commit(){
        super.setChanged();
        super.notifyObservers("提交信息成功");
    }

    @Override
    public void action(ActionCode actionCode, Object param) {
        if (actionCode == ActionCode.CLOSE) {
            close();
        } else if (actionCode == ActionCode.COMMIT) {
            commit();
        }
    }
}
