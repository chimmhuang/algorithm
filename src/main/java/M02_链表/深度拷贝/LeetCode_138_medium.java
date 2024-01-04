package M02_链表.深度拷贝;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣138. 随机链表的复制
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/description/">https://leetcode.cn/problems/copy-list-with-random-pointer/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/4
 */
public class LeetCode_138_medium {

    /*
       |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |-------------|
       |   | next    | → |   | next    | → |   | next    | → |   | next    | → |   | next    | → |             |
       | A |---------|   | B |---------|   | C |---------|   | D |---------|   | E |---------|   |     null    |
       |   | random  |   |   | random  |   |   | random  |   |   | random  |   |   | random  |   |             |
       |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |-------------|
                ↓                 ↓                ↓                  ↓                 ↓
               null               A                E                  C                 A



       我的解题思路：新建一个 map，key为节点，value为随机节点。然后创建一个哨兵节点，循环添加进去，最后返回


       |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |-------------|
       |   | next    | → |   | next    | → |   | next    | → |   | next    | → |   | next    | → |   | next    | → |             |
       | S |---------|   | A |---------|   | B |---------|   | C |---------|   | D |---------|   | E |---------|   |     null    |
       |   | random  |   |   | random  |   |   | random  |   |   | random  |   |   | random  |   |   | random  |   |             |
       |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |---|---------|   |-------------|
                                  ↓                 ↓                ↓                  ↓                 ↓
                                 null               A                E                  C                 A
     */


    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Map<Node, Node> nodeMap = new HashMap<>();

            Node firstNewNode = new Node(head.val);
            nodeMap.put(head, firstNewNode);

            Node currentNode = head;
            Node currentNewNode = firstNewNode;

            while (currentNode != null) {
                // 先给 next 节点赋值
                Node newNextNode = null;
                if (currentNode.next != null) {
                    if (nodeMap.containsKey(currentNode.next)) {
                        newNextNode = nodeMap.get(currentNode.next);
                    } else {
                        newNextNode = new Node(currentNode.next.val);
                        nodeMap.put(currentNode.next, newNextNode);
                    }
                    currentNewNode.next = newNextNode;
                }

                // 再给 random 节点赋值
                Node newRandomNode = null;
                if (currentNode.random != null) {
                    if (nodeMap.containsKey(currentNode.random)) {
                        newRandomNode = nodeMap.get(currentNode.random);
                    } else {
                        newRandomNode = new Node(currentNode.random.val);
                        nodeMap.put(currentNode.random, newRandomNode);
                    }
                    currentNewNode.random = newRandomNode;
                }

                currentNode = currentNode.next;
                currentNewNode = newNextNode;
            }
            return firstNewNode;
        }
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


}
