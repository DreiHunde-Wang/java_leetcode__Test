package src.dptest;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的�?�? 公共子序�? 的长度�?�如果不存在 公共子序�? ，返�? 0 �?
 * �?个字符串的 子序列 是指这样�?个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串�??
 * 例如�?"ace" �? "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列�?
 * 两个字符串的 公共子序�? 是这两个字符串所共同拥有的子序列�?
 * @author Dreihunde
 *
 */
public class MaxCommonSub {
	//method 1 dp O(mn) O(mn)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text1 = "oxcpqrsvwf";
		String text2 = "shmtulqrypy";
		
		MaxCommonSub mc = new MaxCommonSub();
		System.out.println(mc.longestCommonSubsequence(text1, text2));

	}

}
