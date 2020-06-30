package me.shimanqiang.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 参考：https://juejin.im/post/5e4f9f25e51d4526c358faab
 * -XX:+UseCompressedOops   开启指针压缩
 * -XX:-UseCompressedOops   关闭指针压缩
 * <p>
 * <p>
 * 参考：https://www.cnblogs.com/sxrtb/p/12730139.html
 * java.lang.Object object internals:
 * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 * 8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
 * 12     4        (loss due to the next object alignment)
 * <p>
 * <p>
 * mark word =
 * jol中打印的 mark word 这个图里看到的真好相反，这个设计到“大端存储和小端存储”这个知识
 *
 * @author shimanqiang
 * @since 2020/6/28 19:41
 */
public class JolTest {
    private int a;

    public static void main(String[] args) {

        int initialCapacity = -3;

        System.out.println(initialCapacity = initialCapacity >>> 1);

        /**
         * Object内存中的大小以及布局
         */
        Object obj = new String();

        //使用jol计算对象的大小（单位为字节）：
        long objSize = ClassLayout.parseInstance(obj).instanceSize();
        System.out.println(objSize);

        //使用jol计算对象头大象
        System.out.println(ClassLayout.parseInstance(obj).headerSize());

        //使用jol查看对象的内存布局：
        String objLayout = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println(objLayout);


        synchronized (obj) {
//            System.out.println("加锁后内容的变化");
            String objLayout2 = ClassLayout.parseInstance(obj).toPrintable();
            System.out.println(objLayout2);
        }


    }
}
