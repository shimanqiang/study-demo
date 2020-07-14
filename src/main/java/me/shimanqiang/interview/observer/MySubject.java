package me.shimanqiang.interview.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题
 *
 * @author shimanqiang
 * @since 2020/7/1 21:21
 */
public class MySubject {

    private static List<MyObserver> observers = new ArrayList<>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String msg) {
        for (MyObserver observer : observers) {
            observer.update(msg);
        }
    }


    public void pushMsg(String newMsg) {
        notifyObservers(newMsg);
    }
}
