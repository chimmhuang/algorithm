package M16_贪心.任务调度器;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        char[] tasks = new char[]{'A','A','A','B','B','B','C','C','C','D','D','E'};
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
     */
    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> taskMap = new HashMap<>();
            for (int i = 0; i < tasks.length; i++) {
                taskMap.put(tasks[i], taskMap.getOrDefault(tasks[i], 0) + 1);
            }

            // 按照大小进行排序
            List<Map.Entry<Character, Integer>> taskList = taskMap.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .collect(Collectors.toList());

            // 新建二维数组
            Character[][] executors = new Character[taskList.get(0).getValue()][tasks.length];

            int N = 0;
            for (int j = 0; j < executors[0].length && N == 0; j++) {
                for (int i = 0; i < executors.length && !taskList.isEmpty(); i++) {
                    Map.Entry<Character, Integer> entry = taskList.get(0);
                    executors[i][j] = entry.getKey();
                    entry.setValue(entry.getValue() - 1);
                    if (entry.getValue() == 0) {
                        taskList.remove(0);
                    }
                    if (taskList.isEmpty()) {
                        N = j + 1;
                    }
                }
            }

            // 统计时间
            int time = 0;
            int executorNums = 0;
            for (int i = 0; i < executors.length; i++) {
                for (int j = 0; j < N; j++) {
                    if (executorNums == tasks.length) {
                        return time;
                    }

                    if (executors[i][j] != null) {
                        executorNums++;
                    }
                    time++;
                }
            }
            return time;
        }
    }
}
