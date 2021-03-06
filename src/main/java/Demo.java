import data.structure.ListNode;
import data.structure.TreeNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import rank.algorithm.HeapSort;

import java.util.*;

public class Demo {

    // 查找最长子字符串
    // 关键思路：
    // 当遇到重复的字符时，此时索引为k，重复的字符索引为k'，
    // 需要将前面已经扫过的字符（索引为 0~k'）全部删除，从 k' 开始计算
    public int longestSubStr(String s) {
        int l = 0;
        int r = -1;
        int res = 0;
        int[] freq = new int[256];
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r++;
                freq[s.charAt(r)] = 1;
            } else {
                freq[s.charAt(l)]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    // 两数之和
    // 给定一个整数数组 nums 和一个目标值 target，
    // 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标
    // 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
    // 核心思路：
    // 2次遍历 - 第一次遍历记录值和下标的映射，第二次遍历寻找差值
    public String twoNumsum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null) {
                return "[" + i +"," + j + "]";
            }
        }
        return null;
    }

    // 两数相加
    // 给出两个非空的链表用来表示两个非负的整数，它们各自的位数逆序存储，且每个节点只能存储一位数字
    // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    // 您可以假设除了数字 0 之外，这两个数都不会以0开头。
    // 示例：
    // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    // 输出：7 -> 0 -> 8
    // 原因：342 + 465 = 80

    // 进阶：两数相加，位数按照顺序存储
    // 核心思想： 让两个链表分别进入栈，然后分别出栈相加，新建节点，逐个插入
    public ListNode<Integer> twoNumAdd(ListNode<Integer> l1, ListNode<Integer> l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.value);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.value);
            l2 = l2.next;
        }
        ListNode<Integer> res = null;
        int flag = 0;
        while (!s1.empty() && !s2.empty()) {
            int value = s1.pop() + s2.pop() + flag;
            flag = 0;
            if (value > 9) {
                res = new ListNode<>(value - 10, res);
                flag = 1;
            } else {
                res = new ListNode<>(value, res);
            }
        }
        if (s1.empty()) {
            while (!s2.empty()) {
                int value = s2.pop() + flag;
                if (value > 9) {
                    res = new ListNode<>(value - 10, res);
                    flag = 1;
                } else {
                    res = new ListNode<>(value, res);
                }
            }
        }
        if (s2.empty()) {
            while (!s1.empty()) {
                int value = s1.pop() + flag;
                if (value > 9) {
                    res = new ListNode<>(value - 10, res);
                    flag = 1;
                } else {
                    res = new ListNode<>(value, res);
                }
            }
        }
        if (flag == 1) res = new ListNode<>(1, res);
        return res;
    }

    // 查找链表倒数第k个元素
    // 核心思想：双指针，让前一个指针先走k步
    public ListNode<Integer> lastKElement(ListNode<Integer> root, int k) {
        ListNode<Integer> pre = root;
        ListNode<Integer> next = root;
        for (int i = 1; i < k; i++) {
            if (next.next == null) return null;
            else next = next.next;
        }
        while (next.next != null) {
            next = next.next;
            pre = pre.next;
        }
        return pre;
    }


    // 给定一个链表，删除链表的倒数第 k 个节点，并且返回链表的头结点
    public ListNode<Integer> deleteLastKNode(ListNode<Integer> root, int k) {
        if (root == null) return null;
        ListNode<Integer> _next = root;
        for (int i = 1; i < k; i++) {
            if (_next.next == null) return null;
            _next = _next.next;
        }
        ListNode<Integer> res = root;
        ListNode<Integer> pre = root;
        if (_next.next == null) {
            res = pre.next;
            pre.next = null;
        } else {
            _next = _next.next;
            ListNode<Integer> next = root.next;
            while (_next.next != null) {
                pre = pre.next;
                next = next.next;
                _next = _next.next;
            }
            pre.next = next.next;
            next.next = null;
        }
        return res;
    }

    // 给定一个包含 n 个整数的数组，判断是否存在三个元素 a，b，c
    // 使得 a + b + c = 0 找出所有满足条件且不重复的三元组
    public void threeNumSumZero(int[] arr) {
        HeapSort hs = new HeapSort();
        int[] _arr = hs.heapSort(arr);
        if (_arr[0] >0 || _arr.length < 3 || _arr[_arr.length - 1] < 0) return;
        for (int i = 0; i < _arr.length - 2; i++) {
            int j = i + 1;
            int k = _arr.length - 1;
            while (j < k) {
                int res = _arr[i] + _arr[j] + _arr[k];
                if (res == 0) {
                    System.out.println(_arr[i] + ", " + _arr[j] + ", " + _arr[k]);
                    j++;
                    k--;
                } else if (res > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
    }

    // 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
    // 有效字符串需满足：
    // 左括号必须用相同类型的右括号闭合
    // 左括号必须以正确的顺序闭合
    public boolean brackets(String s) {
        Map<String, String> map = new HashMap<String, String>(){{
            put(")", "(");
            put("}", "{");
            put("]", "[");
        }};
        Stack<String> ss = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String tmp = String.valueOf(s.charAt(i));
            if (ss.empty()) ss.push(tmp);
            else if (ss.peek() != null && ss.peek().equals(map.get(tmp))) ss.pop();
            else ss.push(tmp);
        }
        return ss.empty();
    }


    // 合并 k 个排序链表，返回合并后的排序链表
    // 核心思想：采用归并排序
    public ListNode<Integer> mergeKLinkedList(ListNode<Integer>[] lls) {
        if (lls == null) return null;
        return _mergeKLinkedList(lls, 0, lls.length - 1);
    }

    private ListNode<Integer> _mergeKLinkedList(ListNode<Integer>[] lls, int start, int end) {
        if (start == end) {
            return lls[start];
        } else if (start - end == 1) {
            return megerTwoLinkedList(lls[start], lls[end]);
        } else {
            int mid = (start + end) / 2;
            return megerTwoLinkedList(_mergeKLinkedList(lls, start, mid), _mergeKLinkedList(lls, mid, end));
        }
    }

    private ListNode<Integer> megerTwoLinkedList(ListNode<Integer> ll1, ListNode<Integer> ll2) {
        // 不额外产生空间开销
        if (ll1 == null) return ll2;
        if (ll2 == null) return ll1;
        ListNode<Integer> res = null;
        ListNode<Integer> pre = null;
        if (ll1.value < ll2.value) {
            res = ll1;
            pre = ll1;
            ll1 = ll1.next;
        } else {
            res = ll2;
            pre = ll2;
            ll2 = ll2.next;
        }
        while (ll1 != null && ll2 != null) {
            if (ll1.value < ll2.value) {
                pre.next = ll1;
                ll1 = ll1.next;
            } else {
                pre.next = ll2;
                ll2 = ll2.next;
            }
            pre = pre.next;
        }
        if (ll1 != null) pre.next = ll1;
        if (ll2 != null) pre.next = ll2;
        return res;
    }

    // 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加 1
    // 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字
    public int[] addOne(int[] arr) {
        if (arr == null) return null;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 9) {
                arr[i] = 0;
            } else {
                arr[i] = arr[i] + 1;
                return arr;
            }
        }
        int[] res = new int[arr.length + 1];
        res[0] = 1;
        return res;
    }


    // 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前
    // 保留两个分区中每个节点的初始相对位置
    public ListNode<Integer> partitionLinkedList(ListNode<Integer> head, int x) {
        ListNode<Integer> dummy1 = new ListNode<>(-1, null);
        ListNode<Integer> dummy2 = new ListNode<>(-1, null);
        ListNode<Integer> pre1 = dummy1;
        ListNode<Integer> pre2 = dummy2;
        while (head != null) {
            if (head.value < x) {
                pre1.next = head;
                head = head.next;
                pre1 = pre1.next;
                pre1.next = null;
            } else {
                pre2.next = head;
                head = head.next;
                pre2 = pre2.next;
                pre2.next = null;
            }
        }
        pre1.next = dummy2.next;
        return dummy1.next;
    }


    // 给定一个二叉树，返回其按层次遍历的节点值
    // 即逐层地，从左到右访问所有节点
    public void levelOrderTraversal(TreeNode<Integer> root) {
        if (root == null) return;
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            while (count > 0) {
                TreeNode<Integer> tmp = q.poll();
                System.out.println(tmp.value);
                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
                count--;
            }
        }
    }


    // 给定一个二叉树，返回其节点值自底向上的层次遍历
    // 核心思想：队列 + 栈
    public void reversedLevelOrderTraversal(TreeNode<Integer> root) {
        if (root == null) return;
        Stack<List<Integer>> stack = new Stack<>();
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            List<Integer> l = new ArrayList<>();
            while (count > 0) {
                TreeNode<Integer> tmp = q.poll();
                l.add(tmp.value);
                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
                count--;
            }
            stack.push(l);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }


    // 给定一个二叉树，打印第 k 层所有节点的值
    public void levelKValue(TreeNode<Integer> root, int k) {
        if (root == null || k == 0) return;
        if (k == 1) {
            System.out.println(root.value);
        } else {
            levelKValue(root.left, k -1);
            levelKValue(root.right, k - 1);
        }
    }


    // 给定一个非负整数 row
    // 生成杨辉三角的前 row 行
    public void yangHuiGen(int row) {
        int[] pre = {1};
        _yangHuiGen(pre, 0, row);
    }
    private void _yangHuiGen(int[] pre, int currentRow, int row) {
        if (currentRow < row) {
            int[] tmp = new int[currentRow + 1];
            tmp[0] = 1;
            tmp[currentRow] = 1;
            for (int i = 0; i < pre.length - 1; i++) {
                tmp[i + 1] = pre[i] + pre[i + 1];
            }
            System.out.println(Arrays.toString(tmp));
            _yangHuiGen(tmp, currentRow + 1, row);
        }
    }


    // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
    public int[] moveZeroToEnd(int[] arr) {
        int zero = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0 && zero < 0) {
                zero = i;
            }
            if (arr[i] != 0 && zero >= 0) {
                arr[zero++] = arr[i];
                arr[i] = 0;
            }
        }
        return arr;
    }


    // 一个二维数组，从左至右递增，从上往下递增
    // 判断给定数字是否在数组中
    public boolean multiArrayContainsElement(int[][] arrs, int ele) {
        if (arrs == null || arrs.length == 0) return false;
        int row = arrs.length;
        int col = arrs[0].length;
        int irow = 0;
        int jcol = col - 1;
        while (irow >= 0 && jcol >= 0 && irow < row && jcol < col) {
            if (arrs[irow][jcol] == ele) {
                return true;
            } else if (arrs[irow][jcol] < ele) {
                irow++;
            } else if (arrs[irow][jcol] > ele) {
                jcol--;
            }
        }
        return false;
    }


    // 根据逆波兰表示法，求表达式的值
    // 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
    //
    // 说明：
    // 整数除法只保留整数部分。
    // 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。

    // 示例 1：
    // 输入: ["2", "1", "+", "3", "*"]
    // 输出: 9
    // 解释: ((2 + 1) * 3) = 9

    // 示例 2：
    // 输入: ["4", "13", "5", "/", "+"]
    // 输出: 6
    // 解释: (4 + (13 / 5)) = 6

    // 示例 3：
    // 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    // 输出: 22
    // 解释:
    // ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
    // = ((10 * (6 / (12 * -11))) + 17) + 5
    // = ((10 * (6 / -132)) + 17) + 5
    // = ((10 * 0) + 17) + 5
    // = (0 + 17) + 5
    // = 17 + 5
    // = 22
    public int calRPN(String[] ss) {
        Stack<Integer> s = new Stack<>();
        Set<String> ops = new HashSet(){{
            add("+");
            add("-");
            add("*");
            add("/");
        }};
        //"2", "1", "+", "3", "*"
        for (int i = 0; i < ss.length; i++) {
            if (!ops.contains(ss[i])) {
                s.push(Integer.parseInt(ss[i]));
            } else {
                int b = s.pop();
                int a = s.pop();
                int c = cal(ss[i], a, b);
                s.push(c);
            }
        }
        return s.pop();
    }
    private Integer cal(String op, int a, int b) {
        Integer res = null;
        switch (op) {
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
        }
        return res;
    }


    // 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k)
    // 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）
    // 找到所有回旋镖的数量
    // 假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中

    // 示例:
    // 输入:
    // [[0,0],[1,0],[2,0]]
    // 输出:
    // 2
    // 解释:
    // 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
    public int numBoomerRangs() {
        return 1;
    }


    // 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的 id
    // 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导
    // 他们相应的重要度为 15, 10, 5
    // 那么员工 1 的数据结构是[1, 15, [2]]，员工 2 的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]
    // 注意虽然员工 3 也是员工 1 的一个下属，但是由于并不是直系下属，因此没有体现在员工 1 的数据结构中
    // 现在输入一个公司的所有员工信息，以及单个员工 id，返回这个员工和他所有下属的重要度之和
    public int employeeImpotance(Triple<Integer, Integer, List<Integer>>[] inds, int id) {
        Map<Integer, Pair<Integer, List<Integer>>> _inds = new HashMap<>();
        for (int i = 0; i < inds.length; i++) {
            _inds.put(inds[i].getLeft(), new ImmutablePair<>(inds[i].getMiddle(), inds[i].getRight()));
        }
        Pair<Integer, List<Integer>> l = _inds.get(id);
        int imp = l.getKey();
        if (l.getValue().size() == 0) {
            return imp;
        } else {
            for (Integer item : l.getRight()) {
                imp = imp + _inds.get(item).getLeft();
            }
        }
        return imp;
    }


    // 给定一个含有 n 个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s的长度最小的连续子数组
    // 如不存在符合条件的连续子数组，返回 0
    // 示例:
    // 输入: s = 7, nums = [2,3,1,2,4,3]
    // 输出: 2
    // 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组
    public int minSubArray(int[] arr, int s) {
        if (arr == null || arr.length == 0) return 0;
        int count = 0, left = 0, sum = 0;
        for (int right = 0; right < arr.length; right++) {
            sum = sum + arr[right];
            if (sum >= s) {
                count = right - left + 1;
                for (int j = left; j < right; j++) {
                    if (sum - arr[j] >= s && sum - arr[j] - arr[j + 1] < s) {
                        count = right - j;
                        left = j + 1;
                        break;
                    }
                }
                if (count == 1) return count;
            }
        }
        return count;
    }


    public boolean aliceWin(int n) {
        if (n == 1) return false;
        if (n == 2) return true;
        int[] res = new int[n + 1];
        res[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i%j == 0 && res[i - j] == 0) {
                    res[i] = 1;
                    break;
                }
            }
        }
        return res[n] == 1;
    }


    public int numOfSquare(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i <= n; i++) {
            int square = (int) Math.sqrt(i);
            if (square * square == i) {
                res[i] = 1;
            } else {
                int min = i;
                for (int j = 1; j <= i/2; j++) {
                    int tmp = res[j] + res[i - j];
                    if (min > tmp) min = tmp;
                }
                res[i] = min;
            }
        }
        return res[n];
    }


    public int treePath(TreeNode<Integer> root, int target) {
        if (root == null) return 0;
        int res = 0;
        treePathHelper(root, target, target, res);
        return res;
    }
    private void treePathHelper(TreeNode<Integer> root, int target, int _target, int res) {
        if (target == 0) {
            res++;
            if (root.left != null) treePathHelper(root.left, _target, _target, res);
            if (root.right != null) treePathHelper(root.right, _target, _target, res);
        } else {
            if (root.left != null) {
                treePathHelper(root.left, target - root.value, _target, res);
                treePathHelper(root.left, _target, _target, res);
            }
            if (root.right != null) {
                treePathHelper(root.right, target - root.value, _target, res);
                treePathHelper(root.right, _target, _target, res);
            }
        }
    }


    public int largestRectangle(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] res = new int[rows][cols];
        int[][][] help = new int[rows][cols][2];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                 if (row == 0 && col == 0) {
                     if (arr[row][col] == 1) {
                         res[row][col] = 1;
                         help[row][col][0] = 1;
                         help[row][col][1] = 1;
                     }
                 } else if (row == 0) {
                     if (arr[row][col] == 1) {
                         res[row][col] = res[row][col - 1] + 1;
                         help[row][col][0] = help[row][col][0] + 1;
                         help[row][col][1] = help[row][col][1] + 1;
                     }
                 } else if (col == 0) {
                     if (arr[row][col] == 1) {
                         res[row][col] = res[row - 1][col] + 1;
                         help[row][col][0] = help[row - 1][col][0] + 1;
                         help[row][col][1] = help[row - 1][col][1] + 1;
                     }
                 } else {
                     int tmp_res = 0;
                     int tmp_row = 0;
                     int tmp_col = 0;
                     if (arr[row][col] == 1) {
                         if (res[row - 1][col] + 1 > res[row][col - 1] + 1) {
                             tmp_res = res[row - 1][col] + 1;
                             tmp_row = help[row - 1][col][0] + 1;
                             tmp_col = help[row - 1][col][1] + 1;
                         } else {
                             tmp_res = res[row][col - 1] + 1;
                             tmp_row = help[row][col - 1][0] + 1;
                             tmp_col = help[row][col - 1][1] + 1;
                         }
                     }
                     res[row][col] = tmp_res;
                     help[row][col][0] = tmp_row;
                     help[row][col][1] = tmp_col;
                 }
                 if (max < res[row][col]) max = res[row][col];
            }
        }
        return max;
    }


    public int _largestRectangle(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] res = new int[rows][cols];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) {
                    if (arr[row][col] == 1) res[row][col] = 1;
                } else if (row == 0) {
                    if (arr[row][col] == 1) res[row][col] = res[row][col - 1] + 1;
                } else if (col == 0) {
                    if (arr[row][col] == 1) res[row][col] = res[row - 1][col] + 1;
                } else {
                    int tmp_res = 0;
                    if (arr[row][col] == 1) {
                        if (res[row - 1][col] + 1 > res[row][col - 1] + 1) tmp_res = res[row - 1][col] + 1;
                        else tmp_res = res[row][col - 1] + 1;
                    }
                    res[row][col] = tmp_res;
                }
                if (max < res[row][col]) max = res[row][col];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Demo demo = new Demo();

//        ListNode<Integer> root = new ListNode<Integer>(5);
//        ListNode<Integer> node1 = new ListNode<Integer>(2);
//        ListNode<Integer> node2 = new ListNode<Integer>(3);
//        ListNode<Integer> node3 = new ListNode<Integer>(5);
//        root.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        ListNode<Integer> res = Demo.partitionLinkedList(root, 4);
//        while (res != null) {
//            System.out.println(res.value);
//            res = res.next;
//        }
//        System.out.println(Arrays.toString(Demo.moveZeroToEnd(new int[]{1,0,3,0,5,6})));

        TreeNode<Integer> root = new TreeNode<Integer>(1);
        TreeNode<Integer> node1 = new TreeNode<Integer>(2);
        TreeNode<Integer> node2 = new TreeNode<Integer>(3);
        TreeNode<Integer> node3 = new TreeNode<Integer>(4);
        TreeNode<Integer> node4 = new TreeNode<Integer>(5);
        TreeNode<Integer> node5 = new TreeNode<Integer>(6);
        root.left = node1;
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node3.left = node5;
        //Demo.reversedLevelOrderTraversal(root);
        //System.out.println(Demo.calRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        System.out.println(demo.numOfSquare(12));
    }

}
