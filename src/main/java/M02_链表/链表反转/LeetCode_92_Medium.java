package M02_链表.链表反转;

/**
 * 力扣92.反转链表Ⅱ [中等]
 * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/description/">https://leetcode.cn/problems/reverse-linked-list-ii/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/4
 */
public class LeetCode_92_Medium {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            /*
               preNode = null
               remainNode = null
               leftNode = null
               rightNode = null
               currentNode = null
               nextNode = null
               left = 2
               right = 4
               -----------------------------
                    preNode
                       ↓
                 |S| → A → B → C → D → E
               -----------------------------
                                   remainNode
                                       ↓
                 |S| → A → B → C → D → E
               -----------------------------
              leftNode
                 ↓
                 B → C → D 进行反转 D ← C ← B
               -----------------------------
                    preNode     leftNode
                       ↓           ↓
                 |S| → A → D → C → B → E
               -----------------------------
             */
            int nodeNum = 1;
            ListNode sentinel = new ListNode();
            sentinel.next = head;

            ListNode preNode = null;
            ListNode remainNode = null;
            ListNode leftNode = null;

            ListNode currentNode = head;
            ListNode currentNextNode = currentNode.next;
            if (currentNextNode == null) {
                return head;
            }
            while (nodeNum <= right) {
                // pre 节点
                if (nodeNum == left - 1) {
                    preNode = currentNode;
                }
                // left 节点
                if (nodeNum == left) {
                    leftNode = currentNode;
                }

                // remain 节点
                if (nodeNum == right) {
                    if (preNode == null) {
                        sentinel.next = currentNode;
                    } else {
                        preNode.next = currentNode;
                    }
                    leftNode.next = currentNextNode;
                    break;
                }

                // 反转中间节点
                ListNode currentNextNextNode = currentNextNode.next;
                if (nodeNum >= left && nodeNum < right) {
                    // 反转
                    currentNextNode.next = currentNode;
                }

                currentNode = currentNextNode;
                currentNextNode = currentNextNextNode;
                nodeNum++;
            }
            return sentinel.next;
        }

        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }
    }
}
