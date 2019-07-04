package solution;

import java.util.ArrayList;
import java.util.List;

public class MaxDiffSeat {

    // 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
    // 少有一个空座位，且至少有一人坐在座位上。
    // 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
    // 返回他到离他最近的人的最大距离。
    // 示例 1：
    // 输入：[1,0,0,0,1,0,1]
    // 输出：2
    public int getMaxDiffSeat_1(int[] seat) {
        int tt = seat.length;
        List<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < tt; i++) {
            if (seat[i] == 1) order.add(i);
        }
        int minus = order.get(0) > tt -order.get(0) - 1? order.get(0) : tt -order.get(0) - 1;
        for (int j = 1; j < order.size(); j++) {
            int tmp = order.get(j) - order.get(j - 1) - 2;
            if (tmp > minus) minus = tmp;
        }
        return minus;
    }

    public int getMaxDiffSeat_2(int[] seat) {
        int max = 0;
        int minus = 0;
        int cnt = 0;
        for (int i = 0; i < seat.length; i++) {
            cnt++;
            int _minus = i - max;
            if (_minus > minus) minus = _minus;
            if (seat[i] == 1) {
                max = i;
            }
        }
        if (cnt == 1 || seat[0] == 0) return minus;
        return minus - 2;
    }

}
