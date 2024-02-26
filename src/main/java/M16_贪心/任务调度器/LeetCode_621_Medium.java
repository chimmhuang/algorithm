package M16_贪心.任务调度器;

import java.util.Arrays;

/**
 * 力扣621. 任务调度器 [中等]
 * <a href="https://leetcode.cn/problems/task-scheduler/description/">https://leetcode.cn/problems/task-scheduler/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/26
 */
public class LeetCode_621_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[] tasks = new char[]{'A', 'B', 'C', 'A'};
        System.out.println(solution.leastInterval(tasks, 2));
    }

    /*
        [A,A,A,B,C,B,C,B,A,D,E]

        按照任务类型进行分组，并按照数量从大到小排序

        [A,A,A,A]
        [B,B,B]
        [C,C]
        [D]
        [E]

        新建二维数组，存储执行顺序

        [A][B][C]
        [A][B][C]
        [A][B][D]
        [A][E][ ]

        没有写出来，题解：https://leetcode.cn/problems/task-scheduler/solutions/509866/jian-ming-yi-dong-de-javajie-da-by-lan-s-jfl9/
     */
    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            int[] buckets = new int[26];
            for(int i = 0; i < tasks.length; i++){
                buckets[tasks[i] - 'A']++;
            }
            Arrays.sort(buckets);
            int maxTimes = buckets[25];
            int maxCount = 1;
            for(int i = 25; i >= 1; i--){
                if(buckets[i] == buckets[i - 1])
                    maxCount++;
                else
                    break;
            }
            int res = (maxTimes - 1) * (n + 1) + maxCount;
            return Math.max(res, tasks.length);
        }
    }
}
