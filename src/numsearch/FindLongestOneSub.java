package numsearch;

/**
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。返回仅包含 1 的最长（连续）子数组的长度。
 * @author Dreihunde
 *
 */
public class FindLongestOneSub {
	 public static int longestOnes1(int[] nums, int k) {
	        int n = nums.length;
	        int[] P = new int[n + 1];
	        for (int i = 1; i <= n; ++i) {
	            P[i] = P[i - 1] + (1 - nums[i - 1]);
	        }

	        int ans = 0;
	        for (int right = 0; right < n; right++) {
	            int left = binarySearch(P, P[right + 1] - k);
	            ans = Math.max(ans, right - left + 1);
	        }
	        return ans;
	    }

	    public static int binarySearch(int[] P, int target) {
	        int low = 0, high = P.length - 1;
	        while (low < high) {
	            int mid = (high - low) / 2 + low;
	            if (P[mid] < target) {
	                low = mid + 1;
	            } else {
	                high = mid;
	            }
	        }
	        return low;
	    }

	    public static int longestOnes2(int[] nums, int k) {
	        int n = nums.length;
	        int left = 0;
	        int lsum = 0;
	        int rsum = 0;
	        int ans = 0;

	        for (int right = 0; right < n; right++) {
	            rsum += 1 - nums[right];
	            while (lsum < rsum - k) {
	                lsum += 1 - nums[left];
	                left++;
	            }
	            ans = Math.max(ans, right - left + 1);
	        }

	        return ans;
	    }
	    
	    public static void main(String[] args) {
			int[] nums = new int[] {1,1,1,0,0,0,1,1,1,1,0};
			int k = 2;
			
			long startTime = System.nanoTime();
			System.out.println(longestOnes1(nums, k));
			long endTime = System.nanoTime();
			System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
			
			startTime = System.nanoTime();
			System.out.println(longestOnes2(nums, k));
			endTime = System.nanoTime();
			System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		}

}
