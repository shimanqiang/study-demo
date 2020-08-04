package me.shimanqiang.arithmetic.sort;

import java.util.Arrays;

/**
 * @author shimanqiang
 * @since 2020/6/17 14:46
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {4, 33, 1, 2, 13, 4, 5, 5, 36, 7, 8, 9};
        System.out.println(Arrays.toString(a));
        quickSort4(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    static void quickSort4(int[] target, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = target[low];
        int i = low;
        int j = high;

        while (i < j) {
            while (i < j && pivot <= target[j]) {
                j--;
            }
            while (i < j && pivot >= target[i]) {
                i++;
            }
            if (i < j) {
                int tmp = target[i];
                target[i] = target[j];
                target[j] = tmp;
            }
        }
        target[low] = target[i];
        target[i] = pivot;
        quickSort4(target, low, i - 1);
        quickSort4(target, i + 1, high);

    }


    static void quickSort3(int[] target, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = target[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && target[i] < mid) {
                i++;
            }
            while (i < j && target[j] > mid) {
                j--;
            }
            if (i < j) {
                int temp = target[i];
                target[i] = target[j];
                target[j] = temp;
            }
        }
        System.out.println(Arrays.toString(target));
        quickSort3(target, low, j - 1);
        quickSort3(target, j + 1, high);
    }

    static void quickSort2(int[] target, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = target[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i < j && target[i] <= mid) {
                i++;
            }
            while (i < j && target[j] >= mid) {
                j--;
            }
            if (i < j) {
                int temp = target[i];
                target[i] = target[j];
                target[j] = temp;
            }
        }

        int t = target[low];
        target[low] = target[i - 1];
        target[i - 1] = t;

        quickSort2(target, low, j - 1);
        quickSort2(target, j + 1, high);
    }

    /**
     * 快排
     *
     * @param target
     * @param left
     * @param right
     */
    static void quickSort(int[] target, int left, int right) {
        if (left >= right) {
            return;
        }
        // 基准点
        int pivot = target[left];
        int temp;
        int i = left;
        int j = right;
        while (i < j) {
            while (target[i] <= pivot && i < j) {
                i++;
            }
            while (target[j] >= pivot && i < j) {
                j--;
            }
            if (i < j) {
                temp = target[i];
                target[i] = target[j];
                target[j] = temp;
            }
        }
        // left和right相遇了：
        // ①将相遇点的元素和pivot做交换：
        target[left] = target[i - 1];
        target[i - 1] = pivot;
        // ②基准点两边的元素的分别再做排序：
        quickSort(target, left, j - 1);
        quickSort(target, j + 1, right);
    }


}
