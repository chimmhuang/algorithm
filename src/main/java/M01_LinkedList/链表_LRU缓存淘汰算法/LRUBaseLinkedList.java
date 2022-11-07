package M01_LinkedList.链表_LRU缓存淘汰算法;

import lombok.Data;

/**
 * 基于单链表LRU算法（java）
 *
 * @author Chimm Huang
 * @date 2022/11/7
 */
public class LRUBaseLinkedList<E> {

    /**
     * 默认链表容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;

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

    @Data
    private static class Node<E> {
        private E element;
        private Node<E> next;
    }

}
