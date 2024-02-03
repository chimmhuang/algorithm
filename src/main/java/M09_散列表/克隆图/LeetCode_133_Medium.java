package M09_散列表.克隆图;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 力扣133. 克隆图 [中等]
 * <a href="https://leetcode.cn/problems/clone-graph/description/">https://leetcode.cn/problems/clone-graph/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/3
 */
public class LeetCode_133_Medium {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors = Arrays.asList(node2, node4);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node2, node4);
        node4.neighbors = Arrays.asList(node1, node3);
        Node node = solution.cloneGraph(node1);
        System.out.println(node);
    }

    /*
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    */

    static class Solution {
        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }
            if (node.neighbors == null || node.neighbors.isEmpty()) {
                return new Node(node.val);
            }
            // 将 node，转为哈希数组
            Map<Integer, List<Node>> relationMap = new HashMap<>();

            Deque<Node> deque = new ArrayDeque<>();
            deque.add(node);

            while (!deque.isEmpty()) {
                Node node1 = deque.poll();
                relationMap.put(node1.val, node1.neighbors);
                for (Node neighbor : node1.neighbors) {
                    if (!relationMap.containsKey(neighbor.val)) {
                        deque.add(neighbor);
                    }
                }
            }

            // 开始深度克隆
            Map<Integer, Node> newNodeMap = new HashMap<>();
            Node newNode = new Node(node.val);
            newNodeMap.put(newNode.val, newNode);

            for (Map.Entry<Integer, List<Node>> entry : relationMap.entrySet()) {
                Integer nodeVal = entry.getKey();
                Node newNode1 = newNodeMap.getOrDefault(nodeVal, new Node(nodeVal));

                // 完善新节点的关系
                List<Node> nodeRelationList = entry.getValue();
                List<Node> newNodeRelationList = new ArrayList<>(nodeRelationList.size());
                for (Node nodeRelation : nodeRelationList) {
                    Node newNodeRelation = newNodeMap.getOrDefault(nodeRelation.val, new Node(nodeRelation.val));
                    newNodeRelationList.add(newNodeRelation);
                    if (!newNodeMap.containsKey(newNodeRelation.val)) {
                        newNodeMap.put(newNodeRelation.val, newNodeRelation);
                    }
                }
                newNode1.neighbors = newNodeRelationList;
            }
            return newNode;
        }
    }


    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
