package src.dptest;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * @author Dreihunde
 *
 */
public class LongestSubSum {
	//method 1 动态规划
    public int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            ans = Math.max(ans, pre);
        }

        return ans;
    }

    //method 2 线段树(分治) 第一次O(n)，成堆建树以后查询O(logn)
    public class Status {
        public int lSum, rSum, mSum, iSum;

        /**
         * 对于一个区间 [l,r][l,r]，我们可以维护四个量：
         * lSum 表示 [l,r][l,r] 内以 ll 为左端点的最大子段和
         * rSum 表示 [l,r][l,r] 内以 rr 为右端点的最大子段和
         * mSum 表示 [l,r][l,r] 内的最大子段和
         * iSum 表示 [l,r][l,r] 的区间和
         */
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
    
   //method 3 前缀和
   public int maxSubArray3(int[] nums) {
        int min = 0;
        int ans = nums[0];
        int sum = 0;

        for (int num : nums) {
            sum += num;
            ans = Math.max(ans, sum - min);
            min = Math.min(sum, min);
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
		LongestSubSum ls = new LongestSubSum();
		
		long startTime = System.nanoTime();
		System.out.println(ls.maxSubArray1(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(ls.maxSubArray2(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(ls.maxSubArray3(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
