package M01_链表.链表反转;

import M01_链表.Node;
import com.alibaba.fastjson2.JSON;

/**
 * @author Chimm Huang
 * @date 2022/11/8
 */
public class SingleLinkedList {

    /*
    方式一：
        待反转的链表: head → A → B → C
                           ↑
        新链表:      head → A.next → A
                    head → B → A

        待反转的链表: head → A → B → C
                               ↑
        新链表:      head → B.next → B
                    head → C → B → A
     */

    /**
     方式二：<a href="https://blog.csdn.net/qq_42351880/article/details/88637387">CSDN反转链表</a>
     <p/>
         待反转的链表: head → A → B → C

                             head
                              ↓
                     null ← A B → C

                     null ← A ← B C ← head

                     null ← A ← B ← C ← head
     */
    public static <E> Node<E> revert2(Node<E> node) {
        // newNode 的添加顺序为：null ← ... ← head
        Node<E> newNode = null;
        while (node != null) {
            Node<E> next = node.getNext();
            node.setNext(newNode);
            newNode = node;
            node = next;
        }
        return newNode;
    }

    public static void main(String[] args) {
        Node<Integer> node =
                new Node<>(1,
                        new Node<>(2,
                                new Node<>(3,
                                        new Node<>(4, null))));

        System.out.println("初始化的 node: " + JSON.toJSONString(node));
        System.out.println("反转后的 node: " + JSON.toJSONString(revert2(node)));
    }


}
