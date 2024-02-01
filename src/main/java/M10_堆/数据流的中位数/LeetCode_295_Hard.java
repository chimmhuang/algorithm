package M10_堆.数据流的中位数;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 力扣295. 数据流的中位数 [困难]
 * <a href="https://leetcode.cn/problems/find-median-from-data-stream/description/">https://leetcode.cn/problems/find-median-from-data-stream/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/1
 */
public class LeetCode_295_Hard {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }

    /*
        看了力扣官方讲解后，自己写一下思路
        解决这道题，先定义两个优先队列，分别为最小队列和最大队列。最小队列按大→小顺序排列，最大队列按小→大顺序排列 minQueue 大[]小 (大顶堆)  maxQueue 小[]大 (小顶堆)
        第1次添加：1  -->   minQueue 大[1]小  maxQueue 小[]大。 打印效果：minQueue.size() > maxQueue.size()。是奇数，弹出多的队列，即：1
        第2次添加：2  -->   minQueue 大[1]小  maxQueue 小[2]大。 打印效果：minQueue.size() = maxQueue.size()。是偶数，弹出多的两边的队列取平均值，即：1.5
        第3次添加：3  -->   minQueue 大[2,1]小  maxQueue 小[3]大。 打印效果：minQueue.size() > maxQueue.size()。是奇数，弹出多的队列，即：2
        第4次添加：0  -->   minQueue 大[1,0]小  maxQueue 小[2,3]大。 打印效果：minQueue.size() = maxQueue.size()。是偶数，弹出多的两边的队列取平均值，即：1.5

        时间复杂度：
            - addNum  O(logn)，其中 n 为累计添加的数的数量
            - findMedian  O(1)
        空间复杂度：O(n)，主要为优先队列的开销
     */
    static class MedianFinder {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Integer::compareTo);

        public MedianFinder() {

        }

        public void addNum(int num) {
            // 优先往 min 队列添加，max数量不变
            if (minQueue.size() == maxQueue.size()) {
                if (minQueue.isEmpty() || num < maxQueue.peek()) {
                    minQueue.add(num);
                } else {
                    minQueue.add(maxQueue.poll());
                    maxQueue.add(num);
                }
            }
            // 往 max 队列添加，min数量不变
            else {
                if (num > minQueue.peek()) {
                    maxQueue.add(num);
                } else {
                    maxQueue.add(minQueue.poll());
                    minQueue.add(num);
                }
            }
        }

        public double findMedian() {
            // 奇数队列，直接打印 min队列头部即可
            if (minQueue.size() > maxQueue.size()) {
                return minQueue.peek();
            }
            // 偶数队列，打印 min 和 max 的头部的平均数
            else {
                Integer minNum = minQueue.peek();
                Integer maxNum = maxQueue.peek();
                return (minNum + maxNum) / 2.0;
            }
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
