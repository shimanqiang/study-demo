package me.shimanqiang.leetcode;

import me.shimanqiang.model.ListNode;

/**
 * https://www.nowcoder.com/discuss/198840?type=1
 *
 * @author shimanqiang
 * @since 2020/2/5 15:00
 */
public class PrintLinkedListFromTailToHead {

    public static void main(String[] args) {
        ListNode head = initData();
        head.print();
        head.printNodeFromTailToHead();
    }


    private static ListNode initData() {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        for (int i = 1; i < 5; i++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return head;
    }
}
