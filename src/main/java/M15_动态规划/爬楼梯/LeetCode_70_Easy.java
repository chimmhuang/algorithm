package M15_动态规划.爬楼梯;

/**
 * 力扣70. 爬楼梯 [简单]
 *
 * @author Chimm.Huang
 * @date 2024/2/22
 */
public class LeetCode_70_Easy {

    class Solution {

        /*
            n = 1 → 1(1)
            n = 2 → 2(11,2)
            n = 3 → 3(111,12,21)
            n = 4 → 5(1111,112,121,211,22)
            n = 5 → 8(11111,1112,1121,1211,2111,221,212,122)

            类似斐波那契函数。
         */
        public int climbStairs(int n) {
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return 1;
            } else if (n == 2) {
                return 2;
            }

            int a = 1;
            int b = 2;

            for (int i = 3; i <= n; i++) {
                int c = a + b;
                a = b;
                b = c;
            }

            return b;
        }
    }
}
