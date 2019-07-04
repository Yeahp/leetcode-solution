package solution;

import data.structure.TreeNode;

public class SymmetricalBinaryTree {

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

}
