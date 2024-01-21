package M08_树.中序遍历;

import M04_队列.基于数组实现的队列.ArrayQueue;

import java.util.*;

/**
 * 力扣94. 二叉树的中序遍历 [简单]
 * 使用栈来解决
 *
 * @author Chimm.Huang
 * @date 2024/1/21
 */
public class LeetCode94_Easy_Stack {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {

        /*
            中序遍历为：左中右 的顺序

            树结构：
                1
            2       3
                  4   5

            定义一个当前节点 currentNode，将当前节点放入栈，后续弹出，如果左边有值，就继续遍历左边的值
            若左边没有值了，则证明自己就是第一个了，弹出栈，此时将 currentNode 节点变更为 right 节点。如此循环。

            第1次遍历：currentNode = 1，左边有值，继续遍历左边的值。 stack=[1   resultList=[]
            第2次遍历：currentNode = 2，左边没有值了，该值就是第一个，放入结果集，右边也没值，则弹出栈， stack=[1  resultList=[2]
            第3次遍历：currentNode = 1，将该值放入结果集，右边有值，开始遍历右边的值 stack=[   resultList=[2,1]
            第4次遍历：currentNode = 3，左边有值，继续遍历左边的值，stack=[3   resultList=[2,1]
            第5次遍历：currentNode = 4，左边没有值了，该值就是第一个，放入结果集，右边也没值，则弹出栈， stack=[3  resultList=[2,1,4]
            第6次遍历：currentNode = 3，将该值放入结果集，右边有值，开始遍历右边的值 stack=[   resultList=[2,1,4,3]
            第7次遍历：currentNode = 5，左边没有值了，该值就是第一个，放入结果集，右边也没值，结束循环 stack=[  resultList=[2,1,4,3,5]
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> resultList = new ArrayList<>();

            TreeNode currentNode = root;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (currentNode != null || !stack.isEmpty()) {
                while (currentNode != null) {
                    // 先遍历左边的值，将该值放入栈
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
                // 当前节点的左边已经没有值了，代表当前节点就是最后的节点，将结果弹出放入结果集。
                TreeNode treeNode = stack.pop();
                resultList.add(treeNode.val);
                // 开始遍历右边的节点
                currentNode = treeNode.right;
            }
            return resultList;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
