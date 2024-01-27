package M09_散列表.LRU缓存淘汰算法;

/**
 * 基于散列表+链表的 LRU算法（java）
 *
 * 散列表查询元素的时候，时间复杂度为 O(1) + O(n)
 * 我还写了一个 基于单链表 LRU算法 {@link M02_链表.LRU缓存淘汰算法.LRUBaseLinkedList}
 *
 * <p><img src="https://static001.geekbang.org/resource/image/ea/6e/eaefd5f4028cc7d4cfbb56b24ce8ae6e.jpg?wh=1142*726" width=550 height="350"/></p>
 * 我们使用双向链表存储数据，链表中的每个结点处理存储数据（data）、前驱指针（prev）、后继指针（next）之外，还新增了一个特殊的字段 hnext。
 * 这个 hnext 有什么作用呢？因为我们的散列表是通过链表法解决散列冲突的，所以每个结点会在两条链中。
 * 一个链是刚刚我们提到的双向链表，另一个链是散列表中的拉链。前驱和后继指针是为了将结点串在双向链表中，hnext 指针是为了将结点串在散列表的拉链中。
 *
 * @author Chimm.Huang
 * @date 2024/1/26
 */
public class LRUBaseHashList<E> {

    /**
     * 默认链表容量
     */
    private static final Integer DEFAULT_CAPACITY = 5;

    /**
     * 头结点
     * 该节点其实就是哨兵，可以保证后续每一个节点的操作都是一致的
     */
    private final Node<E> headNode = new Node<>();

    /**
     * 链表长度
     */
    private int length = 0;

    /**
     * 链表容量
     */
    private transient int capacity = 5;

    /**
     * 为了方便测试，这里只初始化长度为3的数组
     */
    private int arrayLength = 3;

    private Node<E>[] elements = new Node[arrayLength];

    public LRUBaseHashList() {
    }

    public LRUBaseHashList(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 添加元素
     * 在 哨兵 head 后面添加即可
     * head -> E -> next
     */
    public void add(E data) {
        // 先查找队列中是否已存在该元素
        Node<E> existsNode = this.findNode(data);

        // 如果链表中存在，则先删除，再添加
        if (existsNode != null) {
            this.removeNode(existsNode);
        }

        int index = this.hash(data);
        if (length == capacity) {
            // 删除最后一个
            removeLast();
        }
        // 进行正常的添加
        Node<E> node = new Node<>();
        node.element = data;
        node.pre = headNode;
        if (headNode.next != null) {
            headNode.next.pre = node;
        }
        node.next = headNode.next;
        headNode.next = node;

        if (elements[index] == null) {
            elements[index] = node;
        } else {
            Node<E> currentNode = elements[index];
            while (currentNode.hnext != null) {
                currentNode = currentNode.hnext;
            }
            currentNode.hnext = node;
            node.hpre = currentNode;
        }
        length++;
    }

    /**
     * 对值进行 hash，返回数组索引
     *
     * @return 数组索引
     */
    private int hash(E data) {
        int hashCode = data.hashCode();
        // 对 数组长度进行取 余，判断落在哪个索引上。
        return hashCode % arrayLength;
    }

    /**
     * 获取查找到元素的前一个结点
     */
    private Node<E> findNode(E data) {
        int index = this.hash(data);
        if (elements[index] != null) {
            Node<E> node = elements[index];
            while (node != null) {
                if (node.element == data) {
                    return node;
                }
                node = node.hnext;
            }
        }
        return null;
    }

    /**
     * 删除节点
     */
    private void removeNode(Node<E> delNode) {
        Node<E> preNode = delNode.pre;
        Node<E> next = delNode.next;
        Node<E> hnext = delNode.hnext;
        Node<E> hpre = delNode.hpre;

        preNode.next = next;
        if (hpre == null) {
            int index = hash(delNode.element);
            elements[index] = hnext;
        } else {
            hpre.hnext = hnext;
        }
        delNode.pre = null;
        delNode.next = null;
        delNode.hnext = null;
        delNode.hpre = null;
        delNode = null;
        length--;
    }

    /**
     * 删除尾节点
     *
     * | head | node1 | node2 | lastNode |
     * | head | node1 | nde2 |
     */
    private void removeLast() {
        Node<E> currentNode = headNode;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        removeNode(currentNode);
    }

    @Override
    public String toString() {
        StringBuilder headSb = new StringBuilder("[");
        Node<E> currentNode = headNode.next;
        while (currentNode != null) {
            headSb.append(currentNode.element);
            if (currentNode.next != null) {
                headSb.append(" ");
            }
            currentNode = currentNode.next;
        }
        headSb.append("]");

        StringBuilder elementsSb = new StringBuilder("[");
        for (int i = 0; i < elements.length; i++) {
            Node<E> node = elements[i];
            if (node == null) {
                elementsSb.append("null");
            } else {
                while (node != null) {
                    elementsSb.append(node.element);
                    if (node.hnext != null) {
                        elementsSb.append("->");
                    }
                    node = node.hnext;
                }
            }
            if (i != elements.length - 1) {
                elementsSb.append(" ");
            }
        }
        elementsSb.append("]");

        return "LRU链表为：" + headSb.toString() + "  散列表为：" + elementsSb.toString();
    }


    /* ---------------------- 测试类 ----------------------*/
    public static void main(String[] args) {
        LRUBaseHashList<Integer> list = new LRUBaseHashList<>(7);
        // h -> 1
        list.add(1);
        // h -> 1 -> 2
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("初始化的 List: " + list);
        list.add(7);
        System.out.println("第一次添加元素后 List: " + list);
        list.add(8);
        System.out.println("第二次添加元素后 List: " + list);
        list.add(2);
        System.out.println("添加重复元素后 List: " + list);
    }

    class Node<E> {
        private E element;
        private Node<E> pre;
        private Node<E> next;
        private Node<E> hpre;
        private Node<E> hnext;
    }
}
