package M01_数组;

/**
 * @author Chimm Huang
 * @date 2022/11/14
 */
public class GenericArray<T> {

    private T[] data;
    private int size;

    /**
     * 传入容量，构造 Array
     * @param capacity 容量
     */
    public GenericArray(int capacity) {
        data = (T[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造，默认10的大小
     */
    public GenericArray() {
        this(10);
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取当前元素个数
    public int count() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 修改 index 位置的元素
     */
    public void set(int index, T t) {
        checkIndex(index);
        data[index] = t;
    }

    /**
     * 获取对应 index 位置的元素
     */
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * 查看数组是否包含元素
     */
    public boolean contains(T t) {
        for (int i = 0; i < data.length; i++) {
            T datum = data[i];
            if (datum.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应元素的下标, 未找到，返回 -1
     */
    public int find(T t) {
        for (int i = 0; i < data.length; i++) {
            T datum = data[i];
            if (datum.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在 index 位置，插入元素e, 时间复杂度 O(m+n)
     */
    public void add(int index, T t) {
        checkIndexForAdd(index);
        // 如果数组容量已满，则扩容
        if (size == data.length) {
            resize(data.length * 2);
        }

        // 需要将 index 后续的元素，后移一位
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = t;
        size++;
    }

    /**
     * 向数组头插入元素
     */
    public void addFirst(T e) {
        add(0, e);
    }

    /**
     * 向数组尾插入元素
     */
    public void addLast(T e) {
        add(size, e);
    }

    /**
     * 删除 index 位置的元素，并返回
     */
    public T remove(int index) {
        checkIndex(index);

        T removeT = data[index];

        // 需要将 index 后续的元素，向前移动一位
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        // 缩容，如果当前大小 = 数组长度的 1/4，则缩容为 1/2
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return removeT;
    }

    /**
     * 删除第一个元素
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * 删除末尾元素
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除指定元素
     */
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * 扩容方法，时间复杂度 O(n)
     */
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }
    }

    private void checkIndexForAdd(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index <= size.");
        }
    }

}
