package me.shimanqiang.leetcode;

/**
 * https://www.nowcoder.com/discuss/198840?type=1
 *
 * @author shimanqiang
 * @since 2020/2/5 15:00
 */
public class PrintLinkedListFromTailToHead {

    public static void main(String[] args) {
        Node head = initData();
        head.print();
        head.printNodeFromTailToHead();
    }


    private static Node initData() {
        Node head = new Node(0);
        Node tmp = head;
        for (int i = 1; i < 5; i++) {
            tmp.next = new Node(i);
            tmp = tmp.next;
        }
        return head;
    }
}
