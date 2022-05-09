package src.numsearch;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * @author Dreihunde
 *
 */
public class SingleNonDuplicate {
	//method 1 异或 O(n) O(1)
    public int singleNonDuplicate1(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret = ret ^ num;
        }
        return ret;
    }

    //method 2 二分查找 O(logn) O(1)
    public int singleNonDuplicate2(int[] nums) {
        int ret = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (left == right || mid == 0 || mid == nums.length - 1) {
                break;
            }
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            //单独数在右边的情况：即当中点索引为奇数时，与左边比较，为偶数时，与右边比较
            if ((nums[mid] == nums[mid - 1] && mid % 2 != 0) || (nums[mid] == nums[mid + 1] && mid % 2 == 0)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        
        return nums[left];
    }

    //method3 全数组的二分 O(logn) O(1) 按位异或的性质，可以得到mid和相邻的数之间关系 当 mid 是偶数时，mid + 1 = mid ^ 1, 当mid为奇数时, mid - 1 = mid ^ 1;
    public int singleNonDuplicate3(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    //method 4 偶数范围的二分查找 O(logn) O(1) 由于单独数左边一定有数个偶数对，因此该数索引值一定为偶数
    public int singleNonDuplicate4(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
