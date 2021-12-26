package numsorttest;

/**
 * ����һ������������ nums������ k �����ҳ��������ڳ˻�С�� k ��������������ĸ�����
 * @author Dreihunde
 *
 */
public class CountSubMulLessThanK {
	//method 1 ���� O(n2) O(1)
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int left = 0; left < n; left++) {
            int right = left;
            int mul = 1;
            while (right < n) {
                mul *= nums[right];
                right++;
                if (mul < k) {
                    sum++;
                } else {
                    break;
                }
            }
        }
        return sum;
    }

    //method 2 ˫ָ���Ż� O(n) O(1)
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int left = 0;
        int mul = 1;
        for (int right = 0; right < n; right++) {
            mul *= nums[right];
            while (left <= right && mul >= k) {
                mul /= nums[left];
                left++;
            }
            if (left <= right) {
                sum += right - left + 1;
            }
        }
        return sum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {10,5,2,6};
		int k = 100;
		
		CountSubMulLessThanK cs = new CountSubMulLessThanK();
		long startTime = System.nanoTime();
		System.out.println(cs.numSubarrayProductLessThanK1(nums, k));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(cs.numSubarrayProductLessThanK2(nums, k));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
