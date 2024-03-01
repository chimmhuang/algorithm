package M15_动态规划.检查数组是否存在有效划分;

/**
 * 力扣2369. 检查数组是否存在有效划分 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/3/1
 */
public class LeetCode_2369_Medium {

    /*
        [4,4,4,5,6]

        新建一个数组，用于存储是否符合条件。满足 F(n) 符合条件，那么 F(n-1) 也必须符合条件
          [4,4,4,5,6]
        [1,0,1,1,0,1]
          [4,4]
          [4,4,4]
              [4,5,6]
     */
    class Solution {
        public boolean validPartition(int[] nums) {
            boolean[] conditions = new boolean[nums.length + 1];
            // 初始化
            conditions[0] = true;

            for (int i = 1; i < nums.length; i++) {
                int num = nums[i];
                // 看前一个的类型。
                int preI1 = i - 1;
                int preI2 = i - 2;

                // 符合条件1：子数组 恰 由 2 个相等元素组成
                if (nums[preI1] == num) {
                    conditions[i + 1] = conditions[preI1];

                    // 符合条件2：子数组 恰 由 3 个相等元素组成
                    if (preI2 >= 0 && nums[preI2] == num && !conditions[preI1]) {
                        conditions[i + 1] = conditions[preI2];
                    }
                }
                // 符合条件3：子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1
                else if (nums[preI1] == num - 1 && preI2 >= 0 && nums[preI2] == num - 2) {
                    conditions[i + 1] = conditions[preI2];
                }
            }

            return conditions[nums.length];
        }
    }

}
