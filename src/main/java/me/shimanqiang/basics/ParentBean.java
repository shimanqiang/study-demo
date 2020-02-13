package me.shimanqiang.basics;

/**
 * Created by Administrator on 2020/1/11.
 */
public class ParentBean {
    private static int a = 1;

    {
        System.out.println("我在前面ParentBean");
    }

    private int c = initc();

    static {
        b = 1;
        System.out.println("1.父类静态代码块：赋值b成功");
        System.out.println("1.父类静态代码块：a的值" + a);
    }

    private static int b;

    int initc() {
        System.out.println("3.父类成员变量赋值：---> c的值" + c);
        this.c = 12;
        System.out.println("3.父类成员变量赋值：---> c的值" + c);
        return c;
    }

    public ParentBean() {
        System.out.println("4.父类构造方式开始执行---> a:" + a + ",b:" + b);
        System.out.println("4.父类构造方式开始执行---> c:" + c);
    }
}
