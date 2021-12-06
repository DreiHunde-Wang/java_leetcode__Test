package mathtest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import binaryandtest.PrintSingleNum2;

/**
 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡�
 * ����Լ��������Ƿǿյģ����Ҹ������������Ǵ��ڶ���Ԫ�ء�
 * @author Dreihunde
 *
 */
public class MajorityElement {
	//method 1 �������� O(nlogn) O(1)
    public int majorityElement1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n / 2];
    }

    //method 2 ͶƱ������(ֻ������������������һ��) O(n) O(1)
    public int majorityElement2(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            //����Ϊ0 ���ú�ѡֵ
            if (count == 0)     candidate = num;
            //num���ѡֵƥ��count+1����count-1
            count = (candidate == num) ? count + 1 : count - 1;
        }
        return candidate;
    }

    //method 3 map��Ƶ�� O(n) O(n)
    public int majorityElement3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.getOrDefault(num, 0) > nums.length / 2)   return num;
        }
        return 0;
    }
    
    //method 4 �����ѡ O(n) o(1)
    public int majorityElement4(int[] nums) {
        int candidate = 0;
        int threashold = nums.length / 2;
        Random rand = new Random();
        while (true) {
            candidate = nums[rand.nextInt(nums.length)];
            if (countNum(nums, candidate) > threashold)
                return candidate;
        }
    }

    private int countNum(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (target == num)  
                count++;
        }
        return count;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] nums = new int[] {1,2,3,2,2,2,5,4,2};
		MajorityElement me = new MajorityElement();
		
		long startTime = System.nanoTime();
		System.out.println(me.majorityElement1(nums));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(me.majorityElement2(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(me.majorityElement3(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(me.majorityElement4(nums));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
