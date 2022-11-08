package M01_LinkedList.链表_LRU缓存淘汰算法;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * 基于单链表LRU算法（java）
 *
 * @author Chimm Huang
 * @date 2022/11/7
 */
@Data
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

    @Data @AllArgsConstructor @NoArgsConstructor
    private static class Node<E> {
        private E element;
        private Node<E> next;
    }

    /**
     * 添加元素
     * 在 哨兵 head 后面添加即可
     * head -> E -> next
     */
    public void add(E data) {
        // 先查找队列中是否已存在该元素
        Node<E> preNode = findPreNode(data);

        // 如果链表中存在，则先删除，再添加
        if (preNode != null) {
            /*
                链表中存在，将其移动到首位
                | head | preNode | node | next1 |
                | head | node | preNode | next1 |
             */
            removeNext(preNode);
            insertAtBegin(data);
        } else {
            // 判断是否超出了设置的长度
            if (length >= this.capacity) {
                // 删除尾节点
                removeLast();
            }
            insertAtBegin(data);
        }
    }

    /**
     * 在开头增加元素
     */
    private void insertAtBegin(E data) {
        Node<E> temp = headNode.getNext();
        headNode.setNext(new Node<>(data, temp));
        length++;
    }

    /**
     * 获取查找到元素的前一个结点
     */
    private Node<E> findPreNode(E data) {
        Node node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除下一个节点
     *
     * | head | preNode | next1 | next2 |
     * | head | preNode | next2 |
     */
    private void removeNext(Node preNode) {
        Node next = preNode.getNext();
        preNode.setNext(next == null ? null : next.getNext());
        next = null;
        length--;
    }

    /**
     * 删除尾节点
     *
     * | head | node1 | node2 | lastNode |
     * | head | node1 | nde2 |
     */
    private void removeLast() {
        Node<E> preNode = headNode;
        Node<E> headNextNode = headNode.getNext();
        if (headNextNode == null) {
            // 空链表直接返回
            return;
        }

        // 获取倒数第二个节点
        while (preNode.getNext().getNext() != null) {
            preNode = preNode.getNext();
        }

        Node<E> temp = preNode.getNext();
        preNode.setNext(null);
        temp = null;
        length--;
    }

    /* ---------------------- 测试类 ----------------------*/
    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("初始化的 List: " + JSON.toJSONString(list));
        list.add(5);
        System.out.println("第一次添加元素后 List: "+JSON.toJSONString(list));
        list.add(6);
        System.out.println("第二次添加元素后 List: "+JSON.toJSONString(list));
        list.add(2);
        System.out.println("添加重复元素后 List: "+JSON.toJSONString(list));
    }
}
