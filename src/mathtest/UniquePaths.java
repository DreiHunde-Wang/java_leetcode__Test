package src.mathtest;

/**
 * �?个机器人位于�?�? m x n 网格的左上角 （起始点在下图中标记�? “Start�? ）�??
 * 机器人每次只能向下或者向右移动一步�?�机器人试图达到网格的右下角（在下图中标记为 “Finish�? ）�??
 * 问�?�共有多少条不同的路径？
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * @author Dreihunde
 *
 */
public class UniquePaths {
	//method 1 dfs O((mn)^2) O(1) 超时
    int pathCount;
    public static int[][] dirs = new int[][]{{0, 1}, {1, 0}};
    public int uniquePaths1(int m, int n) {
        pathCount = 0;
        dfs(0, 0, m, n);
        return pathCount;
    }

    public void dfs(int curX, int curY, int m, int n) {
        if (curX == m - 1 && curY == n - 1) {
            pathCount++;
            return;
        }
        for (int[] dir : dirs) {
            int nx = curX + dir[0];
            int ny = curY + dir[1];
            if (nx >= m || ny >= n) {
                continue;
            }
            dfs(nx, ny, m, n);
        }
    }

    //method 2 dp O(mn) O(mn) dp[i][j]表示�?0,0到i,j有多少种路径
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //method 3 math O(m) O(1) 从左上角到右下角的过程中，我们需要移�? m+n�?2 次，其中�? m�?1 次向下移动，n�?1 次向右移动�?�C(m + n - 2, m - 1)
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

}
