package numsorttest;

/**
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，
 * 所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 * @author Dreihunde
 *
 */
public class TwoPlusTarget {
	
	//method 1 单指针移动+二分查找 O(nlogn) O(1)
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int rev = binarySearch(nums, i + 1, nums.length - 1, target - nums[i]);
            if (rev != -1) {
                return new int[]{nums[i], nums[rev]};
            }
        }

        return new int[0];
    }


    private static int binarySearch(int[] nums, int left, int right, int target) {
        int l = left;
        int r = right;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    //method 2 双指针移动 O(n) O(1)
    public int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (nums[left] + nums[right] != target) {
            if (nums[left] < target - nums[right]) {
                left++;
            } else if(nums[left] > target - nums[right]){
                right--;
            }
            if (left >= right)
                return new int[0];
        }

        return new int[]{nums[left], nums[right]};
    }
    
    private static void printNums(int[] nums) {
    	for (int num : nums) {
    		System.out.print(num + " ");
    	}
    	System.out.println();
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {2, 4, 5, 6, 7, 8, 10};
		int target = 10;
		TwoPlusTarget tw = new TwoPlusTarget();
		
		long startTime=System.nanoTime(); 
		printNums(tw.twoSum1(nums, target));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		printNums(tw.twoSum2(nums, target));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
