package rank.algorithm;

import java.util.Arrays;

public class BubbleSort<T extends Comparable<T>> {

    public T[] bubbleSortImp(T[] arr, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j].compareTo(arr[j + 1]) < 0) {
                        T tmp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j].compareTo(arr[j + 1]) > 0) {
                        T tmp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        BubbleSort<Integer> br = new BubbleSort<Integer>();
        Integer[] arr = new Integer[]{1,4,2,3,5,7};
        System.out.println(Arrays.toString(br.bubbleSortImp(arr, false)));
    }
}
