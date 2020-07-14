package test;

import java.util.Arrays;

/**
 * 给定一个整数数组，求出满足“和”是特定值的所有组合。例如，数组：【1，2，3】， “和”为3， 要打印出：
 * 1，2
 * 3
 * 两种情况。
 * case2， 【2，3，4，5】， “和”为7， 打印出：
 * 2，5
 * 3，4
 * 两种情况。
 * <p>
 * 【2，3，4，5】 ， “和”为9
 * 4，5
 * 2，3，4
 *
 * @author shimanqiang
 * @since 2020/7/2 21:28
 */
public class Test001 {

    public static void main(String[] args) {
        int target = 9;
        int[] a = new int[]{2, 3, 4, 5};
        Arrays.sort(a);

        case1(a, target);

    }

    static void case1(int[] a, int target) {
        int small = 0;
        int big = 0;

        int sum = a[small];

        while (small <= big) {
            if (sum == target) {
                print(a, small, big);
            }
            while (small < big && sum > target) {
                sum = sum - a[small];
                small++;
                if (sum == target) {
                    print(a, small, big);
                }
            }
            big++;
            if (big >= a.length) {
                break;
            }
            sum = sum + a[big];
        }
    }

    static void print(int[] a, int small, int big) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = small; i <= big; i++) {
            sb.append(a[i]).append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

}
