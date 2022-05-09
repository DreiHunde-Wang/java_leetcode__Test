package src.numsorttest;

import java.util.Arrays;

public class ChooseSortTest {
	//ѡ������ÿһ��ѡ����С��Ԫ�ؽ�����δ�Ķ��Ŀ�ͷ
	/**summary
	 * �㷨˼�� 1��̰���㷨��ÿһ�ξ���ֻ����ǰ����ǰ���ţ���ȫ�����š�ע�⣺����˼�벻���κ�ʱ�����á�
	 * �㷨˼�� 2������˼�룺���ѭ��ÿһ�ζ����Ŷ�һ��Ԫ�أ�����Ĺ�ģ�𽥼��٣�ֱ��ȫ����������������С��С�����ˡ������á�����˼�롹�ܵ��͵��㷨���Ǵ��������ġ����ֲ��ҡ���
	 * �ŵ㣺�����������١�
	 */
	
	public static int[] chooseSort(int[] nums) {
		int len = nums.length;
		//ѭ��������:[0, i)������Ϊ�����Ŷ�
		for(int i= 0; i < len - 1; i++) {
			//ѡ������[i, len - 1]����С��Ԫ�ص��������������±�i
			int minIndex = i;
			for(int j = i + 1; j < len; j++) {
				if(nums[j] < nums[minIndex]) {
					minIndex = j;
				}
			}
			swap(nums, i, minIndex);
		}
		
		
		return nums;
	}
	
	public static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	

}
