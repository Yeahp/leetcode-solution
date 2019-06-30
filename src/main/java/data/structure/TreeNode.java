package data.structure;

public class TreeNode<E> {

    public E value;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E value) {
        this.value = value;
    }

    public TreeNode() {}

}
