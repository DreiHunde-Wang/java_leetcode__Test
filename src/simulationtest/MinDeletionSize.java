package src.simulationtest;

/**
 * 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：
 * abc
 * bce
 * cae
 * 你需要找出并删除 不是按字典序升序排列的 列。
 * 在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）和列 2（'c', 'e', 'e'）都是按升序排列的，而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * 返回你需要删除的列数。
 * 链接：https://leetcode.cn/problems/delete-columns-to-make-sorted
 * @author Dreihunde
 *
 */
public class MinDeletionSize {
	class Solution {
	    //method 1 mask顺序遍历 O(nm) O(m)
	    public int minDeletionSize1(String[] strs) {
	        if (strs.length == 0) {
	            return -1;
	        }
	        int[] mask = new int[strs[0].length()];
	        for (int i = 0; i < strs.length; i++) {
	            for (int j = 0; j < strs[i].length(); j++) {
	                if (mask[j] == -1) {
	                    continue;
	                }
	                int t = strs[i].charAt(j) - 'a';
	                if (t < mask[j]) {
	                    mask[j] = -1;
	                } else {
	                    mask[j] = t;
	                }
	            }
	        }
	        int count = 0;
	        for (int t : mask) {
	            count += t == -1 ? 1 : 0;
	        }
	        return count;
	    }

	    //method 2 模拟 O(nm) O(1)
	    public int minDeletionSize(String[] strs) {
	        int n = strs.length;
	        int m = strs[0].length();
	        int count = 0;
	        for (int i = 0; i < m; i++) {
	            for (int j = 1; j < n; j++) {
	                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
	                    count++;
	                    break;
	                }
	            }
	        }
	        return count;
	    }

	}

}
