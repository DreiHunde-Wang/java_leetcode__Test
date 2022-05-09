package src.numsearch;

/**
 * 给定�?个按照升序排列的整数数组 nums，和�?个目标�?? target。找出给定目标�?�在数组中的�?始位置和结束位置�?
 * 如果数组中不存在目标�? target，返回 [-1, -1]�?
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * @author Dreihunde
 *
 */
public class SearchRange {
	//method 1 二分 O(logn + k) O(1) k为重复的目标元素个数
    public int[] searchRange1(int[] nums, int target) {
        int index = binarySearch1(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int lt = index;
        int gt = index;
        while (lt >= 1 && nums[lt] == nums[lt - 1]) {
            lt--;
        }
        while (gt <= nums.length - 2 && nums[gt] == nums[gt + 1]) {
            gt++;
        }
        return new int[]{lt, gt};
    }

    private int binarySearch1(int[] nums, int target) {
        int n = nums.length;
        int lt = 0;
        int rt = n - 1;
        while (lt <= rt) {
            int mid = (lt + rt) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }
        return -1;
    }

    //method 2 两次二分 O(logn) O(1) k为重复的目标元素个数
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } 
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
