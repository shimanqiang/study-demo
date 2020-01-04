package me.shimanqiang.performance;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 结论：【Comparator比较器】执行效率大于【lambda表达式】
 *
 * @author shimanqiang
 * @since 2019/8/13 15:55
 */
public class TestArraySort {
    public static void main(String[] args) {
        int exeCount = 10000;

        Integer[] array = {1, 4, 3, 2};
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < exeCount; i++) {
            Arrays.sort(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });                                             // 倒序排序
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime());

        array = new Integer[]{1, 4, 3, 2};
        stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < exeCount; i++) {
            Arrays.sort(array, (v1, v2) -> v2.compareTo(v1));
            // 正序排序
            Arrays.sort(array, Comparator.naturalOrder());
            // 倒序排序
            Arrays.sort(array, Comparator.reverseOrder());
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime());
    }
}
