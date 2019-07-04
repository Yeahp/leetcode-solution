package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxValueOfSlideWindow {

    // 给定一个数组 arr，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧
    // 你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位
    // 返回滑动窗口最大值。
    public List<Integer> maxValueofWindow(Integer[] arr, int k) {
        assert k > 0;
        List<Integer> res = new ArrayList<Integer>();
        if (k == 1) return new ArrayList<Integer>(Arrays.asList(arr));
        return res;
    }
}
