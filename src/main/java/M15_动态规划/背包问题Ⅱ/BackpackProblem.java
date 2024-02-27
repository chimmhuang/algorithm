package M15_动态规划.背包问题Ⅱ;

/**
 * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，每个物品有对应的价值。
 * 每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的【总价值】最大？
 *
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class BackpackProblem {

    /*
        我们把整个求解过程分为 n 个阶段，每个阶段会决策一个物品是否放到背包中。
        每个物品决策（放入或者不放入背包）完之后，背包中的物品的重量会有多种情况，也就是说，会达到多种不同的状态。

        我们把每一层重复的状态合并，只记录不同的状态，然后基于上一层的状态集合，来推导下一层的状态集合。
        我们可以通过合并每一层重复的状态，这样就保证每一层不同状态的个数都不会超过 w 个（w 表示背包的承载重量）。
        于是，我们就成功避免了每层状态个数的指数级增长。
     */
    public static void main(String[] args) {
        // 假设背包可承受重量9，物品个数5
        int maxWeight = 9;
        // 物品的数量
        int[] items = new int[]{2, 2, 4, 6, 3};
        // 物品的价值
        int[] value = new int[]{3, 4, 8, 9, 6};

        /*
            二维数组。
                X轴：表示物品决策后，可能存在的当下背包重量。
                Y轴：遍历的物品。
                值表示当下背包的价值
         */
        Integer[][] itemDecide = new Integer[items.length][maxWeight + 1];

        // 如果不放，则重量+0，价值+0
        itemDecide[0][0] = 0;
        // 如果放入，则重量+itemWeight，价值+price
        itemDecide[0][items[0]] = value[0];
        for (int i = 1; i < items.length; i++) {
            int itemWeight = items[i];

            // 在上一种的结果中去判断
            Integer[] preDecide = itemDecide[i - 1];
            for (int weight = 0; weight < preDecide.length; weight++) {
                Integer itemPrice = preDecide[weight];
                if (itemPrice == null) {
                    continue;
                }

                // 如果不放，则重量+0，价值+0
                itemDecide[i][weight] = Math.max(itemPrice, itemDecide[i][weight] == null ? itemPrice : itemDecide[i][weight]);

                if ((weight + itemWeight) <= maxWeight) {
                    // 如果放入，则重量+itemWeigh，价值+value[i]
                    itemDecide[i][weight + itemWeight] = Math.max(itemPrice + value[i], preDecide[weight + itemWeight] == null ? 0 : preDecide[weight + itemWeight]);
                } else {
                    break;
                }
            }
        }

        // 找出最大值
        int maxValue = 0;
        for (int weight = maxWeight; weight >= 0; weight--) {
            Integer price = itemDecide[items.length - 1][weight];
            if (price != null) {
                maxValue = Math.max(price, maxValue);
            }
        }
        System.out.println("背包最大价值为：" + maxValue);
    }
}
