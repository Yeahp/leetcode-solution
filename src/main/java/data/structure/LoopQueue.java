package data.structure;

import java.util.Arrays;

/*
 * implemented based on array
 */
public class LoopQueue<E> {

    public Object[] data = null;
    private int maxSize; // the maximum size
    private int rear; // tail of queue, allowing inserting
    private int front; // head of queue, allowing deleting
    private int size = 0; // the current size

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("Initial size mustn't less than 0: " + initialSize);
        }
    }

    // judge whether the queue is empty
    public boolean empty() {
        return size == 0;
    }

    // insert
    public boolean add(E e) {
        if (size == maxSize) {
            throw new RuntimeException("The queue is full, no element can be inserted!");
        } else {
            data[rear] = e;
            rear = (rear + 1)%maxSize;
            size++;
            return true;
        }
    }

    // display the head element without deletion
    public E peek() {
        if (empty()) {
            throw new RuntimeException("The queue is empty");
        } else {
            return (E)data[front];
        }
    }

    // 出队
    public E pull() {
        if (empty()) {
            throw new RuntimeException("The queue is empty!");
        } else {
            E value = (E)data[front];
            data[front] = null;
            front = (front + 1)%maxSize;
            size--;
            return value;
        }
    }

    // the current size
    public int length() {
        return size;
    }

    // clear queue
    public void clear(){
        Arrays.fill(data, null);
        size = 0;
        front = 0;
        rear = 0;
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue=new LoopQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < queue.length(); i++) {
            System.out.print(queue.peek()+" ");
        }
        System.out.println();
        int size=queue.length();
        for (int i = 0; i < size; i++) {
            System.out.print(queue.pull()+" ");
        }
    }
}
