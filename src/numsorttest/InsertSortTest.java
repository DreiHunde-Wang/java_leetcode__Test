package numsorttest;

public class InsertSortTest {
	//插入排序：稳定排序，在接近有序的情况下，表现优异
	/**
	 * 优化：「将一个数字插入一个有序的数组」这一步，可以不使用逐步交换，使用先赋值给「临时变量」
	 * ，然后「适当的元素」后移，空出一个位置，最后把「临时变量」赋值给这个空位的策略。
	 * 编码的时候如果不小心，可能会把数组的值修改，建议多调试；
	 * 特点：「插入排序」可以提前终止内层循环（体现在 nums[j - 1] > temp 不满足时），
	 * 在数组「几乎有序」的前提下，「插入排序」的时间复杂度可以达到 O(N)；
	 * 由于「插入排序」在「几乎有序」的数组上表现良好，特别地，在「短数组」上的表现也很好。因为「短数组」的特点是：
	 * 每个元素离它最终排定的位置都不会太远。为此，在小区间内执行排序任务的时候，可以转向使用「插入排序」。
	 */
	
	/**
	 * 
	 * @param nums
	 * @return nums
	 */
	public static int[] insertSort(int[] nums) {
		int len = nums.length;
		// 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
		for(int i = 1; i < len; i++) {
			// 先暂存这个元素，然后之前元素逐个后移，留出空位
			int temp = nums[i];
			int j = i;
			// 注意边界 j > 0
			while(j > 0 && nums[j - 1] > temp) {
				nums[j] = nums[j - 1];
				j--;
			}
			nums[j] = temp;
		}
		return nums;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
