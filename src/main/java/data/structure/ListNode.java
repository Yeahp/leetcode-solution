package data.structure;

public class ListNode<E> {

    public E value;
    public ListNode<E> next;

    public ListNode(E value) {
        this.value = value;
    }

    public ListNode(E value, ListNode<E> next) {
        this.value = value;
        this.next = next;
    }

}
