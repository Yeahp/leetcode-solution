package demo;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {

    // LinkedList: 大小可变的链表双端队列，允许元素为插入null
    // ArrayDeque 大下可变的数组双端队列，不允许插入null
    // ConcurrentLinkedDeque 大小可变且线程安全的链表双端队列，非阻塞，不允许插入null
    // LinkedBlockingDeque 为线程安全的双端队列，在队列为空的情况下，获取操作将会阻塞，直到有元素添加

    private int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        // double ended queue
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            // add element at tail of queue
            // make sure that element at head of queue must be larger than element at tail of queue
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // remove element which is out of window
            if (deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            // add the first element
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1};
        DequeDemo dequeDemo = new DequeDemo();
        int[] res = dequeDemo.maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(res));

    }

}
