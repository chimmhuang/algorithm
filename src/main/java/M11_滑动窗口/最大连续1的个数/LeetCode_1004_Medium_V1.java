package M11_滑动窗口.最大连续1的个数;

/**
 * 力扣1004. 最大连续1的个数 Ⅲ [中等]
 *
 * @author Chimm.Huang
 * @date 2024/2/5
 */
public class LeetCode_1004_Medium_V1 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,1,1,0,1,1};
        System.out.println(solution.longestOnes(nums, 8));
    }

    static class Solution {
        public int longestOnes(int[] nums, int k) {
            int ret = 0;
            for(int left = 0, right = 0,count = 0; right < nums.length; right++) {
                if(nums[right] == 0) count++;
                if(count <= k) {
                    ret = Math.max(ret,right - left + 1);
                }
                while(count > k) {
                    if(nums[left++] == 0) count--;
                }
            }

            return ret;
        }
    }
}
