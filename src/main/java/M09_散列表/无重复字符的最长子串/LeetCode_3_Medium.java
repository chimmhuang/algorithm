package M09_散列表.无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣3. 无重复字符的最长子串 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/3
 */
public class LeetCode_3_Medium {

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }
            char[] charArray = s.toCharArray();
            Map<Character, Integer> wordMap = new HashMap<>();
            int maxLength = 0;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];

                // 如果已经存在了该单个字符
                if (wordMap.containsKey(c)) {
                    maxLength = Math.max(maxLength, wordMap.size());
                    // 将指针移动到该重复字符的上一个索引
                    Integer index = wordMap.get(c);
                    wordMap.clear();
                    i = index;
                }
                // 如果不存在该字符
                else {
                    wordMap.put(c, i);
                    maxLength = Math.max(maxLength, wordMap.size());
                }
            }
            return maxLength;
        }
    }
}
