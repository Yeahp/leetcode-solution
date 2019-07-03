package solution;

import data.structure.ListNode;
import data.structure.TreeNode;
import javafx.util.Pair;

import java.util.*;

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

    // 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
    // 少有一个空座位，且至少有一人坐在座位上。
    // 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
    // 返回他到离他最近的人的最大距离。
    // 示例 1：
    // 输入：[1,0,0,0,1,0,1]
    // 输出：2
    public int solution_5(List<Integer> seat) {
        int tt = seat.size();
        List<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < tt; i++) {
            if (seat.get(i) == 1) order.add(i);
        }
        int minus = order.get(0) > tt -order.get(0) - 1? order.get(0) : tt -order.get(0) - 1;
        for (int j = 1; j < order.size(); j++) {
            int tmp = order.get(j) - order.get(j - 1) - 2;
            if (tmp > minus) minus = tmp;
        }
        return minus;
    }

    public int solution_6(int[] seat) {
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

    // judget whether a string is a huiwen one
    public boolean solution_7(String s) {
        int len = s.length();
        if (len == 0) return true;
        int i = 0;
        int j = len - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
                i++;
                j--;
            }
        }
        return true;
    }

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

    // search for the only two elements which occur once in an array
    public List<Integer> frequenceOneEle_3(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res^arr[i];
        }
        System.out.println(res);
        List<Integer> resList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if ((res^arr[i]) == arr[i]) resList.add(arr[i]);
        }
        return resList;
    }

    // reverse an array
    public char[] reverseArr(char[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = arr[j];
            i++;
            j--;
        }
        return arr;
    }

    // left order
    public List<Integer> leftOrder(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<Integer>();
        leftOrderImp(root, res);
        return res;
    }

    private void leftOrderImp(TreeNode<Integer> node, List<Integer> l) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            l.add(node.value);
        } else {
            leftOrderImp(node.left, l);
            l.add(node.value);
            leftOrderImp(node.right, l);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

//        String input = "asddfdsd";
//        System.out.println(solution.solution_1(input));
//
        List<Integer> flist = new ArrayList<Integer>(){
            {
                add(0);
                add(0);
                add(0);
                add(0);
                add(1);
                add(0);
                add(1);
                add(0);
                add(0);
            }
        };
        int[] s = new int[]{1,2,1,2,4,3};
        TreeNode<Integer> node1 = new TreeNode<Integer>(1);
        TreeNode<Integer> node2 = new TreeNode<Integer>(2);
        TreeNode<Integer> node3 = new TreeNode<Integer>(3);
        node1.right = node2;
        node1.left = node3;
        //System.out.println(solution.frequenceOneEle_3(s));
        System.out.println(solution.leftOrder(node1));
    }

}
