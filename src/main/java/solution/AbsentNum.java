package solution;

public class AbsentNum {

    // 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
    // 说明 : 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
    public int findAbsentNum(int[] arr) {
        int n = arr.length;
        int sum = (n + 1) * n / 2;
        for (int i = 0; i < n; i++) {
            sum -= arr[i];
        }
        return sum;
    }

    public int findAbsentNum_1(int[] arr) {
        int flag = 0;
        for (int i = 0; i < arr.length; i++) {
            flag = flag ^ i ^ arr[i];
        }
        return flag ^ arr.length;
    }

    // 进阶一： 如果包含 0, 1, 2, ..., n 中 n+1 个数的序列，找出 0 .. n 中出现两次的那个数。
    public int findNumOccurTwice(int[] arr) {
        int idx = 0;
        int res = -1;
        while (idx < arr.length) {
            if (arr[idx] != idx) {
                int tmp = arr[idx];
                if (arr[tmp] == tmp) {
                    res = tmp;
                    break;
                } else {
                    arr[idx] = arr[tmp];
                    arr[tmp] = tmp;
                }
            } else {
                idx++;
            }
        }
        return res;
    }

    // 进阶二： 如果包含 0, 1, 2, ..., n 中 n+1 个数的序列，找出 0 .. n 中没有出现的那个数。
    public int findReplacedNum(int[] arr) {
        int n = arr.length;
        int dup = findNumOccurTwice(arr);
        int sum = (n - 1) * n / 2;
        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
        }
        return dup + sum;
    }

    public static void main(String[] args) {
        AbsentNum an = new AbsentNum();
        int[] arr = new int[]{0,1,1,3};
        System.out.println(an.findReplacedNum(arr));
    }
}
