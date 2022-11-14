package M02_链表;

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
