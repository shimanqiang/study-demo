package me.shimanqiang.basics;

/**
 * Created by Administrator on 2020/1/11.
 */
public class SonBean extends ParentBean {
    private static int sa = 1;
    {
        System.out.println("我在前面SonBean");
    }
    private int sc = initc2();

    static {
        sb = 1;
        System.out.println("2.子类静态代码块：赋值sb成功");
        System.out.println("2.子类静态代码块：sa的值" + sa);
    }

    private static int sb;

    int initc2() {
        System.out.println("5.子类成员变量赋值--->：sc的值" + sc);
        this.sc = 12;
        return sc;
    }

    public SonBean() {
        System.out.println("6.子类构造方式开始执行---> sa:" + sa + ",sb:" + sb);
        System.out.println("6.子类构造方式开始执行---> sc:" + sc);
    }
}
