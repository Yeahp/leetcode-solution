package solution;

import data.structure.ListNode;

public class CircleLikedTable {

    // judge whether a liked table is a circle
    public boolean solution_3(ListNode node) {
        if (node == null) return false;
        ListNode node1 = node;
        ListNode node2 = node;
        while((node1 = node1.next.next) != null && (node2 = node2.next) != null) {
            if (node1.value == node2.value && node1.equals(node2)) return true;
        }
        return false;
    }

}
