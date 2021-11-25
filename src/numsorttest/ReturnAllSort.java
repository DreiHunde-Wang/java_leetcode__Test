package numsorttest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mathtest.PoorPig;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * @author Dreihunde
 *
 */
public class ReturnAllSort {
	//method 1 回溯递归
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
            //拷贝值而非传递地址
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
