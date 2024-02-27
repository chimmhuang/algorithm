package M15_动态规划.不同路径;

/**
 * 力扣62. 不同路径 [中等]
 * <a href="https://leetcode.cn/problems/unique-paths/description/">https://leetcode.cn/problems/unique-paths/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/2/27
 */
public class LeetCode_62_Medium {

    /*
        [0,0,0,0,0,0,0]  [1,1,1,1,1,1,1]
        [0,0,0,0,0,0,0]  [1,2,3,4,0,0,0]
        [0,0,0,0,0,0,0]  [1,2,5,0,0,0,0]

        对于第 F(n,m) 个坐标。都有 F(n,m) = f(n-1,m) + f(n,m-1) 条路径
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1) {
                return 1;
            }

            // 新建一个二维数组
            int[][] grid = new int[n][m];

            // 初始化格子的路径数
            grid[0][0] = 1;

            for (int i = 0; i < n; i++) {
                // 上一排的路径
                int lastI = i - 1;
                int[] lastGrid;
                if (lastI < 0) {
                    lastGrid = new int[m];
                } else {
                    lastGrid = grid[lastI];
                }
                for (int j = 0; j < m; j++) {
                    // 最上边或最左边，只能从左边或上面走过来
                    if (j == 0 || i == 0) {
                        grid[i][j] = 1;
                    }
                    // 其他情况，则可以从左边或上面两个方向走过来
                    else {
                        grid[i][j] = grid[i][j - 1] + lastGrid[j];
                    }
                }
            }

            return grid[n - 1][m - 1];
        }
    }
}
