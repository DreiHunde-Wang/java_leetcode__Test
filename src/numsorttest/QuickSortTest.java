package src.numsorttest;

import java.util.Random;

public class QuickSortTest {
	
	private static final int INSERTION_SORT_THRESHOLD = 0;
	
	private final Random RANDOM = new Random();
	
	public int[] quickSort(int[] nums) {
		int len = nums.length;
		quickSortOrder(nums, 0, len - 1);
		return nums;
	}
	
	private void quickSortOrder(int[] nums, int left, int right) {
		//С����ʹ�ò�������
		if(right - left <= INSERTION_SORT_THRESHOLD) {
			InsertSortTest.insertSort(nums);
			return;
		}
		
		int pIndex = partition(nums, left, right);
		quickSortOrder(nums, left, pIndex - 1);
		quickSortOrder(nums, pIndex + 1, right);
	}
	
	private int partition(int[] nums, int left, int right) {
		int randomIndex = left + RANDOM.nextInt(right - left + 1);
		swap(nums, left, randomIndex);
		
		int pivot = nums[left];
		int lt = left + 1;
		int gt = right;
		
		// ѭ����������
        // all in [left + 1, lt) <= pivot
        // all in (gt, right] >= pivot
		while(true) {
			while(lt <= right && nums[lt] < pivot) {
				lt++;
			}
			while(gt > left && nums[gt] > pivot) {
				gt--;
			}
			
			if(lt >= gt)
				break;
			// ϸ�ڣ���ȵ�Ԫ��ͨ���������ȸ��ʷֵ����������
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
	
	public static void main(String[] args) {
//		int[] nums = new int[] {0,1,2,1};
		int[] nums = new int[] {3,2,1};
		QuickSortTest qs = new QuickSortTest();
		nums = qs.quickSort(nums);
		for (int num : nums) {
			System.out.print(num + " ");
		}
	}
}
