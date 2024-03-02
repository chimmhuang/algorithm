package M12_广度优先搜索.受限条件下可到达节点的数目;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 力扣2368. 受限条件下可到达节点的数目 [中等]
 *
 * @author Chimm Huang
 * @date 2024/3/2
 */
public class LeetCode_2368_Medium {

    class Solution {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            // 先将二维数组转换为图
            LinkedList<Integer>[] graph = new LinkedList[n];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new LinkedList<>();
            }

            for (int i = 0; i < edges.length; i++) {
                int[] edgeI = edges[i];
                graph[edgeI[0]].add(edgeI[1]);
                graph[edgeI[1]].add(edgeI[0]);
            }

            // 转换完毕后，进行BFS遍历
            return bfs(graph, restricted);
        }

        private int bfs(LinkedList<Integer>[] graph, int[] restricted) {
            Set<Integer> restrictedSet = new HashSet<>();
            for (int i = 0; i < restricted.length; i++) {
                restrictedSet.add(restricted[i]);
            }

            Deque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[graph.length];
            // 从0为起点
            queue.add(0);
            visited[0] = true;

            int num = 1;
            while (!queue.isEmpty()) {
                Integer edge = queue.poll();
                visited[edge] = true;

                LinkedList<Integer> list = graph[edge];
                for (Integer i : list) {
                    if (!visited[i] && !restrictedSet.contains(i)) {
                        queue.add(i);
                        num++;
                    }
                }
            }

            return num;
        }
    }
}
