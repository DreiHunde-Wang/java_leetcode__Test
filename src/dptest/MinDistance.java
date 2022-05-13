package src.dptest;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 链接：https://leetcode.cn/problems/edit-distance
 * @author Dreihunde
 *
 */
public class MinDistance {
	//method 1 dp O(mn) O(mn)
    //问题拆解
    //本质不同的操作实际上只有三种：
    //在单词 A 中插入一个字符；
    //在单词 B 中插入一个字符；
    //修改单词 A 的一个字符。
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n * m == 0) {
            return n + m;
        }
        int[][] dp = new int[n + 1][m + 1];

        //预设边界值
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                //给A插入一个字母
                int left = dp[i - 1][j] + 1;
                //给B插入一个字母
                int down = dp[i][j - 1] + 1;
                //修改一个字母
                int leftdown = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    leftdown += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, leftdown));
            }
        }
        return dp[n][m];

    }

}
