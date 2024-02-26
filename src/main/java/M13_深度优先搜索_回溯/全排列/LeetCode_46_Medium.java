package M13_深度优先搜索_回溯.全排列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 力扣46. 全排列 [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/21
 */
public class LeetCode_46_Medium {

    class Solution {
        /*
            nums = [1,2,3,4]

            回溯算法：先添加一个，再递归循环添加，再删除添加的。

            第1次递归：[1]       待删除：[1]，待遍历：[2,3,4]
            第2次递归：[1,2]     待删除：[1,2]，待遍历：[3,4]
            第3次递归：[1,2,3]   待删除：[1,2,3]，待遍历：[4]
            第4次递归：[1,2,4]   待删除：[1,2,3,4]，待遍历：[]
            第5次递归：递归达到最大，加入结果集，结束递归。
            第4次递归：[1,2,3,4] 子递归完成，删除1位添加的值。
            第4次递归：[1,2,3]   遍历完成，结束本次递归。
            第3次递归：[1,2,3]   子递归完成，删除1位添加的值
            第3次递归：[1,2]     开始下一次循环。
            第3次递归：[1,2,4]   开始下一次循环。
            第6次递归：[1,2,4,3]
            ...
         */
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> resultList = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return resultList;
            }

            if (nums.length == 1) {
                resultList.add(Collections.singletonList(nums[0]));
                return resultList;
            }

            List<Integer> arrayList = new ArrayList<>();
            dfs(resultList, arrayList, nums);

            return resultList;
        }

        private void dfs(List<List<Integer>> resultList, List<Integer> arrayList, int[] nums) {
            if (arrayList.size() == nums.length) {
                resultList.add(new ArrayList<>(arrayList));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (arrayList.contains(nums[i])) {
                    continue;
                }
                arrayList.add(nums[i]);
                dfs(resultList, arrayList, nums);
                arrayList.remove(Integer.valueOf(nums[i]));
            }
        }
    }
}
