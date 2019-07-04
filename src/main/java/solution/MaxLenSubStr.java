package solution;

import java.util.HashMap;
import java.util.Map;

public class MaxLenSubStr {

    // search for the maximum sub-string of a given string
    private int solution_1(String input) {
        if (input == null) return 0;
        Map<Character, Integer> pair = new HashMap<Character, Integer>();
        int maxlen = 0;
        int tmplen = 0;
        int repidx = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!pair.containsKey(input.charAt(i))) {
                pair.put(input.charAt(i), i);
                tmplen += 1;
            } else {
                int _repidx = pair.get(input.charAt(i));
                if (repidx > _repidx) {
                    tmplen = i - repidx + 1;
                } else {
                    tmplen = i - repidx;
                }
                pair.remove(input.charAt(i));
                pair.put(input.charAt(i), i);
                repidx = i;
            }
            if (maxlen < tmplen) maxlen = tmplen;
        }
        return maxlen;
    }

}
