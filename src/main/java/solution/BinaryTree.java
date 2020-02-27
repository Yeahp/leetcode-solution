package solution;

import data.structure.TreeNode;
import java.util.*;

public class BinaryTree {

    // Q1: the maximum depth of a binary tree
    public int maxDepth(TreeNode<Integer> root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }

    // Q2: the minimum depth of a binary tree
    public int minDepth(TreeNode<Integer> root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return 1 + Math.min(left, right);
    }

    // Q3: the number of nodes of a binary tree
    public int nodeNum(TreeNode<Integer> root) {
        if (root == null) return 0;
        int left = nodeNum(root.left);
        int right = nodeNum(root.right);
        return 1 + left + right;
    }

    // Q4: the number of leaf nodes of a binary
    public int leafNodeNum(TreeNode<Integer> root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = leafNodeNum(root.left);
        int right = leafNodeNum(root.right);
        return left + right;
    }

    // Q5: the number of nodes at k-th level of a binary tree
    public int nodeNumAtKlevel(TreeNode<Integer> root, int k) {
        if (root == null || k < 1) return 0;
        if (k == 1) return 1;
        int left = nodeNumAtKlevel(root.left, k - 1);
        int right = nodeNumAtKlevel(root.right, k - 1);
        return left + right;
    }

    // Q6: judge whether a binary tree is a balanced one
    public boolean isBalanced(TreeNode<Integer> node){
        return maxDepth2(node) != -1;
    }
    private int maxDepth2(TreeNode<Integer> node){
        if(node == null) return 0;
        int left = maxDepth2(node.left);
        int right = maxDepth2(node.right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    // Q7: 两个二叉树是否完全相同
    boolean isSameTree(TreeNode<Integer> t1,TreeNode<Integer> t2){
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }
        if(t1.value.equals(t2.value)) {
            return false;
        }
        boolean left = isSameTree(t1.left, t2.left);
        boolean right = isSameTree(t1.right, t2.right);
        return left && right;
    }

    // Q8: 两个二叉树是否互为镜像
    boolean isMirror(TreeNode<Integer> t1,TreeNode<Integer> t2){
        if (t1 == null || t2 == null) return t1 == null && t2 == null;
        if (!t1.value.equals(t2.value)) return false;
        return isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    }

    // Q9: 二叉树的前序遍历
    // 迭代求解
    public ArrayList<Integer> preOrder(TreeNode<Integer> root){
        Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        stack.push(root);
        while(!stack.empty()){
            TreeNode<Integer> node = stack.pop();
            list.add((Integer)node.value);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }

    // 递归求解
    ArrayList<Integer> preOrderReverse(TreeNode<Integer> root){
        ArrayList<Integer> result = new ArrayList<Integer>();
        preOrder2(root,result);
        return result;
    }
    void preOrder2(TreeNode<Integer> root,ArrayList<Integer> result){
        if(root == null){
            return;
        }
        result.add((Integer)root.value);
        preOrder2(root.left,result);
        preOrder2(root.right,result);
    }

    // Q10: 二叉树的中序遍历
    ArrayList<Integer> inOrder(TreeNode<Integer> root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
        TreeNode<Integer> current = root;
        while(current != null|| !stack.empty()){
            while(current != null){
                stack.add(current);
                current = current.left;
            }
            current = stack.peek();
            stack.pop();
            list.add((Integer) current.value);
            current = current.right;
        }
        return list;
    }

    // Q11: 二叉树的后序遍历
    ArrayList<Integer> postOrder(TreeNode<Integer> root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null){
            return list;
        }
        list.addAll(postOrder(root.left));
        list.addAll(postOrder(root.right));
        list.add((Integer) root.value);
        return list;
    }

    // Q12: judge whether a binary tree is a completed one
    // 完全二叉树：除了非叶子节点外，每个节点都有左右两个孩子，叶子节点只存在于最末层左端
    boolean isComplete(TreeNode<Integer> root){
        if (root == null) return false;
        Queue<TreeNode<Integer>> queue = new java.util.LinkedList<>();
        queue.add(root);
        boolean result = true;
        boolean hasNoChild = false;
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.remove();
            if (hasNoChild) {
                if (current.left != null || current.right != null) {
                    result = false;
                    break;
                }
            } else {
                if (current.left != null && current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                } else if (current.left != null) {
                    queue.add(current.left);
                    hasNoChild = true;
                } else if (current.right != null) {
                    result = false;
                    break;
                } else {
                    hasNoChild = true;
                }
            }
        }
        return result;
    }

