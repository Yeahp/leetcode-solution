package solution;

import java.util.HashMap;
import java.util.Map;

public class OnlyEleFreqOne {

    // search for the only element which occurs once in an array
    public Integer frequenceOneEle_1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int item : arr) {
            if (map.containsKey(item)) {
                int freq = map.get(item);
                map.remove(item);
                map.put(item, freq + 1);
            } else {
                map.put(item, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Integer frequenceOneEle_2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1]^arr[i];
        }
        return arr[arr.length - 1];
    }

}
