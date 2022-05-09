package src.numsorttest;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * @author Dreihunde
 *
 */
public class CountSubArrSumIsK {
	//method 1 前缀和 O(n) O(n)
    public int subarraySum(int[] nums, int k) {
        int pre_sum = 0;
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i : nums) {
            pre_sum += i;
            ret += map.getOrDefault(pre_sum - k, 0);
            map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,1,2,1};
		int k = 3;
		CountSubArrSumIsK cs = new CountSubArrSumIsK();
		
		System.out.println(cs.subarraySum(nums, k));

	}

}
