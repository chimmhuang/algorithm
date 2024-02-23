package M16_贪心.分发饼干;

import java.util.Arrays;

/**
 * 力扣455. 分发饼干 [简单]
 * <a href="https://leetcode.cn/problems/assign-cookies/description/">https://leetcode.cn/problems/assign-cookies/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/23
 */
public class LeetCode_455_Easy {
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            // 将孩子的胃口排序
            Arrays.sort(g);
            // 将饼干的大小排序
            Arrays.sort(s);

            // 给孩子分发饼干
            int num = 0;

            // 从大到小分配
            int childIndex = g.length - 1;
            int cookieIndex = s.length - 1;
            while (childIndex >= 0 && cookieIndex >= 0) {
                // 如果当前饼干大小合适小孩的胃口。则投喂
                if (s[cookieIndex] >= g[childIndex]) {
                    childIndex--;
                    cookieIndex--;
                    num++;
                }
                // 如果这个孩子嫌弃太小了，则分配给下一个孩子。
                else {
                    childIndex--;
                }
            }

            return num;
        }
    }
}
