package M11_滑动窗口.最大连续1的个数;

import java.util.HashSet;
import java.util.Set;

/**
 * 力扣1004. 最大连续1的个数 Ⅲ [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/5
 */
public class LeetCode_1004_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1};
        System.out.println(solution.longestOnes(nums, 8));
    }

    /*
        nums = [ 1 , 1 , 0 , 0 , 1 ]  k=1

                 L/R
                  ↓
        第1次滑： [ 1 , 1 , 0 , 0 , 1 ]  k=1  length=1

                  L   R
                  ↓   ↓
        第2次滑： [ 1 , 1 , 0 , 0 , 1 ]  k=1  length=2

                  L        R
                  ↓        ↓
        第3次滑： [ 1 , 1 , 0(1) , 0 , 1 ]  翻转。k=0  length=3

                  L              R
                  ↓              ↓
        第4次滑： [ 1 , 1 , 0(1) , 0 , 1 ]  k=0  length=3，翻不动了，找到下一个为1的值

                                L    R
                                ↓    ↓
        第5次滑： [ 1 , 1 , 0 , 0(1) , 1 ]  翻转。k=0  length=3，翻不动了，找到下一个为1的值
     */
    static class Solution {
        public int longestOnes(int[] nums, int k) {
            int length = 0;
            int leftIndex = 0;
            int rightIndex = 0;
            int acturlWindowNum = 0;
            int revertTimes = k;
            Set<Integer> revertIndexSet = new HashSet<>();
            while (rightIndex < nums.length) {
                if (nums[rightIndex] == 0) {
                    revertIndexSet.add(rightIndex);
                }
                if (leftIndex == rightIndex) {
                    acturlWindowNum = nums[rightIndex];
                } else {
                    acturlWindowNum += nums[rightIndex];
                }
                int expectWindowNum = rightIndex - leftIndex + 1;

                // 需要翻转或移动窗口
                if (acturlWindowNum == 0 || acturlWindowNum < expectWindowNum) {
                    revertTimes--;
                    // 翻转，并且继续扩大窗口
                    if (revertTimes >= 0) {
                        length = Math.max(expectWindowNum, length);
                        acturlWindowNum++;
                    }
                    // 已无法翻转，移动左边索引，直到可以反转为止。
                    else {
                        while (leftIndex < rightIndex && revertTimes < 0) {
                            acturlWindowNum -= nums[leftIndex];
                            if (revertIndexSet.contains(leftIndex++)) {
                                revertTimes++;
                                break;
                            }
                        }
                    }
                    // 处理完成，移动右索引
                    rightIndex++;
                }
                // 不需要翻转
                else {
                    length = Math.max(expectWindowNum, length);
                    rightIndex++;
                }

                if (leftIndex > rightIndex) {
                    rightIndex++;
                }
            }

            return length;
        }
    }
}
