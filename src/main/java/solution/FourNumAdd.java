package solution;

import java.util.HashMap;
import java.util.Map;

public class FourNumAdd {

    // 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
    //例如:
    //输入:
    //A = [ 1, 2]
    //B = [-2,-1]
    //C = [-1, 2]
    //D = [ 0, 2]
    //输出:
    //2
    public int addFourNum(int[] arr1, int[] arr2, int[] arr3, int[] arr4) {
        Map<Integer, Integer> map1 = getNumPair(arr1, arr2);
        Map<Integer, Integer> map2 = getNumPair(arr3, arr4);
        int res = 0;
        for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey())) res += entry1.getKey() * entry2.getKey();
            }
        }
        return res;
    }

    private Map<Integer, Integer> getNumPair(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int tmp = a[i] + b[j];
                if (map.containsKey(tmp)) {
                    int cnt = map.get(tmp);
                    map.remove(tmp);
                    map.put(tmp, cnt + 1);
                } else {
                    map.put(tmp, 1);
                }
            }
        }
        return map;
    }
}
