package slidewindowtest;

import numsorttest.CommonTest;

/**
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * @author Dreihunde
 *
 */
public class MaxSumOfThreeSub {
	//method 1 三个滑动窗口 O(n) O(1)
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //返回坐标
        int[] ans = new int[3];

        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum2Idx1 = 0, maxSum2Idx2 = 0;
        int sum3 = 0, maxSumTotal = 0;

        for (int i = k * 2; i < nums.length; i++) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];

            //新输入k个值之后更新
            if (i >= k * 3 - 1) {
                //窗口一比较
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                //窗口一与窗口二的最大值比较
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum2Idx1 = maxSum1Idx;
                    maxSum2Idx2 = i - k * 2 + 1;
                }
                //窗口一，二，三的最大值比较
                if (maxSum12 + sum3 > maxSumTotal) {
                    maxSumTotal = maxSum12 + sum3;
                    //每次比较完更新答案，避免重复
                    ans[0] = maxSum2Idx1;
                    ans[1] = maxSum2Idx2;
                    ans[2] = i - k + 1;
                }

                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }

        return ans;
    }
    
    //method 2 两个不重叠窗口的最大和
    public int[] maxSumOfTwoSubarrays(int[] nums, int k) {
    	int[] ans = new int[2];
    	int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
    	int sum2 = 0, maxSumTotal = 0;
    	
    	for (int i = k; i < nums.length; i++) {
    		sum1 += nums[i - k];
    		sum2 += nums[i];
    		
    		if (i >= 2 * k - 1) {
    			if (sum1 > maxSum1) {
    				maxSum1 = sum1;
    				maxSum1Idx = i - 2 * k + 1;
    			}
    			if (maxSum1 + sum2 > maxSumTotal) {
    				maxSumTotal = maxSum1 + sum2;
    				ans[0] = maxSum1Idx;
    				ans[1] = i -k + 1;
    			}
    			sum1 -= nums[i - 2 * k + 1];
    			sum2 -= nums[i - k + 1];
    		}
    	}
    	
    	return ans;
    }
    
  //method 3 一个窗口的最大和
    public int[] maxSumOfSubarrays(int[] nums, int k) {
    	int[] ans = new int[1];
    	int sum = 0, maxSum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		sum += nums[i];
    		if (i >= k - 1) {
    			if (sum > maxSum) {
    				maxSum = sum;
    				ans[0] = i - k + 1;
    			}
    			sum -= nums[i - k + 1];
    		}
    	}
    	return ans;
    }
    
    //method 4 dp
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        long[][] dp = new long[n + 2][4];
        for (int i = n - k + 1; i >= 1; i--) {
            for (int j = 1; j < 4; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]);
            }
        }
        int[] ans = new int[3];
        int i = 1, j = 3, idx = 0;
        
        while (j > 0) {
            if (dp[i + 1][j] > dp[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]) {
                i++;
            } else {
                ans[idx++] = i - 1;
                i += k;
                j--;
            }
        }

        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {20,18,9,2,14,1,10,3,11,18};
		int k = 3;
		MaxSumOfThreeSub ms = new MaxSumOfThreeSub();
		
		CommonTest.printNum(ms.maxSumOfThreeSubarrays(nums, k));
		CommonTest.printNum(ms.maxSumOfThreeSubarrays2(nums, k));
		CommonTest.printNum(ms.maxSumOfTwoSubarrays(nums, k));
		CommonTest.printNum(ms.maxSumOfSubarrays(nums, k));

	}

}
