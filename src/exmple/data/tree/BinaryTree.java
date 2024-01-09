package exmple.data.tree;

public interface BinaryTree<E> {

    /**
     * 判断为空树
     * @return
     */
    boolean isEmpty();

    /**
     * 树的元素个数
     * @return
     */
    int size();

    /**
     * 树的高度
     * @return
     */
    int getHeight();

    TreeNode<E> findKey(E e);

    /**
     * 前序遍历（ 根节点 左子树 右子树） 通过递归方式
     */
    void preOrderTraverse();
    /**
     * 中序遍历 （左 根 右） 通过递归方式
     */
    void inOrderTraverse();

    /**
     * 后序遍历 （左 右 根） 通过递归方式
     */
    void postOrderTraverse();

    void preOrderTraverse(TreeNode<E> node);
    void inOrderTraverse(TreeNode<E> node);
    void postOrderTraverse(TreeNode<E> node);

    /**
     * 非递归方式的前序遍历 根左右
     */
    void preOrderByStack();
    /**
     * 非递归方式的中序遍历 左根右
     */
    void inOrderByStack();
    /**
     * 非递归方式的后序遍历 左右根
     */
    void postOrderByStack();

    /**
     * 按照层次遍历树
     */
    void levelOrderByQueue();

    /**
     * 添加元素
     */
    void add(E e);

    /**
     * 删除元素
     */
    TreeNode<E> remove(E e);
}
