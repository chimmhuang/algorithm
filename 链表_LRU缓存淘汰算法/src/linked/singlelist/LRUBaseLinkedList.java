package linked.singlelist;

import java.util.LinkedList;

/**
 * 基于单链表LRU算法（java）
 *
 * @author Chimm Huang
 * @date 2020/10/22
 */
public class LRUBaseLinkedList<E> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     * 该节点其实就是哨兵，可以保证后续每一个节点的操作都是一致的
     */
    private final Node<E> headNode;

    /**
     * 链表容量
     */
    private transient int capacity = 10;

    /**
     * 链表长度
     */
    private int length = 0;

    public LRUBaseLinkedList() {
        this.headNode = new Node<>();
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new Node<>();
        this.capacity = capacity;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node() {
        }
    }

    public boolean add(E e) {
        if (capacity == length) {
            // 当链表表格已经满了的时候，需要删除最前面的元素
            
        }

        // 将元素添加到末尾

    }


    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("s");
    }
}
