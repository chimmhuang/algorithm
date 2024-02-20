package M12_广度优先搜索.岛屿数量;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 力扣200. 岛屿数量 [中等]
 * <a href="https://leetcode.cn/problems/number-of-islands/description/">https://leetcode.cn/problems/number-of-islands/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/20
 */
public class LeetCode_200_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grids = new char[][]{
                {'1','1','1'},
                {'0','1','0'},
                {'0','1','0'},
        };
        System.out.println(solution.numIslands(grids));
    }

    static class Solution {

        /*
            [
                [1,1,1,0,0],
                [1,1,0,0,0],
                [0,0,1,0,0],
                [0,0,0,1,1]
            ]
         */
        public int numIslands(char[][] grid) {

            // 用于记录该节点是否已被访问过
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            // 用于存储已被访问过，但相邻的节点还没有被访问过的节点
            Deque<Integer> xQueue = new ArrayDeque<>();
            Deque<Integer> yQueue = new ArrayDeque<>();

            int num = 0;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    char c = grid[y][x];
                    if ('0' == c || visited[y][x]) {
                        continue;
                    }

                    bfs(grid, xQueue, yQueue, visited, x, y);

                    num++;
                }
            }
            return num;
        }

        private void bfs(char[][] grid, Deque<Integer> xQueue, Deque<Integer> yQueue, boolean[][] visited, int x, int y) {
            xQueue.push(x);
            yQueue.push(y);

            // bfs
            while (!xQueue.isEmpty() && !yQueue.isEmpty()) {
                // 判断该节点的上下左右节点是否为陆地。
                Integer xIndex = xQueue.pop();
                Integer yIndex = yQueue.pop();

                if (visited[yIndex][xIndex]) {
                    continue;
                }

                visited[yIndex][xIndex] = true;

                int upIndex = yIndex - 1; // 上
                int downIndex = yIndex + 1; // 下
                int leftIndex = xIndex - 1; // 左
                int rightIndex = xIndex + 1; // 右

                // 判断上方是否是岛屿
                if (upIndex >= 0
                        && '1' == grid[upIndex][xIndex]
                        && !visited[upIndex][xIndex]) {
                    xQueue.push(xIndex);
                    yQueue.push(upIndex);
                }

                // 判断下方是否是岛屿
                if (downIndex < grid.length
                        && '1' == grid[downIndex][xIndex]
                        && !visited[downIndex][xIndex]) {
                    xQueue.push(xIndex);
                    yQueue.push(downIndex);
                }

                // 判断左方是否是岛屿
                if (leftIndex >= 0
                        && '1' == grid[yIndex][leftIndex]
                        && !visited[yIndex][leftIndex]) {
                    xQueue.push(leftIndex);
                    yQueue.push(yIndex);
                }

                // 判断右方是否是岛屿
                if (rightIndex < grid[yIndex].length
                        && '1' == grid[yIndex][rightIndex]
                        && !visited[yIndex][rightIndex]) {
                    xQueue.push(rightIndex);
                    yQueue.push(yIndex);
                }
            }
        }
    }
}
