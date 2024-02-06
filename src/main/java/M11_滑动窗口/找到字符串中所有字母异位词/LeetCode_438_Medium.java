package M11_滑动窗口.找到字符串中所有字母异位词;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣438. 找到字符串中所有字母异位词 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/6
 */
public class LeetCode_438_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.findAnagrams("abaacbabc", "abc")));
    }

    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> resultList = new ArrayList<>();
            if (s == null || s.trim().isEmpty()) {
                return Collections.emptyList();
            }

            char[] pCharArray = p.toCharArray();

            // 将目标字符拆分，存储起来
            Map<Character, Integer> sourceCharMap = new HashMap<>();
            for (int i = 0; i < pCharArray.length; i++) {
                sourceCharMap.put(pCharArray[i], sourceCharMap.getOrDefault(pCharArray[i], 0) + 1);
            }

            // 定义窗口值
            Map<Character, Integer> windowCharMap = new HashMap<>();

            // 滑起来～
            char[] charArray = s.toCharArray();
            for (int leftIndex = 0, rightIndex = 0; rightIndex < charArray.length; rightIndex++) {
                char c = charArray[rightIndex];

                // 如果该字符根本不符合条件
                if (!sourceCharMap.containsKey(c)) {
                    // 将左边索引移动到符合条件为止
                    while (leftIndex <= rightIndex) {
                        char leftChar = charArray[leftIndex];
                        int charCount = windowCharMap.getOrDefault(leftChar, 0) - 1;
                        if (charCount <= 0) {
                            windowCharMap.remove(leftChar);
                        } else {
                            windowCharMap.put(leftChar, charCount);
                        }
                        leftIndex++;
                        if (leftIndex > rightIndex) {
                            break;
                        }
                    }
                }
                // 字符符合要求，进行下一步判断。
                else {
                    int windowCharCount = windowCharMap.getOrDefault(c, 0) + 1;
                    int sourceCharCount = sourceCharMap.get(c);
                    windowCharMap.put(c, windowCharCount);

                    // 判断是否符合异位词要求
                    boolean allMatch = sourceCharMap.entrySet().stream()
                            .allMatch(entry -> {
                                Integer windowCount = windowCharMap.getOrDefault(entry.getKey(), 0);
                                return entry.getValue().equals(windowCount);
                            });

                    // 记录左边索引的值
                    if (allMatch) {
                        resultList.add(leftIndex);
                        // 记录完毕后，移动左索引
                        int leftCount = windowCharMap.get(charArray[leftIndex]) - 1;
                        if (leftCount == 0) {
                            windowCharMap.remove(charArray[leftIndex]);
                        } else {
                            windowCharMap.put(charArray[leftIndex], leftCount);
                        }
                        leftIndex++;
                    }
                    // 判断是否超出了指定大小，如果超出了，则移动左索引，如果没有超出，则移动右索引
                    else if (windowCharCount > sourceCharCount) {
                        // 移动左边索引，直到该字符符合条件大小为止
                        while (windowCharCount > sourceCharCount) {
                            if (c == charArray[leftIndex]) {
                                windowCharCount -= 1;
                            }
                            int leftCount = windowCharMap.get(charArray[leftIndex]) - 1;
                            if (leftCount == 0) {
                                windowCharMap.remove(charArray[leftIndex]);
                            } else {
                                windowCharMap.put(charArray[leftIndex], leftCount);
                            }
                            leftIndex++;
                        }

                    }
                }
            }

            return resultList;
        }
    }
}
