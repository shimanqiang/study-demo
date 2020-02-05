package me.shimanqiang.leetcode;

/**
 * @author shimanqiang
 * @since 2020/2/5 14:59
 */
public class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void print() {
        Node node = this;
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

    public void printNodeFromTailToHead() {
        Node node = this;
        if (node != null) {
            if (node.hasNext()) {
                node.next.printNodeFromTailToHead();
                System.out.print("->");
                System.out.print(node.val);
            } else {
                System.out.print(node.val);
            }
        } else {
            System.out.println();
        }
    }
}
