package M11_滑动窗口.将x减到0的最小操作数;

/**
 * 力扣1658. 将x减到0的最小操作数
 *
 * @author Chimm.Huang
 * @date 2024/2/5
 */
public class LeetCode_1658_Medium {

    /**
     * 和这道题{@link M11_滑动窗口.最大连续1的个数.LeetCode_1004_Medium}类似的解法
     * 先对整个数组求和 sum，然后再求出 sum-x 的最长滑动窗口。再用总长度-滑动窗口长度。就等于最小的操作数
     */
    class Solution {
        public int minOperations(int[] nums, int x) {
            int totalSum = 0;
            for (int i = 0; i < nums.length; i++) {
                totalSum += nums[i];
            }

            if (totalSum == x) {
                return nums.length;
            }

            // 滑起来~
            int maxLength = 0;
            for (int leftIndex = 0, rightIndex = 0, windowSum = 0; rightIndex < nums.length; rightIndex++) {
                windowSum += nums[rightIndex];
                int substract = totalSum - windowSum;

                // 太大了，需要缩小范围
                while (substract < x && leftIndex <= rightIndex) {
                    substract += nums[leftIndex];
                    windowSum -= nums[leftIndex];
                    leftIndex++;
                }
                if (substract == x) {
                    maxLength = Math.max(maxLength, rightIndex - leftIndex + 1);
                }
            }

            return maxLength == 0 ? -1 : nums.length - maxLength;
        }
    }
}
