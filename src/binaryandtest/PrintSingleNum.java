package src.binaryandtest;



/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * @author Dreihunde
 *
 */
public class PrintSingleNum {
	//method 1 按位异或 异或运算满足交换律 a ^ b = b ^ a， 相同位为0，不同位为1 O(n) O(1)
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }

        int div = 1;
        //0说明此位相等,找出不相等的一个1
        while ((div & ret) == 0) {
            div <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
        	//按不相等的1划分num，可以把a, b分开
            if ((num & div) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1,2,5,2};
		PrintSingleNum ps = new PrintSingleNum();
		int[] rev = ps.singleNumbers(nums);
		System.out.println(rev[0] + " " + rev[1]);

	}

}
