package me.shimanqiang.arithmetic.sort;

/**
 * 冒泡排序
 */
public class BubbleSort extends AbstractSort {
    @Override
    protected void sort(int[] arr) {
        sort1(arr);
    }

    /**
     * 从小到大
     *
     * @param arr
     */
    void sort1(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 从大到小
     *
     * @param arr
     */
    void sort2(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

}
