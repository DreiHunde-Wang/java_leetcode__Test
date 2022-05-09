package src.numsorttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import src.printtreenode.ComnTest;

/**
 * ����һ�����飬��ӡ�������������ֵ��������С�
 * �����������˳�򷵻�������飬�����治�����ظ�Ԫ�ء�
 * @author Dreihunde
 *
 */
public class ReturnUniqueAllSort {
	//method 1 �ݻ� api����
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> input = new ArrayList<>();

        for (int num: nums) {
            input.add(num);
        }

        DFS(0, nums.length, input, res);
        return res;
    }

    private static void DFS(int order, int n, List<Integer> input, List<List<Integer>> res) {
        if (order == n) {
            if (!res.contains(input))
                res.add(new ArrayList<>(input));
            return;
        }
        for (int i = order; i < n; i++) {
            Collections.swap(input, i, order);
            DFS(order + 1, n, input, res);
            Collections.swap(input, i, order);
        }

    }

    //method 2 ��֦
    boolean[] vis;

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            //�������ҵ�һ��δ�����������
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[] {1, 2, 3};
		
		ReturnUniqueAllSort rt = new ReturnUniqueAllSort();
    	long startTime=System.nanoTime(); 
		ComnTest.printListList(rt.permuteUnique1(nums));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		ComnTest.printListList(rt.permuteUnique2(nums));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
