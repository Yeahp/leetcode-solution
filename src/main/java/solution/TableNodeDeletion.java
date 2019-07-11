package solution;

import data.structure.ListNode;

public class TableNodeDeletion {

    // 删除链表中等于给定值 val 的所有节点。
    // 示例:
    // 输入: 1->2->6->3->4->5->6, val = 6
    // 输出: 1->2->3->4->5
    public ListNode<Integer> nodeDeletion(ListNode<Integer> root, int val) {
        if (root == null) return null;
        ListNode<Integer> res = root;
        while (root != null && root.value == val) {
            res = root.next;
            root.next = null;
            root = res;
        }
        deletion(root, val);
        return res;
    }

    private void deletion(ListNode<Integer> node, int val) {
        if (node == null) return;
        ListNode<Integer> cur = node;
        ListNode<Integer> next = node.next;
        while (next != null) {
            if (next.value == val) {
                cur.next = next.next;
                next.next = null;
                next = cur.next;
            } else {
                cur = next;
                next = next.next;
            }
        }
    }

    public static void main(String[] args) {
        TableNodeDeletion tnd = new TableNodeDeletion();
        ListNode<Integer> root = new ListNode<Integer>(0);
        ListNode<Integer> node1 = new ListNode<Integer>(1);
        ListNode<Integer> node2 = new ListNode<Integer>(2);
        ListNode<Integer> node3 = new ListNode<Integer>(3);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode<Integer> res = tnd.nodeDeletion(root, 1);
        System.out.println(res.next.value);
    }

}
