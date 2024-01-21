package M08_树.中序遍历;

import M08_树.前序遍历.LeetCode_144_Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 力扣94. 二叉树的中序遍历 [简单]
 *
 * @author Chimm.Huang
 * @date 2024/1/21
 */
public class LeetCode94_Easy {

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
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> resultList = new ArrayList<>();
            this.preOrder(resultList, root);
            return resultList;
        }

        private void preOrder(List<Integer> resultList, TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            // 左
            this.preOrder(resultList, treeNode.left);
            // 中
            resultList.add(treeNode.val);
            // 右
            this.preOrder(resultList, treeNode.right);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, LeetCode_144_Easy.TreeNode left, LeetCode_144_Easy.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
