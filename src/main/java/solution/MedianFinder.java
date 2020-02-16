package solution;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> minheap, maxheap;  // 优先队列的作用是能保证每次取出的元素都是队列中权值最小的

    public MedianFinder() {
        //维护较大的元素的最小堆
        maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
        //维护较小元素的最大堆
        minheap = new PriorityQueue<Integer>();
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxheap.add(num);
        minheap.add(maxheap.poll());
        if (maxheap.size() < minheap.size()) {
            maxheap.add(minheap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxheap.size() == minheap.size()) {
            return (maxheap.peek() + minheap.peek()) * 0.5;
        } else {
            return maxheap.peek();
        }
    }

}
