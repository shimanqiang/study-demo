package me.shimanqiang.leetcode.arrays;

/**
 * Created by Administrator on 2020/3/3.
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] a = new int[]{1,2,4,6,7};
        int[] b = new int[]{3,5,8};

        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays1(a, b));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;

        int left = 0, right = 0;
        int i = 0, j = 0;
        for (int k = 0; k <= len / 2; k++) {
            left = right;
            if (i < m && (j >= n || nums1[i] < nums2[j])) {
                right = nums1[i++];
            } else {
                right = nums2[j++];
            }
        }

        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }


    /**
     * 时间复杂度：O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int k = m + n;
        int[] nums = new int[k];

        int count = 0;
        int i = 0, j = 0;
        while (count < k) {
//            if (i < m && j >= n) {
//                nums[count++] = nums1[i];
//                i++;
//            }
//            if (j < n && i >= m) {
//                nums[count++] = nums2[j];
//                j++;
//            }
//            if (i < m && j < n && nums1[i] <= nums2[j]) {
//                nums[count++] = nums1[i];
//                i++;
//            }
//            if (i < m && j < n && nums1[i] > nums2[j]) {
//                nums[count++] = nums2[j];
//                j++;
//            }

            if (i < m && (j >= n || nums1[i] < nums2[j])) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }
        if (k % 2 == 0) {
            return (nums[k / 2 - 1] + nums[k / 2]) / 2d;
        }
        return nums[k / 2];
    }
}
