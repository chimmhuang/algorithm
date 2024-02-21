package M14_字符串.整数转罗马数字;

/**
 * 力扣12. 整数转罗马数字 [中等]
 *
 * @author Chimm Huang
 * @date 2024/2/21
 */
public class LeetCode_12_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(20));
    }

    static class Solution {

        private final Pair[] dict = new Pair[]{
                new Pair(0, ""),
                new Pair(1, "I"),
                new Pair(2, "II"),
                new Pair(3, "III"),
                new Pair(4, "IV"),
                new Pair(5, "V"),
                new Pair(6, "VI"),
                new Pair(7, "VII"),
                new Pair(8, "VIII"),
                new Pair(9, "IX"),
                new Pair(10, "X"),
                new Pair(40, "XL"),
                new Pair(50, "L"),
                new Pair(90, "XC"),
                new Pair(100, "C"),
                new Pair(500, "D"),
                new Pair(400, "CD"),
                new Pair(900, "CM"),
                new Pair(1000, "M")
        };

        static class Pair {
            int key;
            String value;

            public Pair(int key, String value) {
                this.key = key;
                this.value = value;
            }
        }


        public String intToRoman(int num) {
            StringBuilder resultBuilder = new StringBuilder();
            if (num > 1000) {
                for (int i = num / 1000; i > 0; i--) {
                    resultBuilder.append("M");
                }
                num %= 1000;
            }

            // 使用二分法判断 num 所在位置
            for (int leftIndex = 0, rightIndex = dict.length; leftIndex <= rightIndex; ) {
                if (num <= 10) {
                    resultBuilder.append(dict[num].value);
                    break;
                }

                int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
                int middleNum = dict[middleIndex].key;

                // 如果相等，则直接拼接返回
                if (middleNum == num) {
                    resultBuilder.append(dict[middleIndex].value);
                    break;
                }
                // 如果偏大，则移动右索引。
                else if (middleNum > num) {
                    rightIndex = middleIndex;
                }
                // 如果偏小，则移动左索引
                else {
                    // 先判断是否还能移动，如果不能移动了，则记录值，并充值 left 和 right
                    if (leftIndex + 1 == rightIndex) {


//todo
//                        // 如果能被整除，则直接赋值
//                else if (middleNum >= 10 && num % middleNum == 0) {
//                            for (int i = num / middleNum; i > 0; i--) {
//                                resultBuilder.append(dict[middleIndex].value);
//                            }
//                            break;
//                        }
//todo




                        Pair pair = dict[leftIndex];
                        resultBuilder.append(pair.value);
                        num %= pair.key;
                        leftIndex = 0;
                        rightIndex = middleIndex;
                        continue;
                    }
                    leftIndex = middleIndex;
                }
            }

            return resultBuilder.toString();
        }
    }
}
