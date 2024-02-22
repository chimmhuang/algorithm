package M15_动态规划.斐波那契数;

import java.math.BigInteger;

/**
 * 力扣509. 斐波那契数 [简单]
 * <a href="https://leetcode.cn/problems/fibonacci-number/description/">https://leetcode.cn/problems/fibonacci-number/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/22
 */
public class LeetCode_509_Easy {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 要超出 int/long 的范围
        System.out.println(solution.fib(95));
    }

    static class Solution {
        public int fib(int n) {
            if (n == 0) {
                return 0;
            }

            if (n == 1) {
                return 1;
            }

            BigInteger[] nums = new BigInteger[n + 1];
            nums[0] = BigInteger.ZERO;
            nums[1] = BigInteger.ONE;

            for (int i = 2; i <= n; i++) {
                BigInteger num = nums[i - 1].add(nums[i - 2]);
                nums[i] = num;
            }

            return nums[n].mod(BigInteger.valueOf(1000000007)).intValue();
        }
    }
}
