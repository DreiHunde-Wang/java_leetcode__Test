package numsorttest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mathtest.PoorPig;

/**
 * ����һ�������ظ����ֵ����� nums �������� ���п��ܵ�ȫ���� ������� ������˳�� ���ش𰸡�
 * @author Dreihunde
 *
 */
public class ReturnAllSort {
	//method 1 ���ݵݹ�
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rev = new ArrayList<>();
        ArrayList<Integer> input = new ArrayList<>();
        for (int num : nums) {
            input.add(num);
        }

        backtrack(0, nums.length, input, rev);
        return rev;
    }

    private static void backtrack(int order, int n, ArrayList<Integer> input, List<List<Integer>> rev) {
        if (order == n) {
            //����ֵ���Ǵ��ݵ�ַ
            rev.add(new ArrayList<>(input));
            return;
        }

        for (int i = order; i < n; i++) {
            Collections.swap(input, order, i);
            backtrack(order + 1, n, input, rev);
            Collections.swap(input, order, i);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 2, 3};
		
		ReturnAllSort rt = new ReturnAllSort();
    	long startTime=System.nanoTime(); 
		System.out.println(rt.permute(nums));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");


	}

}
