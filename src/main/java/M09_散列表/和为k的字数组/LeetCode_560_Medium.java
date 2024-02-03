package M09_散列表.和为k的字数组;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣560. 和为k的字数组 [中等]
 * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">https://leetcode.cn/problems/subarray-sum-equals-k/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/3
 */
public class LeetCode_560_Medium {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 1, 1};
        Solution solution = new Solution();
        System.out.println(solution.subarraySum(ints, 2));
    }

    static class Solution {

        /*
            使用【前缀和】进行求解。
            根据题目我们需要求解，哪几个连续数组可以求和为 k
            在使用【前缀和】求解时，能得出以下规律：该位置的前缀和 - 该位置之前的某一个位置的前缀和 = k
            则：该位置的前缀和 - k = 该位置之前的某一个位置的前缀和。将该位置保存，即计算为一个数组。

            原来数组：[1,3,2,-2,1,3,2,3,2] k=6
            前缀和： [1,4,6,4, 5,8,10,13,15]
                    前缀和-出现的次数
            1-6 = -5  [1-1]
            4-6 = -2  [4-1]
            6-6 = 0   [6-1]
            4-6 = -2  [4-2]
            5-6 = -1  [5-1]
            8-6 = 2   [8-1]
            10-6 = 4  [10-1]
            13-6 = 7  [13-1]
            15-6 = 9  [15-1]

            当查询到符合条件的值时，结果集 += 出现的次数
         */
        public int subarraySum(int[] nums, int k) {
            // 新建一个数组，保存前缀和
            int[] preSums = new int[nums.length];
            preSums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSums[i] = nums[i] + preSums[i - 1];
            }

            // 1\3\6
            Map<Integer, Integer> hashMap = new HashMap<>();
            int count = 0;
            for (int i = 0; i < preSums.length; i++) {
                int preSum = preSums[i];
                int subtract = preSum - k;
                // 如果再此之前的刚好=k，则表示从0到此处符合条件
                if (preSum == k) {
                    count++;
                }
                // 如果 该位置的前缀和 - k = 该位置之前的某一个位置的前缀和 && 该值存在。
                if (hashMap.containsKey(subtract)) {
                    count += hashMap.get(subtract);
                }
                hashMap.put(preSum, hashMap.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }
    }
}
