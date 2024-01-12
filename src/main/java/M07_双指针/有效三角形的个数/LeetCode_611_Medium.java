package M07_双指针.有效三角形的个数;

import java.util.Arrays;

/**
 * 力扣611. 有效三角形的个数
 * <a href="https://leetcode.cn/problems/valid-triangle-number/description/">https://leetcode.cn/problems/valid-triangle-number/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/12
 */
public class LeetCode_611_Medium {

    class Solution {
        /*
            该题我没有解答出来，现将思路写下
            输入：nums [2,2,3,4]

            先将其排序，再倒叙遍历（从最大的边开始遍历）

                                s       e   t
                                ↓       ↓   ↓
            第1次遍历，第1次查询：[ 2 , 2 , 3 , 4 ] 由于2+3>4，那么中间的数值相加也一定>4，所以 count = e-s=2，e--

                                s   e       t
                                ↓   ↓       ↓
            第1次遍历，第2次查询：[ 2 , 2 , 3 , 4 ] 由于2+3=4，三角形不成立，查询结束，t--

                                s   e   t
                                ↓   ↓   ↓
            第2次遍历，第1次查询：[ 2 , 2 , 3 , 4 ] 由于2+2>3，三角形成立，count += (e-s) = 3 ，遍历结束
         */
        public int triangleNumber(int[] nums) {
            if (nums == null || nums.length <= 2) {
                return 0;
            }
            // 对数组进行排序
            Arrays.sort(nums);
            int count = 0;
            for (int k = nums.length - 1; k > 1; k--) {
                int side3 = nums[k];

                int lowIndex = 0;
                int highIndex = k - 1;

                while (lowIndex < highIndex && lowIndex < k - 1) {
                    int lowSideNum = nums[lowIndex];
                    int highSideNum = nums[highIndex];

                    // 三角形边长的特点：任意两个边长之和大于第三边，任意两个边长只差小于第三边
                    if (lowSideNum + highSideNum > side3) {
                        // 【精髓】两个小的数，相加，都大于了大的数，那么中间的数一定也是大于最大数的。
                        count += (highIndex - lowIndex);
                        highIndex--;
                    } else {
                        lowIndex++;
                    }
                }
            }
            return count;
        }
    }
}
