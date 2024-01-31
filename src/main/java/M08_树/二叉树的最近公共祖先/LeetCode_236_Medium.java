package M08_树.二叉树的最近公共祖先;

/**
 * 力扣236. 二叉树的最近公共祖先 [中等]
 * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/">https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_236_Medium {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 如果该节点 = p 或 q ，则返回该节点
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 如果以 root 为顶点，既找到了左边的值，又找到了右边的值，则证明该节点为公共祖先节点
            if (left != null && right != null) {
                return root;
            }
            // 如果只有左边找到了值，则最先找到 p 或 q 的值为公共祖先节点
            else if (left != null) {
                return left;
            }
            // 如果只有右边找到了值，则最先找到 p 或 q 的值为公共祖先节点
            else if (right != null) {
                return right;
            }
            // 如果没有找到，则返回空
            return null;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
