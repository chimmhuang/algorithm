package M11_滑动窗口.串联所有单词的子串;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣30. 串联所有单词的子串 [困难]
 * <a href="https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/">https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/</a>
 *
 * 博主的解题思路：<a href="https://blog.csdn.net/m0_73888323/article/details/132388678">https://blog.csdn.net/m0_73888323/article/details/132388678<a/>
 *
 * @author Chimm.Huang
 * @date 2024/2/6
 */
public class LeetCode_30_Hard_V1 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = new String[]{"ab","ba","ba"};
        System.out.println(JSON.toJSONString(solution.findSubstring("ababaab", words)));
    }

    static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> list = new ArrayList<>();

            // 统计单词出现的次数
            Map<String,Integer> map1 = new HashMap<>();
            for(String tmp : words) {
                map1.put(tmp,map1.getOrDefault(tmp,0) + 1);
            }

            //求words数组的大小
            int len = words.length;

            //求words数组中每个单词的长度
            int m = words[0].length();

            for(int i = 0; i < m; i++) {
                Map<String,Integer> map2 = new HashMap<>();
                // 开始滑动，对字符进行切割 substring。每次滑动 单词长度的距离。然后统计单词出现的次数
                for (int left = i, right = i, count = 0; right <= s.length() - m; right += m) {
                    String in = s.substring(right, right + m);
                    map2.put(in, map2.getOrDefault(in, 0) + 1);
                    if (map2.get(in) <= map1.getOrDefault(in, 0)) {
                        count++;
                    }
                    if (right - left + 1 > len * m) {
                        String out = s.substring(left, left + m);
                        if (map2.get(out) <= map1.getOrDefault(out, 0)) {
                            count--;
                        }
                        map2.put(out, map2.get(out) - 1);
                        left += m;
                    }
                    if (count == len) {
                        list.add(left);
                    }
                }
            }
            return list;
        }
    }
}
