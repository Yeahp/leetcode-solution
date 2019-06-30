package data.structure;

/*
* ordered queue implemented based on array
* */
public class Queue<E> {
    private Object[] data = null;
    private int maxSize; // size of queue
    private int front;  // head of queue，allowing deleting
    private int rear;   // tail of queue, allowing inserting

    // constructor
    public Queue(){
        this(10);
    }

    public Queue(int initialSize){
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("Initial size mustn't less than 0：" + initialSize);
        }
    }

    // judge whether the queue is empty
    public boolean empty(){
        return rear == front ? true : false;
    }

    // insert
    public boolean add(E e) {
        if (rear == maxSize - 1) {
            throw new RuntimeException("The queue is full, and no element can be inserted!");
        } else {
            data[rear++] = e;
            return true;
        }
    }

    // display the front element without deletion
    public E peek() {
        if (empty()) {
            throw new RuntimeException("The queue is empty!");
        } else {
            return (E)data[front];
        }
    }

    // get out of queue
    public E pull() {
        if (empty()) {
            throw new RuntimeException("The queue is empty!");
        } else {
            E value = (E)data[front];
            data[front++] = null;
            return value;
        }
    }

    //队列长度
    public int length(){
        return rear - front;
    }

    public static void main(String[] args) {
        Queue<Integer> queue=new Queue<Integer>();
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