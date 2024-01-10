package M04_队列.基于链表实现的队列;

/**
 * 基于链表实现的队列
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class LinkedQueue {

    /*
            |---|
            | S |
            |---|
         next    pre
          ↓       ↓
        -------------
          C | B | A
        -------------
     */
    class Node {
        String val;
        Node next;
        Node pre;

        public Node(String val) {
            this.val = val;
        }
    }

    Node sentinel = new Node("");
    int capacity = 10;
    int size = 0;

    public LinkedQueue(int capacity) {
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        this.capacity = capacity;
    }

    /**
     * 入队
     */
    public void enqueue(String item) {
        Node node = new Node(item);
        if (size == capacity) {
            // 队列满了，就不允许添加了
            return;
        }
        sentinel.next.pre = node;
        node.next = sentinel.next;
        node.pre = sentinel;
        sentinel.next = node;
        size++;
    }

    /**
     * 出队
     */
    public String dequeue() {
        Node pre = sentinel.pre;
        pre.pre.next = sentinel;
        sentinel.pre = pre.pre;
        size--;
        return pre.val;
    }

    public void printAll() {
        Node currentNode = sentinel.pre;
        while (currentNode != sentinel) {
            System.out.print(currentNode.val + " ");
            currentNode = currentNode.pre;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue(5);
        linkedQueue.enqueue("1");
        linkedQueue.enqueue("2");
        linkedQueue.enqueue("3");
        linkedQueue.enqueue("4");
        linkedQueue.enqueue("5");
        linkedQueue.enqueue("6");

        linkedQueue.printAll();

        linkedQueue.dequeue();
        linkedQueue.dequeue();

        linkedQueue.printAll();

        linkedQueue.enqueue("7");
        linkedQueue.enqueue("8");
        linkedQueue.enqueue("9");

        linkedQueue.printAll();
    }
}
