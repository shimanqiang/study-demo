package me.shimanqiang.arithmetic;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * top K 问题
 * https://blog.csdn.net/wufaliang003/article/details/82940218
 *
 * @author shimanqiang
 * @since 2020/6/17 12:05
 */
public class TopK {
    private static final int TOP_K = 5;
    /**
     * 准备一个总结点数为topK的小顶堆
     */
    private static final PriorityQueue<Integer> heapQueue = new PriorityQueue<>(TOP_K);

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < a.length; i++) {
            topK(a[i], TOP_K);
        }

        printHeap();
    }

    static void printHeap() {
        Iterator<Integer> iterator = heapQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static void topK(int data, int topK) {
        int size = heapQueue.size();
        if (size < topK) {
            heapQueue.add(data);
        } else {
            Integer peek = heapQueue.peek();
            if (peek < data) {
                heapQueue.poll();
                heapQueue.add(data);
            }
        }
    }
}
