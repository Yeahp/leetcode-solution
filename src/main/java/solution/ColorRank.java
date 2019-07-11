package solution;

import java.util.Arrays;

public class ColorRank {

    // 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序
    // 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列
    // 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
    // 注意: 不能使用代码库中的排序函数来解决这道题。
    // 示例:
    // 输入: [2,0,2,1,1,0]
    // 输出: [0,0,1,1,2,2]
    public int[] rankColor(int[] arr) {
        int zero = 0, one = 0, two = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) zero++;
            else if (arr[i] == 1) one++;
            else two++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (i < zero) arr[i] = 0;
            else if (i >= zero && i < zero + one) arr[i] = 1;
            else arr[i] = 2;
        }
        return arr;
    }

    // fast rank with three partitions
    public int[] rankColorFaster(int[] arr) {
        int idx = 0, zero = 0, two = arr.length - 1;
        while (idx != two) {
            if (arr[idx] == 1) {
                idx++;
            } else if (arr[idx] == 0) {
                arr[idx] = arr[zero];
                arr[zero] = 0;
                zero++;
                idx++;
            } else if (arr[idx] == 2) {
                arr[idx] = arr[two];
                arr[two] = 2;
                two--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        ColorRank cr = new ColorRank();
        int[] arr = new int[]{1,0,2,1,0,2,2,1,0,0,1};
        System.out.println(Arrays.toString(cr.rankColorFaster(arr)));
    }
}
