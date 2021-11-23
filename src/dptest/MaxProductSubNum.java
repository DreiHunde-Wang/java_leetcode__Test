package dptest;

import numsearch.HaveRepeatNumber;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * @author Dreihunde
 *
 */
public class MaxProductSubNum {
	public int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int minF = nums[0];
        int maxF = nums[0];
        int maxProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF;
            int mi = minF;
            // minF(n - 1) * nums[n], nums[n], maxF(n - 1) * nums[n]中的最小值
            minF = Math.min(mi * nums[i], Math.min(nums[i], mx * nums[i]));
            // maxF(n - 1) * nums[n], nums[n], mminF(n - 1) * nums[n]中的最大值
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mi * nums[i]));
            maxProduct = Math.max(maxProduct, maxF);
        }

        return maxProduct;
    }
	
	//扩展，输出minF
	public int minProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int minF = nums[0];
        int maxF = nums[0];
        int minProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF;
            int mi = minF;
            // minF(n - 1) * nums[n], nums[n], maxF(n - 1) * nums[n]中的最小值
            minF = Math.min(mi * nums[i], Math.min(nums[i], mx * nums[i]));
            // maxF(n - 1) * nums[n], nums[n], mminF(n - 1) * nums[n]中的最大值
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mi * nums[i]));
            minProduct = Math.min(minProduct, minF);
        }

        return minProduct;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,3, 1, -2, 0, -5, -6, 1};
		MaxProductSubNum mp = new MaxProductSubNum();
		
		long startTime = System.nanoTime();
		System.out.println(mp.maxProduct(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(mp.minProduct(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
