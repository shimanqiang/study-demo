package me.shimanqiang.jvm;

/**
 * Created by Administrator on 2020/3/5.
 */
public class StackVariablesTest {

    public static void main(String[] args) throws Exception{
        StackVariablesTest test = new StackVariablesTest();

        int i = 10;

        System.out.println(i);

        String s = ".abb.";
        System.out.println(s.lastIndexOf("."));
        System.out.println(s.substring(s.lastIndexOf(".")));
    }
}
