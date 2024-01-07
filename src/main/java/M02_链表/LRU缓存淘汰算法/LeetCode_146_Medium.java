package M02_链表.LRU缓存淘汰算法;

import java.util.HashMap;
import java.util.Map;

/**
 * 力扣146. LRU缓存
 * <a href="https://leetcode.cn/problems/lru-cache/description/">https://leetcode.cn/problems/lru-cache/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/7
 */
public class LeetCode_146_Medium {

    class LRUCache {

        /**
         * 在类里面构建 Node 节点
         */
        class LRUNode {
            int key;
            int value;
            LRUNode nextNode;
            LRUNode preNode;

            public LRUNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        /**
         * 存储的节点值
         */
        Map<Integer, LRUNode> nodeValueMap = new HashMap<>();

        /**
         * LRU链表的长度
         */
        int capacity;

        LRUNode headNode = new LRUNode(-1, -1);
        LRUNode tailNode = new LRUNode(-1, -1);


        public LRUCache(int capacity) {
            this.capacity = capacity;
            headNode.nextNode = tailNode;
            tailNode.preNode = headNode;
        }

        public int get(int key) {
            if (nodeValueMap.containsKey(key)) {
                LRUNode node = nodeValueMap.get(key);
                this.move2Tail(node);
                return node.value;
            }
            return -1;
        }

        /**
         * 移动到尾节点
         */
        private void move2Tail(LRUNode node) {
            /*
                第一步：head ←→ A ←→ B ←→ C ←→ D ←→ E ←→ F ←→ tail
                第二步：get C
                第三步：head ←→ A ←→ B ←→ D ←→ E ←→ F ←→ C ←→ tail
             */
            LRUNode preNode = node.preNode;
            LRUNode nextNode = node.nextNode;
            preNode.nextNode = nextNode;
            nextNode.preNode = preNode;

            LRUNode tailPreNode = tailNode.preNode;
            tailPreNode.nextNode = node;
            node.preNode = tailPreNode;
            node.nextNode = tailNode;
            tailNode.preNode = node;
        }

        public void put(int key, int value) {
            // 若存在，则返回该值，并更新到尾节点
            if (nodeValueMap.containsKey(key)) {
                LRUNode currentNode = nodeValueMap.get(key);
                currentNode.value = value;
                this.move2Tail(currentNode);
                return;
            }

            LRUNode newNode = new LRUNode(key, value);
            // 超出了LRU链表长度，删除最远端的节点
            if (nodeValueMap.size() + 1 > capacity) {
                LRUNode headNextNode = headNode.nextNode;
                headNode.nextNode = headNextNode.nextNode;
                headNextNode.nextNode.preNode = headNode;
                nodeValueMap.remove(headNextNode.key);
            }

            // 将元素添加到末尾
            LRUNode tailPreNode = tailNode.preNode;
            tailPreNode.nextNode = newNode;
            newNode.preNode = tailPreNode;
            tailNode.preNode = newNode;
            newNode.nextNode = tailNode;
            nodeValueMap.put(key, newNode);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
