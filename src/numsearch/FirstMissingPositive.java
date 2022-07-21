package src.numsearch;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * https://leetcode.cn/problems/first-missing-positive/
 */
public class FirstMissingPositive {
    //method 1 负号统计是否出现 O(n) O(1)
    public int firstMissingPositive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
                continue;
            }
            minVal = Math.min(minVal, nums[i]);
        }
        if (minVal > 1) {
            return 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(nums[i]) > n) {
                continue;
            }
            int index = Math.abs(nums[i]) - 1;
            nums[index] = - Math.abs(nums[index]);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    //method 2 交换
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            minVal = Math.min(minVal, nums[i]);
        }
        if (minVal > 1) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] < n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int[] num = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive.firstMissingPositive(num));
    }

}
