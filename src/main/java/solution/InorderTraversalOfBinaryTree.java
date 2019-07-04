package solution;

import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversalOfBinaryTree {

    // inorder traversal of a binary tree
    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<Integer>();
        inorderTraversalImp(root, res);
        return res;
    }

    private void inorderTraversalImp(TreeNode<Integer> node, List<Integer> l) {
        if (node == null) {
            return;
        } else {
            inorderTraversalImp(node.left, l);
            l.add(node.value);
            inorderTraversalImp(node.right, l);
        }
    }

}
