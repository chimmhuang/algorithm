package M13_深度优先搜索_回溯.岛屿数量;

/**
 * 力扣200. 岛屿数量 [中等]
 * <a href="https://leetcode.cn/problems/number-of-islands/description/">https://leetcode.cn/problems/number-of-islands/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/21
 */
public class LeetCode_200_Medium {

    class Solution {

        /*
            [
                [1,1,1],
                [0,1,0],
                [1,1,1]
            ]
         */
        public int numIslands(char[][] grid) {

            // 用于记录该节点是否已被访问过
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            int num = 0;
            for (int i = 0; i < grid.length; i++) {
                char[] xChars = grid[i];
                for (int j = 0; j < xChars.length; j++) {
                    char c = grid[i][j];
                    if (c == '1' && !visited[i][j]) {
                        dfs(grid, j, i, visited);
                        num++;
                    }
                }
            }
            return num;
        }

        private void dfs(char[][] grid, int xIndex, int yIndex, boolean[][] visited) {
            if (visited[yIndex][xIndex]) {
                return;
            }
            visited[yIndex][xIndex] = true;

            // 判断上方是否有岛屿
            int upIndex = yIndex - 1;
            if (upIndex >= 0 && grid[upIndex][xIndex] == '1') {
                dfs(grid, xIndex, upIndex, visited);
            }

            // 判断下方是否有岛屿
            int downIndex = yIndex + 1;
            if (downIndex < grid.length && grid[downIndex][xIndex] == '1') {
                dfs(grid, xIndex, downIndex, visited);
            }

            // 判断左边是否有岛屿
            int leftIndex = xIndex - 1;
            if (leftIndex >= 0 && grid[yIndex][leftIndex] == '1') {
                dfs(grid, leftIndex, yIndex, visited);
            }

            // 判断右边是否有岛屿
            int rightIndex = xIndex + 1;
            if (rightIndex < grid[yIndex].length && grid[yIndex][rightIndex] == '1') {
                dfs(grid, rightIndex, yIndex, visited);
            }
        }
    }
}
