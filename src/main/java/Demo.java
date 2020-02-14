import data.structure.ListNode;
import data.structure.TreeNode;
import jnr.ffi.annotations.In;
import rank.algorithm.HeapSort;

import java.util.*;

public class Demo {

    // 查找最长子字符串
    // 关键思路：
    // 当遇到重复的字符时，此时索引为k，重复的字符索引为k'，
    // 需要将前面已经扫过的字符（索引为 0~k'）全部删除，从 k' 开始计算
    public static int longestSubStr(String s) {
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
    public static String twoNumsum(int[] nums, int target) {
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
    public static ListNode<Integer> twoNumAdd(ListNode<Integer> l1, ListNode<Integer> l2) {
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
    public static ListNode<Integer> lastKElement(ListNode<Integer> root, int k) {
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
    public static ListNode<Integer> deleteLastKNode(ListNode<Integer> root, int k) {
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
    public static void threeNumSumZero(int[] arr) {
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
    public static boolean brackets(String s) {
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
    public static ListNode<Integer> mergeKLinkedList(ListNode<Integer>[] lls) {
        if (lls == null) return null;
        return _mergeKLinkedList(lls, 0, lls.length - 1);
    }

    private static ListNode<Integer> _mergeKLinkedList(ListNode<Integer>[] lls, int start, int end) {
        if (start == end) {
            return lls[start];
        } else if (start - end == 1) {
            return megerTwoLinkedList(lls[start], lls[end]);
        } else {
            int mid = (start + end) / 2;
            return megerTwoLinkedList(_mergeKLinkedList(lls, start, mid), _mergeKLinkedList(lls, mid, end));
        }
    }

    private static ListNode<Integer> megerTwoLinkedList(ListNode<Integer> ll1, ListNode<Integer> ll2) {
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
    public static int[] addOne(int[] arr) {
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
    public static ListNode<Integer> partitionLinkedList(ListNode<Integer> head, int x) {
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
    public static void levelOrderTraversal(TreeNode<Integer> root) {
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
    public static void reversedLevelOrderTraversal(TreeNode<Integer> root) {
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
    public static void levelKValue(TreeNode<Integer> root, int k) {
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
    public static void yangHuiGen(int row) {
        int[] pre = {1};
        _yangHuiGen(pre, 0, row);
    }
    private static void _yangHuiGen(int[] pre, int currentRow, int row) {
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
    public static int[] moveZeroToEnd(int[] arr) {
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
    public static boolean multiArrayContainsElement(int[][] arrs, int ele) {
        
    }


    public static void main(String[] args) {

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
        System.out.println(Arrays.toString(Demo.moveZeroToEnd(new int[]{1,0,3,0,5,6})));
    }

}
