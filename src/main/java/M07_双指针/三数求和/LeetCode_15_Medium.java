package M07_双指针.三数求和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 力扣15. 三数之和
 * <a href="https://leetcode.cn/problems/3sum/description/">https://leetcode.cn/problems/3sum/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class LeetCode_15_Medium {

    class Solution {
        /*
            输入：nums [-1,0,1,2,-1,-4]
            排序：nums [-4,-1,-1,0,1,2]

                         b    s                 e
                         ↓    ↓                 ↓
            第1次查询：[ -4 , -1 , -1 , 0 , 1 , 2 ] 无符合条件的值

                              b    s           e
                              ↓    ↓           ↓
            第2次查询：[ -4 , -1 , -1 , 0 , 1 , 2 ] 查询出：[-1,0,1] [-1,-1,2]

                                   b
                                   ↓
            第3次查询：[ -4 , -1 , -1 , 0 , 1 , 2 ] 重复数据，无需再次计算

                                       b   s   e
                                       ↓   ↓   ↓
            第4次查询：[ -4 , -1 , -1 , 0 , 1 , 2 ] 无符合条件的值
         */
        public List<List<Integer>> threeSum(int[] nums) {
            // 先给 nums 排序，时间复杂度 = O(nlogn)
            Arrays.sort(nums);

            // for循环+while循环遍历，时间复杂度= O(n²)
            List<List<Integer>> resultList = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                // 开头重复的值，就不需要再进行判断了
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int baseNum = nums[i];
                int lowIndex = i + 1;
                int highIndex = nums.length - 1;
                while (lowIndex <= nums.length - 2 && highIndex > lowIndex) {
                    int lowNum = nums[lowIndex];
                    int highNum = nums[highIndex];

                    int sum = baseNum + lowNum + highNum;
                    // 如果求和=0，则返回
                    if (sum == 0) {
                        List<Integer> result = new ArrayList<>();
                        result.add(baseNum);
                        result.add(lowNum);
                        result.add(highNum);
                        resultList.add(result);
                        /*
                            【踩坑】接下来要遍历的数据，也可能存在一样的，如：[-2,0,0,2,2]
                         */
                        lowIndex++;
                        highIndex--;
                        while (lowIndex < highIndex && nums[lowIndex] == nums[lowIndex - 1]) {
                            lowIndex++;
                        }
                        while (highIndex > lowIndex && nums[highIndex] == nums[highIndex + 1]) {
                            highIndex--;
                        }
                    }
                    // 如果求和小于0，则右移 低位索引
                    else if (sum < 0) {
                        lowIndex++;
                    } else {
                        highIndex--;
                    }
                }
            }
            return resultList;
        }
    }
}
