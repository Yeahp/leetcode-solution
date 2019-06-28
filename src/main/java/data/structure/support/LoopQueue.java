package data.structure.support;

/*
 * 入队：rear=(rear+1)%maxSize，data[rear]=e
 * 出队：front = (front+1)%maxSize; E value =(E)data[front]; data[front] = null;
 * 得到对首元素：(E)data[front+1];
 * 判断对空：rear==front
 * 对长：rear-front
 */
import java.util.Arrays;

public class LoopQueue<E> {
    public Object[] data = null;
    private int maxSize; // 队列容量
    private int rear;// 队列尾，允许插入
    private int front;// 队列头，允许删除
    private int size=0; //队列当前长度

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    // 判空
    public boolean empty() {
        return size == 0;
    }

    // 插入
    public boolean add(E e) {
        if (size == maxSize) {
            throw new RuntimeException("队列已满，无法插入新的元素！");
        } else {
            data[rear] = e;
            rear = (rear + 1)%maxSize;
            size ++;
            return true;
        }
    }

    // 返回队首元素，但不删除
    public E peek() {
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
            return (E) data[front];
        }
    }

    // 出队
    public E poll() {
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
            E value = (E) data[front]; // 保留队列的front端的元素的值
            data[front] = null; // 释放队列的front端的元素
            front = (front+1)%maxSize;  //队首指针加1
            size--;
            return value;
        }
    }

    // 队列长度
    public int length() {
        return size;
    }

    //清空循环队列
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
            System.out.print(queue.poll()+" ");
        }
    }
}
