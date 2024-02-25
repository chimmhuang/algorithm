package M16_贪心.柠檬水找零;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 力扣860. 柠檬水找零 [简单]
 *
 * @author Chimm Huang
 * @date 2024/2/25
 */
public class LeetCode_860_Easy {

    class Solution {
        public boolean lemonadeChange(int[] bills) {
            Deque<Integer> wallet5 = new ArrayDeque<>();
            Deque<Integer> wallet10 = new ArrayDeque<>();
            Deque<Integer> wallet20 = new ArrayDeque<>();

            for (int i = 0; i < bills.length; i++) {
                int bill = bills[i];

                // 5元直接收下
                if (bill == 5) {
                    wallet5.add(bill);
                }
                // 10元需要找零5元
                else if (bill == 10) {
                    if (wallet5.isEmpty()) {
                        return false;
                    }
                    wallet5.poll();
                    wallet10.add(bill);
                }
                // 20元需要找了15元，优先从10元开始找
                else {
                    int give = bill - 5;
                    if (!wallet10.isEmpty()) {
                        wallet10.poll();
                        give -= 10;
                    }
                    while (give != 0) {
                        if (wallet5.isEmpty()) {
                            return false;
                        }
                        wallet5.poll();
                        give -= 5;
                    }
                    wallet20.add(bill);
                }
            }

            return true;
        }
    }
}
