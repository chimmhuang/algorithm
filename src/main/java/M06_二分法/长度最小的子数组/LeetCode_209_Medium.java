package M06_二分法.长度最小的子数组;

/**
 * 力扣209. 长度最小的子数组 [中等]
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">https://leetcode.cn/problems/minimum-size-subarray-sum/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/6
 */
public class LeetCode_209_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{10, 2, 3};
        System.out.println(solution.minSubArrayLen(6, nums));
    }

    static class Solution {

        /**
         * 使用前缀和+二分查找解答
         */
        public int minSubArrayLen(int target, int[] nums) {
            int length = 0;
            // 计算前缀和
            int[] preSums = new int[nums.length];
            preSums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSums[i] = nums[i] + preSums[i - 1];
            }

            for (int i = 0; i < preSums.length; i++) {
                int preSum = preSums[i];
                if (preSum >= target) {
                    length = length == 0 ? i + 1 : Math.min(length, i + 1);
                }
                /*
                    preSum - preSum1 >= k;
                 */

                // 使用二分查找，找到最大的，符合条件的值
                int maxPreSumIndex = 0;
                for (int leftIndex = 0, rightIndex = i; leftIndex < rightIndex; ) {
                    int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

                    int preSum1 = preSums[middleIndex];
                    int subtract = preSum - preSum1;

                    // 符合条件，将左索引移动到 middle
                    if (subtract >= target) {
                        leftIndex = middleIndex;
                        if (leftIndex == rightIndex - 1) {
                            maxPreSumIndex = preSum - preSums[rightIndex] <= target ? rightIndex : leftIndex;
                            length = length == 0 ? i - maxPreSumIndex + 1 : Math.min(length, i - maxPreSumIndex + 1);
                            break;
                        }
                    }
                    // 如果太大了，则移动右索引
                    else {
                        rightIndex = middleIndex;
                    }
                }
            }

            return length;
        }
    }
}
