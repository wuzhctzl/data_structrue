package exmple.data.tree;

/**
 * 二叉树的节点类
 * @param <E>
 */
public class TreeNode<E> {
    private E e;
    TreeNode left;
    TreeNode right;

    public TreeNode(E e, TreeNode left, TreeNode right) {
        this.e = e;
        this.left = left;
        this.right = right;
    }

    public TreeNode(E e) {
        this.e = e;
    }

    public TreeNode() {
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "e=" + e +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
