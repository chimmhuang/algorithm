package M11_滑动窗口.串联所有单词的子串;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 力扣30. 串联所有单词的子串 [困难]
 * <a href="https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/">https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/6
 */
public class LeetCode_30_Hard {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[]{"ab","ba","ba"};
        System.out.println(JSON.toJSONString(solution.findSubstring("ababaab", words)));
    }

   static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> resultList = new ArrayList<>();
            if (s == null) {
                return resultList;
            }

            // 存储 words 里面的 字符
            Set<Character> allWordsCharSet = new HashSet<>();
            Map<String, Integer> wordsCountMap = new HashMap<>();
            int wordsAllCharLength = 0;
            int wordPerLength = words[0].length();
            for (String word : words) {
                wordsCountMap.put(word, wordsCountMap.getOrDefault(word, 0) + 1);
                wordsAllCharLength += word.length();
                char[] c = word.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    allWordsCharSet.add(c[i]);
                }
            }

            char[] charArray = s.toCharArray();
            // 滑起来～
            for (int leftIndex = 0, rightIndex = 0; rightIndex < charArray.length; rightIndex++) {
                char rightChar = charArray[rightIndex];

                // 如果该字符根本不符合条件
                if (!allWordsCharSet.contains(rightChar)) {
                    // 移动左边索引，直到跳过该字符为止
                    while (leftIndex <= rightIndex) {
                        leftIndex++;
                        if (leftIndex > rightIndex) {
                            break;
                        }
                    }
                }
                // 该字符包含在words内，进行下一步判断
                else {
                    int windowLength = rightIndex - leftIndex + 1;
                    // 窗口的值还未达到应有长度，继续往右判断
                    if (windowLength < wordsAllCharLength) {
                        continue;
                    }
                    // 窗口的长度满足判断，进行下一步判断
                    else if (windowLength == wordsAllCharLength) {

                        // 再滑～～～～～～此次滑动，一次滑 单词 数量的长度
                        boolean isDifference = false;
                        Map<String, Integer> map = new HashMap<>();
                        for (int subLeftIndex = leftIndex; subLeftIndex <= rightIndex; subLeftIndex += wordPerLength) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < wordPerLength; i++) {
                                char c = charArray[subLeftIndex + i];
                                sb.append(c);
                            }
                            String str = sb.toString();

                            // 如果原始数据没有这个单词，则结束
                            if (!wordsCountMap.containsKey(str)) {
                                isDifference = true;
                                break;
                            }
                            map.put(str, map.getOrDefault(str, 0) + 1);
                        }

                        // 如何有匹配的值，则记录左索引
                        if (!isDifference) {
                            // 判断是否符合异位词要求
                            boolean allMatch = wordsCountMap.entrySet()
                                    .stream()
                                    .allMatch(entry -> {
                                        if (!map.containsKey(entry.getKey())) {
                                            return false;
                                        }
                                        return entry.getValue().equals(map.get(entry.getKey()));
                                    });
                            // 如何有匹配的值，则记录左索引
                            if (allMatch) {
                                resultList.add(leftIndex);
                            }
                        }
                        leftIndex++;
                    }
                    // 窗口长度大于应有长度，缩小窗口值
                    else {
                        // 移动左索引，进行下一步判断
                        leftIndex++;
                    }
                }
            }

            return resultList;
        }
    }
}
