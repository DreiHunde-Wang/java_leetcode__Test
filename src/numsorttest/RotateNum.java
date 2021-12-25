package numsorttest;

/**
 * ����һ�����飬�������е�Ԫ��������ת k ��λ�ã����� k �ǷǸ�����
 * �����ʹ�ÿռ临�Ӷ�Ϊ O(1) ��ԭ���㷨����������
 * @author Dreihunde
 *
 */
public class RotateNum {
	//method 1 ��ת O(n) O(1)
	public void rotate1(int[] nums, int k) {
		//��תn�ε��ڲ���
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
    
    //method 2 ��״���� O(n) O(1)
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