    // Q13: 翻转二叉树or镜像二叉树
    public TreeNode<Integer> mirrorTree(TreeNode<Integer> root) {
        if (root == null) return null;
        TreeNode<Integer> left = mirrorTree(root.left);
        TreeNode<Integer> right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // Q14: 求两个二叉树的最低公共祖先节点
    TreeNode<Integer> getLastCommonParent(TreeNode<Integer> root, TreeNode<Integer> t1, TreeNode<Integer> t2){
        if (findNode(root.left, t1)) {
            if (findNode(root.right, t2)) return root;
            else return getLastCommonParent(root.left, t1, t2);
        } else {
            if (findNode(root.left, t2)) return root;
            else return getLastCommonParent(root.right, t1, t2);
        }
    }

    // Q15：查找节点node是否在当前二叉树中
    public boolean findNode(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (root == null || node == null) return false;
        if (root == node) return true;
        return findNode(root.left, node) || findNode(root.right,node);
    }

    // 在没有重复值的二叉树中:
    //   先序和后序遍历无法唯一确定一棵二叉树
    //   先序和中序遍历可以唯一确定二叉树
    //   中序和后序遍历可以唯一确定二叉树
    // Q16: 根据先序、后序遍历构造二叉树
    TreeNode<Integer> buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length) return null;
        return myBuildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    TreeNode<Integer> myBuildTree(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
        if (instart > inend) return null;
        TreeNode<Integer> root = new TreeNode<Integer>(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, preorder[instart]);
        root.left = myBuildTree(inorder, instart,position - 1, preorder, prestart + 1, prestart + position - instart);
        root.right = myBuildTree(inorder, position + 1, inend, preorder,position - inend + preend + 1, preend);
        return root;
    }
    int findPosition(int[] arr, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if(arr[i] == key) return i;
        }
        return -1;
    }

    // Q17: 在二叉树查找树中插入节点
    // BST：
    // 一棵空树，或具有下列性质的二叉树：
    // （1）若左子树不空，则左子树上所有结点的值均小于它的根结点的值
    // （2）若右子树不空，则右子树上所有结点的值均大于它的根结点的值
    // （3）左、右子树也分别为二叉排序树
    // （4）没有键值相等的结点
    TreeNode<Integer> insertNode(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (root == node || root == null) return node;
        TreeNode<Integer> tmp = root;
        TreeNode<Integer> last = null;
        while (tmp != null) {
            last = tmp;
            if (tmp.value > node.value) tmp = tmp.left;
            else tmp = tmp.right;
        }
        if (last.value > node.value) last.left = node;
        else last.right = node;
        return root;
    }
    public TreeNode<Integer> insertNodeS2(TreeNode<Integer> root, TreeNode<Integer> node) {
        if (root == null || root == node) return root;
        TreeNode<Integer> res = root;
        while (true) {
            if (root.value > node.value) {
                if (root.left != null) {
                    root = root.left;
                } else {
                    root.left = node;
                    break;
                }
            } else {
                if (root.right != null) {
                    root = root.right;
                } else {
                    root.right = node;
                    break;
                }
            }
        }
        return res;
    }

    // Q18: 输入一个二叉树和一个整数，打印出二叉树中节点值的和等于输入整数所有的路径
    // 注：一个有效的路径，指的是从根节点到叶节点的路径
    void findPath(TreeNode<Integer> root, int i){
        if (root == null) return;
        Stack<Integer> stack = new Stack<>();
        int currentSum = 0;
        findPath(root, i, stack, currentSum);
    }
    void findPath(TreeNode<Integer> node, int i, Stack<Integer> stack, int currentSum) {
        currentSum += node.value;
        stack.push(node.value);
        if (node.left == null && node.right == null) {
            if (currentSum == i) {
                for (int path : stack) {  // 遍历栈中的元素，而不将元素推出栈
                    System.out.println(path);
                }
            }
        }
        if (node.left != null) {
            findPath(node.left, i, stack, currentSum);
        }
        if (node.right != null) {
            findPath(node.right, i, stack, currentSum);
        }
        stack.pop();
    }

    // Q19: 二叉树的搜索区间
    // 给定两个值 k1 和 k2（k1 < k2）和一个二叉查找树的根节点，找到树中所有值在 k1 到 k2 范围内的节点
    // 即打印所有x (k1 <= x <= k2)，其中 x 是二叉查找树的中的节点值，返回所有升序的节点值
    // 核心思想：中序遍历，判断节点值是否在范围内
    public List<Integer> _searchRange(TreeNode<Integer> root, int k1, int k2) {
        List<Integer> res = new ArrayList<>();
        _searchRangeHelper(root, k1, k2, res);
        return res;
    }
    private void _searchRangeHelper(TreeNode<Integer> node, int k1, int k2, List<Integer> res) {
        if (node == null) return;
        if (node.value > k1) _searchRangeHelper(node.left, k1, k2, res);
        if (node.value >= k1 && node.value <= k2) res.add(node.value);
        if (node.value < k2) _searchRangeHelper(node.right, k1, k2, res);
    }

    // Q21: 二叉树内两个节点的最长距离
    // 二叉树中两个节点的最长距离可能有三种情况：
    // 1.左子树的最大深度+右子树的最大深度为二叉树的最长距离
    // 2.左子树中的最长距离即为二叉树的最长距离
    // 3.右子树种的最长距离即为二叉树的最长距离
    //因此，递归求解即可
    private static class Result {
        int maxDistance;
        int maxDepth;
        public Result() {}
        public Result(int maxDistance, int maxDepth) {
            this.maxDistance = maxDistance;
            this.maxDepth = maxDepth;
        }
    }
    public int getMaxDistance(TreeNode<Integer> root){
        return getMaxDistanceResult(root).maxDistance;
    }
    private Result getMaxDistanceResult(TreeNode<Integer> root) {
        if (root == null) return new Result(0,-1);
        Result lmd = getMaxDistanceResult(root.left);
        Result rmd = getMaxDistanceResult(root.right);
        Result result = new Result();
        result.maxDepth = Math.max(lmd.maxDepth, rmd.maxDepth) + 1;
        result.maxDistance = Math.max(lmd.maxDepth + rmd.maxDepth, Math.max(lmd.maxDistance, rmd.maxDistance));
        return result;
    }

    // Q22: 不同的二叉树
    // 给出 n，问由 1…n 为节点组成的不同的二叉查找树有多少种？
    // 核心思想：f(n)=f(i)*f(n-i-1)，这里的减 1 是用于充当根节点
    int numTrees(int n){
        int[] counts = new int[n+2];
        counts[0] = 1;
        counts[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                counts[i] += counts[j] * counts[i - j - 1];
            }
        }
        return counts[n];
    }

    // Q23: 判断二叉树是否是合法的二叉查找树(BST)
    public int lastVal = Integer.MAX_VALUE;
    public boolean firstNode = true;
    public boolean isValidBST(TreeNode<Integer> root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (!firstNode && lastVal >= root.value) return false;
        firstNode = false;
        lastVal = root.value;
        return isValidBST(root.right);
    }

    // 下面的解法是错误的
    // 一个例子：
    //          20
    //         / \
    //       10  30
    //     /  \  / \
    //   9   29 28  31
    public boolean isValidBSTS2(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left != null) {
            if (root.left.value >= root.value || !isValidBSTS2(root.left)) return false;
        }
        if (root.right != null) {
            return root.right.value > root.value && isValidBSTS2(root.right);
        }
        return true;
    }

}
