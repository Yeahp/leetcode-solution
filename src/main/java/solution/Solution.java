package solution;

import data.structure.ListNode;
import data.structure.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

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

    // judge whether a liked table is a circle
    public boolean solution_3(ListNode node) {
        if (node == null) return false;
        ListNode node1 = node;
        ListNode node2 = node;
        while((node1 = node1.next.next) != null && (node2 = node2.next) != null) {
            if (node1.value == node2.value && node1.equals(node2)) return true;
        }
        return false;
    }

    // judge whether a binary tree is symmetrical
    public boolean solution_4(TreeNode node) {
        if (node == null) return true;
        return issymmetrical(node.left, node.right);
    }

    private boolean issymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.value == right.value && issymmetrical(left.left, right.right) && issymmetrical(left.right, right.left);
    }

    //

    public static void main(String[] args) {
        Solution solution = new Solution();

//        String input = "asddfdsd";
//        System.out.println(solution.solution_1(input));
//
//        List<Float> flist = new ArrayList<Float>(){
//            {
//                add(2.0f);
//                add(3.0f);
//                add(0.0f);
//                add(3.0f);
//            }
//        };
//        System.out.println(solution.solution_2(flist));
    }

}
