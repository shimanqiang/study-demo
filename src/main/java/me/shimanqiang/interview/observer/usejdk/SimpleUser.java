package me.shimanqiang.interview.observer.usejdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 被观察者
 *
 * @author shimanqiang
 * @since 2020/7/1 21:33
 */
public class SimpleUser implements Observer {
    private String name;

    public SimpleUser(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("用户：" + name + "，收到：" + arg);
    }
}
