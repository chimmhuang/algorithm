package M06_二分法.二分查询;

/**
 * <h1>二分查找</h1>
 * <h1>时间复杂度：O(logn)</h1>
 *
 * <p>二分查找是一种非常简单易懂的快速查找算法，生活中到处可见。比如说，我们现在来做一个猜字游戏。我随机写一个 0 到 99 之间的数字，然后你来猜我写的是什么。猜的过程中，你每猜一次，我就会告诉你猜的大了还是小了，直到猜中为止。你来想想，如何快速猜中我写的数字呢？假设我写的数字是 23，你可以按照下面的步骤来试一试。（如果猜测范围的数字有偶数个，中间数有两个，就选择较小的那个。）</p>
 * <p><img src="https://static001.geekbang.org/resource/image/9d/9b/9dadf04cdfa7b3724e0df91da7cacd9b.jpg?wh=1142*846",width="500" height="400" alt="极客时间-数据结构与算法之美-15二分查找"/></p>
 * <p>7 次就猜出来了，是不是很快？这个例子用的就是二分思想，按照这个思想，即便我让你猜的是 0 到 999 的数字，最多也只要 10 次就能猜中。不信的话，你可以试一试。</p>
 * <br/>
 * <p>这是一个生活中的例子，我们现在回到实际的开发场景中。假设有 1000 条订单数据，已经按照订单金额从小到大排序，每个订单金额都不同，并且最小单位是元。我们现在想知道是否存在金额等于 19 元的订单。如果存在，则返回订单数据，如果不存在则返回 null。</p>
 * <br/>
 * <p>最简单的办法当然是从第一个订单开始，一个一个遍历这 1000 个订单，直到找到金额等于 19 元的订单为止。但这样查找会比较慢，最坏情况下，可能要遍历完这 1000 条记录才能找到。那用二分查找能不能更快速地解决呢？</p>
 * <br/>
 * <p>为了方便讲解，我们假设只有 10 个订单，订单金额分别是：8，11，19，23，27，33，45，55，67，98。</p>
 * <br/>
 * <p>还是利用二分思想，每次都与区间的中间数据比对大小，缩小查找区间的范围。为了更加直观，我画了一张查找过程的图。其中，low 和 high 表示待查找区间的下标，mid 表示待查找区间的中间元素下标。</p>
 * <br/>
 * <p><img src="https://static001.geekbang.org/resource/image/8b/29/8bce81259abf0e9a06f115e22586b829.jpg?wh=1142*819",width="500" height="400" alt="极客时间-数据结构与算法之美-15二分查找"/></p>
 * <br/>
 *
 *
 * @author Chimm Huang
 * @date 2024/1/10
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23, 24, 25};
        BinarySearch binarySearch = new BinarySearch(arr);

        int findNum = 22;
        int index = binarySearch.find(findNum);
        System.out.println("----------------");
        System.out.println("要查询的数字为：" + findNum);
        if (index != -1) {
            System.out.println("查询到的索引为：" + index);
            System.out.println("索引对应的值为：" + arr[index]);
        } else {
            System.out.println("该数组不存在该值");
        }
    }

    int[] arr;
    int lowIndex;
    int highIndex;

    public BinarySearch(int[] arr) {
        this.arr = arr;
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
    }

    public int find(int num) {
        int findNum = 1;
        int middleIndex;

        while (true) {
            middleIndex = (highIndex - lowIndex) / 2 + lowIndex;
            int middleNum = arr[middleIndex];
            System.out.println("第" + (findNum++) + "次二分查询");
            // 如果相等，则直接返回
            if (middleNum == num) {
                return middleIndex;
            }
            // 如果中间索引已经等于了低位索引，则代表不存在
            else if (middleIndex == lowIndex && middleIndex == (highIndex - 1)) {
                System.out.println("未查询到该值");
                return -1;
            }
            // 如果查询的值小于中间值，则调整 高位索引
            else if (num < middleNum) {
                highIndex = middleIndex;
            }
            // 如果查询的值大于中间值，则调整 低位索引
            else {
                lowIndex = middleIndex;
            }
        }
    }
}
