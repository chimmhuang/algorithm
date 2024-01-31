package M08_树.判断是否为平衡二叉树;

/**
 * 力扣110. 平衡二叉树 [简单]
 * 【优化版本】
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/description/">https://leetcode.cn/problems/balanced-binary-tree/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_110_Easy_V2 {

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
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            return this.maxHight(root) != -1;
        }

        private int maxHight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHight = this.maxHight(root.left);
            int rightHight = this.maxHight(root.right);

            if (leftHight == -1 || rightHight == -1 || Math.abs(leftHight - rightHight) > 1) {
                return -1;
            } else {
                return Math.max(leftHight, rightHight) + 1;
            }
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
