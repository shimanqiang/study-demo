package me.shimanqiang.arithmetic.sort;

/**
 * 选择排序
 * 思路：
 * 1、从第一个位置 出发往后面找，找到数组的最小值，和出发的位置交换
 */
public class SelectionSort extends AbstractSort {
    @Override
    protected void sort(int[] arr) {
        sort1(arr);
    }

    void sort2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        for (int i = left; i <= right; i++) {
            int minPos = left;
            int maxPos = right;
            //找最小值和最大值的过程
            for (int j = left; j <= right; j++) {
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
                if (arr[maxPos] < arr[j]) {
                    maxPos = j;
                }
            }
            print(arr);
            swap(arr, left, minPos);
            print(arr);
            swap(arr, right, maxPos);
            print(arr);
            //缩绒数组的范围
            left++;
            right--;
        }
    }

    void sort1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            //找最小值的过程
            for (int j = i; j < arr.length; j++) {
                if (arr[minPos] > arr[j]) {
                    minPos = j;
                }
            }
            swap(arr, i, minPos);
        }
    }

}
