package M06_二分法.寻找旋转排序数组中的最小值;

/**
 * 力扣153. 寻找旋转排序数组中的最小值 [中等]
 * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/submissions/494810663/">https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/submissions/494810663/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class LeetCode_153_Medium {

    class Solution {
        /*
           输入：nums = [4,5,6,7,0,1,2]

                                s       m                    e
                                ↓       ↓                    ↓
           第一次查询：middle=6 [ 4 , 5 , 6 ]    [ 7 , 0 , 1 , 2 ]   m > e && m > s   s = m

                                s      m       e
                                ↓      ↓       ↓
           第二次查询：middle=0 [ 6, 7 , 0 , 1 , 2 ]  m < e && m < s 证明最小值将在此段产生 if m > (m-1)  则 e = m
        */
        public int findMin(int[] nums) {
            int lowIndex = 0;
            int highIndex = nums.length - 1;
            // 只有一个元素
            if (highIndex == 0) {
                return nums[0];
            }
            while (true) {
                int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
                int lowNum = nums[lowIndex];
                int highNum = nums[highIndex];
                int middleNum = nums[middleIndex];

                // 已经不能再找了
                if (middleIndex == lowIndex && middleIndex == highIndex - 1) {
                    return Math.min(lowNum, highNum);
                }

                // 中间值 大于 高位值，证明最小值在右区间
                if (middleNum > highNum) {
                    lowIndex = middleIndex;
                }
                // 中间值 小于等于 高位值，证明最小值在左区间
                else {
                    highIndex = middleIndex;
                }
            }
        }
    }
}
