package solution;

import javafx.util.Pair;

import java.util.LinkedList;

public class SquareDigit {

    // 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少
    // 示例:
    // 输入: n = 12
    // 输出: 3
    // 解释: 12 = 4 + 4 + 4
    public int minSquareDigitNum(int n) {
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;
        int a = 0;
        while ((a * a) <= n) {
            int b = (int) Math.pow((n - a * a), 0.5);
            if (a * a + b * b == n) {
                if (a != 0 && b != 0) return 2;
                else return 1;
            }
            a++;
        }
        return 3;
    }

    public int numSquares(int n) {
        if(n == 0)
            return 0;

        LinkedList<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
        queue.addLast(new Pair<Integer, Integer>(n, 0));

        boolean[] visited = new boolean[n+1];
        visited[n] = true;

        while(!queue.isEmpty()){
            Pair<Integer, Integer> front = queue.removeFirst();
            int num = front.getKey();
            int step = front.getValue();

            if(num == 0)
                return step;

            for(int i = 1 ; num - i*i >= 0 ; i ++){
                int a = num - i*i;
                if(!visited[a]){
                    if(a == 0) return step + 1;
                    queue.addLast(new Pair(num - i * i, step + 1));
                    visited[num - i * i] = true;
                }
            }
        }
        return 0;
    }

}
