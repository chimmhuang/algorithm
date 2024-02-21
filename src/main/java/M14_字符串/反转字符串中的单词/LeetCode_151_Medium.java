package M14_字符串.反转字符串中的单词;

/**
 * 力扣151. 反转字符串中的单词 [中等]
 * <a href="https://leetcode.cn/problems/reverse-words-in-a-string/description/">https://leetcode.cn/problems/reverse-words-in-a-string/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/2/21
 */
public class LeetCode_151_Medium {

    class Solution {
        public String reverseWords(String s) {
            if (s == null || "".equals(s.trim())) {
                return "";
            }

            String[] split = s.trim().split(" ");

            // 倒序循环
            StringBuilder sb = new StringBuilder();
            for (int i = split.length - 1; i >= 0; i--) {
                if ("".equals(split[i])) {
                    continue;
                }

                sb.append(split[i]);

                if (i != 0) {
                    sb.append(" ");
                }
            }

            return sb.toString();
        }
    }
}
