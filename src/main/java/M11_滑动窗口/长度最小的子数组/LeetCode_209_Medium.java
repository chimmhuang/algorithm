package M11_滑动窗口.长度最小的子数组;

/**
 * 力扣209. 长度最小的子数组 [中等]
 * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/description/">https://leetcode.cn/problems/minimum-size-subarray-sum/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/3
 */
public class LeetCode_209_Medium {

    /*
        nums = [2,3,1,2,4,3] target = 7

                 L/R
                  ↓
        第1次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=2，小于target， R右移

                  L   R
                  ↓   ↓
        第2次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=5，小于target， R右移

                  L       R
                  ↓       ↓
        第3次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=6，小于target， R右移

                  L           R
                  ↓           ↓
        第4次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=8，大于target， L右移

                      L       R
                      ↓       ↓
        第5次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=6，小于target， R右移

                      L           R
                      ↓           ↓
        第6次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=10，大于target， L右移

                          L       R
                          ↓       ↓
        第7次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=7，等于target， LR都右移 length=3

                              L       R
                              ↓       ↓
        第8次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=9，大于target， L右移

                                  L   R
                                  ↓   ↓
        第9次滑: [ 2 , 3 , 1 , 2 , 4 , 3 ]  sum=7，等于target， R无法右移，结束滑动 length=2
     */
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int leftIndex = 0;
            int rightIndex = 0;
            int length = nums.length;

            int result = 0;
            while (rightIndex < length) {
                int sum = 0;
                for (int i = leftIndex; i <= rightIndex; i++) {
                    sum += nums[i];
                }
                if (sum == target) {
                    leftIndex++;
                    rightIndex++;
                    // 计算长度，按照题目要求取最小的长度。
                    int elementLength = rightIndex - leftIndex + 1;
                    result = result == 0 ? elementLength : Math.min(result, elementLength);
                } else if (sum < target) {
                    rightIndex++;
                } else {
                    // 计算长度，按照题目要求取最小的长度。
                    int elementLength = rightIndex - leftIndex + 1;
                    result = result == 0 ? elementLength : Math.min(result, elementLength);
                    leftIndex++;
                    if (leftIndex > rightIndex) {
                        rightIndex++;
                    }
                }
            }
            return result;
        }
    }
}
