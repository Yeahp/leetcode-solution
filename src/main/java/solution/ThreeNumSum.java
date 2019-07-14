package solution;

import java.util.Arrays;

public class ThreeNumSum {

    // 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
    // 使得 a + b + c = 0 找出所有满足条件且不重复的三元组
    public int findTripleCnt(int[] arr) {
        if (arr.length < 3) return 0;
        Arrays.sort(arr);
        if ((arr[0] < 0 && arr[arr.length - 1] < 0) || (arr[0] > 0 && arr[arr.length - 1] > 0)) return 0;
        int res = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int j = i + 1;
            int k = arr.length - 1;
            while (i < k) {
                int tmp = arr[i] + arr[j] + arr[k];
                if (tmp == 0) {
                    res++;
                    i++;
                    j--;
                } else if (tmp < 0) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1,4,5};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
