package M12_广度优先搜索.岛屿数量;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 力扣200. 岛屿数量 [中等]
 * <a href="https://leetcode.cn/problems/number-of-islands/description/">https://leetcode.cn/problems/number-of-islands/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/20
 */
public class LeetCode_200_Medium_V1 {

class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        boolean[][] visited = new boolean[row][column];
        int number = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(grid, i, j, visited);
                    number++;
                }
            }
        }
        return number;
    }

    private void bfs(char[][] grid, int i, int j, boolean[][] visited) {
        int[] kx = {1, -1, 0, 0};
        int[] ky = {0, 0, 1, -1};
        visited[i][j] = true;
        Queue<Integer> xQueue = new ArrayDeque<>();
        Queue<Integer> yQueue = new ArrayDeque<>();
        xQueue.offer(i);
        yQueue.offer(j);
        while (!xQueue.isEmpty()) {
            Integer currentX = xQueue.poll();
            Integer currentY = yQueue.poll();
            // 上下左右
            for (int k = 0; k < 4; k++) {
                int newX = currentX + kx[k];
                int newY = currentY + ky[k];
                if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && !visited[newX][newY]) {
                    if (grid[newX][newY] == '1') {
                        xQueue.offer(newX);
                        yQueue.offer(newY);
                        visited[newX][newY] = true;
                    }
                }
            }
        }
    }
}
}
