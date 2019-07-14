package solution;

import data.structure.ListNode;
import java.util.Stack;

public class TwoNumAdd {

    // 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
    // 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
    // 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
    //
    // 进阶:
    // 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
    //
    // 示例:
    // 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    // 输出: 7 -> 8 -> 0 -> 7
    public ListNode<Integer> addTwoNum(ListNode<Integer> node1, ListNode<Integer> node2) {
        // store number via stack
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        while (node1 != null) {
            stack1.push(node1.value);
            node1 = node1.next;
        }
        while (node2 != null) {
            stack2.push(node2.value);
            node2 = node2.next;
        }

        // calculation
        ListNode<Integer> head = null;
        int flag = 0;
        while (!stack1.empty() || !stack2.empty() || flag != 0) {
            if (!stack1.empty()) flag += stack1.pop();
            if (!stack2.empty()) flag += stack2.pop();
            ListNode<Integer> node = new ListNode<Integer>(flag%10, head);
            head = node;
            flag = flag / 10;
        }
        return head;
    }

}
