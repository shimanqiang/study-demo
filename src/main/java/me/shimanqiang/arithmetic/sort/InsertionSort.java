package me.shimanqiang.arithmetic.sort;

/**
 * 插入排序
 */
public class InsertionSort extends AbstractSort {
    @Override
    protected void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] >= arr[j - 1]) {
                    break;
                }
                swap(arr, j, j - 1);
//                if (arr[j] < arr[j - 1]) {
//                    swap(arr, j, j - 1);
//                }
            }
        }
    }
}
