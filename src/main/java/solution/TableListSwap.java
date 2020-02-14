package solution;

import data.structure.ListNode;

public class TableListSwap {

    // 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
    // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
    // 示例:
    // 给定 1->2->3->4, 你应该返回 2->1->4->3.
    public ListNode<Integer> nodeSwap(ListNode<Integer> root) {
        if (root == null) return null;
        if (root.next == null) return root;
        ListNode<Integer> res = root.next;
        ListNode<Integer> dummy = new ListNode<Integer>(null, root);
        while (dummy != null) {
            dummy = swap(dummy);
        }
        return res;
    }

    private ListNode<Integer> swap(ListNode<Integer> dummy) {
        ListNode<Integer> pre = dummy.next;
        if (pre == null) return null;
        ListNode<Integer> next = pre.next;
        if (next == null) return null;
        pre.next = next.next;
        next.next = pre;
        dummy.next = next;
        return pre;
    }

    // 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
    // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
    // 示例:
    // 给定 1->2->3->4, 你应该返回 4->3->2->1
    public ListNode<Integer> reverse(ListNode<Integer> root) {
        if (root == null) return null;
        ListNode<Integer> head = root;
        ListNode<Integer> pre = root;
        ListNode<Integer> next = root.next;
        while (next != null) {
            pre.next = next.next;
            next.next = head;
            head = next;
            next = pre.next;
        }
        return head;
    }


    public static void main(String[] args) {
        TableListSwap tls = new TableListSwap();
        ListNode<Integer> root = new ListNode<Integer>(0);
        ListNode<Integer> node1 = new ListNode<Integer>(1);
        ListNode<Integer> node2 = new ListNode<Integer>(2);
        ListNode<Integer> node3 = new ListNode<Integer>(3);
        ListNode<Integer> node4 = new ListNode<Integer>(4);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode<Integer> res = tls.reverse(root);
        while (res != null) {
            System.out.println(res.value);
            res = res.next;
        }
    }
}
