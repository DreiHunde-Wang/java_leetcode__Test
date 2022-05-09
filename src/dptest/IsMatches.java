package src.dptest;

/**
 * 给你�?个字符串 s 和一个字符规律 p，请你来实现�?个支�? '.' 和�?'*' 的正则表达式匹配�?
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元�?
 * �?谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串�?
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * @author Dreihunde
 *
 */
public class IsMatches {
	//method 1 dp O(mn) O(mn)
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        //dp[i][j] 为s的前i个字符与p的前j个字符匹�?
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (match(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; 
                    } 
                } else {
                    if (match(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    private boolean match(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
