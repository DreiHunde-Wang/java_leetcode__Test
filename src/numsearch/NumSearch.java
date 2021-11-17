package numsearch;

public class NumSearch {
	public static int search(int[] nums, int target) {
        
        if (nums.length == 0)
            return 0;

        //method1
        // int count = 0;
        // for (int num: nums) {
        //     if (num == target)
        //         count++;
        // }

        // return count;

        //method2
        // return searchTarget(nums, 0, nums.length, target);

        //method3
        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) 
                left = mid + 1;
            else if (nums[mid] > target) 
                right = mid - 1;
            else {
                left = mid;
                right = mid;
                while (left >= 0 && nums[left] == target)
                    left--;
                while (right < nums.length && nums[right] == target)
                    right++;
                return right - left - 1;
            }
        }
        return 0;
       

    }

    private static int searchTarget(int[] nums, int left, int right, int target) {
        while (left < right && nums[left] != target)
            left++;
        int left2 = left;
        while (left2 < right && nums[left2] == target)
            left2++;

        return left2 - left;

    }
    
    public static void main(String[] args) {
    	int[] nums = new int[] {1, 2};
    	System.out.println(search(nums, 1));
    }
}
