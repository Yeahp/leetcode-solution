package rank.algorithm;

import java.util.Arrays;

public class InsertSort<T extends Comparable<T>> {

    public T[] insertSortImp(T[] arr, boolean reverse) {
        if (reverse) {
            for (int i = 0; i < arr.length - 1; i++) {
                int idx = 0;
                while (idx <= i) {
                    if (arr[i + 1].compareTo(arr[idx]) > 0) break;
                    else idx++;
                }
                for (int j = i + 1; j > idx; j--) {
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                int idx = 0;
                while (idx <= i) {
                    if (arr[i + 1].compareTo(arr[idx]) < 0) break;
                    else idx++;
                }
                for (int j = i + 1; j > idx; j--) {
                    T tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
        return arr;
    }

    public T[] _insertSortImp(T[] arr, boolean reverse) {
        if (reverse) {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (arr[j].compareTo(arr[j - 1]) > 0) {
                        T tmp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = tmp;
                    } else {
                        break;
                    }
                }
            }
        } else {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (arr[j].compareTo(arr[j - 1]) < 0) {
                        T tmp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = tmp;
                    } else {
                        break;
                    }
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        InsertSort<Integer> br = new InsertSort<Integer>();
        Integer[] arr = new Integer[]{1,4,2,3,5,7};
        System.out.println(Arrays.toString(br._insertSortImp(arr, false)));
    }
}
