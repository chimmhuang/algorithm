package M10_堆.数组中的第K个最大元素;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 力扣215. 数组中的第K个最大元素 [中等]
 * <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/">https://leetcode.cn/problems/kth-largest-element-in-an-array/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/1/31
 */
public class LeetCode_215_Medium {

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.reverseOrder());
            for (int i = 0; i < nums.length; i++) {
                priorityQueue.add(nums[i]);
            }
            for (int i = 0; i < k - 1; i++) {
                Integer poll = priorityQueue.poll();
            }
            return priorityQueue.poll();
        }
    }
}
