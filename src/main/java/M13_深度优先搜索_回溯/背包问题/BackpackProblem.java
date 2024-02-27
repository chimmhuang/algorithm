package M13_深度优先搜索_回溯.背包问题;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class BackpackProblem {

    /*
        我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，
        每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。
        在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
     */
    public static void main(String[] args) {
        // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中
        int[] items = new int[]{10, 10, 12, 10, 15, 8, 17, 21, 5, 6};

        List<Integer> resultList = new ArrayList<>();
        packageItem(resultList, 0, items, 0);

        // 对结果集排序
        resultList.sort(Comparator.reverseOrder());
        System.out.println(resultList.get(0));
    }

    private static void packageItem(List<Integer> resultList, int currentWeight, int[] items, int index) {
        // 遍历到最后一个了，停止递归
        if (index == items.length) {
            resultList.add(currentWeight);
            return;
        }

        for (int i = index; i < items.length; i++) {
            // 先判断还能装下本次物品吗
            int nextWeight = currentWeight + items[i];
            // 本次物品装不下去了，装下一个物品
            if (nextWeight > 100) {
                packageItem(resultList, currentWeight, items, index + 1);
                continue;
            }

            // 先将本次物品装进去
            currentWeight += items[i];

            // 装下一个物品
            packageItem(resultList, currentWeight, items, index + 1);

            // 再将本次物品拿出来，循环判断下一个。
            currentWeight -= items[i];
        }
    }
}
