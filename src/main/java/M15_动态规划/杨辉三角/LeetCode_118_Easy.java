package M15_动态规划.杨辉三角;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 力扣118. 杨辉三角 [简单]
 *
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class LeetCode_118_Easy {

    class Solution {

        /*
            F(0) = f(0,0) = [1]
            F(1) = f(1,0)+f(1,1) = f(0,0) + f(0,0) = [1,1]
            F(2) = f(2,0) + f(2,1) + f(2,2) = f(1,0) + (f(1,0) +f(1,1)) + f(1,1) = [1,2,1]
            F(n) = f(n,0) + ... + f(n,m) = f(n-1,m-1) + f(n-1,m)
         */
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> resultList = new ArrayList<>();
            if (numRows == 0) {
                return resultList;
            } else if (numRows == 1) {
                resultList.add(Collections.singletonList(1));
                return resultList;
            } else if (numRows == 2) {
                resultList.add(Collections.singletonList(1));
                resultList.add(Arrays.asList(1, 1));
                return resultList;
            }

            // 初始化1-2层
            resultList.add(Collections.singletonList(1));
            resultList.add(Arrays.asList(1, 1));

            for (int i = 2; i < numRows; i++) {
                List<Integer> levelList = new ArrayList<>();
                List<Integer> lastList = resultList.get(i - 1);
                for (int j = 0; j <= i; j++) {
                    // 第一个
                    if (j == 0) {
                        levelList.add(lastList.get(0));
                    }
                    // 最后一个
                    else if (j == i) {
                        levelList.add(lastList.get(lastList.size() - 1));
                    }
                    // 中间的
                    else {
                        // f(n,m) = f(n-1,m-1) + f(n-1,m)
                        levelList.add(lastList.get(j - 1) + lastList.get(j));
                    }
                }
                resultList.add(levelList);
            }

            return resultList;
        }
    }
}
