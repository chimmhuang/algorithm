package M08_树.判断是否为平衡二叉树;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 力扣110. 平衡二叉树 [简单]
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/description/">https://leetcode.cn/problems/balanced-binary-tree/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_110_Easy {

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
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);

            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pop();
                    if (node.left != null) {
                        deque.add(node.left);
                    }
                    if (node.right != null) {
                        deque.add(node.right);
                    }
                    int leftHeight = this.height(node.left, 1);
                    int rightHeight = this.height(node.right, 1);

                    boolean flag = Math.abs(leftHeight - rightHeight) <= 1;
                    if (!flag) {
                        return false;
                    }
                }
            }
            return true;
        }

        private int height(TreeNode node, int height) {
            if (node == null) {
                return height;
            }
            height++;

            int leftHight = this.height(node.left, height);
            int rightHight = this.height(node.right, height);

            return Math.max(leftHight, rightHight);
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
