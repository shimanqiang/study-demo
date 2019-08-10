package me.shimanqiang.agent;

import java.lang.instrument.Instrumentation;

/**
 * maven 打包配置
 * <p>
 * <plugin>
 * <groupId>org.apache.maven.plugins</groupId>
 * <artifactId>maven-jar-plugin</artifactId>
 * <version>2.4</version>
 * <configuration>
 * <archive>
 * <manifestEntries>
 * <Premain-Class>me.shimanqiang.agent.AgentMain</Premain-Class>
 * <Agent-Class>me.shimanqiang.agent.AgentMain</Agent-Class>
 * <Can-Redefine-Classes>true</Can-Redefine-Classes>
 * <Can-Retransform-Classes>true</Can-Retransform-Classes>
 * <Can-Set-Native-Method-Prefix>true</Can-Set-Native-Method-Prefix>
 * </manifestEntries>
 * </archive>
 * </configuration>
 * </plugin>
 * <p>
 * 启动配置
 * <p>
 * java -jar -javaagent:/xx/xxx.jar
 */
public class AgentMain {

    /**
     * 以vm参数的形式载入，在程序main方法执行之前执行
     * 其jar包的manifest需要配置属性Premain-Class
     * 需要app启动配置 -javaagent:/xxx/xxx.jar
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain");
    }

    /**
     * 以Attach的方式载入，在Java程序启动后执行
     * 其jar包的manifest需要配置属性Agent-Class
     * 需要使用attach api动态操作
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain");
    }
}
