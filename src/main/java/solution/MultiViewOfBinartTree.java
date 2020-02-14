package solution;

import data.structure.TreeNode;

public class MultiViewOfBinartTree {

    // 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
    // 示例:
    // 输入: [1,2,3,null,5,null,4]
    // 输出: [1, 3, 4]
    // 解释:
    //     1            <---
    //   /   \
    //  2     3         <---
    //   \     \
    //    5     4       <---

    // 右视图
    // 核心细想：
    public static void rightViewOfBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            System.out.println(root.value);
            if (root.right != null) rightViewOfBinaryTree(root.right);
            else if (root.left != null) rightViewOfBinaryTree(root.left);
        }
    }


    // 左视图
    // 核心细想：只看左节点
    public static void leftViewOfBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            System.out.println(root.value);
            if (root.left != null) leftViewOfBinaryTree(root.left);
            else if (root.right != null) leftViewOfBinaryTree(root.right);
        }
    }


    // 俯视图
    // 核心思想：左子树只看左边，右子树只看右边
    public static void upperViewOfBinaryTree(TreeNode<Integer> root) {
        upperLeftViewOfBinaryTree(root);
        upperRightViewOfBinaryTree(root.right);
    }
    public static void upperLeftViewOfBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            upperLeftViewOfBinaryTree(root.left);
            System.out.println(root.value);
        }
    }
    public static void upperRightViewOfBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            System.out.println(root.value);
            upperLeftViewOfBinaryTree(root.right);
        }
    }


    // 上视图
    // 核心思想：只看最后两层节点，中序遍历
    //
    public static void bottomViewOfBinaryTree(TreeNode<Integer> root) {
        if (root != null) {
            if (root.left != null && (root.left.left == null || root.left.right == null)) {
                System.out.println(root.left.value);
                System.out.println(root);
            } else {
                bottomViewOfBinaryTree(root.left);
            }
            if (root.right != null && (root.right.left == null || root.right.right == null)) {
                System.out.println(root.right.value);
                System.out.println(root);
            } else {
                bottomViewOfBinaryTree(root.right);
            }
        }
    }

}
