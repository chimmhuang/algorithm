package M06_二分法.二分查询的变种;

/**
 * <h1>二分查找变种</h1>
 * <h2>1：查找第一个值等同于给定值的元素</h2>
 * <h2>2：查找最后一个值等同于给定值的元素</h2>
 * <h2>3：查找第一个大于等于给定值的元素</h2>
 * <h2>4：查找最后一个小于等于给定值的元素</h2>
 * <p><img src="https://static001.geekbang.org/resource/image/42/36/4221d02a2e88e9053085920f13f9ce36.jpg?wh=1142*783",width="500" height="350" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 * <p>比如下面这样一个有序数组，其中，a[5]，a[6]，a[7]的值都等于 8，是重复的数据。我们希望查找第一个等于 8 的数据，也就是下标是 5 的元素。</p>
 * <p><img src="https://static001.geekbang.org/resource/image/50/f8/503c572dd0f9d734b55f1bd12765c4f8.jpg?wh=1142*284",width="500" height="100" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class BinarySearchVariety {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        BinarySearchVariety binarySearch = new BinarySearchVariety(arr);

        int findNum = 8;
        System.out.println("要查询的数字为：" + findNum);
        System.out.println("----------------");
        int firstIndex = binarySearch.findFirst(findNum);
        if (firstIndex != -1) {
            System.out.println("查询到第一次出现的索引为：" + firstIndex);
            System.out.println("索引对应的值为：" + arr[firstIndex]);
        } else {
            System.out.println("该数组不存在该值");
        }

        System.out.println("----------------");
        int lastIndex = binarySearch.findLast(findNum);
        if (lastIndex != -1) {
            System.out.println("查询到最后一次出现的索引为：" + lastIndex);
            System.out.println("索引对应的值为：" + arr[lastIndex]);
        } else {
            System.out.println("该数组不存在该值");
        }

        System.out.println("----------------");
        int firstGeIndex = binarySearch.findFirstGE(findNum);
        System.out.println("查找第一个大于等于给定值的元素索引为：" + firstGeIndex);
        System.out.println("索引对应的值为：" + arr[firstGeIndex]);

        System.out.println("----------------");
        int lastLeIndex = binarySearch.findLastLE(findNum);
        System.out.println("查找最后一个小于等于给定值的元索引为：" + lastLeIndex);
        System.out.println("索引对应的值为：" + arr[lastLeIndex]);
    }

    int[] arr;
    int lowIndex;
    int highIndex;
    public BinarySearchVariety(int[] arr) {
        this.arr = arr;
    }

    /**
     * 1：查找第一个值等同于给定值的元素
     */
    public int findFirst(int num) {
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
        while (true) {
            int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            int middleNum = arr[middleIndex];

            // 如果相等，判断是否前一个值是否相等，若不相等，则返回
            if (middleNum == num && (middleIndex == 0 || arr[middleIndex - 1] != num)) {
                return middleIndex;
            }
            // 若相等，且前面还有相等的值，则调整 高位索引
            else if (middleNum == num) {
                highIndex = middleIndex;
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

    /**
     * 2：查找最后一个值等同于给定值的元素
     */
    public int findLast(int num) {
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
        while (true) {
            int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            int middleNum = arr[middleIndex];

            // 如果相等，判断是否前一个值是否相等，若不相等，则返回
            if (middleNum == num && (middleIndex == arr.length - 1 || arr[middleIndex + 1] != num)) {
                return middleIndex;
            }
            // 若相等，且前面还有相等的值，则调整 高位索引
            else if (middleNum == num) {
                lowIndex = middleIndex;
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

    /**
     * 3：查找第一个大于等于给定值的元素
     */
    public int findFirstGE(int num) {
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
        while (true) {
            int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            int middleNum = arr[middleIndex];

            // 如果相等，判断是否前一个值是否相等，若不相等，则返回
            if (middleNum == num && (middleIndex == arr.length - 1 || arr[middleIndex + 1] != num)) {
                return middleIndex == arr.length - 1 ? middleIndex : middleIndex + 1;
            }
            // 若相等，且前面还有相等的值，则调整 高位索引
            else if (middleNum == num) {
                lowIndex = middleIndex;
            }
            // 如果中间索引已经等于了低位索引，则代表不存在，返回低位索引
            else if (middleIndex == lowIndex && middleIndex == (highIndex - 1)) {
                return lowIndex;
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

    /**
     * 4：查找最后一个小于等于给定值的元
     */
    public int findLastLE(int num) {
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
        while (true) {
            int middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            int middleNum = arr[middleIndex];

            // 如果相等，判断是否前一个值是否相等，若不相等，则返回
            if (middleNum == num && (middleIndex == 0 || arr[middleIndex - 1] != num)) {
                return middleIndex == 0 ? middleIndex : middleIndex - 1;
            }
            // 若相等，且前面还有相等的值，则调整 高位索引
            else if (middleNum == num) {
                highIndex = middleIndex;
            }
            // 如果中间索引已经等于了低位索引，则代表不存在，返回高位索引的值
            else if (middleIndex == lowIndex && middleIndex == (highIndex - 1)) {
                return highIndex;
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
