package numsorttest;

import java.util.Arrays;
import java.util.Random;

/**
 * ����һ���������� nums ������㷨������һ��û���ظ�Ԫ�ص����顣
 * ʵ�� Solution class:
 * Solution(int[] nums) ʹ���������� nums ��ʼ������
 * int[] reset() �������鵽���ĳ�ʼ״̬������
 * int[] shuffle() ��������������Һ�Ľ��
 * @author Dreihunde
 *
 */
public class NumMass {
	 int[] pre;
	 int[] rev;
	 Random random = new Random();

	 public NumMass(int[] nums, int flag) {
		 switch(flag) {
		 case 1:
			 pre = new int[nums.length];
			 System.arraycopy(nums, 0, pre, 0, nums.length);
		 case 2:
			 pre = nums.clone();
		 case 3:
			 pre = Arrays.copyOf(nums, nums.length);
		 }
		 rev = nums;
	}
	    
	public int[] reset() {
		return pre;
	}
	    
	public int[] shuffle() {
		int n = pre.length;
		if (n == 0) {
			return new int[0];
		}
		for (int i = 0; i < n; i++) {
			int j = i + random.nextInt(n - i);
			swap(rev, i, j);
		}
		return rev;
	}

	private static void swap (int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	private static void printNum(int[] nums) {
		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 2, 3};

		long startTime = System.nanoTime();
		NumMass nm1 = new NumMass(nums, 1);
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		NumMass nm2 = new NumMass(nums, 2);
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		NumMass nm3 = new NumMass(nums, 3);
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

		printNum(nm1.shuffle());
		printNum(nm1.reset());
		printNum(nm1.shuffle());


	}

}
