package M06_二分法.切木头;

/**
 * 切木头
 * Given n pieces of wood with length L[i] (integer array).
 * Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length,
 * What is the longest length you can get from the n pieces of wood?
 * Given L & k,return the maximum length of the small pieces.
 *
 * 给定n块长度为L[i]的木头(整数数组)。
 * 把它们切成小块，以保证你可以有等于或超过k个相同长度的小块，
 * n块木头你能得到的最长长度是多少?
 * 给定L和k，返回小块的最大长度。
 *
 * 例：有三块木头，长度分别为：232、124、456。我最终需要在这3块木头中，切出7块一样长的木头
 * L = [232, 124, 456]
 * k = 7
 * 例：每块长度为100.则：第1块木头能切2块、第2块木头能切1块、第3块木头能切4块。刚好=7块，那么长度就为100。
 *
 * @author Chimm Huang
 * @date 2024/1/11
 */
public class WoodCut {

    public static void main(String[] args) {
        int[] woods = new int[]{232, 124, 456};
        WoodCut woodCut = new WoodCut(woods);

        int cutNum = 7;
        int perLength = woodCut.cut(7);

        System.out.println("我要把木头平均切成：" + cutNum + "块");

        if (perLength != -1) {
            System.out.println("那么每块木头需要切的长度为：" + perLength);
        } else {
            System.out.println("不行，我做不到");
        }

        System.out.println("-------------------------------------");

        int[] woods2 = new int[]{100, 100, 100};
        WoodCut woodCut2 = new WoodCut(woods2);

        int cutNum2 = 3;
        int perLength2 = woodCut2.cut(cutNum2);

        System.out.println("我要把木头平均切成：" + cutNum2 + "块");

        if (perLength2 != -1) {
            System.out.println("那么每块木头需要切的长度为：" + perLength2);
        } else {
            System.out.println("不行，我做不到");
        }
    }

    int[] woods;
    int maxWoodsLength;

    public WoodCut(int[] woods) {
        this.woods = woods;
        maxWoodsLength = woods[0];
        for (int i = 0; i < woods.length; i++) {
            if (maxWoodsLength < woods[i]) {
                maxWoodsLength = woods[i];
            }
        }
    }

    public int cut(int cutNum) {
        int lowLength = 0;
        int highLength = maxWoodsLength;
        while (true) {
            int middleLength = lowLength + ((highLength - lowLength) >> 1);
            int number = 0;
            for (int i = 0; i < woods.length; i++) {
                int wood = woods[i];
                number += (wood / middleLength);
            }

            // 不能再切了
            if (middleLength == lowLength && middleLength == highLength - 1) {
                // 判断右边的长度能不能切
                number = 0;
                for (int i = 0; i < woods.length; i++) {
                    int wood = woods[i];
                    number += (wood / highLength);
                }
                return number == cutNum ? highLength : middleLength;
            }
            // 如果可切割的数量大于等于7，则代表可能还可以再长一点
            else if (number >= cutNum) {
                lowLength = middleLength;
            }
            // 如果可切割的数量小于7，则代表长度太长了，需要短一点
            else {
                highLength = middleLength;
            }
        }
    }
}
