package M11_滑动窗口.水果成篮;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣904. 水果称篮 [中等]
 * <a href="https://leetcode.cn/problems/fruit-into-baskets/description/">https://leetcode.cn/problems/fruit-into-baskets/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/6
 */
public class LeetCode_904_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] fruits = new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println(solution.totalFruit(fruits));
    }

    static class Solution {

        /*
            有这几颗树：[ 1 , 2 , 3 , 2 , 2 ]

                      L/R
                       ↓
            第1次采摘：[ 1 , 2 , 3 , 2 , 2 ]   L种类：1  R种类：null 最大数目：1

                       L   R
                       ↓   ↓
            第2次采摘：[ 1 , 2 , 3 , 2 , 2 ]   L种类：1  R种类：2 最大数目：2

                            L  R
                            ↓  ↓
            第3次采摘：[ 1 , 2 , 3 , 2 , 2 ]   不符合要求，重新放入L果篮 L种类：2  R种类：3 最大数目：2

                            L      R
                            ↓      ↓
            第4次采摘：[ 1 , 2 , 3 , 2 , 2 ]   符合要求，L种类：2  R种类：3 最大数目：3

                            L           R
                            ↓           ↓
            第5次采摘：[ 1 , 2 , 3 , 2 , 2 ]   符合要求，L种类：2  R种类：3 最大数目：4
         */
        public int totalFruit(int[] fruits) {
            Map<Integer, Integer> fruitType = new HashMap<>();

            int maxTreeNum = 0;

            // 滑起来
            for (int leftIndex = 0, rightIndex = 0; rightIndex < fruits.length; rightIndex++) {
                // 如果已经存在该水果，则证明符合要求。
                if (fruitType.containsKey(fruits[rightIndex])) {
                    fruitType.put(fruits[rightIndex], fruitType.get(fruits[rightIndex]) + 1);
                    maxTreeNum = Math.max(maxTreeNum, rightIndex - leftIndex + 1);
                }
                // 如果不存在该水果，判断果篮的种类是否装满
                else {
                    // 装入果篮
                    fruitType.put(fruits[rightIndex], fruitType.getOrDefault(fruits[rightIndex], 0) + 1);
                    // 没有多装，符合要求
                    if (fruitType.size() <= 2) {
                        maxTreeNum = Math.max(maxTreeNum, rightIndex - leftIndex + 1);
                    }
                    // 超装了，不符合要求
                    else {
                        // 将左边的果树种类剔除，并知道能塞入新的水果为止
                        int leftFruit = fruits[leftIndex];
                        while (fruitType.size() > 2) {
                            Integer count = fruitType.get(fruits[leftIndex]) - 1;
                            if (count == 0) {
                                fruitType.remove(fruits[leftIndex]);
                            } else {
                                fruitType.put(fruits[leftIndex], count);
                            }
                            leftIndex++;
                        }
                    }
                }
            }

            return maxTreeNum;
        }
    }
}
