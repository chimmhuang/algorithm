package M15_动态规划.三角形最小路径和;

import java.util.List;

/**
 * 力扣120. 三角形最小路径和 [中等]
 * <a href="https://leetcode.cn/problems/triangle/description/">https://leetcode.cn/problems/triangle/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class LeetCode_120_Medium {

    /*
            2
          3   4
         6  5  7
        4  1  8  3

        从上往下，加最小的值。
            [2]
           [5,6]
         [11,10,13]
        [15,11,18,16]

        由图可知，F(n,m) = min(f(n-1,m-1),f(n-1,m)) + f(n,m)
     */
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null) {
                return 0;
            }
            if (triangle.size() == 1) {
                return triangle.get(0).get(0);
            }

            int n = triangle.size();
            // 创建一个 n*n 的数组。用于存储最小路径和。
            int[][] minSums = new int[n][n];

            // 初始化
            minSums[0][0] = triangle.get(0).get(0);

            for (int i = 1; i < n; i++) {
                List<Integer> thisLevel = triangle.get(i);
                int lastI = i - 1;
                for (int j = 0; j <= i; j++) {
                    // 第一个
                    if (j == 0) {
                        minSums[i][j] = minSums[lastI][0] + thisLevel.get(j);
                    }
                    // 最后一个
                    else if (j == i) {
                        minSums[i][j] = minSums[lastI][lastI] + thisLevel.get(j);
                    }
                    // 中间的
                    else {
                        minSums[i][j] = Math.min(minSums[lastI][j - 1], minSums[lastI][j]) + thisLevel.get(j);
                    }
                }
            }

            // 找出最小值
            int[] lastLevelMinSum = minSums[n - 1];
            int min = lastLevelMinSum[0];
            for (int i = 1; i < lastLevelMinSum.length; i++) {
                min = Math.min(lastLevelMinSum[i], min);
            }
            return min;
        }
    }
}
