package me.shimanqiang.leetcode;

import me.shimanqiang.model.ListNode;

import java.util.function.Supplier;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author shimanqiang
 * @since 2020/1/16 14:56
 */
public class TwoNumPlusTest {

    public static void main(String[] args) {
        Supplier<TwoNumPlusTest> supplier = TwoNumPlusTest::new;
        TwoNumPlusTest twoNumPlusTest = supplier.get();

        ListNode n1 = new ListNode(3);
        ListNode n1_1 = new ListNode(7);
        ListNode n1_1_1 = new ListNode(2);
        n1.next = n1_1;
        n1_1.next = n1_1_1;
        n1.print();

        ListNode n2 = new ListNode(2);
        ListNode n2_1 = new ListNode(5);
        ListNode n2_1_1 = new ListNode(1);
        n2.next = n2_1;
        n2_1.next = n2_1_1;
        n2.print();

        //3,7,2
        //2,5,1
        //target: 5,2,4
        ListNode listNode = twoNumPlusTest.twoNumPlus(n1, n2);
        listNode.print();
    }

    private ListNode twoNumPlus(ListNode n1, ListNode n2) {
        ListNode r = new ListNode(0);
        ListNode tmp = r;
        int carry = 0;
        while (n1 != null || n2 != null) {
            int x = 0, y = 0;
            if (n1 != null) {
                x = n1.val;
                n1 = n1.next;
            }
            if (n2 != null) {
                y = n2.val;
                n2 = n2.next;
            }
            int sum = x + y + carry;
            carry = sum / 10;
            int v = sum % 10;
            tmp.next = new ListNode(v);
            tmp = tmp.next;
        }
        if (carry > 0) {
            tmp.next = new ListNode(carry);
        }
        return r.next;
    }
}
