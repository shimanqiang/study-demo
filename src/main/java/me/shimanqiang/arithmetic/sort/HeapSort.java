package me.shimanqiang.arithmetic.sort;

/**
 * 堆排序
 * https://www.cnblogs.com/chengxiao/p/6129630.html  （思想推理）
 * https://www.jianshu.com/p/0d383d294a80   （解题算法）
 */
public class HeapSort extends AbstractSort {
    @Override
    protected void sort(int[] arr) {
        int len = arr.length;
        //https://blog.csdn.net/weixin_30553065/article/details/99537126
        /**
         * 找到非叶子节点（最后一个叶子节点的索引值是n-1，它的父节点索引值是[(n-1)-1]/2 = n/2 -1）
         * 即：n/2-1
         */
        int notLeaf = len / 2 - 1;
        //构建大顶堆（升序）
        for (int i = notLeaf; i >= 0; i--) {
            adjustHeap(arr, i, len);
        }
        //交换堆顶元素与末尾元素，然后，重新调整堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            adjustHeap(arr, 0, len);
        }
    }

    /**
     * 调整堆
     *
     * @param arr
     * @param i
     * @param len
     */
    private void adjustHeap(int[] arr, int i, int len) {
        //左右孩子位置
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        //临时变量：最大值的位置
        int largest = i;
        //上下左右比较找出最大值的位置
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        //交换数据
        if (largest != i) {
            swap(arr, i, largest);
            adjustHeap(arr, largest, len);
        }
    }
}
