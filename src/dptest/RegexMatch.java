package src.dptest;

/**
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * @author Dreihunde
 *
 */
public class RegexMatch {
	//method 1 dp O(mn) O(mn)
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //前m个字符和前n个字符是否匹配
        boolean[][] f = new boolean[m + 1][n + 1];
        //代表s和p都为空字符时的状态
        f[0][0] = true;
        //i s的第i个字母 j p的第j个字母
        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                //如果p的第j个字母为*
                if (p.charAt(j - 1) == '*') {
                    //倒退两个字母(*代表前面的字母可以为0个)
                    f[i][j] = f[i][j - 2];
                    //如果s的第i个字母和p的第j-1个字母相同，那么选择一种可以的匹配模式
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                //否则按对应字符匹配
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        //匹配完s的m个字母和p的n个字母
        return f[m][n];

    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.')
            return true;

        return s.charAt(i - 1) == p.charAt(j - 1);

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "mississippi";
		String p = "mis*is*p*.";
		
		RegexMatch rm = new RegexMatch();
		System.out.println(rm.isMatch(s, p));
	}

}
