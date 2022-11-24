package M03_栈.基于链表实现的栈;

import M03_栈.Node;

/**
 * 基于链表实现的栈
 *
 * @author huangshuai
 * @date 2022/11/24
 */
public class StackBasedOnLinkedList<T> {

    /*
     *       ------------
     * -->    C | B | A |
     *       ------------
     */

    private Node<T> top = null;

    // 添加元素
    public void push(T value) {
        Node<T> tNode = new Node<>(value, null);
        if (top == null) {
            top = tNode;
        } else {
            tNode.setNext(top);
            top = tNode;
        }
    }

    // 弹出元素
    public void pop() {
        if (top == null) {
            return;
        }
        Node<T> next = top.getNext();
        top.setNext(null);
        top = next;
    }

    public void printAll() {
        Node p = top;
        while (p != null) {
            System.out.print(p.getElement() + " ");
            p = p.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StackBasedOnLinkedList<Integer> stack = new StackBasedOnLinkedList<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.printAll();

        stack.pop();
        stack.pop();

        stack.printAll();
    }
}
