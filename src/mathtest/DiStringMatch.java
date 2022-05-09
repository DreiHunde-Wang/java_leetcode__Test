package src.mathtest;

/**
 * 由范�? [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其�?:
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
 * 如果 perm[i] > perm[i + 1] ，那�? s[i] == 'D' 
 * 给定�?个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何�?�? �?
 * 链接：https://leetcode.cn/problems/di-string-match
 * @author Dreihunde
 *
 */
public class DiStringMatch {
	//method 1 math O(n) O(1)
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int lt = 0;
        int rt = n;
        int index = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                ans[index++] = lt++;
            } else {
                ans[index++] = rt--;
            }
        }
        ans[index] = lt;
        return ans;
    }

}
