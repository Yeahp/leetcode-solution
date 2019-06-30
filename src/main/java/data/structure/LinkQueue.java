package data.structure;

public class LinkQueue<E> {

    // node of linked queue
    private class Node<E> implements Cloneable {
        E e;
        Node<E> next;

        public Node() {}

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    private Node<E> front; // front of queue, allowing deleting
    private Node<E> rear; // rear of queue, allowing inserting
    private int size; // current size

    public LinkQueue() {
        front = null;
        rear = null;
    }

    // judge whether the queue is empty
    public boolean empty() {
        return size == 0;
    }

    // get into queue
    public boolean add(E e) {
        Node<E> node = new Node<E>(e, null);
        if (size == 0) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
        return true;
    }

    // display the front element without deletion
    public Node<E> peek() {
        if (size == 0) {
            throw new RuntimeException("The queue is empty!");
        } else {
            return front;
        }
    }

    // get out of queue
    public Node<E> pull() {
        if (size == 0) {
            throw new RuntimeException("The queue is empty!");
        } else {
            Node<E> node = front;
            front = front.next;
            node.next = null;
            size--;
            return node;
        }
    }

    // current size
    public int length() {
        return size;
    }

    public static void main(String[] args) {
        LinkQueue<Integer> queue=new LinkQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < queue.length(); i++) {
            System.out.print(queue.peek().e+" ");
        }
        System.out.println();
        int size=queue.length();
        for (int i = 0; i < size; i++) {
            System.out.print(queue.pull().e+" ");
        }
    }
}
