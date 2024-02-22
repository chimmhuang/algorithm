package M14_字符串.整数转罗马数字;

/**
 * 力扣12. 整数转罗马数字 [中等]
 * <a href="https://leetcode.cn/problems/integer-to-roman/description/">https://leetcode.cn/problems/integer-to-roman/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/2/21
 */
public class LeetCode_12_Medium {

    class Solution {

        private String[] g = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        private String[] x = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        private String[] b = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        private String[] q = new String[]{"", "M", "MM", "MMM"};

        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();

            sb.append(q[num / 1000]);

            int bNum = num % 1000;
            sb.append(b[bNum / 100]);

            int xNum = bNum % 100;
            sb.append(x[xNum / 10]);

            int gNum = xNum % 10;
            sb.append(g[gNum]);

            return sb.toString();
        }
    }
}
