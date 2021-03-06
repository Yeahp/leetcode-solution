package solution;

import java.util.Arrays;

public class AddOne {

    // 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
    // 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
    // 你可以假设除了整数 0 之外，这个整数不会以零开头。
    // 示例:
    // 输入: [1,2,3]
    // 输出: [1,2,4]
    // 解释: 输入数组表示数字 123。
    public int[] getAddoneNum(int[] arr) {
        int idx = arr.length -1;
        while (idx >= 0) {
            if (arr[idx] != 9) {
                arr[idx] += 1;
                return arr;
            } else {
                arr[idx] = 0;
                idx--;
            }
        }
        int[] res = new int[arr.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,9,9};
        AddOne ao = new AddOne();
        System.out.println(Arrays.toString(ao.getAddoneNum(arr)));
    }
}
