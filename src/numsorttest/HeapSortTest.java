package numsorttest;

public class HeapSortTest {
	public static int[] heapSort(int[] nums) {
		int len = nums.length;
		heapify(nums);
		
		// 循环不变量：区间 [0, i] 堆有序
		for (int i = len - 1; i >= 1; ) {
			// 把堆顶元素（当前最大）交换到数组末尾
            swap(nums, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            siftDown(nums, 0, i);
		}
		return nums;
	}
	
	/**
	 * 将数组整理成堆（堆有序）
	 * @param nums
	 */
	private static void heapify(int[] nums) {
		int len = nums.length;
		
		
		for (int i = (len - 1) / 2; i >= 0; i--) {
			siftDown(nums, i, len - 1);
		}
	}
	
	/**
	 * 
	 * @param nums
	 * @param k  当前下沉元素的下标
	 * @param end  [0, end] 是 nums 的有效部分
	 */
	private static void siftDown(int[] nums, int k, int end) {
		while (2 * k + 1 <= end) {
			int j = 2 * k + 1;
			if (j + 1 <= end && nums[j + 1] > nums[j]) {
				j++;
			}
			if (nums[j] > nums[k]) {
				swap(nums, j, k);
			} else {
				break;
			}
			k = j;
			
		}
		
	}
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
