package M03_栈.括号验证;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 力扣20. 有效的括号
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">https://leetcode.cn/problems/valid-parentheses/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/8
 */
public class LeetCode_20_Easy {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("{[]}"));
    }

    static class Solution {

        /**
         * 示例 1：
         *
         * 输入：s = "()"
         * 输出：true
         *
         * 示例 2：
         * 输入：s = "()[]{}"
         * 输出：true
         *
         * 示例 3：
         * 输入：s = "(]"
         * 输出：false
         *
         * 示例 4:
         * 输入: s = "([)]"
         * 输出：false
         *
         * 示例 5:
         * 输入: s = "{[]}"
         * 输出：true
         */
        public boolean isValid(String s) {
            Map<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            Stack<Character> stack = new Stack<>();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    Character character = stack.pop();
                    if (character == null) {
                        return false;
                    }
                    Character expectChar = map.get(character);
                    if (expectChar == null) {
                        return false;
                    }
                    if (!expectChar.equals(c)) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }
}
