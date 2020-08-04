package me.shimanqiang.arithmetic.sort;

import java.util.Arrays;

public class RepeatNum {


    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 0, 2, 5, 3};

        System.out.println(findRepeatNum(a));
    }

    public static int findRepeatNum(int[] a) {
        for (int i = 0; i < a.length; i++) {
            while (a[i] != i) {
                if (a[a[i]] == a[i]) {
                    return a[i];
                }
                int tmp = a[i];
                a[i] = a[tmp];
                a[tmp] = tmp;
                System.out.println(Arrays.toString(a));
            }
        }
        return -1;
    }

}