package me.shimanqiang.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author shimanqiang
 * @since 2019/7/15 10:37
 */
public class InstrumentTest {

    /**
     * 以vm参数的形式载入，在程序main方法执行之前执行
     * 其jar包的manifest需要配置属性Premain-Class
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain");
    }

    /**
     * 以Attach的方式载入，在Java程序启动后执行
     * 其jar包的manifest需要配置属性Agent-Class
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain");
    }
}
