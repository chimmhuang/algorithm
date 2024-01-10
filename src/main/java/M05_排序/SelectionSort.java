package M05_排序;

/**
 * <h1>选择排序</h1>
 * <h1>时间复杂度：O(n²)</h1>
 *
 * <p>选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。</p>
 * <p><img src="https://static001.geekbang.org/resource/image/32/1d/32371475a0b08f0db9861d102474181d.jpg?wh=1142*856",width="500" height="400" alt="极客时间-数据结构与算法之美-11排序-选择排序"/><p>
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] a = new int[]{4, 5, 6, 3, 2, 1};
        selectionSort.sort(a);
    }

    public int[] sort(int[] a) {
        System.out.print("初始状态： ");
        print(a);

        int maxIndex = a.length - 1;
        for (int i = 0; i < a.length; i++) {
            System.out.print("第" + (i + 1) + "次选择排序：");
            int min = a[i];
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];
                    minIndex = j;
                }
            }
            a[minIndex] = a[i];
            a[i] = min;
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
