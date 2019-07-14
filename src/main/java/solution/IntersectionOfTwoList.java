package solution;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoList {

    // 给定两个数组，编写一个函数来计算它们的交集
    // 示例 1:
    // 输入: nums1 = [1,2,2,1], nums2 = [2,2]
    // 输出: [2,2]
    public List<Integer> findIntersection(int[] arr1, int[] arr2) {
        Map<Integer, Integer> valueCnt = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr1.length; i++) {
            if (valueCnt.containsKey(arr1[i])) {
                int tmp = valueCnt.get(arr1[i]);
                valueCnt.remove(arr1[i]);
                valueCnt.put(arr1[i], tmp + 1);
            } else {
                valueCnt.put(arr1[i], 1);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < arr2.length; i++) {
            if (valueCnt.containsKey(arr2[1])) {
                res.add(arr2[i]);
                int tmp = valueCnt.get(arr2[i]);
                valueCnt.remove(arr2[i]);
                if (tmp > 1) {
                    valueCnt.put(arr2[i], tmp - 1);
                }
            }
        }
        return res;
    }

    // 进阶:
    // (1) 如果给定的数组已经排好序呢？你将如何优化你的算法 ？
    public List<Integer> findIntersectionOfTwoRankedArr(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<Integer>();
        int idx1 = 0, idx2 = 0;
        while (idx1 < arr1.length && idx2 < arr2.length) {
            if (arr1[idx1] == arr2[idx2]) {
                res.add(arr1[idx1]);
                idx1++;
                idx2++;
            } else if (arr1[idx1] > arr2[idx2]) {
                idx2++;
            } else {
                idx1++;
            }
        }
        return res;
    }

    // 如果 nums1 的大小比 nums2 小很多，哪种方法更优 ？
    // the formal solution: o(n) + o(m)
    // the latter solution: min{o(n), o(m)}
    // so the latter is better.

    // 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
    public List<Integer> findIntersectionOfTwoArr(int[] arr, String filePath) {
        Map<Integer, Integer> valueCnt = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (valueCnt.containsKey(arr[i])) {
                int tmp = valueCnt.get(arr[i]);
                valueCnt.remove(arr[i]);
                valueCnt.put(arr[i], tmp + 1);
            } else {
                valueCnt.put(arr[i], 1);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                int tmp = Integer.parseInt(line.trim());
                if (valueCnt.containsKey(tmp)) {
                    res.add(tmp);
                    int _tmp = valueCnt.get(tmp);
                    valueCnt.remove(tmp);
                    if (_tmp > 1) {
                        valueCnt.put(tmp, _tmp - 1);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException _ioe) {
                    _ioe.printStackTrace();
                }
            }
        }
        return res;
    }

    // or we can read file with try-with-resources
//    private List<Integer> process(String filePath, HashMap<Integer, Integer> valueCnt) {
//        List<Integer> res = new ArrayList<Integer>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                int tmp = Integer.parseInt(line.trim());
//                if (valueCnt.containsKey(tmp)) {
//                    res.add(tmp);
//                    int _tmp = valueCnt.get(tmp);
//                    valueCnt.remove(tmp);
//                    if (_tmp > 1) {
//                        valueCnt.put(tmp, _tmp - 1);
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException _e) {
//            _e.printStackTrace();
//        }
//        return null;
//    }

}
