package solution;

public class StoneGame {

    // 喜羊羊和灰太狼用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i]
    // 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局
    // 喜羊羊和灰太狼轮流进行，喜羊羊先开始
    // 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜
    // 假设喜羊羊和灰太狼都发挥出最佳水平，当喜羊羊赢得比赛时返回 true ，当灰太狼赢得比赛时返回 false
    // 例子：
    // 输入：[ 5，3，4，5 ]
    // 输出：true
    public boolean judgeWiner(int[] arr) {
        int wolf = 0, sheep = 0, turn = 0, head = 0, tail = arr.length - 1;
        while (head != tail) {
            int who = turn%2;
            if (arr[head] >= arr[tail]) {
                if (who == 1) {
                    sheep += arr[head];
                    head++;
                } else {
                    wolf += arr[head];
                    head++;
                }
            } else {
                if (who == 1) {
                    sheep += arr[tail];
                    tail--;
                } else {
                    wolf += arr[tail];
                    tail--;
                }
            }
            turn++;
        }
        return sheep > wolf;
    }
}
