package M05_排序;

/**
 * <h1>冒泡排序</h1>
 * <h1>时间复杂度：O(n²)</h1>
 *
 * <p><img src="https://static001.geekbang.org/resource/image/40/e9/4038f64f47975ab9f519e4f739e464e9.jpg?wh=1142*741",width="500" height="300" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 * <p><img src="https://static001.geekbang.org/resource/image/92/09/9246f12cca22e5d872cbfce302ef4d09.jpg?wh=1142*749",width="500" height="280" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 * <p><img src="https://static001.geekbang.org/resource/image/fe/0f/fe107c06da8b290fb78fcce4f6774c0f.jpg?wh=1142*299",width="500" height="120" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] a = new int[]{4, 5, 6, 3, 2, 1};
        bubbleSort.sort(a);
    }

    public int[] sort(int[] a) {
        System.out.print("初始状态： ");
        print(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print("第" + (i + 1) + "次冒泡：");
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    // 交换
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
            print(a);
        }
        return a;
    }

    private void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
