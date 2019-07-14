package solution;

public class TrailingZeros {

    // 给定一个整数 n，返回 n! 结果尾数中零的数量
    // 示例:
    // 输入: 3
    // 输出: 0
    // 解释: 3! = 6, 尾数中没有零
    public int findTrailingZeros(int n) {
        if (n == 0) return 0;
        else return n / 5 + findTrailingZeros(n / 5);
    }

    public static void main(String[] args) {
        TrailingZeros tz = new TrailingZeros();
        System.out.println(tz.findTrailingZeros(10));
    }
}
