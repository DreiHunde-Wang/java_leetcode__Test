package src.dptest;

/**
 * ����һ���������飬�����е�һ������������������һ�������顣������������ĺ͵����ֵ��
 * Ҫ��ʱ�临�Ӷ�ΪO(n)��
 * @author Dreihunde
 *
 */
public class LongestSubSum {
	//method 1 ��̬�滮
    public int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            ans = Math.max(ans, pre);
        }

        return ans;
    }

    //method 2 �߶���(����) ��һ��O(n)���ɶѽ����Ժ��ѯO(logn)
    public class Status {
        public int lSum, rSum, mSum, iSum;

        /**
         * ����һ������ [l,r][l,r]�����ǿ���ά���ĸ�����
         * lSum ��ʾ [l,r][l,r] ���� ll Ϊ��˵������Ӷκ�
         * rSum ��ʾ [l,r][l,r] ���� rr Ϊ�Ҷ˵������Ӷκ�
         * mSum ��ʾ [l,r][l,r] �ڵ�����Ӷκ�
         * iSum ��ʾ [l,r][l,r] �������
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
    
   //method 3 ǰ׺��
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
