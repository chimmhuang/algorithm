package M06_二分法.搜索旋转排序数组;

/**
 * 力扣33. 搜索旋转排序数组 [中等]
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class LeetCode_33_Medium {

    class Solution {
        /*
           输入：nums = [4,5,6,7,0,1,2], target = 1

                                s       m                    e
                                ↓       ↓                    ↓
           第一次查询：middle=6 [ 4 , 5 , 6 ]    [ 7 , 0 , 1 , 2 ]   target < m && target < s，落在第二区间 s = m

                                s      m       e
                                ↓      ↓       ↓
           第二次查询：middle=0 [ 6, 7 , 0 , 1 , 2 ]  target > m && target < e， 落在第二区间 s = m

                                s   m   e
                                ↓   ↓   ↓
           第三次查询：middle=1 [ 0 , 1 , 2 ] target = m
        */
        public int search(int[] nums, int target) {
            int lowIndex = 0;
            int highIndex = nums.length - 1;
            while (true) {
                if (lowIndex == highIndex) {
                    // 只有一位元素
                    return nums[lowIndex] == target ? lowIndex : -1;
                }
                int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
                int middleNum = nums[middleIndex];
                int lowNum = nums[lowIndex];
                int highNum = nums[highIndex];

                // 如果值相等，则直接返回
                if (middleNum == target) {
                    return middleIndex;
                }
                // 已经不能再找了
                else if (middleIndex == lowIndex && middleIndex == highIndex - 1) {
                    return nums[highIndex] == target ? highIndex : -1;
                }
                // 目标值小于中间值
                else if (target < middleNum) {
                    /*
                        落在第一区间
                        s[4,5,6,7]m         t=6  6<7 && 6>=4 && 4<7
                        s[4,5,6,7,0,1]m     t=0  0<1 && 0<=4 && 4>1
                     */
                    if (target >= lowNum || lowNum > middleNum) {
                        highIndex = middleIndex;
                    } else {
                        // 落在第二区间
                        lowIndex = middleIndex;
                    }
                }
                // 目标值大于中间值
                else {
                    /*
                        落在第二区间
                        m[0,1,2]e          t=1  1>0 && 1<=2 && 0<2
                        m[4,5,6,7,0,1,2]e  t=6  6>4 && 6>=2 && 4>2
                     */
                    if (target <= highNum || middleNum > highNum) {
                        lowIndex = middleIndex;
                    } else {
                        // 落在第一区间
                        highIndex = middleIndex;
                    }
                }
            }
        }
    }
}
