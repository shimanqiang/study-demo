package me.shimanqiang.arithmetic;

/**
 * 二分法搜索
 *
 * @author shimanqiang
 * @since 2020/6/17 12:08
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};


        System.out.println(bs(a, 0, a.length - 1, 7));
    }

    static int bs(int[] a, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (a[mid] == target) {
            return mid;
        }
        if (a[mid] < target) {
            return bs(a, mid + 1, high, target);
        } else {
            return bs(a, low, mid - 1, target);
        }
    }
}
