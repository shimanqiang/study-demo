package me.shimanqiang.agent;

/**
 * Created by Administrator on 2019/8/10.
 * <p>
 * java -jar -javaagent:target/study-demo-1.0-SNAPSHOT.jar
 * 配置idea Vm options:  -javaagent:target/study-demo-1.0-SNAPSHOT.jar
 */
public class TestAgentMain {
    public static void main(String[] args) throws Exception {
        System.out.println("test agent main");

        Thread.sleep(Integer.MAX_VALUE);
    }
}
