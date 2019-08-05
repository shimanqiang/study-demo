package me.shimanqiang.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.lang.instrument.Instrumentation;

/**
 * https://notes.diguage.com/byte-buddy-tutorial/
 * Created by Administrator on 2018/8/4.
 *
 * @author shimanqiang
 */
public class ByteBuddyHelloWorld {
    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static final String classpath = classLoader.getResource("").getPath();

    public static void premain(String agentArgs, Instrumentation instrumentation) {

    }


    public static void main(String[] args) throws Exception {
        demo4();
    }

    private static void demo4() throws Exception {
        //操作没有加载的类
        //定义一个成员变量
        TypeDescription typeDescription = TypePool.Default.ofClassPath()
                .describe("smq.deml.bytebuddy.Bar") //此处不可以使用 Bar.class.getName()
                .resolve();
        ClassFileLocator classFileLocator = ClassFileLocator.ForClassLoader.ofClassPath();
        new ByteBuddy().redefine(typeDescription, classFileLocator)
                .defineField("name", String.class)
                .make()
                .load(ClassLoader.getSystemClassLoader());

        System.out.println(Bar.class.getDeclaredField("name"));
    }

    private static void demo3() throws Exception {
        //重新加载类
        //把bar类替换为了foo类
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        System.out.println(foo.m());
        new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.m());
    }

    private static void demo2() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        /**
         * 创建一个类型，继承Object类型，重写toString方法
         */
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("hello byte buddy !"))
                .make();
        //Class<?> clazz = dynamicType.load(classLoader).getLoaded();
        Class<?> clazz = dynamicType.load(classLoader, ClassLoadingStrategy.Default.WRAPPER).getLoaded();

        System.out.println(clazz.newInstance().toString());
    }


    private static void demo1() throws Exception {
        /**
         * 创建一个类型，继承Object类型
         * 生成的class在classes下
         */
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy().subclass(Object.class).make();
        dynamicType.saveIn(new File(classpath));
    }
}
