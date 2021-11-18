package numsearch;

import java.util.Arrays;
import numsorttest.*;

/**
 * 寻找第k个最大值
 * @author Dreihunde
 *
 */
public class FindKMaxNumber {
	
	//method 1
	public static int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        if(len == 1)
            return nums[0];
        
        Arrays.sort(nums);
        return nums[len - k];
    }
	
	//method 2
	public static int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        if(len == 1)
            return nums[0];
        
        HeapSortTest.heapSort(nums);
        return nums[len - k];
    }
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {2, 5, 6, 1, 3, 5, 7};
		long startTime = System.nanoTime();
		System.out.println(findKthLargest1(nums, 5));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(findKthLargest2(nums, 5));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		
	}

}
