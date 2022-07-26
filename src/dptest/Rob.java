package src.dptest;

import org.junit.jupiter.api.Test;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 链接：https://leetcode.cn/problems/house-robber
 * @author Dreihunde
 *
 */
public class Rob {
	//method 1 dp O(n) O(n)
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    //method 1 dp+优化 O(n) O(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int cur = 0, pre = 0;
        pre = nums[0];
        cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int ncur = Math.max(cur, pre + nums[i]);
            pre = cur;
            cur = ncur;

        }
        return cur;
    }
	
	@Test
	public void test() {
		int[] nums = new int[] {2,7,9,3,1};
		System.out.println(rob(nums));
	}

}
