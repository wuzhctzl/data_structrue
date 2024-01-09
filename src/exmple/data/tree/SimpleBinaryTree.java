package exmple.data.tree;

import java.util.*;

public class SimpleBinaryTree<E> implements BinaryTree<E>{

    private int size;
    private TreeNode<E> root;

    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * 根节点构建
     * @param root
     */
    public SimpleBinaryTree(TreeNode<E> root) {
        this.root = root;
    }

    public SimpleBinaryTree() {

    }

    /**
     * 前序和中序遍历集合构建树
     * @param pre
     * @param in
     */
    public SimpleBinaryTree(List<E> pre,List<E> in) {
        if(in!=null&&pre!=null&&in.size()==pre.size()&&pre.size()>0){
            createTree(pre,in);
            size = pre.size();
        }
    }

    /**
     * 根据某特定树的前序遍历集合和中序遍历集合构建二叉树
     * 本创建方法只考虑 给定集合确实满足特定树的前序和中序集合关系的情况
     * 不考虑树中有重复元素的情况
     * @param pre 树的前序集合
     * @param in 树的后序集合
     */
    private void createTree(List<E> pre, List<E> in) {
        HashSet<E> spre = new HashSet<>(pre);
        HashSet<E> sin = new HashSet<>(in);
        if(spre.size()<pre.size()||sin.size()<in.size()){
            root = null;
        }else{
            root = new TreeNode<>(pre.get(0));
            if(!createTree(pre,in,root)){
                root = null;
            }
        }
    }
    private boolean createTree(List<E> pre, List<E> in,TreeNode<E> node) {
        if(pre.size()<=1){
            if(pre.equals(in))
                return true;
            else{
                System.err.println("传入的两个集合不满足树的前序和中序关系,不满足关系的元素是："
                        +pre.get(0)+" "+in.get(0));
                return false;
            }
        }else{
            //根据前序遍历的集合找到根节点元素
            E e = pre.get(0);
//            node.setE();
            //根据找到的根节点元素在中序遍历中找到其对应的位置
            int index = in.indexOf(e);
            if(index==-1){
                System.err.println("传入的两个集合不满足树的前序和中序关系，原因为传入的前序遍历的结点 "+
                        e+" 在中序集合没有匹配到");
                return false;
            }
            //然后根据下标将这两个list拆分成左子树的前序、中序遍历集合和右子树的前序、中序遍历集合
            List<E> leftPre = pre.subList(1,index+1);
            List<E> rightPre = pre.subList(index+1,pre.size());
            List<E> leftIn = in.subList(0,index);
            List<E> rightIn = in.subList(index+1,in.size());
            boolean lFlag = true;
            boolean rFlag = true;
            //然后在相应的左右子树的前序集合中找到根节点的左右子树
            if(leftPre!=null&&leftPre.size()>0){
                node.left = new TreeNode<>(leftPre.get(0));
                lFlag = createTree(leftPre,leftIn,node.left);
            }
            if(rightPre!=null&&rightPre.size()>0){
                node.right = new TreeNode<>(rightPre.get(0));
                rFlag = createTree(rightPre,rightIn,node.right);
            }
            return lFlag&&rFlag;
        }
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * 获取某个结点的高度
     * @param node
     * @return
     */
    private int getHeight(TreeNode<E> node){
        if(node == null){
            return 0;
        }else{
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            return leftHeight>=rightHeight ? leftHeight+1 : rightHeight+1;
        }
    }

    /**
     * 递归查找
     * @param e
     * @return
     */
    @Override
    public TreeNode<E> findKey(E e) {
        if(e==null){
            System.err.println("不能查找空元素");
            return null;
        }
        TreeNode<E> node = findKey(e, root);
        if(node == null){
            System.out.println("树中没有要查找的元素 "+e
                    +" ，可通过前、中、后或层次遍历查看树中包含的所有元素！");
        }
        return node;
    }

    private TreeNode<E> findKey(E e,TreeNode<E> node){
        if(node==null||node.getE().equals(e)){
            return node;
        }else {
            TreeNode left = findKey(e, node.left);
            TreeNode right = findKey(e, node.right);
            if(left!=null&&e.equals(left.getE())){
                return left;
            }else if(right!=null&&e.equals(right.getE())){
                return right;
            }else{
                return null;
            }
        }
    }

    /**
     * 根据元素查找父结点
     * @param e
     * @return
     */
    public TreeNode<E> findParent(E e){
        TreeNode<E> parent = findParent(e, root);
        System.out.println("找到的父结点是："+parent);
        return parent;
    }
    private TreeNode<E> findParent(E e,TreeNode<E> node){
        if(isParent(e,node)){
            return node;
        }else {
            if(node==null){
                return null;
            }
            TreeNode left = findParent(e, node.left);
            TreeNode right = findParent(e, node.right);
            if (isParent(e,left)) {
                return left;
            } else if (isParent(e,right)) {
                return right;
            } else {
                return null;
            }
        }
    }


    /**
     * 判断结点是否是给定元素的父结点
     * @param e
     * @param node
     * @return
     */
    private boolean isParent(E e,TreeNode<E> node){
        //结点为空，或者结点没有左右孩子返回空
        if(node==null){
            return false;
        }
        //结点有孩子，判断孩子结点的值是传进来的值
        if((node.left!=null&&e.equals(node.left.getE()))
                ||(node.right!=null&&e.equals(node.right.getE()))){
            return true;
        }
        return false;
    }
    /**
     * 判断结点是否是给定给定结点的父结点
     * @param origin
     * @param parent
     * @return
     */
    private boolean isParent(TreeNode<E> origin,TreeNode<E> parent){
        //结点为空，或者结点没有左右孩子返回空
        if(origin==null||parent==null){
            return false;
        }
        //结点有孩子，判断孩子结点的值是传进来的值
        if(parent.left==origin||parent.right==origin) {
            return true;
        }
        return false;
    }
    @Override
    public void preOrderTraverse() {
        System.out.println("前序遍历");
        preOrderTraverse(root);
        System.out.println();
    }

    @Override
    public void inOrderTraverse() {
        System.out.println("中序遍历");
        inOrderTraverse(root);
        System.out.println();
    }

    @Override
    public void postOrderTraverse() {
        System.out.println("后序遍历");
        postOrderTraverse(root);
        System.out.println();
    }

    /**
     * 前序遍历
     * @param node
     */
    @Override
    public void preOrderTraverse(TreeNode<E> node) {
        //先根,再左子树,再右子树
        if(node==null){
            return ;
        }else{
            System.out.print(node.getE()+" ");
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    @Override
    public void inOrderTraverse(TreeNode<E> node) {
        //先左子树,再根,再右子树
        if(node==null){
            return ;
        }else{
            inOrderTraverse(node.left);
            System.out.print(node.getE()+" ");
            inOrderTraverse(node.right);
        }
    }

    @Override
    public void postOrderTraverse(TreeNode<E> node) {
        //先左子树,再右子树,再根
        if(node==null){
            return ;
        }else{
            postOrderTraverse(node.left);
            postOrderTraverse(node.right);
            System.out.print(node.getE()+" ");
        }
    }

    @Override
    public void preOrderByStack() {
        orderByStack("pre");
    }

    private void orderByStack(String flag) {
        String outPart1 = "非递归";
        String outPart2 = "遍历";
        String out = "";
        boolean postFlag = false;
        if("pre".equals(flag)){
            out = outPart1+"前序"+outPart2;
        }else if("in".equals(flag)){
            out = outPart1+"中序"+outPart2;
        }else{
            out = outPart1+"后序"+outPart2;
            postFlag = true;
        }
        System.out.println(out);
        //先根,再左子树,再右子树
        if(root==null){
            System.out.println("二叉树为空");
            return;
        }
        //LinkedList实现了Deque接口，可作为栈来使用
        Deque<TreeNode<E>> deque = new LinkedList<>();
        Deque<E> edq = null ;
        if(postFlag){
            edq = new LinkedList<>();
        }
        TreeNode<E> curr = root;
        while(curr!=null || !deque.isEmpty()){
            //先将根结点添加到栈中，再向栈中循环添加左子树
            while(curr!=null){
                if("pre".equals(flag)){
                    //前序遍历先输出根节点（左子树再作为后续子树的根输出）
                    System.out.print(curr.getE()+" ");
                }
                if(postFlag){
                    //后序遍历 是左右根，而这里采用的根右左的顺序，所以再压入栈中，最后弹出即可
                    edq.push(curr.getE());
                }
                deque.push(curr);
                if(!postFlag){
                    curr = curr.left;
                }else{
                    curr = curr.right;
                }
            }
            //当循环结束，说明当前结点没有左(右)子树了，那么当前结点可以作为最下子树的根结点弹出
            //然后再将其右（左）子树作为当前结点循环上述过程。
            if(!deque.isEmpty()){
                TreeNode<E> pop = deque.pop();
                if("in".equals(flag)){
                    //中序遍历先输出左子树（左子树再作为后续子树的根输出）
                    System.out.print(pop.getE()+" ");
                }
                if(postFlag){
                    curr = pop.left;
                }else{
                    curr = pop.right;
                }
            }
        }
        if(postFlag){
            while(edq.size()>0){
                System.out.print(edq.pop()+" ");
            }
        }
        System.out.println();
    }

    @Override
    public void inOrderByStack() {
//        System.out.println("非递归中序遍历");
//        if(root==null){
//            System.out.println("二叉树为空");
//            return;
//        }
//        //LinkedList实现了Deque接口，可作为栈来使用
//        Deque<TreeNode<E>> deque = new LinkedList<>();
//        TreeNode<E> curr = root;
//        while(curr!=null || !deque.isEmpty()){
//            //先将根结点添加到栈中，再向栈中循环添加左子树
//            while(curr!=null){
//                deque.push(curr);
//                curr = curr.left;
//            }
//            //当循环结束，说明当前结点没有左子树了，那么当前结点可以作为最下子树的根结点弹出
//            //然后再将其右子树作为当前结点循环上述过程。
//            if(!deque.isEmpty()){
//                TreeNode<E> pop = deque.pop();
//                System.out.print(pop.getE()+" ");
//                curr = pop.right;
//            }
//        }
//        System.out.println();
        orderByStack("in");
    }

    @Override
    public void postOrderByStack() {
//        System.out.println("非递归后序遍历");
//        //再左子树,再右子树，再根
//        //这里可参考上面前序非递归遍历的思想，只是顺序变为栈操作根右子树，这样那么输出的顺序就是 根右左
//        //所以只要将输出的结果压入栈中，最后再弹出即可 得到 左子树、右子树、根的顺序
//        if(root==null){
//            System.out.println("二叉树为空");
//            return;
//        }
//        //LinkedList实现了Deque接口，可作为栈来使用
//        Deque<TreeNode<E>> deque = new LinkedList<>();
//        //用队列存放根右左 顺序遍历的元素
//        Deque<E> edq = new LinkedList<>();
//        TreeNode<E> curr = root;
//        while(curr!=null || !deque.isEmpty()){
//            //先将根结点添加到栈中，再向栈中循环添加右子树
//            while(curr!=null){
//                edq.push(curr.getE());
//                deque.push(curr);
//                curr = curr.right;
//            }
//            //当循环结束，说明当前结点没有右子树了，那么当前结点可以作为最下子树的根结点弹出
//            //然后再将其左子树作为当前结点循环上述过程。
//            if(!deque.isEmpty()){
//                TreeNode<E> pop = deque.pop();
//                curr = pop.left;
//            }
//        }
//        while(edq.size()>0){
//            System.out.print(edq.pop()+" ");
//        }
//        System.out.println();
        orderByStack("post");
    }

    @Override
    public void levelOrderByQueue() {
        System.out.println("按层遍历");
        if(root==null){
            System.out.println("二叉树为空");
            return;
        }
        //LinkedList实现了Queue，可以作为队列使用
        Queue<TreeNode<E>> queue = new LinkedList<>();
        //先将根节点添加到队列中
        queue.add(root);
        //如果
        while(!queue.isEmpty()){
            TreeNode<E> poll = queue.poll();
            System.out.print(poll.getE()+" ");
            if(poll.left!=null){
                queue.add(poll.left);
            }
            if(poll.right!=null){
                queue.add(poll.right);
            }
        }
        System.out.println();
    }

    /**
     * 添加元素的算法
     * @param e
     */
    @Override
    public void add(E e) {
        add(root,e);
    }

    /**
     * 往指定结点上添加元素，如果该节点左右子树都存在元素则递归向其子树添加元素
     * @param node
     * @param e
     */
    private void add(TreeNode<E> node,E e){
        if(node==null){
            root = new TreeNode<>(e);
            size++;
            return;
        }else{
            //随机向左右子树添加元素
            TreeNode<E> temp ;
            int random = random();
            if(random ==0){
                temp = node.left;
            }else{
                temp = node.right;
            }
            //如果该子树为空，则向该子树添加元素，否则递归该结点的子树
           if(temp==null){
               if(random ==0){
                   node.left = new TreeNode<E>(e);
               }else{
                   node.right = new TreeNode<E>(e);
               }
               size++;
           }else{
               add(temp,e);
           }
        }
    }
    /**
     * 往指定结点上添加元素，如果该节点左右子树都存在元素则递归向其子树添加元素
     * @param reMake 要重构的树
     * @param node 要添加到 重构树reMake 的 添加结点。
     */
    private void add(TreeNode<E> reMake,TreeNode<E> node){
        if(reMake==null){
            System.out.println();
            return;
        }else{
            //随机向左右子树添加元素
            TreeNode<E> temp ;
            int random = random();
            if(random ==0){
                temp = reMake.left;
            }else{
                temp = reMake.right;
            }
            //如果该子树为空，则向该子树添加元素，否则递归该结点的子树
           if(temp==null){
               if(random ==0){
                   reMake.left = node;
               }else{
                   reMake.right = node;
               }
           }else{
               add(temp,node);
           }
        }
    }
    private int random(){
        return new Random().nextInt(2);
    }
    @Override
    public TreeNode<E> remove(E e) {
        if(root==null){
            System.err.println("树为空");
            return null;
        }
        TreeNode<E> temp = null;
        //如果要删除的是根结点，那么删除根结点并重构树赋值给根结点。
        if(e.equals(root.getE())){
            temp = root;
            root = removeAndReMakeTree(root);
            size--;
            return temp;
        }
        //如果不是根节点，先找到其父结点
        TreeNode<E> parent = findParent(e);

        if(parent.left!=null&&e.equals(parent.left.getE())){
            temp = parent.left;
            parent.left = removeAndReMakeTree(parent.left);
        }else{
            temp = parent.right;
            parent.right = removeAndReMakeTree(parent.right);
        }
        size--;
        return temp;
    }

    /**
     * 根据删除的结点重新构建子树
     * @param remove
     * @return 返回重新构建的子树
     */
    private TreeNode<E> removeAndReMakeTree(TreeNode<E> remove) {
        if(remove==null){
            System.err.println("传入结点为空，不能重建树");
            return null;
        }else if(remove.left!=null){
            add(remove.left,remove.right);
            return remove.left;
        }else if(remove.right!=null){
            return remove.right;
        }else{
            //说明删除的结点没有左右子树（叶子结点）
            return null;
        }
    }

    @Override
    public String toString() {
        return "SimpleBinaryBalanceTree{" +
                "size=" + size +
                ", root=" + root +
                '}';
    }
}
