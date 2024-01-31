package M08_树.层序遍历;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 力扣102. 二叉树的层序遍历 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_102_Medium {

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            int[] arr = new int[10];
            if (root == null) {
                return Collections.emptyList();
            }

            List<List<Integer>> resultList = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);
            while (!deque.isEmpty()) {
                int size = deque.size();
                List<Integer> levelList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = deque.pop();
                    levelList.add(treeNode.val);
                    if (treeNode.left != null) {
                        deque.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        deque.add(treeNode.right);
                    }
                }
                if (!levelList.isEmpty()) {
                    resultList.add(levelList);
                }
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
