package src.mathtest;

import java.util.Arrays;

/**
 * 给你�?个整数数�? nums，和�?个整�? k �?
 * 在一个操作中，您可以选择 0 <= i < nums.length 的任何索�? i 。将 nums[i] 改为 nums[i] + x ，其�? x 是一个范围为 [-k, k] 的整数�??
 * 对于每个索引 i ，最�? 只能 应用 �?�? 此操作�??
 * nums 的 分数 是 nums 中最大和�?小元素的差�?��?��?
 * 在对  nums 中的每个索引�?多应用一次上述操作后，返回 nums 的最�? 分数 �?
 * 链接：https://leetcode-cn.com/problems/smallest-range-i
 * @author Dreihunde
 *
 */
public class SmallestRangeI {
	//method 1 math O(n) O(1)
    public int smallestRangeI1(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return (max - min - 2 * k) >= 0 ? max - min - 2 * k : 0;
    }

    //method 2 math+stream O(n) O(1)
    public int smallestRangeI(int[] nums, int k) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        return (max - min - 2 * k) >= 0 ? max - min - 2 * k : 0;
    }

}
