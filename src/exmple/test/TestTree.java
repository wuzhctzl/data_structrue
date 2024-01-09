package exmple.test;


import exmple.data.tree.BinaryTree;
import exmple.data.tree.SimpleBinaryTree;

import java.util.Arrays;
import java.util.List;


public class TestTree {
    public static void main(String[] args) {
        testSimpleBinaryTree();
//        testCreateTree();
    }
    public static void testSimpleBinaryTree(){
//        A B F I E C J D H G K
//                中序遍历
//        F I B E A J C H D K G
//                后序遍历
//        I F E B J H K G D C A
//        BinaryTree<String> tree = new SimpleBinaryBalanceTree<>();
        BinaryTree<Integer> tree = new SimpleBinaryTree<>();
        for(int i=0; i<10; i++){
            tree.add(i);
        }
//        System.out.println(tree);

        System.out.println("高度-->"+tree.getHeight());
        System.out.println("长度-->"+tree.size());

        //查找元素
//        TreeNode<Integer> key0 = tree.findKey(2);
//        System.out.println("查找到的元素:"+key0);
//        TreeNode<Integer> key = tree.findKey(8);
//        System.out.println("查找到的元素:"+key);
//        TreeNode<Integer> key1 = tree.findKey(15);
//        System.out.println("查找到的元素:"+key1);


        //测试前序遍历
        tree.preOrderTraverse();
        tree.preOrderByStack();
        //测试中序遍历
        tree.inOrderTraverse();
        tree.inOrderByStack();
        //测试后序遍历
        tree.postOrderTraverse();
        tree.postOrderByStack();
        //测试按层遍历
        tree.levelOrderByQueue();

        SimpleBinaryTree<Integer> sTree = (SimpleBinaryTree<Integer>) tree;
//        sTree.findParent(3);
//        System.out.println("删除的结点是："+tree.remove(3));
        sTree.findParent(0);
        System.out.println("删除的结点是："+tree.remove(0));

        System.out.println("高度-->"+tree.getHeight());
        System.out.println("长度-->"+tree.size());

        //测试前序遍历
        tree.preOrderTraverse();
        tree.preOrderByStack();
        //测试中序遍历
        tree.inOrderTraverse();
        tree.inOrderByStack();
        //测试后序遍历
        tree.postOrderTraverse();
        tree.postOrderByStack();
        //测试按层遍历
        tree.levelOrderByQueue();

    }

    public static void testCreateTree(){
//        String[] pre = {"A","B","D","G","K","F","J","C","E","I","H"};
//        String[] in = {"G","D","K","B","J","F","A","I","E","C","H"};
//        String[] pre = {"A"};
//        String[] in = {"A"};
//        String[] pre = {"A"};
//        String[] in = {"B"};
//        String[] pre = {"A","B","C"};
//        String[] in = {"A","B","C"};
//        String[] pre = {"A","B","C"};
//        String[] in = {"D","B","C"};
//        String[] pre = {"A","B","C","D"};
//        String[] in = {"A","C","B","D"};
//        String[] pre = {"A","B","C","D","E"};
//        String[] in = {"A","C","D","B","E"};
        String[] pre = {"A","B","C","D","E"};
        String[] in = {"A","C","E","B","D"};
        List<String> preList = Arrays.asList(pre);
        List<String> inList = Arrays.asList(in);
        SimpleBinaryTree<String> simpleTree = new SimpleBinaryTree<>(preList, inList);
        simpleTree.postOrderTraverse();
    }
}
