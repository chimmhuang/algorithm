package M08_树.二叉树的右视图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 力扣199. 二叉树的右视图 [中等]
 * <a href="https://leetcode.cn/problems/binary-tree-right-side-view/description/">https://leetcode.cn/problems/binary-tree-right-side-view/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_199_Medium {

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
                 1
              2     3
            4  5

            层序遍历，优先从右边开始取值，新建一个队列，将 root 节点放入 queue:[1] result:[]
            每一次遍历，都弹出队列，并添加到结果集，将右节点优先加入队列，再加入左节点。添加过一次值后，就不添加了
            第1次遍历：queue:[3,2]  result:[1]
            第2次遍历：queue:[2] result[1,3]
            第3次遍历：queue:[5,4] result[1,3]
            第4次遍历：queue:[4] result[1,3,5]
            第5次遍历：queue:[] result[1,3,5]
         */
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }

            List<Integer> resultList = new ArrayList<>();
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.add(root);

            while (!deque.isEmpty()) {
                int size = deque.size();
                boolean findRight = false;
                for (int i = 0; i < size; i++) {
                    TreeNode node = deque.pop();
                    if (!findRight) {
                        resultList.add(node.val);
                        findRight = true;
                    }
                    if (node.right != null) {
                        deque.add(node.right);
                    }
                    if (node.left != null) {
                        deque.add(node.left);
                    }
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
