package M06_二分法.寻找峰值;

/**
 * 力扣162. 寻找峰值 [中等]
 * <a href="https://leetcode.cn/problems/find-peak-element/description/">https://leetcode.cn/problems/find-peak-element/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class LeetCode_162_Medium {

    class Solution {
        /*
           输入：nums = [1,2,1,3,5,6,4]

                                s           m                    e
                                ↓           ↓                    ↓
           第一次查询：middle=3 [ 1 , 2 , 1 , 3 ]    [ 7 , 0 , 1 , 2 ]   m > e && m > s   s = m

                                s      m       e
                                ↓      ↓       ↓
           第二次查询：middle=0 [ 6, 7 , 0 , 1 , 2 ]  m < e && m < s 证明最小值将在此段产生 if m > (m-1)  则 e = m
        */
        public int findPeakElement(int[] nums) {
            int lowIndex = 0;
            int highIndex = nums.length - 1;
            // 只有一个元素
            if (highIndex == 0) {
                return 0;
            }
            for (; ; ) {
                int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
                int middleNum = nums[middleIndex];

                // 已经不能再找了，谁大谁就是峰值
                if (middleIndex == lowIndex && middleIndex == highIndex - 1) {
                    return nums[highIndex] > nums[lowIndex] ? highIndex : lowIndex;
                }

                // 中间的值小于他右边的值，证明峰值在右边
                if (middleNum < nums[middleIndex + 1]) {
                    lowIndex = middleIndex;
                }
                // 中间的值大于他右边的值，证明峰值在左边
                else {
                    highIndex = middleIndex;
                }
            }
        }
    }
}
