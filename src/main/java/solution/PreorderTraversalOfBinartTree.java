package solution;

import data.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversalOfBinartTree {

    // preorder traversal of a binart tree
    public List<Integer> preorderTraversal(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<Integer>();
        preorderTraversalImp(root, res);
        return res;
    }
    private void preorderTraversalImp(TreeNode<Integer> node, List<Integer> l) {
        if (node == null) {
            return;
        } else {
            l.add(node.value);
            preorderTraversalImp(node.left, l);
            preorderTraversalImp(node.right, l);
        }
    }

}
