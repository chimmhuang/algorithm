package M15_动态规划.背包问题;

/**
 * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，
 * 每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
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
        // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中
        int maxWeight = 100;
        int[] items = new int[]{10, 10, 12, 10, 15, 8, 17, 21, 5, 6};

        /*
            二维数组。
                X轴：表示物品决策后，可能存在的当下背包重量。
                Y轴：遍历的物品。
         */
        Boolean[][] itemDecide = new Boolean[items.length][101];

        // 如果不放，则重量+0
        itemDecide[0][0] = true;
        // 如果放入，则重量+itemWeight
        itemDecide[0][items[0]] = true;
        for (int i = 1; i < items.length; i++) {
            int itemWeight = items[i];

            // 在上一种的结果中去判断
            Boolean[] booleans = itemDecide[i - 1];
            for (int weight = 0; weight < booleans.length; weight++) {
                Boolean isPackage = booleans[weight];
                if (isPackage == null) {
                    continue;
                }

                // 如果不放，则重量+0
                itemDecide[i][weight] = isPackage;

                if (maxWeight >= (weight + itemWeight)) {
                    // 如果放入，则重量+itemWeigh
                    itemDecide[i][weight + itemWeight] = true;
                }
            }
        }

        // 输出可放入的最大重量
        for (int weight = maxWeight; weight >= 0; weight--) {
            if (itemDecide[items.length - 1][weight]) {
                System.out.println("背包最大装载重量为：" + weight);
                break;
            }
        }
        System.out.println("背包一个也装不下");
    }
}
