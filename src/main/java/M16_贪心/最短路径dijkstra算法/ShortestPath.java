package M16_贪心.最短路径dijkstra算法;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 使用 dijkstra 算法计算从0到每个点的最短路径
 *
 * <img src="https://static001.geekbang.org/resource/image/e2/a9/e20907173c458fac741e556c947bb9a9.jpg?wh=1142*856" width="500" height="400"/>
 * @author Chimm.Huang
 * @date 2024/3/7
 */
public class ShortestPath {

    /*
                —— 2 ——     5
          —— 1     |
        0       —— 3 ——     5
          —— 4 ————————     5
     */
    public static void main(String[] args) {
        // 初始化图
        LinkedList<Graph>[] road = new LinkedList[6];
        for (int i = 0; i < road.length; i++) {
            road[i] = new LinkedList<>();
        }
        road[0].add(new Graph(1, 10));
        road[0].add(new Graph(4, 15));

        road[1].add(new Graph(0, 10));
        road[1].add(new Graph(2, 15));
        road[1].add(new Graph(3, 2));

        road[2].add(new Graph(1, 15));
        road[2].add(new Graph(5, 5));
        road[2].add(new Graph(3, 1));

        road[3].add(new Graph(1, 2));
        road[3].add(new Graph(2, 1));
        road[3].add(new Graph(5, 12));

        road[4].add(new Graph(0, 15));
        road[4].add(new Graph(5, 10));

        road[5].add(new Graph(2, 5));
        road[5].add(new Graph(3, 12));
        road[5].add(new Graph(4, 10));

        // 定义一个数组，用于存储从0到该点的最短距离
        int[] shortestPath = new int[6];
        // 初始化为最大值
        for (int i = 0; i < shortestPath.length; i++) {
            shortestPath[i] = Integer.MAX_VALUE;
        }
        shortestPath[0] = 0;

        // 定义一个数组，用于存储达到该位置的前一个点
        int[] preNode = new int[6];

        // 定义一个数组，用于存储该位置是否已确认了最短路径
        Set<Integer> prepareConfirm = new HashSet<>();
        for (int i = 1; i < 6; i++) {
            prepareConfirm.add(i);
        }

        // 从0开始遍历
        int index = 0;
        while (!prepareConfirm.isEmpty()) {
            LinkedList<Graph> relationList = road[index];

            // 计算与之相关节点的最短路径
            for (int i = 0; i < relationList.size(); i++) {
                Graph graph = relationList.get(i);
                if (prepareConfirm.contains(graph.node)) {
                    int newPath = graph.weight + shortestPath[index];
                    // 更新每个节点的最短路径
                    if (shortestPath[graph.node] > newPath) {
                        shortestPath[graph.node] = newPath;
                        preNode[graph.node] = index;
                    }
                }
            }

            // 计算完毕后，选择当前最短路径的节点，将其标记为已确认（移除待确认）
            int thisTimeShortestPathWeight = Integer.MAX_VALUE;
            int thisTimeShortestPathIndex = index;
            for (Integer i : prepareConfirm) {
                int path = shortestPath[i];
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

        // 打印最短路径
        System.out.println("最短距离：" + shortestPath[5]);
        print(5, preNode);
    }

    private static void print(int node, int[] preNode) {
        if (node == 0) {
            System.out.print(node + " → ");
            return;
        }

        print(preNode[node], preNode);
        if (node != 5) {
            System.out.print(node + " → ");
        } else {
            System.out.println(node);
        }
    }

    static class Graph {
        int node;
        int weight;

        public Graph(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
