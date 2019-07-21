package rank.algorithm;

import java.util.Arrays;

public class SelectionSort<T extends Comparable<T>> {

    public T[] selectionSortImp(T[] arr, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        T tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].compareTo(arr[j]) > 0) {
                        T tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        SelectionSort<Integer> br = new SelectionSort<Integer>();
        Integer[] arr = new Integer[]{1,4,2,3,5,7};
        System.out.println(Arrays.toString(br.selectionSortImp(arr, false)));
    }
}
