package me.shimanqiang.interview.observer;

/**
 * @author shimanqiang
 * @since 2020/7/1 21:26
 */
public class TestObserver {

    static class User implements MyObserver {
        private String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public void update(String msg) {
            System.out.println(name + "收到：" + msg);
        }
    }

    public static void main(String[] args) {
        User u1 = new User("Tom");
        User u2 = new User("Anna");


        MySubject mySubject = new MySubject();
        mySubject.addObserver(u1);
        mySubject.addObserver(u2);


        mySubject.pushMsg("吃饭了");
    }
}
