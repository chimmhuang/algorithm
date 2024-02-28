package M15_动态规划.单词拆分;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 力扣139. 单词拆分 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/28
 */
public class LeetCode_139_Medium {

    public static void main(String[] args) {
        System.out.println("leetcode".substring(3, 4));
        Solution solution = new Solution();
        System.out.println(solution.wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }

    /*
        看了题解后，自己写
        题解：https://leetcode.cn/problems/word-break/solutions/302779/shou-hui-tu-jie-san-chong-fang-fa-dfs-bfs-dong-tai/
        l e e t c o d e
        0   j     i

        dp[i+1]取决于 [0,j-1][j,i] 在不在 wordList 里
     */
    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            if (s == null) {
                return false;
            }

            Set<String> wordSet = new HashSet<>(wordDict);

            // 新建一个dp数组，用于记录到指定位置时，是否存在字典中。
            boolean[] dp = new boolean[s.length() + 1];
            // 初始化数组，空串可以被切割。
            dp[0] = true;

            int length = s.length();
            for (int i = 1; i <= length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[length];

        }
    }
}
