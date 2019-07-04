package solution;

import data.structure.ListNode;

public class ReverseLinkedTable {

    private LinkedTableLen linkedListLength = new LinkedTableLen();

    // 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
    // 输入: 1->2->3->4->5->NULL, m = 2, n = 4
    // 输出: 1->4->3->2->5->NULL
    // 当 start = 1 且 end 为链表长度时，翻转整个链表
    public void reverseSubLinkedList(ListNode<Integer> head, int start, int end) {
        if (start < 0 || end < 0 || start > end || end > linkedListLength.linkedListLength(head)) {
            return;
        }

        if (start == 1) {
            ListNode<Integer> pre = head;
            ListNode<Integer> cur = head;
            ListNode<Integer> next = head.next;
            int i = 2;
            while (i < end + 1) {
                cur.next = next.next;
                next.next = pre;
                pre = next;
                next = cur.next;
                i++;
            }
        } else {
            ListNode<Integer> pre = head;
            ListNode<Integer> cur = head.next;
            ListNode<Integer> next = cur.next;
            int i = 3;
            while (i < end + 1) {
                if (i - 2 < start - 1) {
                    pre = pre.next;
                    cur = cur.next;
                    next = next.next;
                }
                if (i >= start + 1) {
                    cur.next = next.next;
                    next.next = pre.next;
                    pre.next = next;
                    next = cur.next;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<Integer>(1);
        ListNode<Integer> node2 = new ListNode<Integer>(2);
        ListNode<Integer> node3 = new ListNode<Integer>(3);
        ListNode<Integer> node4 = new ListNode<Integer>(4);
        ListNode<Integer> node5 = new ListNode<Integer>(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ReverseLinkedTable reverseLinkedTable = new ReverseLinkedTable();
        reverseLinkedTable.reverseSubLinkedList(node1, 1, 5);
        ListNode<Integer> node = node5;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
