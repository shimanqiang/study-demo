package me.shimanqiang.test;

public class StringTest {
    public static void main(String[] args) {
        String s1 = new StringBuilder("go")
                .append("od").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja")
                .append("va").toString();
        System.out.println(s2.intern() == s2);

        String s3 = new StringBuilder("go")
                .append("od").toString();
        System.out.println(s3.intern() == s1);

        System.out.println("====================");
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0.0);
    }
}
