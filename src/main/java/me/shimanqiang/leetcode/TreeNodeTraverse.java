package me.shimanqiang.leetcode;

import me.shimanqiang.model.TreeNode;

import java.util.Stack;

/**
 * 二叉树遍历遍历
 * <p>
 * 前序遍历：根结点 ---> 左子树 ---> 右子树
 * 中序遍历：左子树---> 根结点 ---> 右子树
 * 后序遍历：左子树 ---> 右子树 ---> 根结点
 * 层次遍历：只需按层次遍历即可
 */
public class TreeNodeTraverse {
    public static void main(String[] args) {
        /**
         * 前序遍历：1  2  4  5  7  8  3  6
         * 中序遍历：4  2  7  5  8  1  3  6
         * 后序遍历：4  7  8  5  2  6  3  1
         * 层次遍历：1  2  3  4  5  6  7  8
         */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        node5.left = node7;
        node5.right = node8;

        TreeNode root = node1;
        TreeNodeTraverse treeNodeTraverse = new TreeNodeTraverse();
        treeNodeTraverse.preOrderTraverse(root);
        System.out.println("");
        treeNodeTraverse.preOrderTraverse2(root);
        System.out.println("");

        treeNodeTraverse.postOrderTraverse(root);
        System.out.println("");
    }

    /**
     * 后序遍历(递归）
     */
    public void postOrderTraverse(TreeNode root) {
        if (root != null) {
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.val + "  ");
        }
    }

    public void postOrderTraverse2(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode left = node.left;
            if (left != null) {
                stack.push(left);
            } else {
                
                System.out.print(node.val + "  ");

            }

        }
    }

    /**
     * 前序遍历(递归）
     */
    public void preOrderTraverse(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "  ");
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrderTraverse2(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                System.out.print(node.val + "  ");
                stack.push(node);
                node = node.left;
            } else {
                TreeNode tmpNode = stack.pop();
                node = tmpNode.right;
            }
        }
    }
}
