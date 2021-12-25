package numsorttest;

/**
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 你可以使用空间复杂度为 O(1) 的原地算法解决这个问题
 * @author Dreihunde
 *
 */
public class RotateNum {
	//method 1 反转 O(n) O(1)
	public void rotate1(int[] nums, int k) {
		//轮转n次等于不变
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp  = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
    //method 2 环状轮替 O(n) O(1)
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,3,4,5,6,7};
		int k = 3;
		
		RotateNum rn = new RotateNum();
		long startTime = System.nanoTime();
		rn.rotate1(nums, k);
		CommonTest.printNum(nums);
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		rn.rotate2(nums, k);
		CommonTest.printNum(nums);
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
