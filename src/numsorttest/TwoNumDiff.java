package numsorttest;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 * |x| 的值定义为：
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 * 链接：https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 * @author 1
 *
 */
public class TwoNumDiff {
	//method 1 双重遍历 O(n^2) O(1)
    public int countKDifference1(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[j] - nums[i]) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //method 2 哈希表 O(n) O(n)
    public int countKDifference2(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            count += map.getOrDefault(k + num, 0) + map.getOrDefault(num - k, 0);
        }
        return count;
    }

    //method 3 桶排序查找 O(n) O(C) C = 100
    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int[] nums_count = new int[100];
        int count = 0;
        //将nums数组中的数插入桶排序中
        for (int num : nums) {
            nums_count[num - 1]++;
        }
        //从大到小遍历，因此查询序号满足i, i - k
        for (int i = 99; i >= k; i--) {
            count += nums_count[i] * nums_count[i - k];
        }
        return count;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
