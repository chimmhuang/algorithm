package M08_树.前序遍历;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 力扣144. 二叉树的前序遍历 [简单]
 *
 * @author Chimm Huang
 * @date 2024/1/15
 */
public class LeetCode_144_Easy {

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
         */
        public List<Integer> preorderTraversal(TreeNode root) {
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
            // 中
            resultList.add(treeNode.val);
            // 左
            this.preOrder(resultList, treeNode.left);
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
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }
}
