package numsorttest;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * @author Dreihunde
 *
 */
public class LeftPreRight {
	
	//method 1 双指针交换 O(n) O(1)
    public int[] exchange(int[] nums) {
        if (nums.length == 0)
            return new int[0];
        int left = 0;
        int right = nums.length - 1;
        
        //左右指针没相遇就继续判断
        while (left < right) {
            //左指针跳过奇数
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            //右指针跳过偶数
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            //交换左指针(当前指向偶数)与右指针(当前指向奇数)
            swap(nums, left, right);
            //已经交换过的位置直接跳过
            left++;
            right--;
        }

        return nums;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
