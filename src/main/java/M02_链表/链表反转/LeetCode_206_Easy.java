package M02_链表.链表反转;

/**
 * 力扣206.反转链表 [简单]
 * <a href="https://leetcode.cn/problems/reverse-linked-list/description/">https://leetcode.cn/problems/reverse-linked-list/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/3
 */
public class LeetCode_206_Easy {

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
        public ListNode reverseList(ListNode head) {
            if (head != null) {
                ListNode next = head.next;
                head.next = null;
                if (next != null) {
                    return this.reverse(head, next);
                }
            }
            return head;
        }

        private ListNode reverse(ListNode head, ListNode nextNode) {
            /*
                A → B → C → null

                第1次：null ← A   B → C
                第2次：null ← A ← B   C
                第3次：null ← A ← B ← C

                返回 C 节点
             */
            ListNode nextNextNode = nextNode.next;
            nextNode.next = head;
            if (nextNextNode == null) {
                // nextNode 为最后一个节点。
                return nextNode;
            }
            return this.reverse(nextNode, nextNextNode);
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}