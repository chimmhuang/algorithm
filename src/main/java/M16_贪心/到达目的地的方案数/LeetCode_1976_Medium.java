package M16_贪心.到达目的地的方案数;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 力扣1976. 到达目的地的方案数 [中等]
 *
 * 没有做出来
 *
 * @author Chimm Huang
 * @date 2024/3/9
 */
public class LeetCode_1976_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 7;
        int[][] roads = new int[][]
                {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        System.out.println(solution.countPaths(n, roads));
    }

    static class Solution {
        public int countPaths(int n, int[][] roads) {
            if (n == 1) {
                return 0;
            } else if (n == 2) {
                return 1;
            }
            // 将二维数组转换为图
            LinkedList<Road>[] roadGraph = new LinkedList[n];
            for (int i = 0; i < roadGraph.length; i++) {
                roadGraph[i] = new LinkedList<>();
            }

            for (int i = 0; i < roads.length; i++) {
                int[] roadI = roads[i];
                Road road0 = new Road(roadI[0], roadI[2]);
                Road road1 = new Road(roadI[1], roadI[2]);

                roadGraph[road0.node].add(road1);
                roadGraph[road1.node].add(road0);
            }

            return dijkstra(n, roadGraph);
        }

        private int dijkstra(int n, LinkedList<Road>[] roadGraph) {
            // 定义一个数组，用于存储从0到该点的最短距离
            long[] shortestPath = new long[n];
            // 初始化为最大值
            for (int i = 0; i < shortestPath.length; i++) {
                shortestPath[i] = Integer.MAX_VALUE;
            }
            shortestPath[0] = 0;

            // 定义一个数组，用于存储达到该位置有几条最短路径数量
            int[] shortestPathNums = new int[n];
            shortestPathNums[0] = 1;

            // 定义一个数组，用于存储该位置是否已确认了最短路径
            Set<Integer> prepareConfirm = new HashSet<>();
            for (int i = 0; i < n; i++) {
                prepareConfirm.add(i);
            }

            // 从0开始遍历
            int index = 0;
            while (!prepareConfirm.isEmpty()) {
                LinkedList<Road> relationList = roadGraph[index];

                // 计算与之相关节点的最短路径
                for (int i = 0; i < relationList.size(); i++) {
                    Road road = relationList.get(i);
                    long newPath = road.weight + shortestPath[index];
                    // 更新每个节点的最短路径，和数量
                    if (shortestPath[road.node] > newPath) {
                        shortestPath[road.node] = newPath;
                        // 到达此处的数量=前一个数量
                        shortestPathNums[road.node] = shortestPathNums[index];
                    }
                    // 如果相等，则增加道路数量
                    else if (shortestPath[road.node] == newPath) {
                        shortestPathNums[road.node] = (shortestPathNums[road.node] + 1) % 1000000007;
                        // 由于增加了该节点，所以需要重新判断该节点以下的节点
                        prepareConfirm.add(road.node);
                    }
                }

                // 计算完毕后，选择当前最短路径的节点，将其标记为已确认（移除待确认）
                long thisTimeShortestPathWeight = Integer.MAX_VALUE;
                int thisTimeShortestPathIndex = index;
                for (Integer i : prepareConfirm) {
                    long path = shortestPath[i];
                    if (thisTimeShortestPathWeight > path) {
                        thisTimeShortestPathWeight = path;
                        thisTimeShortestPathIndex = i;
                    }
                }

                // 将当前的最短路径标记为已确认（移除待确认）
                prepareConfirm.remove(thisTimeShortestPathIndex);

                // 遍历下一个节点
                index = thisTimeShortestPathIndex;
            }

            // 遍历完成，返回最短路径的路径数量
            return shortestPathNums[n - 1];
        }

        class Road {
            int node;
            int weight;

            public Road(int node, int weight) {
                this.node = node;
                this.weight = weight;
            }
        }
    }
}
