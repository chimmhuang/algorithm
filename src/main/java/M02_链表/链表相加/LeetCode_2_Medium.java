package M02_链表.链表相加;

import java.util.List;

/**
 * 力扣2.两数相加 [中等]
 *
 * @author Chimm Huang
 * @date 2024/1/5
 */
public class LeetCode_2_Medium {

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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }

            ListNode l1CurrentNode = l1;
            ListNode l2CurrentNode = l2;
            ListNode sentinel = new ListNode();
            ListNode newNodeCurrent = sentinel;

            int overTen = 0;
            while (l1CurrentNode != null || l2CurrentNode != null || overTen == 1) {
                int l1Val = 0;
                if (l1CurrentNode != null) {
                    l1Val = l1CurrentNode.val;
                    l1CurrentNode = l1CurrentNode.next;
                }
                int l2Val = 0;
                if (l2CurrentNode != null) {
                    l2Val = l2CurrentNode.val;
                    l2CurrentNode = l2CurrentNode.next;
                }

                int sum = l1Val + l2Val + overTen;
                overTen = 0;

                if (sum >= 10) {
                    overTen = 1;
                    sum %= 10;
                }

                ListNode newNode = new ListNode(sum);
                newNodeCurrent.next = newNode;
                newNodeCurrent = newNode;
            }

            return sentinel.next;
        }
    }


     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}
