package rank.algorithm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeapSort {

    public int[] heapSort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr, len);
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2.0); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int parent, int len) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        if (left < len && arr[left] > arr[largest]) largest = left;
        if (right < len && arr[right] > arr[largest]) largest = right;
        if (parent != largest) {
            swap(arr, parent, largest);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    // 查找给定数组频率最大的k个元素
    // 核心思想：利用堆排序对出现次数排序
    public int[] topKMostFrequent(int[] arr, int k) {
        // 第一趟：找出值与出现次数映射关系
        Map<Integer, Integer> kv = new HashMap<>();
        for (int i : arr) kv.merge(i, 1, (oldvalue, newvalue) -> oldvalue + newvalue);
        int len = kv.size();
        int[] _arr = new int[len];
        int idx = 0;
        for (int key : kv.keySet()) {
            _arr[idx++] = key;
        }
        buildMaxHeapWRTFrequence(kv, _arr, len);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = _arr[0];
            swap(_arr, 0, len - i - 1);
            len--;
            heapifyWRTFrequence(kv, _arr, 0, len);
        }
        return res;
    }

    private void buildMaxHeapWRTFrequence(Map<Integer, Integer> map, int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2.0); i >= 0; i--) {
            heapifyWRTFrequence(map, arr, i, len);
        }
    }

    private void heapifyWRTFrequence(Map<Integer, Integer> map, int[] arr, int parent, int len) {
        int largest = parent;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        if (left < len && map.get(arr[left]) > map.get(arr[largest])) largest = left;
        if (right < len && map.get(arr[right]) > map.get(arr[largest])) largest = right;
        if (parent != largest) {
            swap(arr, parent, largest);
            heapifyWRTFrequence(map, arr, largest, len);
        }
    }


    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        for (int i : hs.topKMostFrequent(new int[]{1,1,1,2,2,3,3,3}, 2)) {
            System.out.println(i);
        }
    }

}
