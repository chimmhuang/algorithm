package M05_排序;

/**
 * <h1>插入排序</h1>
 * <h1>时间复杂度：O(n²)</h1>
 *
 * <p><img src="https://static001.geekbang.org/resource/image/7b/a6/7b257e179787c633d2bd171a764171a6.jpg?wh=1142*699",width="500" height="280" alt="极客时间-数据结构与算法之美-11排序-插入排序"/><p>
 *
 * <h2>插入排序具体是如何借助上面的思想来实现排序的呢？</h2>
 * <p>首先，我们将数组中的数据分为两个区间，已排序区间和未排序区间。初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。</p>
 * <p>如图所示，要排序的数据是 4，5，6，1，3，2，其中左侧为已排序区间，右侧是未排序区间。</p>
 * <p><img src="https://static001.geekbang.org/resource/image/b6/e1/b60f61ec487358ac037bf2b6974d2de1.jpg?wh=1142*699",width="500" height="280" alt="极客时间-数据结构与算法之美-11排序-插入排序"/><p>
 * <p>插入排序也包含两种操作，一种是元素的比较，一种是元素的移动。当我们需要将一个数据 a 插入到已排序区间时，需要拿 a 与已排序区间的元素依次比较大小，找到合适的插入位置。找到插入点之后，我们还需要将插入点之后的元素顺序往后移动一位，这样才能腾出位置给元素 a 插入。</p>
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class InsertionSort {

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] a = new int[]{4, 5, 6, 1, 3, 2};
        insertionSort.sort(a);
    }

    public int[] sort(int[] a) {
        System.out.print("初始状态： ");
        print(a);
        int times = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                System.out.print("第" + (times++) + "次插入排序：");
                int ai = a[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (ai < a[j]) {
                        a[j + 1] = a[j];
                        a[j] = ai;
                    } else {
                        break;
                    }
                }
                print(a);
            }
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
