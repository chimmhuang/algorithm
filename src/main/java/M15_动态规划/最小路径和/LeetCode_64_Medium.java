package M15_动态规划.最小路径和;

/**
 * 力扣64. 最小路径和 [中等]
 * <a href="https://leetcode.cn/problems/minimum-path-sum/description/">https://leetcode.cn/problems/minimum-path-sum/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class LeetCode_64_Medium {

    class Solution {
        public int minPathSum(int[][] grid) {
            // 新建一个二维数组，记录到达该点的最短路径举例
            int[][] minPath = new int[grid.length][grid[0].length];

            // 对于每个点，都有 F(n,m) = min(f(n-1,m),f(n,m-1)) + f(n,m)
            for (int i = 0; i < grid.length; i++) {
                int[] thisPath = grid[i];
                int[] lastPath;
                if (i == 0) {
                    lastPath = new int[thisPath.length];
                } else {
                    lastPath = minPath[i - 1];
                }

                for (int j = 0; j < thisPath.length; j++) {
                    // 最左边的点，只能从上面过来
                    if (j == 0) {
                        minPath[i][j] = lastPath[j] + thisPath[j];
                    }
                    // 最上面的点，只能从左边过来
                    else if (i == 0) {
                        minPath[i][j] = minPath[i][j - 1] + thisPath[j];
                    }
                    // 其他位置的点，都可以从上面或者左边过来。取最小的值
                    else {
                        minPath[i][j] = Math.min(lastPath[j], minPath[i][j - 1]) + thisPath[j];
                    }
                }
            }

            return minPath[grid.length - 1][grid[0].length - 1];
        }
    }
}
