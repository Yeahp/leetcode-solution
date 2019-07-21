package solution;

import data.structure.TreeNode;
import java.util.*;

public class Solution {

    private List<List<String>> res = new ArrayList<List<String>>();
    private void dfs(String s, List<String> remain, int left) {
        if (left == s.length()){  //判断终止条件
            res.add(new ArrayList<String>(remain));  //添加到结果中
            return;
        }
        for (int right = left; right < s.length(); right++) {
            if (isPalindroom(s, left, right)) {
                remain.add(s.substring(left, right + 1));
                dfs(s, remain,right + 1);
                remain.remove(remain.size() - 1);
            }
        }
    }
    private boolean isPalindroom(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
    public List<List<String>> solution_8(String s) {
        if(s == null || s.length() == 0)
            return res;
        dfs(s, new ArrayList<String>(), 0);
        return res;
    }


    public static void main(String[] args) {

//        Solution solution = new Solution();
//
//        int[] s = new int[]{1,2,1,2,4,3};
//        TreeNode<Integer> node1 = new TreeNode<Integer>(1);
//        TreeNode<Integer> node2 = new TreeNode<Integer>(2);
//        TreeNode<Integer> node3 = new TreeNode<Integer>(3);
//        node1.right = node2;
//        node2.left = node3;
//        System.out.println(solution.inorderTraversal(node1));
//
//        int b = 7;
//        System.out.println(Integer.toBinaryString(b));
        String str = "erpeng";
        StringBuilder sb = new StringBuilder(str);
        System.out.println(sb.reverse().toString());
    }

}
