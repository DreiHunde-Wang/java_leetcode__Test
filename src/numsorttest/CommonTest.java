package src.numsorttest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.HashSet;

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
        List<Integer> list1 = new LinkedList<>();
        Hashtable<Integer, Integer> table = new Hashtable<>();
        
        System.out.println(Arrays.toString(res));


	}
	
	public static void printNum(int[] nums) {
		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void printNum(double[] nums) {
		for (double num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void printNum(String[] nums) {
		for (String num : nums) {
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
