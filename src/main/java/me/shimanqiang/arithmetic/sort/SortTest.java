package me.shimanqiang.arithmetic.sort;

public class SortTest {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 17, 12, 5, 32, 7, 8, 95, 3};
        AbstractSort sortImpl = new HeapSort();
        sortImpl.sort(arr);
        sortImpl.print(arr);
    }
}
