package numsorttest;

public class MergeSortTest {
	//�鲢����
	/**
	 * �б��СС�ڵ��ڸ�ֵʱ������ʹ�ò�������
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
//			InsertSortTest.insertSort(nums);
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
		int i = left;//������ָ��
		int j = mid + 1;//������ָ��
		int t = 0;//��ʱ����ָ��
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
		
		//�����ʣ��Ԫ������temp
		while (i <= mid) {
			temp[t] = nums[i];
			i++;
			t++;
		}
		//��������ʣ��Ԫ������temp��
		while (j <= right) {
			temp[t] = nums[j];
			t++;
			j++;
		}
		t = 0;
		//��temp�е�Ԫ��ȫ��������ԭ������
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
