package solution;

import data.structure.TreeNode;

public class BinaryTreeEnhanced {

    /*
    * 0 概述
    *   二叉树相关问题，主要分为：判断类、构建类、存储类、查找类、距离类、混合类这6类大问题
    */
    // 1 判断类问题：判断二叉树是否是二叉搜索树、二叉完全树，以及两棵二叉树是否同构这3个问题
    // 1.1 判断一棵二叉树是否是二叉搜索树(BST)
    //  一种错误解法：假定当前结点值为 k，对于二叉树中每个结点，判断其左孩子的值是否小于 k，其右孩子的值是否大于 k。如果所有结点都满足该条件，则该二叉树是一棵二叉搜索树
    //  上面的错误是因为判断不完整导致，可以这样来判断：
    //  判断结点左子树最大值是否大于等于结点的值，如果是，则该二叉树不是二叉搜索树，否则继续下一步判断
    //  判断右子树最小值是否小于或等于结点的值，如果是，则不是二叉搜索树，否则继续下一步判断

    // 解1：递归判断左右子树是否是二叉搜索树，bstMax 和 bstMin 分别返回二叉树中的最大值和最小值结点
    public boolean isBSTUnefficient(TreeNode<Integer> root) {
        if (root == null) return true;
        if (root.left != null && bstMax(root.left).value >= root.value) return false;
        if (root.right != null && bstMin(root.right).value < root.value) return false;
        return isBSTUnefficient(root.left) && isBSTUnefficient(root.right);
    }
    private TreeNode<Integer> bstMax(TreeNode<Integer> node) {
        if (node == null) return null;
        return bstMaxHelper(node, node);
    }
    private TreeNode<Integer> bstMaxHelper(TreeNode<Integer> node, TreeNode<Integer> max) {
        if (node == null) return max;
        if (node.value < max.value) max = node;
        TreeNode<Integer> leftMax = bstMaxHelper(node.left, max);
        TreeNode<Integer> rightMax = bstMaxHelper(node.right, max);
        return leftMax.value > rightMax.value ? leftMax : rightMax;
    }
    private TreeNode<Integer> bstMin(TreeNode<Integer> node) {
        if (node == null) return null;
        return bstMinHelper(node, node);
    }
    private TreeNode<Integer> bstMinHelper(TreeNode<Integer> node, TreeNode<Integer> min) {
        if (node == null) return min;
        if (node.value > min.value) min = node;
        TreeNode<Integer> leftMin = bstMaxHelper(node.left, min);
        TreeNode<Integer> rightMin = bstMaxHelper(node.right, min);
        return leftMin.value < rightMin.value ? leftMin : rightMin;
    }

    // 解2：一次遍历法
    // 以前面提到的 binary tree(1) 为例，当我们遍历到结点 15 时，我们知道右子树结点值肯定都 >=10。当我们遍历到结点 15 的左孩子结点 6 时，我们知道结点 15 的左子树结点值都必须在 10 到 15 之间。显然，结点 6 不符合条件，因此它不是一棵二叉搜索树。
    public boolean isBSTEfficient(TreeNode<Integer> root, TreeNode<Integer> left, TreeNode<Integer> right) {
        if (root == null) return true;
        if (left != null && root.value <= left.value) return false;
        if (right != null && root.value > right.value) return false;
        return isBSTEfficient(root.left, left, root) && isBSTEfficient(root.right, root, right);
    }

    // 解3：中序遍历解法
    // 中序遍历来判断BST，将中序遍历的结果存到一个辅助数组，然后判断数组是否有序即可判断是否是BST
    // 也可以不用辅助数组，在遍历时通过保留前一个指针 prev，据此来实现判断BST的解法，初始时 prev = NULL
    public boolean isBSTInOrder(TreeNode<Integer> root, TreeNode<Integer> prev) {
        if (root == null) return true;
        if (!isBSTInOrder(root.left, prev)) return false;
        if (prev != null && root.value < prev.value) return false;
        return isBSTInOrder(root.right, root);
    }


    // 1.2 判断二叉树是否是完全二叉树
    // 解1：常规解法-中序遍历
    // 定义满结点：即一个结点存在左右孩子结点，则该结点为满结点。在代码中定义变量 flag 来标识是否发现非满结点，为1表示该二叉树存在非满结点。完全二叉树如果存在非满结点，则根据层序遍历队列中剩下结点必须是叶子结点，且如果一个结点的左孩子为空，则右孩子结点也必须为空。
    // 解2：更简单的方法-判断结点序号法
    // 更简单的方法是判断结点序号法，因为完全二叉树的结点序号都是有规律的，如结点 i 的左右子结点序号为 2i+1 和 2i+2，如根结点序号是 0，它的左右子结点序号是 1 和 2(如果都存在的话)
    // 我们可以计算二叉树的结点数目，然后依次判断所有结点的序号，如果不是完全二叉树，那肯定会存在结点它的序号大于等于结点数目的。如前面提到的 binary tree(1) 就不是完全二叉树
    // int isCompleteBTIndexMethod(BTNode *root, int index, int nodeCount){    if (!root) return 1;    if (index >= nodeCount)        return 0;    return (isCompleteBTIndexMethod(root->left, 2*index+1, nodeCount) &&            isCompleteBTIndexMethod(root->right, 2*index+2, nodeCount));}


    // 1.3 判断平衡二叉树
    // 解1：自顶向下方法
    // 判断一棵二叉树是否是平衡的，对每个结点计算左右子树高度差是否大于1即可，时间复杂度为O(N^2)
    // 解2：自底向上方法
    // 因为解1会重复的遍历很多结点，为此我们可以采用类似后序遍历的方式，自底向上来判断左右子树的高度差，这样时间复杂度为 O(N)
    int isBalanceBTDown2Top(BTNode *root, int *height){    if (!root) {        *height = 0;        return 1;    }    int leftHeight, rightHeight;    if (isBalanceBTDown2Top(root->left, &leftHeight) &&        isBalanceBTDown2Top(root->right, &rightHeight)) {        int diff = abs(leftHeight - rightHeight);        return diff > 1 ? 0 : 1;    }    return 0;}
1.4 判断两棵二叉树是否同构

}
