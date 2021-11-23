package dptest;

import numsearch.HaveRepeatNumber;

/**
 * ����һ���������� nums �������ҳ������г˻��������������飨�������������ٰ���һ�����֣��������ظ�����������Ӧ�ĳ˻���
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
            // minF(n - 1) * nums[n], nums[n], maxF(n - 1) * nums[n]�е���Сֵ
            minF = Math.min(mi * nums[i], Math.min(nums[i], mx * nums[i]));
            // maxF(n - 1) * nums[n], nums[n], mminF(n - 1) * nums[n]�е����ֵ
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mi * nums[i]));
            maxProduct = Math.max(maxProduct, maxF);
        }

        return maxProduct;
    }
	
	//��չ�����minF
	public int minProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int minF = nums[0];
        int maxF = nums[0];
        int minProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF;
            int mi = minF;
            // minF(n - 1) * nums[n], nums[n], maxF(n - 1) * nums[n]�е���Сֵ
            minF = Math.min(mi * nums[i], Math.min(nums[i], mx * nums[i]));
            // maxF(n - 1) * nums[n], nums[n], mminF(n - 1) * nums[n]�е����ֵ
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
