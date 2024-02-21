package M14_字符串.反转字符串中的单词Ⅲ;

/**
 * 力扣557. 反转字符串中的单词Ⅲ [简单]
 *
 * @author Chimm Huang
 * @date 2024/2/21
 */
public class LeetCode_557_Easy {

    class Solution {
        public String reverseWords(String s) {
            if (s == null || "".equals(s.trim())) {
                return "";
            }

            String[] split = s.split(" ");
            int length = split.length;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                String word = split[i];
                char[] charArray = word.toCharArray();
                for (int j = charArray.length - 1; j >= 0; j--) {
                    sb.append(charArray[j]);
                }

                if (i != length - 1) {
                    sb.append(" ");
                }
            }

            return sb.toString();
        }
    }
}
