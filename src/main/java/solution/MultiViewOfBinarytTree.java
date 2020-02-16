package solution;

import data.structure.TreeNode;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

public class MultiViewOfBinarytTree {

    // 右视图
    // 核心思想：用栈层次地存储节点，每次取出最上方元素
    public static void rightViewOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) return;
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode<Integer> tmp = q.peek();
                list.add(tmp.value);
                q.poll();
                if (tmp.right != null) q.add(tmp.right);
                if (tmp.left != null) q.add(tmp.left);
                count--;
            }
            System.out.println(list.get(0));
        }
    }


    // 左视图
    // 核心细想：与右视图类似
    public static void leftViewOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) return;
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode<Integer> tmp = q.peek();
                list.add(tmp.value);
                q.poll();
                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
                count--;
            }
            System.out.println(list.get(0));
        }
    }


    // 二叉树俯视图
    // 核心思想：
    // （1） 分别记录当前层之前的节点向左/向右延伸的最大距离
    // （2） 比较当前节点与左/右最大延伸距离，若超出延伸距离的范围，则先考察左子树、再打印当前节点值、最后考察右子树
    public static void upperViewOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) return;
        int left = -1;  // 用于标记节点往左边伸出的当前最大长度，初始化为 -1，意味着根节点一定会被打印出来
        int right = -1;  // 用于标记节点往右边伸出的当前最大长度，初始化为 -1，意味着根节点一定会被打印出来
        Queue<Pair<TreeNode<Integer>, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(root, 0));  // 标记当前节点伸出的长度为 0
        Map<Integer, Integer> res = new TreeMap<>();  // 存储左/右延伸距离到节点值的映射
        while (!q.isEmpty()) {
            int count = q.size();
            int _left = 0;
            int _right = 0;
            while (count > 0) {
                Pair<TreeNode<Integer>, Integer> tmp = q.peek();
                q.poll();
                TreeNode<Integer> tmpNode = tmp.getKey();
                Integer tmpNodeLoc = tmp.getValue();
                if (tmpNode.left != null) q.add(new Pair<>(tmpNode.left, tmpNodeLoc - 1));
                if (tmpNodeLoc < left) {
                    _left = Math.min(tmpNodeLoc, left);
                    res.put(tmpNodeLoc, tmpNode.value);
                } else if (tmpNodeLoc > right) {
                    _right = Math.max(tmpNodeLoc, right);
                    res.put(tmpNodeLoc, tmpNode.value);
                }
                if (tmpNode.right != null) q.add(new Pair<>(tmpNode.right, tmpNodeLoc + 1));
                count--;
            }
            left = _left;
            right = _right;
        }
        for (Integer item : res.values()) {
            System.out.println(item);
        }
    }


    // 仰视图
    // 核心思想：
    // （1）用栈一次存储每层节点的值，及其向左/右延伸的距离
    // （2）让元素依次出栈，上层元素延伸距离与下层元素延伸距离相同，则只打印
    public static void bottomViewOfBinaryTree(TreeNode<Integer> root) {
        if (root == null) return;
        Queue<Triple<TreeNode<Integer>, Integer, Integer>> q = new LinkedList<>();  // 第一个元素存储节点，第二个元素存储节点层级，第三个元素存储左/右延伸距离
        Stack<Queue<Triple<Integer, Integer, Integer>>> sq = new Stack<>();
        q.add(new ImmutableTriple<>(root, 1, 0));
        while (!q.isEmpty()) {
            int count = q.size();
            Queue<Triple<Integer, Integer, Integer>> tmpq = new LinkedList<>();
            while (count > 0) {
                Triple<TreeNode<Integer>, Integer, Integer> tmp = q.peek();
                q.poll();
                tmpq.add(new ImmutableTriple<>(tmp.getLeft().value, tmp.getMiddle(), tmp.getRight()));
                if (tmp.getLeft().left != null) q.add(new ImmutableTriple<>(tmp.getLeft().left, tmp.getMiddle() + 1, tmp.getRight() - 1));
                if (tmp.getLeft().right != null) q.add(new ImmutableTriple<>(tmp.getLeft().right, tmp.getMiddle() + 1, tmp.getRight() + 1));
                count--;
            }
            sq.add(tmpq);
        }

        Map<Integer, Integer> stretch2level = new HashMap<>();  // 存储 左/右延伸距离至层级的映射
        Map<Integer, List<Integer>> stretch2value = new TreeMap<>();  // 存储 左/右延伸距离至节点值的映射
        while (!sq.empty()) {
            Queue<Triple<Integer, Integer, Integer>> tmpq = sq.peek();
            sq.pop();
            while (!tmpq.isEmpty()) {
                Triple<Integer, Integer, Integer> tmp = tmpq.peek();
                tmpq.poll();
                if (stretch2level.get(tmp.getRight()) == null) {
                    stretch2level.put(tmp.getRight(), tmp.getMiddle());
                    List<Integer> l = new ArrayList<>();
                    l.add(tmp.getLeft());
                    stretch2value.put(tmp.getRight(), l);
                } else if(tmp.getMiddle().intValue() == stretch2level.get(tmp.getRight()).intValue()) {
                    stretch2value.get(tmp.getRight()).add(tmp.getLeft());
                }
            }
        }
        for (List<Integer> values : stretch2value.values()) {
            for (Integer value : values) {
                System.out.println(value);
            }
        }
    }


    public static void main(String[] args) {
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
        MultiViewOfBinarytTree.bottomViewOfBinaryTree(root);
    }

}
