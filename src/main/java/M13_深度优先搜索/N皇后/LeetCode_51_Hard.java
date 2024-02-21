package M13_深度优先搜索.N皇后;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣51. N皇后 [困难]
 * <a href="https://leetcode.cn/problems/n-queens/">https://leetcode.cn/problems/n-queens/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/21
 */
public class LeetCode_51_Hard {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> lists = solution.solveNQueens(5);
        System.out.println(JSON.toJSONString(lists));
    }

    static class Solution {

        /**
         * 斜方向加减
         */
        int[] obliqueDirectionX = {1, -1};
        public List<List<String>> solveNQueens(int n) {
            if (n == 0) {
                return Collections.emptyList();
            }
            if (n == 1) {
                return Collections.singletonList(Collections.singletonList("Q"));
            }

            List<List<String>> resultChessboard = new ArrayList<>();
            int[][] chessboard = new int[n][n];
            Map<Integer, Map<Integer, Integer>> errorIndexMap = new HashMap<>();

            dfs(resultChessboard, chessboard, errorIndexMap, 0, n);
            return resultChessboard;
        }

        /**
         * 使用 DFS 进行搜索
         *
         * @param resultChessboard 结果集
         * @param chessboard       棋盘
         * @param errorIndexMap    错误的位置（不能落子的索引位置） K-yIndex,V-xIndex - Vv-出现的数量
         * @param yIndex           当前的y索引
         * @param n                棋盘大小
         */
        private void dfs(List<List<String>> resultChessboard, int[][] chessboard, Map<Integer, Map<Integer, Integer>> errorIndexMap, int yIndex, int n) {
            // 已经递归完所有层，证明前面的都已经落子，符合条件，添加到结果集中
            if (yIndex == n) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < chessboard.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < chessboard[i].length; j++) {
                        if (chessboard[i][j] == 0) {
                            sb.append(".");
                        } else {
                            sb.append("Q");
                        }
                    }
                    list.add(sb.toString());
                }
                resultChessboard.add(list);
                return;
            }
            for (int xIndex = 0; xIndex < n; xIndex++) {
                if (errorIndexMap.containsKey(yIndex)) {
                    Map<Integer, Integer> errorXIndexMap = errorIndexMap.get(yIndex);
                    // 如果该坐标不能落子，则继续判断
                    if (errorXIndexMap.containsKey(xIndex)) {
                        continue;
                    }
                }
                // 没有错误索引，则表示该坐标可以落子
                chessboard[yIndex][xIndex] = 1;

                // 下方竖排都不可落子
                for (int y = yIndex + 1; y < n; y++) {
                    Map<Integer, Integer> yIndexErrorMap = errorIndexMap.getOrDefault(y, new HashMap<>());
                    Integer count = yIndexErrorMap.getOrDefault(xIndex, 0) + 1;
                    yIndexErrorMap.put(xIndex, count);
                    errorIndexMap.put(y, yIndexErrorMap);
                }

                // 斜下方向也不能落子
                for (int i = 0; i < obliqueDirectionX.length; i++) {
                    addObliqueDirectionErrorIndex(errorIndexMap, xIndex, yIndex, obliqueDirectionX[i], n);
                }

                // 判断完成，开始递归下一层
                dfs(resultChessboard, chessboard, errorIndexMap, yIndex + 1, n);

                // 递归完成，移除该落子，判断后续的落子
                chessboard[yIndex][xIndex] = 0;

                // 移除竖排错误索引
                for (int y = yIndex + 1; y < n; y++) {
                    Map<Integer, Integer> yIndexErrorMap = errorIndexMap.get(y);
                    Integer count = yIndexErrorMap.getOrDefault(xIndex, 0);
                    count--;
                    if (count <= 0) {
                        yIndexErrorMap.remove(xIndex);
                    } else {
                        yIndexErrorMap.put(xIndex, count);
                    }
                    errorIndexMap.put(y, yIndexErrorMap);
                }
                // 移除斜方向错误索引
                for (int i = 0; i < obliqueDirectionX.length; i++) {
                    removeObliqueDirectionErrorIndex(errorIndexMap, xIndex, yIndex, obliqueDirectionX[i], n);
                }
            }
        }

        private void addObliqueDirectionErrorIndex(Map<Integer, Map<Integer, Integer>> errorIndexMap, int currentX, int currentY, int xAdd, int n) {
            while (currentX >= 0 && currentX < n && currentY >= 0 && currentY < n) {
                // 将该坐标加入错误索引中去
                Map<Integer, Integer> yErrorIndexMap = errorIndexMap.getOrDefault(currentY, new HashMap<>());
                Integer count = yErrorIndexMap.getOrDefault(currentX, 0) + 1;
                yErrorIndexMap.put(currentX, count);
                errorIndexMap.put(currentY, yErrorIndexMap);

                currentX += xAdd;
                currentY++;
            }
        }

        private void removeObliqueDirectionErrorIndex(Map<Integer, Map<Integer, Integer>> errorIndexMap, int currentX, int currentY, int xAdd, int n) {
            while (currentX >= 0 && currentX < n && currentY >= 0 && currentY < n) {
                // 将该坐标加入错误索引中去
                Map<Integer, Integer> yErrorIndexMap = errorIndexMap.getOrDefault(currentY, new HashMap<>());
                Integer count = yErrorIndexMap.getOrDefault(currentX, 0);
                count--;
                if (count <= 0) {
                    yErrorIndexMap.remove(currentX);
                } else {
                    yErrorIndexMap.put(currentX, count);
                }
                errorIndexMap.put(currentY, yErrorIndexMap);

                currentX += xAdd;
                currentY++;
            }
        }
    }
}
