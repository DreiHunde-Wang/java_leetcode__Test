package src.stringnumtest;

import java.util.ArrayList;
import java.util.List;

import src.numsorttest.CommonTest;
import src.printtreenode.ComnTest;

/**
 * 输入�?个字符串，打印出该字符串中字符的�?有排列�??
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素�?
 * @author Dreihunde
 *
 */
public class ReturnAllUniqueSort {
	//method 1 回溯+剪枝 O（n * n!) O(n)
	boolean[] vis;
	List<String> rec;
	public String[] permutation1(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        vis = new boolean[n];
        rec = new ArrayList<>();
        StringBuffer perm = new StringBuffer();
        dfs(arr, 0, perm);
        String[] revNum = new String[rec.size()];
        for (int i = 0; i < rec.size(); i++) {
        	revNum[i] = rec.get(i);
        }
        return revNum;
        
    }
	
	private void dfs(char[] arr, int idx, StringBuffer perm) {
		if (idx == arr.length) {
			rec.add(perm.toString());
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			// vis[i - 1] == true，说明同�?树⽀nums[i - 1]使⽤�?
            // vis[i - 1] == false，说明同�?树层nums[i - 1]使⽤�?
            // 如果同⼀树层arr[i - 1]使用过则直接跳过
			if (vis[i] || (i > 0 && !vis[i - 1] && arr[i] == arr[i - 1])) {
				continue;
			}
			vis[i] = true;
			perm.append(arr[i]);
			dfs(arr, idx + 1, perm);
			//idx = perm.length() - 1,即刚刚添加的�?
			perm.deleteCharAt(idx);
			//回溯
			vis[i] = false;
		}
	}
	
	//method 2 下一个排�? O(n * n!) O(1)
	public String[] permutation2(String s) {
		List<String> ret = new ArrayList<>();
        int n = s.length();
        char[] arr = s.toCharArray();
        ret.add(new String(arr));
        while (nextPermutation(arr)) {
        	ret.add(new String(arr));
        }
        
        String[] revNum = new String[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
        	revNum[i] = ret.get(i);
        }
        return revNum;
        
    }
	
	private boolean nextPermutation(char[] arr) {
		int i = arr.length - 2;
		while (i >= 0 && arr[i] >= arr[i + 1]) {
			i--;
		}
		if (i < 0) {
			return false;
		}
		int j = arr.length - 1;
		while (j >= 0 && arr[i] >= arr[j]) {
			j--;
		}
		swap(arr, i, j);
		reverse(arr, i + 1);
		return true;
	}
	
	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private void reverse(char[] arr, int start) {
		int left = start;
		int right = arr.length - 1;
		while (left < right) {
			swap(arr, left, right);
			left++;
			right--;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcd";
		
		ReturnAllUniqueSort ra = new ReturnAllUniqueSort();
		
		long startTime=System.nanoTime(); 
		CommonTest.printNum(ra.permutation1(s));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		CommonTest.printNum(ra.permutation2(s));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
