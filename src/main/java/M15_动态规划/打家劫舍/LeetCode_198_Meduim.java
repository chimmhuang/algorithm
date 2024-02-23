package M15_动态规划.打家劫舍;

/**
 * 力扣198. 打家劫舍 [中等]
 * <a href="https://leetcode.cn/problems/house-robber/description/">https://leetcode.cn/problems/house-robber/description/</a>
 *
 * <p>题解：<a href="https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/">https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/</a></p>
 *
 * @author Chimm.Huang
 * @date 2024/2/22
 */
public class LeetCode_198_Meduim {

    /*
        该题未能解除，需要根据题解来解释代码
        https://leetcode.cn/problems/house-robber/solutions/138131/dong-tai-gui-hua-jie-ti-si-bu-zou-xiang-jie-cjavap/
     */
    class Solution {

        /*
                    [ 6 , 3 , 10 , 8 , 2 , 10 , 3 , 5 , 10 , 5 , 3 ]

                    [ 0 , 6 , 0  , 0 , 0 , 0  , 0 , 0 , 0  , 0 , 0 ]

            k=2 →   [ 0 , 6 , 6,
         */
        public int rob(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            // 子问题：
            // f(k) = 偷 [0..k) 房间的最大金额

            // f(0) = 0
            // f(1) = nums[0]
            // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

            int N = nums.length;
            int[] dp = new int[N + 1];
            dp[0] = 0;
            dp[1] = nums[0];

            for (int k = 2; k <= N; k++) {
                /*
                            [ 6 , 3 , 10 , 8 , 2 , 10 , 3 , 5 , 10 , 5 , 3 ]
                            [ 0 , 6 , 0  , 0 , 0 , 0  , 0 , 0 , 0  , 0 , 0 ]

                    k=2 →   max(6,3+0)   [ 0 , 6 , 6  , 0 , 0 , 0  , 0 , 0 , 0  , 0 , 0 ]
                    k=3 →   max(6,10+6)  [ 0 , 6 , 6  , 16, 0 , 0  , 0 , 0 , 0  , 0 , 0 ]
                 */
                dp[k] = Math.max(dp[k - 1], nums[k - 1] + dp[k - 2]);
            }
            return dp[N];
        }
    }
}
