package M04_队列.用栈实现队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 力扣232. 用栈实现队列
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/description/">https://leetcode.cn/problems/implement-queue-using-stacks/description/</a>
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class LeetCode_232_Easy {

    class MyQueue {

        Deque<Integer> outStack = new ArrayDeque<>();
        Deque<Integer> inStack = new ArrayDeque<>();

        public MyQueue() {

        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            this.popElement2OutStack();
            return outStack.pop();
        }

        public int peek() {
            this.popElement2OutStack();
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void popElement2OutStack() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
}
