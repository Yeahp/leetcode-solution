package data.structure;

public class LinkStack<E> {

    // node of link stack
    private class Node<E> {
        E e;
        Node<E> next;

        public Node() {}
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node<E> top;   // the top element
    private int size;  // the current size

    public LinkStack(){
        top = null;
    }

    // get into stack
    public boolean push(E e){
        top = new Node(e, top);
        size++;
        return true;
    }

    // display the top element without deletion
    public Node<E> peek() {
        if (empty()) {
            throw new RuntimeException("The stack is empty!");
        } else {
            return top;
        }
    }

    // get out of stack
    public Node<E> pop() {
        if (empty()) {
            throw new RuntimeException("The stack is empty!");
        } else {
            Node<E> value = top;
            top = top.next;
            value.next = null;
            size--;
            return value;
        }
    }

    // the current size
    public int length(){
        return size;
    }

    // judge whether the stack is empty
    public boolean empty(){
        return size == 0;
    }

    public static void main(String[] args) {
        LinkStack<Integer> stack = new LinkStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop().e+" ");
        }
    }

}
