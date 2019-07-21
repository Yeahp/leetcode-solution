package rank.algorithm;

import java.util.Arrays;

public class MergeSort {

    public int[] mergeSortImp(int[] arr, boolean reverse) {
        if (arr.length == 1) return arr;
        int mid = (int) Math.floor(arr.length / 2);
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return mergeTwoArr(mergeSortImp(left, reverse), mergeSortImp(right, reverse), reverse);
    }

    private int[] mergeTwoArr(int[] arr1, int[] arr2, boolean reverse) {
        int[] merged = new int[arr1.length + arr2.length];
        int idx = 0, idx1 = 0, idx2 = 0;
        if (reverse) {
            while (idx1 < arr1.length && idx2 < arr2.length) {
                if (arr1[idx1] > arr2[idx2]) merged[idx++] = arr1[idx1++];
                else merged[idx++] = arr2[idx2++];
            }
            if (idx1 == arr1.length) {
                while (idx2 < arr2.length) merged[idx++] = arr2[idx2++];
            } else {
                while (idx1 < arr1.length) merged[idx++] = arr1[idx1++];
            }
        } else {
            while (idx1 < arr1.length && idx2 < arr2.length) {
                if (arr1[idx1] < arr2[idx2]) merged[idx++] = arr1[idx1++];
                else merged[idx++] = arr2[idx2++];
            }
            if (idx1 == arr1.length) {
                while (idx2 < arr2.length) merged[idx++] = arr2[idx2++];
            } else {
                while (idx1 < arr1.length) merged[idx++] = arr1[idx1++];
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = new int[]{4,1,5,3,7};
        System.out.println(Arrays.toString(ms.mergeSortImp(arr, true)));
    }

}
