package src.binaryandtest;
import java.util.Map;
import java.util.HashMap;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * @author Dreihunde
 *
 */
public class PrintSingleNum2 {
	//method 1 hashmap O(n) O(n)
	public int singleNumber1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (Integer n : freq.keySet()) {
            if (freq.get(n) == 1) {
            	return n.intValue();
            }
        }
        return 0;
        
    }

    //method 2 count计位法 O(n) O(1)
    public int singleNumber2(int[] nums) {
        int[] count = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }

        int ans = 0;
        int mod = 3;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= count[31 - i] % mod;
        }

        return ans;
    }

    //method 3 状态自动机 O(n) O(1)
    public int singleNumber3(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;

    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {3,4,3,3};
		PrintSingleNum2 ps2 = new PrintSingleNum2();
		
		long startTime = System.nanoTime();
		System.out.println(ps2.singleNumber1(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(ps2.singleNumber2(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(ps2.singleNumber3(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
