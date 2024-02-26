package M13_深度优先搜索_回溯.子集;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 力扣78. 子集 [中等]
 * <a href="https://leetcode.cn/problems/subsets/description/">https://leetcode.cn/problems/subsets/description/</a>
 *
 * @author Chimm.Huang
 * @date 2024/2/21
 */
public class LeetCode_78_Medium {

    /*
        nums = [1,2,3]
        第1次递归：[1]     待遍历：[2,3]。 添加到结果集
        第2次递归：[1,2]   待遍历：[3]。添加到结果集
        第3次递归：[1,2,3] 待遍历：[]。添加到结果集
        第4次递归：[1,2,3] 已达到最大值，结束递归
        第3次递归：[1,2,3] 子递归已结束，删除一位
        第3次递归：[1,2]   遍历结束，删除一位，结束递归
        第2次递归：[1]     继续遍历
        第2次递归：[1,3]   待遍历：[]。添加到结果集
        第2次递归：[1,3]   遍历结束，删除一位，结束递归
        第1次递归：[1]     删除一位，继续遍历
        第1次递归：[2]     待遍历：[3]。添加到结果集
        第5次递归：[2,3]   添加到结果集
        第6次递归：[2,3]   已达到最大值，结束递归
        第5次递归：[2,3]   子递归已结束，删除一位，结束递归
        第1次递归：[2]     删除一位，继续遍历
        第1次递归：[3]     待遍历：[]。添加到结果集。已无法遍历，结束递归。
     */
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> resultList = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return resultList;
            }
            if (nums.length == 1) {
                resultList.add(Collections.singletonList(nums[0]));
                resultList.add(Collections.emptyList());
                return resultList;
            }

            List<Integer> arrayList = new ArrayList<>();
            dfs(resultList, arrayList, nums, 0);

            resultList.add(Collections.emptyList());
            return resultList;
        }

        private void dfs(List<List<Integer>> resultList, List<Integer> arrayList, int[] nums, int index) {
            for (int i = index; i < nums.length; i++) {
                // 添加到结果集
                arrayList.add(nums[i]);
                resultList.add(new ArrayList<>(arrayList));

                // 递归调用
                dfs(resultList, arrayList, nums, i + 1);
                // 递归结束，删除一位
                arrayList.remove(Integer.valueOf(nums[i]));
            }
        }
    }
}
