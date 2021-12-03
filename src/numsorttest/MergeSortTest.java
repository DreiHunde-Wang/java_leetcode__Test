package numsorttest;

public class MergeSortTest {
	//归并排序
	/**
	 * 列表大小小于等于该值时，优先使用插入排序
	 */
//	private static final int INSERTION_SORT_THRESHOLD = 7;
	private static final int INSERTION_SORT_THRESHOLD = 0;

	public static int[] mergeSort(int[] nums) {
		int len = nums.length;
		int[] temp = new int[len];
		mergeSortOrder(nums, 0, len - 1, temp);
		return nums;
	}
	
	/**
	 * 对数组 nums 的子区间 [left, right] 进行归并排序
	 * 
	 * @param nums
	 * @param left
	 * @param right
	 * @param temp 用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
	 */
	private static void mergeSortOrder(int[] nums, int left, int right, int[] temp) {
		//小区间使用插入排序
		if(right - left <= INSERTION_SORT_THRESHOLD) {
//			InsertSortTest.insertSort(nums);
			return;
		}
		int mid = (left + right) >>> 1;
		mergeSortOrder(nums, left, mid, temp);
		mergeSortOrder(nums, mid + 1, right, temp);
		//如果数组的这个子区间本身有序，无需合并
		if(nums[mid] <= nums[mid + 1]) {
			return;
		}
		mergeOfTwoSortedArray(nums, left, mid, right, temp);
	}
	
	/**
	 * 合并两个有序数组：先把值复制到临时数组，再合并回去
	 * 
	 * @param nums
	 * @param left
	 * @param mid [left, mid] 有序，[mid + 1, right] 有序
	 * @param right
	 * @param temp
	 */
	private static void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
		int i = left;//左序列指针
		int j = mid + 1;//右序列指针
		int t = 0;//临时数组指针
		while (i <= mid && j <= right) {
			if (nums[i] <= nums[j]) {
				temp[t] = nums[i];
				i++;
			} else {
				temp[t] = nums[j];
				j++;
			}
			t++;
		}
		
		//将左边剩余元素填入temp
		while (i <= mid) {
			temp[t] = nums[i];
			i++;
			t++;
		}
		//将右序列剩余元素填充进temp中
		while (j <= right) {
			temp[t] = nums[j];
			t++;
			j++;
		}
		t = 0;
		//将temp中的元素全部拷贝到原数组中
		while (left <= right) {
			nums[left] = temp[t];
			t++;
			left++;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {5, 3, 2, 1, 6, 7, 4, 9, 8};
		CommonTest.printNum(nums);
		mergeSort(nums);
		CommonTest.printNum(nums);
	}


}
