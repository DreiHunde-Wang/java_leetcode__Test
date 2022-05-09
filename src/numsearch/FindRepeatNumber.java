package src.numsearch;

public class FindRepeatNumber {
	 //method1
    // public static int findRepeatNumber(int[] nums) {
    //     Arrays.sort(nums);
    //     for (int i = 0; i < nums.length - 1; i++) {
    //         if(nums[i + 1] == nums[i])
    //             return nums[i];
    //     }

    //     return -1;

    // }

    //method2
    // public static int findRepeatNumber(int[] nums) {
    //     Set<Integer> set = new HashSet<>();

    //     for (int i = 0; i < nums.length; i++) {
    //         if (!set.add(nums[i])) {
    //             return nums[i];
    //         }
    //     }

    //     return -1;

    // }

    //method3
    public static int findRepeatNumber(int[] nums) {
        int[] count = new int[nums.length];

        for (int num : nums) {
            count[num]++;
            if (count[num] > 1)
                return num;
        }

        return -1;

    }

}
