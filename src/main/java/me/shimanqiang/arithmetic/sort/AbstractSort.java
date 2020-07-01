package me.shimanqiang.arithmetic.sort;

public abstract class AbstractSort {

    protected abstract void sort(int[] arr);

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    void print(int[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append("\t");
        }
        System.out.println(sb.toString());
    }
}
