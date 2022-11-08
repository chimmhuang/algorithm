package M01_链表;

import M01_链表.LRU缓存淘汰算法.LRUBaseLinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chimm Huang
 * @date 2022/11/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node<E> {
    private E element;
    private Node<E> next;
}
