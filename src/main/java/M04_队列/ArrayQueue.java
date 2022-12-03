package M04_队列;

/**
 * 基于数组实现的队列
 *
 *
 * @author huangshuai
 * @date 2022/12/3
 */
public class ArrayQueue {

    /*
                head     tail
                ----------------
       head --> | A | B | C |  |
                ----------------
                        ↓
                    head tail
                ----------------
       head --> |   | B | C |  |
                ----------------
                        ↓
                head tail
                ----------------
       head --> | B | C |   |  |
                ----------------
     */

    /**
     * 数组:items
     */
    private String[] items;

    /**
     * 长度：length
     */
    private int length;

    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.length = capacity;
    }

    /**
     * 入对
     */
    public boolean enqueue(String item) {
        // 队列已满
        if (tail == length) {
            // 判断数组是否已被塞满
            if (head == 0) {
                return false;
            }
            String[] newArr = new String[length];
            int newIndex = 0;
            for (int i = head; i < tail; i++) {
                newArr[newIndex++] = items[i];
            }
            items = newArr;
            head = 0;
            tail = newIndex;
        }
        items[tail++] = item;
        return true;
    }

    /**
     * 出队
     */
    public String dequeue() {
        // 没有数据
        if (head == tail) {
            return null;
        }
        return items[head++];
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");
        arrayQueue.enqueue("4");
        arrayQueue.enqueue("5");
        arrayQueue.enqueue("6");

        arrayQueue.printAll();

        arrayQueue.dequeue();
        arrayQueue.dequeue();

        arrayQueue.printAll();

        arrayQueue.enqueue("7");
        arrayQueue.enqueue("8");
        arrayQueue.enqueue("9");

        arrayQueue.printAll();
    }


}
