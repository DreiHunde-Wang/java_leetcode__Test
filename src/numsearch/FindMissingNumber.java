package numsearch;

/**
 * 数组为递增数列，寻找错位的数字下标
 * @author Dreihunde
 *
 */
public class FindMissingNumber {
	public static int missingNumber1(int[] nums) {
        //method 1
        int index = 0;
        for (int num: nums) {
            if (index != num)
                return index;
            else
                index++;
        }

         return index;

    }
	
	public static int missingNumber2(int[] nums) {
        //method2
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) >>> 1;
            if (nums[mid] == mid)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return left;

    }
	public static void main(String[] args) {
		int[] nums = new int[] {0,1,2,3,5,6,7};
		
		long startTime = System.nanoTime();
		System.out.println(missingNumber1(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(missingNumber2(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}
	

}
