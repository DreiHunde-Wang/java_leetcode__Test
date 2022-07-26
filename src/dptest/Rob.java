package src.dptest;

import org.junit.jupiter.api.Test;

/**
 * ����һ��רҵ��С͵���ƻ�͵���ؽֵķ��ݡ�
 * ÿ�䷿�ڶ�����һ�����ֽ�Ӱ����͵�Ե�Ψһ��Լ���ؾ������ڵķ���װ���໥��ͨ�ķ���ϵͳ��
 * ����������ڵķ�����ͬһ���ϱ�С͵���룬ϵͳ���Զ�������
 * ����һ������ÿ�����ݴ�Ž��ķǸ��������飬������ ����������װ�õ������ ��һҹ֮���ܹ�͵�Ե�����߽�
 * ���ӣ�https://leetcode.cn/problems/house-robber
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

    //method 1 dp+�Ż� O(n) O(1)
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
