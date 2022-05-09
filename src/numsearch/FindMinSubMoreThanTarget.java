package src.numsearch;

import java.util.Arrays;

import src.numsorttest.CommonTest;

/**
 * 给定�?个含有 n 个正整数的数组和�?个正整数 target �?
 * 找出该数组中满足其和 �? target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度�??
 * 如果不存在符合条件的子数组，返回 0 �?
 * @author Dreihunde
 *
 */
public class FindMinSubMoreThanTarget {
	//method 1 双指�? O(n) O(1)
    public int minSubArrayLen1(int target, int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int minLen = n + 1;
        int sum = 0;

        while (right < n) {
            while (right < n && sum < target) {
                sum += nums[right];
                right++;
            }       
            while (left < right && sum >= target) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return minLen > n ? 0 : minLen;
    }

    //method 2 前缀�?+二分查找 O(nlogn) O(n)
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1]; 
        // 为了方便计算，令 size = n + 1 
        // sums[0] = 0 意味�?�? 0 个元素的前缀和为 0
        // sums[1] = A[0] �? 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {12,28,83,4,25,26,25,2,25,25,25,12};
		int target = 213;
		
		FindMinSubMoreThanTarget fm = new FindMinSubMoreThanTarget();
		long startTime = System.nanoTime();
		System.out.println(fm.minSubArrayLen1(target, nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(fm.minSubArrayLen2(target, nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
