package M07_双指针.接雨水;

import java.math.BigDecimal;

/**
 * 力扣42. 接雨水 [困难]
 *
 * @author Chimm Huang
 * @date 2024/1/12
 */
public class LeetCode_42_Hard {

    class Solution {
        /*
            2024-01-12备注：用双指针完全不会做

                                  ____
                     ____         |   |___  ____
              ____   |  |___   ___|      |__|  |___
              |  |___|  |  |___|                   |
                ↑                                ↑
             L高度/L索引                       R高度/R索引       L高度=R高度，中间可以存水，L高度>L+1索引高度<R高度 。存水量+1，移动L索引  存水量：1
             -------------------------------------------------------------------------------
                ↑
              L高度
                   ↑                             ↑
                 L索引                        R高度/R索引       L高度=R高度，L高度<L+1索引高度。向上爬坡中，无法存水，更新L高度，移动L索引
             -------------------------------------------------------------------------------
                       ↑                         ↑
                    L高度/L索引                R高度/R索引       L高度>R高度，向下爬坡中，无法存水，移动R索引，R高度<R-1索引高度，，更新R高度，移动R索引
             -------------------------------------------------------------------------------
                       ↑                      ↑
                   L高度/L索引             R高度/R索引           L高度=R高度，L高度>L+1索引高度<R高度。存水量+1，移动L索引 存水量：2
             -------------------------------------------------------------------------------
                       ↑
                     L高度
                          ↑                   ↑
                        L索引               R高度/R索引         L高度=R高度，L高度>L+1索引高度<R高度。存水量+2，移动L索引 存水量：4
             -------------------------------------------------------------------------------
                       ↑                      ↑
                     L高度                   R高度
                             ↑                ↑
                           L索引             R索引              L高度=R高度，L高度>L+1索引高度<R高度。存水量+1，移动L索引 存水量：5
             -------------------------------------------------------------------------------
                                ↑             ↑
                            L高度/L索引     R高度/R索引           L高度>R高度，向下爬坡，移动R索引，R高度>R-1索引高度，存水量+1 存水量：6
             -------------------------------------------------------------------------------
                                              ↑
                                            R高度
                                ↑          ↑
                            L高度/L索引    R索引                 L高度>R高度，向下爬坡，移动R索引，R高度=R-1索引高度， 移动R索引
             -------------------------------------------------------------------------------
                                ↑             ↑
                              L高度          R高度
                                ↑      ↑
                              L索引   R索引                  L高度>R高度，向下爬坡，移动R索引，R高度=R-1索引高度， 移动R索引。R索引已无法移动，计算结束。
             -------------------------------------------------------------------------------
         */
        public int trap(int[] height) {
            if (height.length < 3) {
                return 0;
            }
            int leftIndex = 0;
            int rightIndex = height.length - 1;
            int leftHeight = height[leftIndex];
            int rightHeight = height[rightIndex];

            int rainWaterNum = 0;
            while (leftIndex + 1 < rightIndex) {
                // 移动L索引
                if (leftHeight < rightHeight) {
                    int left_plus_1_height = height[leftIndex + 1];
                    // 如果 L高度 > L+1索引高度。则代表可以存水
                    if (leftHeight > left_plus_1_height) {
                        // 存水
                        rainWaterNum += (leftHeight - left_plus_1_height);
                    }
                    // 如果 L高度 <= L+1索引高度，则不能存水，更新 L高度
                    else {
                        leftHeight = left_plus_1_height;
                    }
                    // 移动L索引
                    leftIndex++;
                }
                // 移动R索引
                else {
                    int right_subtract_1_height = height[rightIndex - 1];
                    // 如果 R高度 > R-1索引高度，则代表可以存水
                    if (rightHeight > right_subtract_1_height) {
                        // 存水
                        rainWaterNum += (rightHeight - right_subtract_1_height);
                    }
                    // 如果 R高度 <= R-1索引高度，则不能存水，更新 R高度
                    else {
                        rightHeight = right_subtract_1_height;
                    }
                    // 移动 R索引
                    rightIndex--;
                }
            }
            return rainWaterNum;
        }
    }
}
