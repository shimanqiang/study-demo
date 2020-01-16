package me.shimanqiang.leetcode;

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

        Node n1 = new Node(3);
        Node n1_1 = new Node(7);
        Node n1_1_1 = new Node(2);
        n1.next = n1_1;
        n1_1.next = n1_1_1;
        twoNumPlusTest.print(n1);

        Node n2 = new Node(2);
        Node n2_1 = new Node(5);
        Node n2_1_1 = new Node(1);
        n2.next = n2_1;
        n2_1.next = n2_1_1;
        twoNumPlusTest.print(n2);

        //3,7,2
        //2,5,1
        //target: 5,2,4
        Node node = twoNumPlusTest.twoNumPlus(n1, n2);
        twoNumPlusTest.print(node);
    }

    private void print(Node node) {
        do {
            System.out.print(node.val);
            if (node.hasNext()) {
                System.out.print("->");
            } else {
                System.out.println();
            }
            node = node.next;
        } while (node != null);
    }

    private Node twoNumPlus(Node n1, Node n2) {
        Node r = new Node(0);
        Node tmp = r;
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
            tmp.next = new Node(v);
            tmp = tmp.next;
        }
        if (carry > 0) {
            tmp.next = new Node(carry);
        }
        return r.next;
    }

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
