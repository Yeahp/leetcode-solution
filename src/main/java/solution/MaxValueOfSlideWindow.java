package solution;

import java.util.*;
//import java.util.Deque;


public class MaxValueOfSlideWindow {

    // 给定一个数组 arr，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧
    // 你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位
    // 返回滑动窗口最大值。
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

}
