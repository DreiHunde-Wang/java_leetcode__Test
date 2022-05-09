package src.numsearch;

/**
 * 给你�?个整数数组 nums ，判断这个数组中是否存在长度�? 3 的�?�增子序列�??
 * 如果存在这样的三元组下标 (i, j, k) 且满�? i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返�? true �?
 * 否则，返�? false �?
 * @author Dreihunde
 *
 */
public class FindIncreasingSub {
	//method 1 贪心 O(n) O(1)
    public boolean increasingTriplet1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int n = nums.length;
        int first = nums[0];
        int second = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            }

            if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }

        return false;
    }

    //method 2 双向遍历 O(n) O(n)
    public boolean increasingTriplet2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int n = nums.length;
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (leftMin[i - 1] < nums[i] && rightMax[i + 1] > nums[i]) {
                return true;
            }
        }

        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {2,1,5,0,4,6};
		FindIncreasingSub fi = new FindIncreasingSub();
		System.out.println(fi.increasingTriplet1(nums));
		System.out.println(fi.increasingTriplet2(nums));

	}

}
