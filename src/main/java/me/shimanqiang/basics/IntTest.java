package me.shimanqiang.basics;

/**
 * https://blog.csdn.net/chijiandi/article/details/79022473
 *
 * @author shimanqiang
 * @since 2020/2/22 21:07
 */
public class IntTest {

    public static void main(String[] args) {
        /**
         * Java中各个进制的表示法
         */
        //二进制:13
        byte a = 0b1101;
        //八进制
        byte b = 015;
        //十进制
        byte c = 13;
        //十六进制
        byte d = 0xd;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println("---------------");


        /**
         * 消1法：x&(x-1)消除二进制的中一个1
         */
        int e = a & (a - 0b0001);
        System.out.println(e);
        System.out.println("---------------");

        /**
         * 十进制转其它进制（二进制，八进制，十六进制）
         */
        int k = 17;
        // 转二进制
        System.out.println(Integer.toBinaryString(k));
        // 转八进制
        System.out.println(Integer.toOctalString(k));
        // 转十六进制
        System.out.println(Integer.toHexString(k));
        System.out.println("---------------");

        /**
         * 其它进制转十进制
         * radix表示参数是几进制的
         */
        String j = "1101";
        int jj = Integer.parseInt(j, 2);
        System.out.println(jj);
        int jjj = Integer.parseInt(j, 16);
        System.out.println(jjj);

    }
}
