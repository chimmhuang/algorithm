package M12_广度优先搜索.最短路径;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 最短路径
 *
 * 深度优先搜索算法和广度优先搜索算法都是基于“图”这种数据结构的。
 * 这是因为，图这种数据结构的表达能力很强，大部分涉及搜索的场景都可以抽象成“图”。
 *
 * @author Chimm.Huang
 * @date 2024/2/20
 */
public class ShortestPath {

    /*
        求 start 到 end 的最短路径

        start
          ↓
          0 - 1 - 2
          |   |   |
          3 - 4 - 5
              |   |
              6 - 7
              ↑
             end
     */
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        // 保存边
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);

        // 计算最短路径
        graph.bfs(0, 6);
    }


    static class Graph { // 无向图
        private int v; // 顶点的个数
        private final LinkedList<Integer>[] adj; // 邻接表

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int start, int end) { // 无向图一条边存两次
            adj[start].add(end);
            adj[end].add(start);
        }

        /**
         * 这段代码不是很好理解，里面有三个重要的辅助变量 visited、queue、prev。
         * 只要理解这三个变量，读懂这段代码估计就没什么问题了。
         *
         * visited 是用来记录已经被访问的顶点，用来避免顶点被重复访问。
         *          如果顶点 q 被访问，那相应的 visited[q]会被设置为 true。
         *
         * queue 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。因为广度优先搜索是逐层访问的，
         *          也就是说，我们只有把第 k 层的顶点都访问完成之后，才能访问第 k+1 层的顶点。
         *          当我们访问到第 k 层的顶点的时候，我们需要把第 k 层的顶点记录下来，
         *          稍后才能通过第 k 层的顶点来找第 k+1 层的顶点。所以，我们用这个队列来实现记录的功能。
         *
         * prev 用来记录搜索路径。当我们从顶点 s 开始，广度优先搜索到顶点 e 后，prev 数组中存储的就是搜索的路径。
         *          不过，这个路径是反向存储的。prev[w]存储的是，顶点 w 是从哪个前驱顶点遍历过来的。
         *          比如，我们通过顶点 2 的邻接表访问到顶点 3，那 prev[3]就等于 2。为了正向打印出路径，
         *          我们需要递归地来打印，你可以看下 print() 函数的实现方式。
         */
        public void bfs(int start, int end) {
            if (start == end) {
                return;
            }

            // 用于记录该节点是否已被访问过
            boolean[] visited = new boolean[v];
            visited[start] = true;

            // 用于存储已被访问过，但相邻的节点还没有被访问过的节点
            Deque<Integer> queue = new ArrayDeque<>();
            queue.push(start);

            // 用于存储搜索路径，反向存储，存储的是该节点是从哪里访问到的，默认初始化为-1 主要用于打印。
            int[] prev = new int[v];
            for (int i = 0; i < prev.length; i++) {
                prev[i] = -1;
            }

            // 开始进行广度访问
            while (!queue.isEmpty()) {
                Integer w = queue.pop();
                LinkedList<Integer> list = adj[w];
                if (list != null) {
                    for (Integer q : list) {
                        // 如果被访问过了，则直接跳过
                        if (!visited[q]) {
                            // 记录被访问的点
                            prev[q] = w;

                            // 如果等于了结果，则直接打印。
                            if (q == end) {
                                print(prev, start, end);
                                return;
                            }

                            visited[q] = true;
                            queue.add(q);
                        }
                    }
                }
            }
        }

        private void print(int[] prev, int start, int end) {
            if (start != end && prev[end] != -1) {
                print(prev, start, prev[end]);
            }
            System.out.print(end + " ");
        }
    }
}
