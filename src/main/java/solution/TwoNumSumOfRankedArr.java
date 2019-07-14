package solution;

import java.util.Arrays;

public class TwoNumSumOfRankedArr {

    // 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数
    public int[] findTwoNumSum(int[] arr, int target) {
        if (arr.length < 2) return null;
        int[] res = new int[2];
        int i = 0, j = arr.length - 1, flag = 0;
        while (i < j) {
            int tmp = arr[i] + arr[j];
            if (tmp == target) {
                res[0] = arr[i];
                res[1] = arr[j];
                flag = 1;
                break;
            } else if (tmp < target) {
                i++;
            } else {
                j--;
            }
        }

        return flag == 1 ? res : null;
    }

    public static void main(String[] args) {
        TwoNumSumOfRankedArr tnsora = new TwoNumSumOfRankedArr();
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(Arrays.toString(tnsora.findTwoNumSum(arr, 9)));
    }
}
