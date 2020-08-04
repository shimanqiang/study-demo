package me.shimanqiang.arithmetic.sort;

public class SortTest {

    public static void main(String[] args) {
        int[] arr = {66, 5, 4, 6, 17, 12, 5, 32, 7, 8, 95, 3};
        AbstractSort sortImpl = new InsertionSort();
        sortImpl.sort(arr);
        sortImpl.print(arr);
    }
}
