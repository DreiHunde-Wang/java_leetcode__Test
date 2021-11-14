package num_sort__test;

import java.util.Random;

public class QuickSortTest {
	
	private static final int INSERTION_SORT_THRESHOLD = 7;
	
	private static final Random RANDOM = new Random();
	
	public static int[] quickSort(int[] nums) {
		int len = nums.length;
		quickSortOrder(nums, 0, len - 1);
		return nums;
	}
	
	private static void quickSortOrder(int[] nums, int left, int right) {
		//小区间使用插入排序
		if(right - left <= INSERTION_SORT_THRESHOLD) {
			InsertSortTest.insertSort(nums);
			return;
		}
		
		int pIndex = partition(nums, left, right);
		quickSortOrder(nums, 0, pIndex - 1);
		quickSortOrder(nums, pIndex + 1, right);
	}
	
	private static int partition(int[] nums, int left, int right) {
		int randomIndex = left + RANDOM.nextInt(right - left + 1);
		swap(nums, left, randomIndex);
		
		int pivot = nums[left];
		int lt = left + 1;
		int gt = right;
		
		// 循环不变量：
        // all in [left + 1, lt) <= pivot
        // all in (gt, right] >= pivot
		while(true) {
			while(lt <= right && nums[lt] < pivot) {
				lt++;
			}
			while(gt > left && nums[gt] > pivot) {
				gt++;
			}
			
			if(lt >= gt)
				break;
			// 细节：相等的元素通过交换，等概率分到数组的两边
			swap(nums, lt, gt);
			lt++;
			gt--;
		}
		swap(nums, left, gt);
		return gt;
	}
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
