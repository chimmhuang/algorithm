package M15_动态规划.打家劫舍;

/**
 * 力扣198. 打家劫舍 [中等]
 * <a href="https://leetcode.cn/problems/house-robber/description/">https://leetcode.cn/problems/house-robber/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/22
 */
public class LeetCode_198_Meduim {

    /**
     * [2]
     * 方案1: [2] --> 2
     *
     * [2,1]
     * 方案1：[2, ]--> 2
     * 方案2：[ ,1]--> 1
     *
     * [2,1,1]
     * 方案1：[2, ,1] --> 3
     * 方案2：[ ,1, ] --> 1
     *
     * [2,1,1,3]
     * 方案1：[2, ,1, ] --> 3
     * 方案2：[2, , ,3] --> 5
     * 方案3：[ ,1, ,3] --> 4
     *
     * [2,1,1,3,5]
     * 方案1：[2, ,1, ,5] --> 8
     * 方案2：[2, , ,3, ] --> 5
     * 方案3：[ ,1, ,3, ] --> 4
     * 方案4：[ ,1, , ,5] --> 6
     * 方案5：[ , ,1, ,5] --> 6
     *
     * 规律：
     *  1.起点只需要判断第一个和第二个。
     *  2.中途的步长只需要跨越2步或3步。
     *
     * 做法：
     *  使用 DFS，先进行跨越2步判断，再回溯，进行跨越3步判断。
     */
    class Solution {
        public int rob(int[] nums) {
            return 0;
        }
    }
}
