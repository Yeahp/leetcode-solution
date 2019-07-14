package solution;

import data.structure.ListNode;
import java.util.List;

public class TableListCombination {

    // 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
    // 示例:
    // 输入:
    // [
    //  1->4->5,
    //  1->3->4,
    //  2->6
    // ]
    // 输出: 1->1->2->3->4->4->5->6
    public ListNode<Integer> combKRankedTableList(ListNode<Integer>[] arr) {
        if (arr.length == 0) return null;
        if (arr.length == 1) return arr[0];
        if (arr.length == 2) return combTwoRankedTableList(arr[0], arr[1]);
        int mid = arr.length / 2;
        ListNode<Integer>[] sub1 = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            sub1[i] = arr[i];
        }
        ListNode<Integer>[] sub2 = new ListNode[arr.length - mid];
        for (int i = mid; i < arr.length; i++) {
            sub2[i - mid] = arr[i];
        }
        return combTwoRankedTableList(combKRankedTableList(sub1), combKRankedTableList(sub2));
    }

    // when k = 2, we have a special case
    public ListNode<Integer> combTwoRankedTableList(ListNode<Integer> node1, ListNode<Integer> node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode<Integer> head = node1.value < node2.value ? node1 : node2;
        ListNode<Integer> pre = new ListNode<Integer>(null, null);
        while (node1 != null || node2 != null) {
            if (node1 != null && node2 != null) {
                if (node1.value < node2.value) {
                    pre.next = node1;
                    pre = node1;
                    node1 = node1.next;
                } else {
                    pre.next = node2;
                    pre = node2;
                    node2 = node2.next;
                }
            } else if (node1 != null) {
                pre.next = node1;
                pre = node1;
                node1 = node1.next;
            } else {
                pre.next = node2;
                pre = node2;
                node2 = node2.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<Integer>(1, new ListNode<Integer>(3, new ListNode<Integer>(5, null)));
        ListNode<Integer> node2 = new ListNode<Integer>(0, new ListNode<Integer>(2, null));
        TableListCombination tlc = new TableListCombination();
        ListNode<Integer>[] tnl = new ListNode[2];
        tnl[0] = node1;
        tnl[1] = node2;
        ListNode<Integer> res = tlc.combKRankedTableList(tnl);
        for (; res != null; res = res.next) {
            System.out.println(res.value);
        }
    }
}
