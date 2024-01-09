package M03_栈.基于链表实现的栈;

/**
 * 力扣155. 最小栈
 *
 * @author Chimm Huang
 * @date 2024/1/9
 */
public class LeetCode_155_Medium {

    class MinStack {
        class Node {
            int val;
            Node nextNode;
            Node nextMinNode;

            public Node(int val) {
                this.val = val;
            }
        }

        Node minStackHead = new Node(-1);
        Node stackHead = new Node(-1);

        public MinStack() {
            /*
                head    → 6 → 3 → 5
                minHead → 3 → 5
             */
        }

        public void push(int val) {
            Node node = new Node(val);
            if (minStackHead.nextMinNode == null) {
                minStackHead.nextMinNode = node;
            } else {
                int minVal = minStackHead.nextMinNode.val;
                if (val < minVal) {
                    node.nextMinNode = minStackHead.nextMinNode;
                    minStackHead.nextMinNode = node;
                }
            }

            if (stackHead.nextNode == null) {
                stackHead.nextNode = node;
            } else {
                node.nextNode = stackHead.nextNode;
                stackHead.nextNode = node;
            }
        }

        public void pop() {
            Node nextNode = stackHead.nextNode;
            Node nextMinNode = minStackHead.nextMinNode;
            if (nextNode == nextMinNode) {
                minStackHead.nextMinNode = minStackHead.nextMinNode.nextMinNode;
            }
            stackHead.nextNode = nextNode.nextNode;
        }

        public int top() {
            return stackHead.nextNode.val;
        }

        public int getMin() {
            return minStackHead.nextMinNode.val;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}
