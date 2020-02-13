package me.shimanqiang.basics;

/**
 * 结论：
 * 1、父类优先之类加载
 * 2、第一执行静态static修饰的（静态变量、静态代码块）谁在前先执行谁
 * 3、第二执行成员变量、代码块、构造函数
 *
 * @author Created by Administrator on 2020/1/11.
 *         1、父类静态代变量、 父类静态代码块、
 *         2、子类静态变量、子类静态代码块、
 *         3、父类非静态变量（父类实例成员变量）、
 *         4、父类构造函数、
 *         5、子类非静态变量（子类实例成员变量）、
 *         6、子类构造函数。
 * @version <----打印结果---->
 *          静态代码快
 *          静态变量
 *          1.父类静态代码块：赋值b成功
 *          1.父类静态代码块：a的值1
 *          2.子类静态代码块：赋值sb成功
 *          2.子类静态代码块：sa的值1
 *          我在前面ParentBean
 *          3.父类成员变量赋值：---> c的值0
 *          3.父类成员变量赋值：---> c的值12
 *          4.父类构造方式开始执行---> a:1,b:1
 *          4.父类构造方式开始执行---> c:12
 *          我在前面SonBean
 *          5.子类成员变量赋值--->：sc的值0
 *          6.子类构造方式开始执行---> sa:1,sb:1
 *          6.子类构造方式开始执行---> sc:12
 */
public class ClassLoaderTest {
    private int a = 1;
    private int b;

    public static void main(String[] args) {
//        new SonBean();
        System.out.println(s);
    }

    public ClassLoaderTest() {
        System.out.println(111);
    }

    {
        System.out.println(b);
        System.out.println(a);
        b = 2;
        System.out.println(b);
    }


    static {
        System.out.println("静态代码快");
    }

    private static int s = test12();

    private static int test12() {
        System.out.println("静态变量");
        return 2;
    }
}
