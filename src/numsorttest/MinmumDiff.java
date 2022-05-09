package src.numsorttest;

import java.util.Arrays;

/**
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * 返回可能的 最小差值 。
 * 链接：https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * @author Dreihunde
 *
 */
public class MinmumDiff {
	//method 1 排序 O(nlogn) O(logn) 排序所需的栈空间
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = k - 1;
        int diff = nums[right] - nums[left];
        while (right < nums.length - 1) {
            right++;
            left++;
            diff = Math.min(nums[right] - nums[left], diff);
        }
        return diff;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
