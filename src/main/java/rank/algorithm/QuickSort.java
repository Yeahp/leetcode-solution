package rank.algorithm;

import java.util.Arrays;

public class QuickSort {

    private int[] quickSortImp(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSortImp(arr, 0, index - 1);
            quickSortImp(arr, index + 1, high);
        }
        return arr;
    }

    private int getIndex(int[] arr, int low, int high) {
        int tmp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= tmp) high--;
            arr[low] = arr[high];
            while (low < high && arr[low] <= tmp) low++;
            arr[high] = arr[low];
        }
        arr[low] = tmp;
        return low;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] arr = {1,4,2,3,5,7};
        System.out.println(Arrays.toString(qs.quickSortImp(arr, 0, arr.length - 1)));
    }

}
