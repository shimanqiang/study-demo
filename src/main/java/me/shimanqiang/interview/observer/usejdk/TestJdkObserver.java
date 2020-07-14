package me.shimanqiang.interview.observer.usejdk;

/**
 * @author shimanqiang
 * @since 2020/7/1 21:36
 */
public class TestJdkObserver {

    public static void main(String[] args) {
        SimpleUser u1 = new SimpleUser("Tom");
        SimpleUser u2 = new SimpleUser("Anna");

        SimpleSubject subject = new SimpleSubject();
        subject.addObserver(u1);
        subject.addObserver(u2);

        subject.pushMsg("吃饭了");

        System.out.println("##########");

        subject.deleteObserver(u1);
        subject.pushMsg("睡觉了");
    }
}
