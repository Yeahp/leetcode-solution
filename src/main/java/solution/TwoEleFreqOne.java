package solution;

import java.util.ArrayList;
import java.util.List;

public class TwoEleFreqOne {

    // search for the only two elements which occur once in an array
    public List<Integer> frequenceOneEle_3(int[] arr) {
        assert arr.length > 3;
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = res^arr[i];
        }

        String strRes = Integer.toBinaryString(res);
        int index = 0;
        while (index < strRes.length()) {
            if (strRes.charAt(index) == '1') break;
        }

        List<Integer> resList = new ArrayList<Integer>();
        int index_0 = -1;
        int index_1 = -1;
        for (int i = 0; i < strRes.length(); i++) {
            if (Integer.toBinaryString(arr[i]).charAt(index) == '0') {
                if (index_0 == -1) {

                } else {
                    arr[i] = arr[index_0]^arr[i];
                }
                index_0 = i;
            } else if (Integer.toBinaryString(arr[i]).charAt(index) == '1') {
                if (index_1 == -1) {

                } else {
                    arr[i] = arr[index_1]^arr[i];
                }
                index_1 = i;
            }
        }
        resList.add(arr[index_0]);
        resList.add(arr[index_1]);
        return resList;

    }

}
