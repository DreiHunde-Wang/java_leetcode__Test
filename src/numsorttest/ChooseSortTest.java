package src.numsorttest;

import java.util.Arrays;

public class ChooseSortTest {
	//选择排序：每一轮选择最小的元素交换到未拍定的开头
	/**summary
	 * 算法思想 1：贪心算法：每一次决策只看当前，当前最优，则全局最优。注意：这种思想不是任何时候都适用。
	 * 算法思想 2：减治思想：外层循环每一次都能排定一个元素，问题的规模逐渐减少，直到全部解决，即「大而化小，小而化了」。运用「减治思想」很典型的算法就是大名鼎鼎的「二分查找」。
	 * 优点：交换次数最少。
	 */
	
	public static int[] chooseSort(int[] nums) {
		int len = nums.length;
		//循环不变量:[0, i)有序，且为最终排定
		for(int i= 0; i < len - 1; i++) {
			//选择区间[i, len - 1]里最小的元素的索引，交换到下标i
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
