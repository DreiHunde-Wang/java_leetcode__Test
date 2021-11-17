package numsearch;

public class FindMissingNumber {
	public static int missingNumber(int[] nums) {
        //method 1
        // int index = 0;
        // for (int num: nums) {
        //     if (index != num)
        //         return index;
        //     else
        //         index++;
        // }

        // return index;

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

        return right + 1;

    }

}
