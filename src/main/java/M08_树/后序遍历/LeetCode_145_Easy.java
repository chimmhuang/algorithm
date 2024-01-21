package M08_树.后序遍历;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 力扣145. 二叉树的后序遍历 [简单]
 *
 * @author Chimm.Huang
 * @date 2024/1/21
 */
public class LeetCode_145_Easy {

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
            后序遍历为：左右中 的顺序
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }
            List<Integer> resultList = new ArrayList<>();
            this.postOrder(resultList, root);
            return resultList;
        }

        private void postOrder(List<Integer> resultList, TreeNode treeNode) {
            if (treeNode == null) {
                return;
            }
            // 左
            this.postOrder(resultList, treeNode.left);
            // 右
            this.postOrder(resultList, treeNode.right);
            // 中
            resultList.add(treeNode.val);
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
