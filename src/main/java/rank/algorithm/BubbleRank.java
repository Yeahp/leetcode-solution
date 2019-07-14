package rank.algorithm;

import java.util.Arrays;

public class BubbleRank<E extends Comparable<E>> {

    public E[] bubbleRankImp(E[] arr, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        E tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                    }
                }
            }
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].compareTo(arr[j]) > 0) {
                        E tmp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = tmp;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        BubbleRank<Integer> br = new BubbleRank<Integer>();
        Integer[] arr = new Integer[]{1,4,2,3,5,7};
        System.out.println(Arrays.toString(br.bubbleRankImp(arr, true)));
    }
}
