package SwordCode;

import data.structure.ListNode;
import data.structure.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class SwordCodeApp {

    // 找出数组中重复的数字: 数组长度为n，元素范围 0 ~ n-1，有重复数字，但不知重复几次。找出其中重复的数字
    // S1：新建 map，元素值 -> 是否出现
    // S2：
    public Set<Integer> duplicateNumberS2(int[] arr) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                while (arr[i] != i) {
                    if (arr[i] == arr[arr[i]]) {
                        res.add(arr[i]);
                        break;
                    }
                    int tmp = arr[i];
                    arr[i] = arr[tmp];
                    arr[tmp] = tmp;
                }
            }
        }
        return res;
    }


    // 把字符串中的空格替换为 “%20”
    public String replaceSpace(String s) {
        //return s.replaceAll(" ", "%20");
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

    }


    // 从尾到头打印链表
    // S1: 使用栈结构，鲁棒性更强
    // S2: 递归是一种栈结构，但是当链表很长的时候，有可能因调用链过长而造成栈溢出
    public void printLinkedListS1(ListNode<Integer> head) {
        Stack<Integer> s = new Stack<>();
        while (head != null) {
            s.push(head.value);
            head = head.next;
        }
        while (!s.empty()) {
            System.out.println(s.peek());
            s.pop();
        }
    }
    public void printLinkedListS2(ListNode<Integer> head) {
        if (head != null) {
            if (head.next != null) {
                printLinkedListS2(head.next);
            }
            System.out.println(head.value);
        }
    }


    // 求斐波那契数列第 n 项
    // S1：伪递归 -- F(n) = F(n-1) + F(n-2)
    public int fibonacciS1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        return fibonacciS1(n - 1) + fibonacciS1(n - 2);
    }
    // S2：移动求和
    public int fibonacciS2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        int i = 1, j = 1;
        for (int _n = 3; _n < n; _n++) {
            int tmp = i + j;
            i = j;
            j = tmp;
        }
        return i + j;
    }


    // 上台阶问题：有 n 级台阶，一次只能上 1 级或者 2 级，总共有多少种不同走法
    public int upstairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return upstairs(n - 1) + upstairs(n - 2);  // 也是斐波那契数列
    }
    // 若一次可上 1 ~ n-1 级
    // 当 n=1 时，共 1 种走法， 1 = 2^(1-1)
    // 当 n=2 时，共 2 种走法， 2 = 2^(2-1)
    // 假设当 n=k 时，共 2^(k-1) 种走法
    // 当 n=k+1 时，共 2 * 2^(k-2) + 2^(k-1) = 2 * 2^(k-1) = 2^k 种走法

    // 升级版：
    // 1～26 分别对应 a～z，给定一个正整数，返回解码数量
    // 核心思想：从末位数开始考察，决定一次往前回溯 1 位 还是 2 位
    public int decodeNum(int x) {
        int[] arr = new int[String.valueOf(x).length()];
        int idx = 0;
        while (x > 0) {
            arr[idx] = x%10;
            x = x / 10;
            idx++;
        }
        return _decodeNum(arr, arr.length - 1);
    }
    public int _decodeNum(int[] arr, int start) {
        if (start < 0) return 0;
        if (start == 0) return 1;
        if (arr[start] * 10 + arr[start - 1] > 26) return _decodeNum(arr, start - 1);  // 回溯 1 位
        return _decodeNum(arr, start - 1) + _decodeNum(arr, start - 2);  // 回溯 2 位
    }


    // 顺时针打印数组
    public void printArrayClockwise(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        _printArrayClockwise(arr, 0, 0, row - 1, col - 1);
    }
    private void _printArrayClockwise(int[][] arr, int startRow, int startCol, int endRow, int endCol) {
        int subRow = endRow - startRow + 1;
        int subCol = endCol - startCol + 1;
        if (subRow > 1 || subCol > 1) {
            for (int i = startCol; i < endCol; i++) System.out.println(arr[startRow][i]);
            for (int i = startRow; i < endRow; i++) System.out.println(arr[i][endCol]);
            for (int i = endCol; i > startCol; i--) System.out.println(arr[endRow][i]);
            for (int i = endRow; i > startRow; i--) System.out.println(arr[i][startCol]);
            _printArrayClockwise(arr, startRow + 1, startCol + 1, endRow - 1, endCol - 1);
        } else if (subRow == 1 && subCol == 1) {
            System.out.println(arr[startRow][startCol]);
        }
    }


    // 给定长度为 n 的绳子，截成 m 段，求长度乘积最大值f(n)，其中 m>1, n>1
    // S1: 一种贪婪的解法，尽可能多的截取长度为3的线段
    // S2：f(n)=f(i)*f(n-i)，依次记录f(i)，对于任意n，循环搜索 1<i<n/2
    public int cutLineS2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[2] = 2;
        int _n = 3;
        while (_n <= n) {
            int max = 1;
            for (int i = 0; i < _n / 2; i++) {
                if (max < arr[i] * arr[_n - i]) max = arr[i] * arr[_n - i];
            }
            arr[_n - 1] = max;
            _n++;
        }
        return arr[n - 1];
    }


    // 输入一棵二叉树 A，返回 A 的镜像
    public TreeNode<Integer> mirrorTree(TreeNode<Integer> root) {
        mirrorTree(root);
        return root;
    }
    private void _mirrorTree(TreeNode<Integer> root) {
        if (root != null) {
            TreeNode<Integer> tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            _mirrorTree(root.left);
            _mirrorTree(root.right);
        }
    }


    // 输入一棵二叉树 A，判断 A 是否对称
    public boolean isSymmetryTree(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left == null && root.right != null) return false;
        if (root.left != null && root.right == null) return false;
        return isSymmetry(root.left, root.right);
    }
    private boolean isSymmetry(TreeNode<Integer> node1, TreeNode<Integer> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null) return false;
        if (node2 == null) return false;
        return node1.value.equals(node2.value)
                && isSymmetry(node1.left, node2.right)
                && isSymmetry(node1.right, node2.left);
    }


    // 输入 2 棵二叉树 A 和 B，判断 B 是不是 A 的子树
    public boolean isSubTree(TreeNode<Integer> A, TreeNode<Integer> B) {
        if (A == null || B == null) return A == null && B == null;
        if (A.value.equals(B.value) && _isSubTree(A, B)) return true;
        return isSymmetry(A.left, B) || isSymmetry(A.right, B);
    }
    private boolean _isSubTree(TreeNode<Integer> A, TreeNode<Integer> B) {
        if (A == null || B == null) return A == null && B == null;
        return A.value.equals(B.value)
                && _isSubTree(A.left, B.left)
                && _isSubTree(A.right, B.right);
    }


    // 给定一个链表，删除其中某一个节点，要求在时间复杂度 o(1) 前提下完成
    // 核心思想：将待删除的节点后面部分复制给该节点
    public ListNode<Integer> deleteListNode(ListNode<Integer> head, ListNode<Integer> node) {
        // 情形1：待删除节点为头节点
        if (head.equals(node)) {
            if (node.next == null) return null;
            ListNode<Integer> tmp = head;
            head = node;
            tmp.next = null;
            return head;
        }
        // 情形2：待删除节点为末尾节点
        if (node.next == null) {
            node = null;
            return head;
        }
        // 情形3：待删除节点为一般节点
        ListNode<Integer> tmp = node.next;
        node.value = tmp.value;
        node.next = tmp.next;
        tmp.next = null;
        return head;
    }


    // 之字形打印二叉树
    // 核心思想：层级从0开始编码，奇数层，从右往左打印，偶数层，从左往右打印
    public void zhiShapedBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            Stack<Pair<TreeNode<Integer>, Integer>> stack = new Stack<>();
            stack.push(new Pair<>(root, 0));
            while (!stack.empty()) {
                int count = stack.size();
                Stack<Pair<TreeNode<Integer>, Integer>> _stack = new Stack<>();
                while (count > 0) {
                    Pair<TreeNode<Integer>, Integer> tmp = stack.pop();
                    System.out.print(tmp.getKey().value + " ");
                    if ((tmp.getValue() + 1)%2 == 0) {
                        if (tmp.getKey().right != null) _stack.push(new Pair<>(tmp.getKey().right, tmp.getValue() + 1));
                        if (tmp.getKey().left != null) _stack.push(new Pair<>(tmp.getKey().left, tmp.getValue() + 1));
                    } else {
                        if (tmp.getKey().left != null) _stack.push(new Pair<>(tmp.getKey().left, tmp.getValue() + 1));
                        if (tmp.getKey().right != null) _stack.push(new Pair<>(tmp.getKey().right, tmp.getValue() + 1));
                    }
                    count--;
                }
                System.out.println();
                stack = _stack;
            }
        }
    }

    // 复制链表
    public ListNode<Integer> copyLinkedList(ListNode<Integer> head) {
        if (head != null) {
            ListNode<Integer> _head  = new ListNode<>(head.value);
            ListNode<Integer> pre = _head;
            while (head.next != null) {
                ListNode<Integer> _pre = new ListNode<>(head.next.value);
                pre.next = _pre;
                pre = _pre;
                head = head.next;
            }
            return _head;
        }
        return null;
    }


    // 字符流中第一个只出现一次的字母
    // 例如，当输入至"go"时，输出"g"，当输入至"google"时，输出"l"
    // 核心思想：维护两个数组，第一个数组记录字符频次，第二个字符记录字符首次进入的时间
    // 制造一个字符生成器：从26个字母中，每次随机抽取一个字母
    private void theFistCharOccurOnce() {
        Random random = new Random();
        char[] s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwsyz".toCharArray();
        int[] s1 = new int[123];  // 用于存储字母次数
        int[] s2 = new int[123];  // 用于存储相应字母首次进入的时间
        while (true) {
            char c = s[random.nextInt(52)];
            s1[c] = s1[c] + 1;  //只要字符流进来，则对应频次大于0
            if (s2[c] == 0) s2[c] = s2[c] + 1;  //只要字符流进来，则对应进入次序大于0
            int fistCharIdx = 0;
            for (int j = 65; j < 123; j++) {  // a~z: 97~122 A~Z:65~90
                if (s1[j] == 1) {  // 确保只出现 1 次
                    if (fistCharIdx == 0) {
                        fistCharIdx = j;
                    } else if (s2[j] < s2[fistCharIdx]) {
                        fistCharIdx = j;
                    }
                }
            }
            if (fistCharIdx > 0) {
                System.out.println((char) fistCharIdx);
            } else {
                break;
            }
        }
    }


    // 数据流中的中位数
    // 核心思想：将流进来的数据拆成 2 个部分，第一部分存储较小一般数据的大顶堆，第二部分存储较大数据的小顶堆
    public void median() {
        Random random = new Random();
        Queue<Integer> minHeap = new PriorityQueue<>();  // 存储较大数据
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());  // 存储较小数据
        while (true) {
            int tmp = random.nextInt();
            minHeap.add(tmp);
            if (maxHeap.size() > 0) {
                if (minHeap.size() == maxHeap.size()) System.out.println((minHeap.peek() + maxHeap.peek()) / 2.0);
                else if (minHeap.size() - maxHeap.size() == 1) System.out.println(minHeap.peek());
                else maxHeap.add(minHeap.poll());
            }
        }
    }


    // 连续子数组的最大和
    // 核心思想：用一个额外的数组存储当前最大值，并移动更新最大和
    // 更新思路是，如果上一步最大值为负，则存储当前元素，否则，存储其与当前元素的加和
    public int maxSumOfSubArray(int[] arr) {
        int[] d = new int[arr.length];
        d[0] = arr[0];
        int sum = arr[0];  //初始化，最大和为第 0 个元素
        for (int i = 1; i < arr.length; i++) {
            d[i] = d[i - 1] < 0 ? arr[i] : d[i - 1] + arr[i];
            if (d[i] > sum) sum = d[i];
        }
        return sum;
    }


    // 1～n 整数中，1 出现的次数


    // 数字以 01234567891011121314··· 存储，求任意第 n 位数字
    // 核心思想：所有位数小于或者等于 k 的数字位数之和为 1 + k*10^k - (10^k - 1)/9
    public int noNDigit(int n) {
        if (n <= 9) return n;
        int k = 1;
        while (k > 0) {
            // 确定第 n 个数的位数
            if (1 + k * Math.pow(10, k) - (Math.pow(10, k) - 1) / 9 < n
                    && 1 + (k + 1) * Math.pow(10, k + 1) - (Math.pow(10, k + 1) - 1) / 9 >= n) {
                break;  //表明为第 n 个数的位数为 k+1
            }
            k++;
        }
        int delt = (int) (n - (1 + k * Math.pow(10, k) - (Math.pow(10, k) - 1) / 9));
        int no = delt / (k + 1);
        int _delta = delt%(k + 1);
        if (_delta == 0) {
            int res =  (int) Math.pow(10, k) + no;
            return res%10;
        } else {
            int res =  (int) Math.pow(10, k) + no + 1;
            return res%((int) Math.pow(10, k + 1 - _delta + 1));
        }
    }


    // 给定一个正整数数组，把数组中数字拼接，返回最小值
    // 核心思想：将数组数字按照最左位从小到大排列，最左位数字相同，按照次左位从小到大排列
    public int minArrRank(int[] arr) {
        int len = arr.length;
        int[][] helper = new int[10][len];
        for (int i = 0; i < len; i++) {
            
        }
        return 0;
    }


    // 丑数: 因子只包含2、3、5的正整数，其中定义 1 为丑数
    // Q1：判断给定的数是否为丑数
    // 循环：
    public boolean isUglyNumber(int x) {
        int[] uglyFactor = new int[]{2, 3, 5};
        while (x != 1) {
            boolean flag = false;
            for (int i : uglyFactor) {
                if (x%i == 0) {
                    x = x / i;
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }
    // 递归：
    public boolean _isUglyNumber(int x) {
        if (x == 1) return true;
        int[] uglyFactor = new int[]{2, 3, 5};
        for (int i : uglyFactor) {
            if (x%i == 0) return _isUglyNumber(x / i);
        }
        return false;
    }
    // Q2：给定范围内有多少个丑数
    // S1: 逐一验证
    public int numOfUglyNumberS1(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (_isUglyNumber(i)) count++;
        }
        return count;
    }
    // S2：数学推导
    public int numOfUglyNumberS2(int start, int end) {
        if (end <= 6) return end - start + 1;
        int startN = (int) (Math.log(start) / Math.log(2.0));
        int endN = (int) (Math.log(end) / Math.log(2.0));
        if (startN == 0) {
            if (end < 5 * Math.pow(2, endN - 2)) return 3 * endN - 2;
            else if (end < 3 * Math.pow(2, endN - 1)) return 3 * endN - 1;
            else return 3 * endN;
        } else if (endN == startN) {
            return intervalJudgeSamePow(end, endN) - intervalJudgeSamePow(start, startN);
        } else if (endN - startN == 1) {
            return intervalJudgeDiffPow(end, endN) - intervalJudgeSamePow(start, startN) + 3;
        } else {
            return 3 * (endN - startN - 1) - Math.max(1, intervalJudgeSamePow(start, startN)) + intervalJudgeDiffPow(end, endN);
        }
    }
    private int intervalJudgeSamePow(int num, int pow) {
        if (num == Math.pow(2, pow)) return 0;
        else if (num < 3 * Math.pow(2, pow - 1)) return 1;
        else return 2;
    }
    private int intervalJudgeDiffPow(int num, int pow) {
        if (num < 5 * Math.pow(2, pow - 2)) return 0;
        else if (num < 3 * Math.pow(2, pow - 1)) return 1;
        else return 2;
    }
    // Q3：给定 n，返回第 n 个丑数
    public int noNUglyNumber(int n) {
        if (n == 1) return 1;
        int i = (n - 1) % 3;
        if (i == 0) return (int) Math.pow(2, 1 + (n - 1.0) / 3);
        else if (i == 1) return (int) (5 * Math.pow(2, (n - 1.0) / 3 - 1));
        else return (int) (3 * Math.pow(2, (n - 1.0) / 3));
    }


    // 数组中的逆序对: 在数组的两个数字，如果前一个数字大于后面的数字，则这两个数字组成一个逆序对
    // 输入一个数组，返回这个数组中逆序对的总数


    // 输入两个链表，找出第一个公共节点
    // 核心思想：依次遍历两个链表，找出各自长度m和n，m>n，让较长的先走m-n步，然后一起走，依次判断当前是否重合
    public ListNode<Integer> firstCommonNodeOfTwoLinkedList(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == head2) return head1;
        ListNode<Integer> h1 = head1;
        int cnt1 = 0;
        ListNode<Integer> h2 = head2;
        int cnt2 = 0;
        while (h1 != null) {
            cnt1 = cnt1 + 1;
            h1 = h1.next;
        }
        while (h2 != null) {
            cnt2 = cnt2 + 1;
            h2 = h2.next;
        }
        if (cnt1 > cnt2) {
            for (int step = 0; step < (cnt1 - cnt2); step++) {
                head1 = head1.next;
            }
        }
        while (head1 != head2) {
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1;
    }


    // 数字在排序数组中出现的次数，假设升序排列
    // S1：遍历求解，但当给定数字靠后时，时间复杂度会很大
    // S2：二分查找，给数字起/止下标
    public int numberFrequenceS2(int[] arr, int x) {
        int len = arr.length;
        return _numberFrequence(arr, x, 0, len - 1);
    }
    public int _numberFrequence(int[] arr, int x, int start, int end) {
        if (start == end && arr[start] != x) return 0;
        if (arr[start] == x && arr[end] == x) return end - start + 1;
        int mid = (start + end);
        if (arr[mid] > x) return _numberFrequence(arr, x, start, mid);
        else return _numberFrequence(arr, x, start, mid) + _numberFrequence(arr, x, mid + 1, end);
    }


    // 给定一棵二叉搜索树，按节点值从小到大排列，返回二叉搜索树的第 k 大节点
    // 核心思想：中序遍历，找到第k个元素即返回
    public Integer noKElementOfBST(TreeNode<Integer> root, int k) {
        if (root == null) return null;
        List<Integer> res = new ArrayList<>();
        return noKElementOfBSTHelper(root, k, res);
    }
    private Integer noKElementOfBSTHelper(TreeNode<Integer> root, int k, List<Integer> res) {
        if (root.left != null) noKElementOfBSTHelper(root.left, k, res);
        if (res.size() == k) return res.get(k - 1);
        res.add(root.value);
        if (res.size() == k) return res.get(k - 1);
        if (root.right != null) noKElementOfBSTHelper(root.right, k, res);
        if (res.size() == k) return res.get(k - 1);
        return null;   // 此处意味着元素个数小于k
    }


    // 给定一棵二叉树，判断是否为平衡二叉树
    public boolean isBlanceBinaryTree(TreeNode<Integer> root) {
        if (root == null) return true;
        return Math.abs(maxDepthOfBinaryTree(root.left) - maxDepthOfBinaryTree(root.right)) > 1;
    }
    private int maxDepthOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) return 0;
        return Math.max(1 + maxDepthOfBinaryTree(root.left), 1 + maxDepthOfBinaryTree(root.right));
    }



    // 一个整数数组里面除 2 个数字外，其他数字都出现了 2 次，找出这两个数字
    // S1：用map存储值到出现次数的映射，然后遍历map找出value为1的key


    // 一个数组中除了 1 个数字只出现 1 次外，其他数字都出现了 3 次，找出这个数字
    // S1：用map存储值到出现次数的映射，然后遍历map找出value为1的key


    // 给定一个整数 s，找出和为 s 的连续正数序列数量
    // 例如，1+2+3+4+5=4+5+6=7+8=15，输出 3
    // 核心思想：对于给定的s，最长序列应从1开始至n，(1+n)n/2=s
    // 对于k个元素序列，当k为奇数时，若s能被k整除且最小值大于0，则成立
    // 当k为偶数时，若s不能被k整除、小数部分为0.5 且最小值大于0，则成立
    public int seriesNumber(int s) {
        int num = (int) Math.sqrt(2 * s);
        int res = 0;
        for (int i = 2; i <= num; i++) {
            if ((i%2 == 0 && s%i !=0 && 1.0 * s / i - s / i == 0.5 && s / i - i / 2 + 1 > 0) || (i%2 != 0 && s%i ==0 && 1.0 * s / i - (i - 1) / 2.0 > 0)) {
                System.out.println(i);
                res++;
            }
        }
        return res;
    }


    // 字符串的左旋操作时把字符串前面的若干个自负移动到字符串的末尾，定义函数实现该功能
    // 例如，输入"abcdefg"和数字2，输出"cdefgab"
    // S1：借助 subString 函数
    public String leftRotateStringS1(String s, int i) {
        if (s.length() == i) return s;
        return s.substring(i + 1) + s.substring(0, i + 1);
    }
    // S2：借助数组
    public String leftRotateStringS2(String s, int i) {
        int len = s.length();
        char[] res = new char[len];
        for (int j = 0; j < len; j++) {
            if (j <= i) res[len - i + j] = s.charAt(j);
            else res[j - i] = s.charAt(j);
        }
        return String.valueOf(res);
    }


    // n 个骰子的点数
    // 投掷 n 个骰子，所有点数之和为 s，输入 n，打印 s 所有可能值出现的概率
    // 核心思想：f(n,s)=f(n-1,s-1)+f(n-1,s-2)+f(n-1,s-3)+f(n-1,s-4)+f(n-1,s-5)+f(n-1,s-6)
    // 其中，当n<s或者s>6n时，f(n,s)=0
    public void shaiziSumProb(int n) {
        int[][] sumCountArr = new int[n + 1][6 * n + 1];
        for (int _n = 1; _n <= n; _n++) {
            for (int _s = _n; _s <= 6 * _n; _s++) {
                if (_n == 1) {
                    sumCountArr[_n][_s] = 1;
                } else {
                    int maxMinus = Math.min(_s - 1, 6);
                    for (int _maxMinus = 1; _maxMinus <= maxMinus; _maxMinus++) {
                        sumCountArr[_n][_s] =  sumCountArr[_n][_s] + sumCountArr[_n - 1][_s - _maxMinus];
                    }
                }
            }
        }
        int total = (int) Math.pow(6, n);
        for (int s = n; s <= 6 * n; s++) {
            System.out.println("sum: " + s + ", prob: " + sumCountArr[n][s] + "/" + total);
        }
    }
    public void _shaiziSumProb(int n) {
        int[][] sumCountArr = new int[n][6 * n];
        for (int _n = 0; _n < n; _n++) {
            for (int _s = _n; _s < 6 * (_n + 1); _s++) {
                if (_n == 0) {
                    sumCountArr[_n][_s] = 1;
                } else {
                    int maxMinus = Math.min(_s, 6);
                    for (int _maxMinus = 1; _maxMinus <= maxMinus; _maxMinus++) {
                        sumCountArr[_n][_s] =  sumCountArr[_n][_s] + sumCountArr[_n - 1][_s - _maxMinus];
                    }
                }
            }
        }
        int total = (int) Math.pow(6, n);
        for (int s = n - 1; s < 6 * n; s++) {
            System.out.println("sum: " + (s + 1) + ", prob: " + sumCountArr[n - 1][s] + "/" + total);
        }
    }


    // 给定一个 m*n 方阵，方阵的每个格子对应一个正正数，先从左上角出发至右下角，只能向右或者向下走，求途经元素和的最大值
    // 核心思想：走到 i，j 格子时，只可能自第 i-1，j 或者 第 i，j-1 格子来，则去两者最大值
    public int numOfMatrixSum(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int[][] maxSumArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (j == 0) maxSumArr[i][j] = arr[i][j];
                    else maxSumArr[i][j] = maxSumArr[i][j - 1] + arr[i][j];
                } else if (j == 0) {
                    maxSumArr[i][j] = maxSumArr[i - 1][j] + arr[i][j];
                } else {
                    maxSumArr[i][j] = Math.max(maxSumArr[i - 1][j], maxSumArr[i][j - 1]) + arr[i][j];
                }
            }
        }
        return maxSumArr[m - 1][n - 1];
    }


    // 给定一棵二叉搜索树，将其转化为双向链表
    // 要求：不能创建任何新的节点，只能调整指针的指向


    // 设计一个函数，用来序列化和反序列化一棵二叉树


    // 打印字符串所有的排列组合
    public List<String> stringPermutation(String s) {
        List<String> pmt = new ArrayList<>();
        if (s.length() == 1) {
            pmt.add(s);
            return pmt;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                for (String item : stringPermutation(s.substring(i + 1))) pmt.add(s.charAt(i) + item);
            } else if (i == s.length() - 1) {
                for (String item : stringPermutation(s.substring(0, s.length() - 1))) pmt.add(s.charAt(i) + item);
            } else {
                for (String item : stringPermutation(s.substring(0, i) + s.substring(i + 1))) pmt.add(s.charAt(i) + item);
            }
        }
        return pmt;
    }


    // 扑克牌中的顺子
    // 从扑克牌中随机抽 5 张派，判断是不是顺子，J/Q/K 对应 11/12/13，大/小王随意配
    public boolean shunzi(String[] arr) {
        int min = -1;
        int wang = 0;
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            switch (arr[i]) {
                case "J":
                    sum += 11;
                    if (min == -1) min = 11;
                    else if (min > 11) min = 11;
                    break;
                case "Q":
                    sum += 12;
                    if (min == -1) min = 12;
                    else if (min > 12) min = 12;
                    break;
                case "K":
                    sum += 13;
                    if (min == -1) min = 13;
                    else if (min > 13) min = 13;
                    break;
                case "Joker":
                    wang++;
                    break;
                default:
                    int tmp = Integer.parseInt(arr[i]);
                    sum += tmp;
                    if (min == -1) min = tmp;
                    else if (min > tmp) min = tmp;
                    break;
            }
        }
        return (wang == 0 && sum == 5 * min + 10)
                || (wang == 1 && sum == 4 * min + 6)
                || (wang == 2 && sum == 3 * min + 3);
    }


    // 圆圈中最后剩下的数字
    // 0，1，...，n-1 这 n 个数字排成一圈，从数字 0 开始，每次删除第 m 个数字，求最后剩下的数字


    // 不用加减乘除做加法：编写一个函数，求两个正数之和，函数体内不得使用 "+"、"-"、"*"、"/"
    public int twoNumSumWithoutAddOp(int i, int j) {
        return 0;
    }


    // 给定一个数组 A[0,1,...,n-1]，构建一个数组 B[0,1,...,n-1]，使得 B[i]=A[0]*...*A[i-1]*A[i+1]*...*A[n-1]
    // 要求不能使用除法



    public static void main(String[] args) {
        SwordCodeApp app = new SwordCodeApp();
//        ListNode<Integer> root = new ListNode<Integer>(1);
//        ListNode<Integer> _root = root;
//        ListNode<Integer> node1 = new ListNode<Integer>(2);
//        ListNode<Integer> node2 = new ListNode<Integer>(3);
//        ListNode<Integer> node3 = new ListNode<Integer>(4);
//        root.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        System.out.println(root.equals(_root));
        System.out.println();
    }

}
