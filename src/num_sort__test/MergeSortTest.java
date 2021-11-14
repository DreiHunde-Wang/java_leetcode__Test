package num_sort__test;

public class MergeSortTest {
	//�鲢����
	/**
	 * �б��СС�ڵ��ڸ�ֵʱ������ʹ�ò�������
	 */
	private static final int INSERTION_SORT_THRESHOLD = 7;

	public static int[] mergeSort(int[] nums) {
		int len = nums.length;
		int[] temp = new int[len];
		mergeSortOrder(nums, 0, len - 1, temp);
		return nums;
	}
	
	/**
	 * ������ nums �������� [left, right] ���й鲢����
	 * 
	 * @param nums
	 * @param left
	 * @param right
	 * @param temp ���ںϲ�������������ĸ������飬ȫ��ʹ��һ�ݣ������δ���������
	 */
	private static void mergeSortOrder(int[] nums, int left, int right, int[] temp) {
		//С����ʹ�ò�������
		if(right - left <= INSERTION_SORT_THRESHOLD) {
			InsertSortTest.insertSort(nums);
			return;
		}
		int mid = (left + right) >>> 1;
		mergeSortOrder(nums, left, mid, temp);
		mergeSortOrder(nums, mid + 1, right, temp);
		//����������������䱾����������ϲ�
		if(nums[mid] <= nums[mid + 1]) {
			return;
		}
		mergeOfTwoSortedArray(nums, left, mid, right, temp);
	}
	
	/**
	 * �ϲ������������飺�Ȱ�ֵ���Ƶ���ʱ���飬�ٺϲ���ȥ
	 * 
	 * @param nums
	 * @param left
	 * @param mid [left, mid] ����[mid + 1, right] ����
	 * @param right
	 * @param temp
	 */
	private static void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
		System.arraycopy(nums, left, temp, mid, right - left + 1);
		
		int i = left;
		int j = mid + 1;
		
		for(int k = left; k <= right; k++) {
			if(i == mid + 1) {
				nums[k] = temp[j];
				j++;
			} else if(j == right + 1) {
				nums[k] = temp[i];
				i++;
			} else if(temp[i] <= temp[j]) {
				// ע��д�� < �Ͷ�ʧ���ȶ��ԣ���ͬԪ��ԭ����ǰ�������Ժ���Ȼ��ǰ��
				nums[k] = temp[i];
				i++;
			} else {
				// temp[i] > temp[j]
				nums[k] = temp[j];
				j++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}


}
