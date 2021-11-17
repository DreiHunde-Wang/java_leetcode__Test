package numsorttest;

public class HeapSortTest {
	public static int[] heapSort(int[] nums) {
		int len = nums.length;
		heapify(nums);
		
		// ѭ�������������� [0, i] ������
		for (int i = len - 1; i >= 1; ) {
			// �ѶѶ�Ԫ�أ���ǰ��󣩽���������ĩβ
            swap(nums, 0, i);
            // �𲽼��ٶ�����Ĳ���
            i--;
            // �±� 0 λ���³�������ʹ������ [0, i] ������
            siftDown(nums, 0, i);
		}
		return nums;
	}
	
	/**
	 * ����������ɶѣ�������
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
	 * @param k  ��ǰ�³�Ԫ�ص��±�
	 * @param end  [0, end] �� nums ����Ч����
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
