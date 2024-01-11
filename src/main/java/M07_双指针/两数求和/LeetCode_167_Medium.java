package M07_双指针.两数求和;

/**
 * 力扣167. 两数求和Ⅱ - 输入有序数组 [中等]
 * <a href="https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/">https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class LeetCode_167_Medium {

    class Solution {

        /*
            输入：nums [2,7,10,11,15]  target = 12

                        s            e
                        ↓            ↓
            第1次查询 [ 2 , 7 , 11 , 15 ]   sum = 17  > target，高位索引左移

                        s       e
                        ↓       ↓
            第2次查询 [ 2 , 7 , 11 , 15 ]   sum = 13  > target，高位索引左移

                        s  e
                        ↓  ↓
            第3次查询 [ 2 , 7 , 11 , 15 ]   sum = 12  = target，返回索引
         */
        public int[] twoSum(int[] numbers, int target) {
            int lowIndex = 0;
            int highIndex = numbers.length - 1;

            for (; ; ) {
                int lowNum = numbers[lowIndex];
                int highNum = numbers[highIndex];

                int sum = lowNum + highNum;
                // 相等则返回索引
                if (sum == target) {
                    return new int[]{lowIndex + 1, highIndex + 1};
                }
                // 如果结果小于期望值，则右移 低位索引
                else if (sum < target) {
                    lowIndex++;
                }
                // 如果结果大于期望值，则左移 高位索引
                else {
                    highIndex--;
                }
            }
        }
    }
}
