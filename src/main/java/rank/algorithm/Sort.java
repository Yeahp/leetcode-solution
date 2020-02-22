package rank.algorithm;


import java.util.*;

public class Sort {

    // 1.冒泡排序
    public int[] bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {  //控制循环，两两比较只需要比较 len-1 次
            for (int j = 0; j < arr.length - i; j++) {  //控制数值比较
                if (arr[j + 1] < arr[j]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }


    // 2.选择排序
    public int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  //控制循环，可以把前 len-1 个数作为每次比较的基准
            for (int j = i + 1; j < arr.length; j++) {  //控制数值比较
                if (arr[j] < arr[i]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }


    // 3.插入排序
    public int[] insertSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  // 控制循环，假设前 i 个元素已经排好序
            for (int j = i + 1; j > 0; j--) {  // 控制数值比较，把第 i+1 个元素插入，最坏比较 i 次
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
        return arr;
    }


    // 3.归并排序
    public int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr;
        int mid = (int) Math.floor(arr.length / 2.0);
        return mergeTwoRankedArray(mergeSort(Arrays.copyOfRange(arr, 0, mid)), mergeSort(Arrays.copyOfRange(arr, mid, arr.length)));
    }
    private int[] mergeTwoRankedArray(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int i = 0, j = 0, k = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j]) res[k++] = a1[i++];
            else res[k++] = a2[j++];
        }
        if (i == a1.length) {
            while (j < a2.length) res[k++] = a2[j++];
        } else {
            while (i < a1.length) res[k++] = a1[i++];
        }
        return res;
    }


    // 5.快速排序
    public int[] quickSort(int[] arr) {
        int high = arr.length - 1;
        return quickSortHelper(arr, 0, high);
    }
    private int[] quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int idx = getIndex(arr, low, high);
            quickSortHelper(arr, low, idx - 1);
            quickSortHelper(arr, idx + 1, high);
        }
        return arr;
    }
    private int getIndex(int[] arr, int low, int high) {   // 快排一次
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) high--;
            if (low < high) arr[low] = arr[high];
            while (low < high && arr[low] < pivot) low++;
            if (low < high) arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }


    // 6.堆排序
    public int[] heapSort(int[] arr) {
        int len = arr.length;
        buildHeap(arr, len);
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }
    private void buildHeap(int[] arr, int len) {
        for (int i = len / 2; i >= 0; i--) {
            heapify(arr, i, len);
        }
    }
    private void heapify(int[] arr, int idx, int len) {
        int largest = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        if (left < len && arr[left] > arr[largest]) largest = left;
        if (right < len && arr[right] > arr[largest]) largest = right;
        if (largest != idx) {
            swap(arr, idx, largest);
            heapify(arr, largest, len);
        }
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 7.希尔排序：
    // 希尔排序是 Donald Shell 于1959年提出的一种排序算法
    // 希尔排序也是一种插入排序，是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
    // 也是冲破 O(n^2）的第一批算法之一
    public int[] shellSort(int[] arr) {
        int groups = arr.length / 2;
        while (groups > 0) {
            for (int group = 0; group < groups; group++) {
                int delta = arr.length%groups >= (group + 1) ? 1 : 0;
                int num = arr.length / groups + delta;  // 第 group 组的元素个数
                for (int i = 0; i < num - 1; i++) {  // 次数进行插入排序
                    for (int j = i + 1; j > 0; j--) {
                        if (arr[group + j * groups] < arr[group + (j - 1) * groups]) {
                            int tmp = arr[group + j * groups];
                            arr[group + j * groups] = arr[group + (j - 1) * groups];
                            arr[group + (j - 1) * groups] = tmp;
                        } else {
                            break;
                        }
                    }
                }
            }
            groups = groups / 2;
        }
        return arr;
    }


    // 8.计数排序 ~ o(3n)
    public int[] countSort(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int i = 0; i < arr.length; i++) {  // 扫描一次，得到最大值和最小值  o(n)
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        int[] countArr = new int[max - min + 1];  // 新建数组存储元素频次
        for (int i = 0; i < arr.length; i++) {  // o(n)
            countArr[arr[i] - min] = countArr[arr[i] - min] + 1;
        }
        int k = 0;
        int[] res = new int[arr.length];  // ~o(n)
        for (int i = 0; i < max - min + 1; i++) {
            if (countArr[i] > 0) {
                for (int j = 0; j < countArr[i]; j++) {
                    res[k++] = i + min;
                }
            }
        }
        return res;
    }


    // 9.桶排序
    public int[] bucketSort(int[] arr, int buckets) {  // buckets: 桶的数量
        int min = arr[0], max = arr[1];
        for (int i = 0; i < arr.length; i++) {  // 第一趟扫描，找到最小值和最大值
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        int numOfBucket = (max - min + 1) / buckets + ((max - min + 1)%buckets == 0 ? 0 : 1);  // 每个桶里的元素数量
        int[] bucketNum = new int[buckets];  // 用于指示桶里是否有数据
        int[][] placeHolderArr = new int[buckets][numOfBucket];  // 存放数据
        for (int i = 0; i < arr.length; i++) {
            int bucket = (arr[i] - min) / numOfBucket;
            placeHolderArr[bucket][bucketNum[bucket]] = arr[i];
            bucketNum[bucket] = bucketNum[bucket] + 1;
        }
        int k = 0;
        int[] res = new int[arr.length];
        for (int i = 0; i < buckets; i++) {
            int bn = bucketNum[i];
            if (bn > 0) {
                for (int j = 0; j < bn - 1; j++) {  // 次数进行插入排序
                    for (int _j = j + 1; _j > 0; _j--) {
                        if (placeHolderArr[i][_j] < placeHolderArr[i][_j - 1]) {
                            int tmp = placeHolderArr[i][_j];
                            placeHolderArr[i][_j] = placeHolderArr[i][_j - 1];
                            placeHolderArr[i][_j - 1] = tmp;
                        } else {
                            break;
                        }
                    }
                }
                for (int _bn = 0; _bn < bn; _bn++) res[k++] = placeHolderArr[i][_bn];
            }
        }
        return res;
    }


    // 10.基数排序
    public int[] radixSort(int[] arr) {
        int max = arr[0];
        List<Queue<Integer>> digit = new ArrayList<>();
        for (int i = 0; i < 10; i++) digit.add(new LinkedList<>());  // 0~9 对应 10 个组
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
            digit.get(arr[i]%10).add(arr[i]);  // 根据个位数将数字放进对应组
        }
        int k = 0;
        for (int j = 0; j < 10; j++) {
            if (digit.get(j).size() > 0) {
                while (!digit.get(j).isEmpty()) arr[k++] = digit.get(j).poll();
            }
        }  // 此时，一次循环找出最大值，并完成一轮排序
        int digitNum = 1;
        while ((max = max/10) != 0) digitNum++;
        for (int pow = 2; pow <= digitNum; pow++) {
            for (int i = 0; i < arr.length; i++) {
                digit.get(getDigit(arr[i], pow)).add(arr[i]);  // 根据个位数将数字放进对应组
            }
            k = 0;
            for (int j = 0; j < 10; j++) {
                if (digit.get(j).size() > 0) {
                    while (!digit.get(j).isEmpty()) arr[k++] = digit.get(j).poll();
                }
            }
        }
        return arr;
    }
    private int getDigit(int ele, int pow) {
        for (int _pow = pow; _pow > 1; _pow--) ele = ele / 10;
        return ele%10;
    }


    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr = new int[]{31,12,10,5,6,4,100};
        System.out.println(Arrays.toString(sort.radixSort(arr)));
    }
}
