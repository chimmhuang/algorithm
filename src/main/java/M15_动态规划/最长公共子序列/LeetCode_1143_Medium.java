package M15_动态规划.最长公共子序列;

/**
 * 力扣1143. 最长公共子序列 [中等]
 * <a href="https://leetcode.cn/problems/longest-common-subsequence/">https://leetcode.cn/problems/longest-common-subsequence/</a>
 *
 * <img src="https://pic.leetcode-cn.com/1617411822-KhEKGw-image.png" width="600" height="350"/>
 *
 * @author Chimm Huang
 * @date 2024/2/27
 */
public class LeetCode_1143_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonSubsequence("ezupkr", "ubmrapg"));
    }

    /*
        似懂非懂，难受鸭匹。

        第一次遍历，将相同的
          a b c d e
        a 1 0 0 0 0
        c 0 0 2 0 0
        e 0 0 0 0 3

          e z u p k r
        u 0 0 1 1 1 1
        b 0 0 1 1 1 1
        m 0 0 1 1 1 1
        r 0 0 1 1 1 2
        a 0 0 1 1 1 2
        p 0 0 1 2 2 2
        g 0 0 1 2 2 2
     */
    static class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] charArray1 = text1.toCharArray();
            char[] charArray2 = text2.toCharArray();

            // 新建一个二维数组，用于记录相同子序列长度
            int[][] grid = new int[charArray2.length][charArray1.length];
            for (int i = 0; i < charArray2.length; i++) {
                char c2 = charArray2[i];
                int[] lastLengthI;
                if (i == 0) {
                    lastLengthI = new int[charArray1.length];
                } else {
                    lastLengthI = grid[i - 1];
                }
                for (int j = 0; j < charArray1.length; j++) {
                    char c1 = charArray1[j];
                    if (c2 == c1) {
                        // 第一次出现
                        if (j == 0) {
                            grid[i][j] = 1;
                        } else {
                            grid[i][j] = lastLengthI[j - 1] + 1;
                        }
                    } else {
                        if (j == 0) {
                            grid[i][j] = lastLengthI[j];
                        } else {
                            grid[i][j] = Math.max(lastLengthI[j], grid[i][j - 1]);
                        }
                    }
                }
            }

            return grid[charArray2.length - 1][charArray1.length - 1];
        }
    }
}
