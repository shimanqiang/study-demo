package me.shimanqiang.model;

/**
 * 列表节点
 *
 * @author shimanqiang
 * @since 2020/2/5 14:59
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void print() {
        ListNode listNode = this;
        do {
            System.out.print(listNode.val);
            if (listNode.hasNext()) {
                System.out.print("->");
            } else {
                System.out.println();
            }
            listNode = listNode.next;
        } while (listNode != null);
    }

    public void printNodeFromTailToHead() {
        ListNode listNode = this;
        if (listNode != null) {
            if (listNode.hasNext()) {
                listNode.next.printNodeFromTailToHead();
                System.out.print("->");
                System.out.print(listNode.val);
            } else {
                System.out.print(listNode.val);
            }
        } else {
            System.out.println();
        }
    }
}
