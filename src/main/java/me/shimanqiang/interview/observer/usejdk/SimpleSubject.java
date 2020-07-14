package me.shimanqiang.interview.observer.usejdk;

import java.util.Observable;

/**
 * 被观察者：主题
 *
 * @author shimanqiang
 * @since 2020/7/1 21:35
 */
public class SimpleSubject extends Observable {

    public void pushMsg(String msg) {

        setChanged();

        notifyObservers(msg);
    }
}
