package numsorttest;

import java.util.Arrays;

public class CommonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {5, 2, 3, 1};
		
//		ChooseSortTest solution = new ChooseSortTest();
//		int[] res = solution.chooseSort(nums);
		
//        int[] res = ChooseSortTest.chooseSort(nums);
//        int[] res = MergeSortTest.mergeSort(nums);
//        int[] res = QuickSortTest.quickSort(nums);
        int[] res = HeapSortTest.heapSort(nums);
        
        System.out.println(Arrays.toString(res));


	}
	
	public static void printNum(int[] nums) {
		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void printNum(int[][] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				System.out.print(nums[i][j] + " ");
			}
		}
		System.out.println();
	}

}
