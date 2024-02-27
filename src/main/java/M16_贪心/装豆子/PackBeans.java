package M16_贪心.装豆子;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * <p>假设我们有一个可以容纳 100kg 物品的背包，可以装各种物品。</p>
 * <p>我们有以下 5 种豆子，每种豆子的总量和总价值都各不相同。</p>
 * <p>为了让背包中所装物品的总价值最大，我们如何选择在背包中装哪些豆子？</p>
 * <p>每种豆子又该装多少呢？</p>
 *
 * <p><img src="https://static001.geekbang.org/resource/image/f9/c7/f93f4567168d3bc65688a785b76753c7.jpg?wh=1142*558" width="500" height="250"/></p>
 *
 * @author Chimm.Huang
 * @date 2024/2/27
 */
public class PackBeans {

    public static void main(String[] args) {
        Bean huangDou = new Bean("黄豆", 100, BigDecimal.valueOf(100));
        Bean lvDou = new Bean("绿豆", 30, BigDecimal.valueOf(90));
        Bean hongDou = new Bean("红豆", 60, BigDecimal.valueOf(120));
        Bean heiDou = new Bean("黑豆", 20, BigDecimal.valueOf(80));
        Bean qingDou = new Bean("青豆", 50, BigDecimal.valueOf(75));

        packet(Arrays.asList(hongDou, lvDou, huangDou, heiDou, qingDou));
    }

    private static void packet(List<Bean> beanList) {
        // 先计算每个豆子的单价
        for (int i = 0; i < beanList.size(); i++) {
            Bean bean = beanList.get(i);
            bean.unitPrice = bean.amount.divide(BigDecimal.valueOf(bean.weight), 2, RoundingMode.HALF_UP);
        }

        // 按照最高单价进行排序
        beanList.sort((o1, o2) -> o2.unitPrice.compareTo(o1.unitPrice));

        // 放入背包
        int packageRemainWeight = 100;
        BigDecimal packageBeanPrice = BigDecimal.ZERO;

        for (int i = 0; i < beanList.size() && packageRemainWeight > 0; i++) {
            Bean bean = beanList.get(i);
            // 全部放入
            if (bean.weight <= packageRemainWeight) {
                packageRemainWeight -= bean.weight;
                packageBeanPrice = packageBeanPrice.add(BigDecimal.valueOf(bean.weight).multiply(bean.unitPrice));
                System.out.printf("放入：[%s]，重量：[%s]%n", bean.name, bean.weight);
            }
            // 只能放入剩余的空间
            else {
                packageBeanPrice = packageBeanPrice.add(BigDecimal.valueOf(packageRemainWeight).multiply(bean.unitPrice));
                System.out.printf("放入：[%s]，重量：[%s]%n", bean.name, packageRemainWeight);
                packageRemainWeight = 0;
            }
        }

        // 输出结果
        System.out.println("背包剩余空间为：" + packageRemainWeight);
        System.out.println("背包总价值为：" + packageBeanPrice);
    }

    static class Bean {
        String name;
        int weight;
        BigDecimal amount;

        BigDecimal unitPrice;

        public Bean(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public Bean(String name, int weight, BigDecimal amount) {
            this.name = name;
            this.weight = weight;
            this.amount = amount;
        }
    }
}
