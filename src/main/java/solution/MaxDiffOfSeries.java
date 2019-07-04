package solution;

import java.util.List;

public class MaxDiffOfSeries {

    // search for the maximum value of the i-th element and j-th element of a list where j > i
    private Float solution_2_1(List<Float> flist) {
        int len = flist.size();
        if (len < 2) return null;
        float max = flist.get(1) - flist.get(0);
        float tmp = 0.0f;
        for (int i = 1; i < len; i++) {
            tmp += flist.get(i) - flist.get(i-1);
            if (tmp < 0) tmp = 0.0f;
            if (max < tmp) max = tmp;
        }
        return max;
    }

}
