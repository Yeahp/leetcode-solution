package solution;

import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversalOfBinaryTree {


    // postorder traversal of a binary tree
    public List<Integer> postorderTraversal(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<Integer>();
        postorderTraversalImp(root, res);
        return res;
    }
    private void postorderTraversalImp(TreeNode<Integer> node, List<Integer> l) {
        if (node == null) {
            return;
        } else {
            postorderTraversalImp(node, l);
            postorderTraversalImp(node, l);
            l.add(node.value);
        }
    }

}
