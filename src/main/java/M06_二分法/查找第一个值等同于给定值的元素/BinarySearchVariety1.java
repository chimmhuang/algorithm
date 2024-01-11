package M06_二分法.查找第一个值等同于给定值的元素;

/**
 * <h1>二分查找变种1：查找第一个值等同于给定值的元素</h1>
 * <p>比如下面这样一个有序数组，其中，a[5]，a[6]，a[7]的值都等于 8，是重复的数据。我们希望查找第一个等于 8 的数据，也就是下标是 5 的元素。</p>
 * <p><img src="https://static001.geekbang.org/resource/image/50/f8/503c572dd0f9d734b55f1bd12765c4f8.jpg?wh=1142*284",width="500" height="100" alt="极客时间-数据结构与算法之美-11排序-冒泡排序"/><p>
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class BinarySearchVariety1 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        BinarySearchVariety1 binarySearch = new BinarySearchVariety1(arr);

        int findNum = 8;
        int index = binarySearch.findFirst(findNum);
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
    public BinarySearchVariety1(int[] arr) {
        this.arr = arr;
        this.lowIndex = 0;
        this.highIndex = arr.length - 1;
    }

    public int findFirst(int num) {
        int findNum = 1;
        int middleIndex;

        while (true) {
            middleIndex = lowIndex + ((highIndex - lowIndex) >> 1);
            int middleNum = arr[middleIndex];
            System.out.println("第" + (findNum++) + "次二分查询");

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
}
