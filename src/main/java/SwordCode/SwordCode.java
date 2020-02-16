package SwordCode;

import data.structure.ListNode;
import data.structure.TreeNode;

import java.util.*;

public class SwordCode {

    // 找出数组中重复的数字: 数组长度为n，元素范围 0 ~ n-1，有重复数字，但不知重复几次。找出其中重复的数字
    // S1：新建 map，元素值 -> 是否出现
    // S2：
    public static Set<Integer> duplicateNumberS2(int[] arr) {
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
    public static String replaceSpace(String s) {
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
    public static void printLinkedListS1(ListNode<Integer> head) {
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
    public static void printLinkedListS2(ListNode<Integer> head) {
        if (head != null) {
            if (head.next != null) {
                printLinkedListS2(head.next);
            }
            System.out.println(head.value);
        }
    }


    // 求斐波那契数列第 n 项
    // S1：伪递归 -- F(n) = F(n-1) + F(n-2)
    public static int fibonacciS1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        return fibonacciS1(n - 1) + fibonacciS1(n - 2);
    }
    // S2：移动求和
    public static int fibonacciS2(int n) {
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
    public static int upstairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return upstairs(n - 1) + upstairs(n - 2);  // 也是斐波那契数列
    }
    // 若一次可上 1 ~ n-1 级
    // 当 n=1 时，共 1 种走法， 1 = 2^(1-1)
    // 当 n=2 时，共 2 种走法， 2 = 2^(2-1)
    // 假设当 n=k 时，共 2^(k-1) 种走法
    // 当 n=k+1 时，共 2 * 2^(k-2) + 2^(k-1) = 2 * 2^(k-1) = 2^k 种走法

    // 顺时针打印数组
    public static void printArrayClockwise(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        _printArrayClockwise(arr, 0, 0, row - 1, col - 1);
    }
    private static void _printArrayClockwise(int[][] arr, int startRow, int startCol, int endRow, int endCol) {
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


    // 判断一个字符数组，是否存在一条包含给定字符串所有字符的路径
    public static boolean arrayPathContainString(char[][] arr, int row, int col, String s) {
        //if (s == null || s.length() ==0) return true;
        //if (arr == null) return false;
        return _firstElementSelect(arr, 0, 0, row, col, s);
    }
    private static boolean _firstElementSelect(char[][] arr, int startRow, int startCol, int row, int col, String s) {
        if (startRow >= row || startCol >= col) return false;
        if (arr[startRow][startCol] == s.charAt(0)) return _arrayPathCheck(arr,startRow, startCol, row, col, s);
        return _firstElementSelect(arr, startRow + 1, startCol, row, col, s)
                || _firstElementSelect(arr, startRow, startCol + 1, row, col, s);
    }
    private static boolean _arrayPathCheck(char[][] arr, int startRow, int startCol, int row, int col, String subStr) {
        if (subStr.length() > 0) {
            if (startRow >= row || startCol >= col) return false;
            if (subStr.length() == 1) return arr[startRow][startCol] == subStr.charAt(0);
            return arr[startRow][startCol] == subStr.charAt(0)
                    && _arrayPathCheck(arr, startRow + 1, startCol, row, col, subStr.substring(1))
                    && _arrayPathCheck(arr, startRow, startCol + 1, row, col, subStr.substring(1));
        }
        return true;
    }


    // 有 m*n 方格，开始时机器人坐标 (0,0)，每次左、右、上、下移动
    // 但不能进入行坐标和列坐标和大于 k 的格子
    // 例如：当 k=18，机器人可以进入 (35,37)， 因为 3+5+3+7=18
    // 不能进入 (35,38)，因为 3+5+3+8=19>18



    // 给定长度为 n 的绳子，截成 m 段，求长度乘积最大值
    public static int lineProduct(int n, int m) {
        if (n % m == 0) {
            return (int) Math.pow(n * 1.0 / m, m);
        } else {
            int avg = n / m;
            return (int) Math.pow(1.0 * avg, m - 1) * (avg + n % m);
        }
    }


    // 输入一棵二叉树 A，返回 A 的镜像
    public static TreeNode<Integer> mirrorTree(TreeNode<Integer> root) {
        mirrorTree(root);
        return root;
    }
    private static void _mirrorTree(TreeNode<Integer> root) {
        if (root != null) {
            TreeNode<Integer> tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            _mirrorTree(root.left);
            _mirrorTree(root.right);
        }
    }


    // 输入一棵二叉树 A，判断 A 是否对称
    public static boolean isSymmetryTree(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left == null && root.right != null) return false;
        if (root.left != null && root.right == null) return false;
        return isSymmetry(root.left, root.right);
    }
    private static boolean isSymmetry(TreeNode<Integer> node1, TreeNode<Integer> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null) return false;
        if (node2 == null) return false;
        return node1.value.equals(node2.value)
                && isSymmetry(node1.left, node2.right)
                && isSymmetry(node1.right, node2.left);
    }


    // 输入 2 棵二叉树 A 和 B，判断 B 是不是 A 的子树
    public static boolean isSubTree(TreeNode<Integer> A, TreeNode<Integer> B) {
        if (A == null && B == null) return true;
        if (A == null) return false;
        if (B == null) return false;
        if (A.value.equals(B.value) && _isSubTree(A, B)) return true;
        return isSymmetry(A.left, B) || isSymmetry(A.right, B);
    }
    private static boolean _isSubTree(TreeNode<Integer> A, TreeNode<Integer> B) {
        if (A == null && B == null) return true;
        if (A == null) return false;
        if (B == null) return false;
        return A.value.equals(B.value)
                && _isSubTree(A.left, B.left)
                && _isSubTree(A.right, B.right);
    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
        SwordCode.printArrayClockwise(arr);
    }

}
