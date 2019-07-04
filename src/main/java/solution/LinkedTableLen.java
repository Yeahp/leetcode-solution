package solution;

import data.structure.ListNode;

public class LinkedTableLen {

    // the length of a linked list
    public int linkedListLength(ListNode<Integer> head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
