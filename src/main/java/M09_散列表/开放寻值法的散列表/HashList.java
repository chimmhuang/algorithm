package M09_散列表.开放寻值法的散列表;

/**
 * 开放寻值法，若 hash 冲突，则往下寻找数组为空的位置，并插入。
 * 散列表查询元素的时间复杂度为 O(1)，但是开放寻值法，最坏的情况下可能会遍历整个数组，所以最坏情况为 O(n)
 *
 * @author Chimm.Huang
 * @date 2024/1/27
 */
public class HashList<E> {

    private Object[] elements;

    /**
     * 链表容量
     */
    private transient int capacity = 5;

    private int length = 0;

    public HashList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    public void add(E data) {
        if (length == capacity) {
            throw new RuntimeException("容量已满，禁止插入");
        }

        int index = this.hash(data);
        if (elements[index] == null) {
            elements[index] = data;
            return;
        }

        int insertIndex = index + 1;
        if (insertIndex == capacity) {
            insertIndex = 0;
        }
        while (insertIndex != index) {
            if (elements[insertIndex] != null) {
                insertIndex++;
                if (insertIndex == capacity) {
                    insertIndex = 0;
                }
            } else {
                elements[insertIndex] = data;
                break;
            }
        }
    }

    public E get(E data) {
        int times = 1;
        int index = this.hash(data);

        if (elements[index] == null) {
            System.out.println("该元素不存在");
            return null;
        }

        System.out.println("第" + (times++) + "次查询元素：" + data + "，当前结果为：" + elements[index]);
        if (elements[index] == data) {
            return (E) elements[index];
        }

        int queryIndex = index + 1;
        if (queryIndex == capacity) {
            queryIndex = 0;
        }

        while (queryIndex != index) {
            System.out.println("第" + (times++) + "次查询元素：" + data + "，当前结果为：" + elements[queryIndex]);
            if (elements[queryIndex] != data) {
                queryIndex++;
                if (queryIndex == capacity) {
                    queryIndex = 0;
                }
            } else {
                return (E) elements[queryIndex];
            }
        }
        System.out.println("未查询到该元素");
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];
            sb.append(element == null ? "null" : element);
            if (i != elements.length - 1) {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 对值进行 hash，返回数组索引
     *
     * @return 数组索引
     */
    private int hash(E data) {
        int hashCode = data.hashCode();
        // 对 数组长度进行取 余，判断落在哪个索引上。
        return hashCode % capacity;
    }

    public static void main(String[] args) {
        HashList<Integer> hashList = new HashList<>(5);
        hashList.add(1);
        hashList.add(2);
        hashList.add(11);
        hashList.add(3);
        hashList.add(4);

        System.out.println("初始化链表：" + hashList.toString());

        System.out.println(hashList.get(1));
        System.out.println(hashList.get(11));
        System.out.println(hashList.get(4));
        System.out.println(hashList.get(5));
    }
}
