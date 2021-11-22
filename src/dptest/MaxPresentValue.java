package dptest;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * @author Dreihunde
 *
 */
public class MaxPresentValue {
	//method 1 动态规划
    public int maxValue1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0)
            return 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    //method 2 动态规划+优化代码行数
    public int maxValue2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0)
            return 0;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = grid[i - 1][j - 1] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
    
  //method 2 动态规划+预处理边界
    public int maxValue3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 0)
            return 0;
        int[][] dp = new int[m][n];
        
        dp[0][0] = grid[0][0];
        
        for (int j = 1; j < n; j++) {
        	dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        
        for (int i = 1; i < m; i++) {
        	dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//[1,3,1]
		//[1,5,1]
		//[4,2,1]
		int[][] grid = new int[][] {{1,3,1}, {1,5,1}, {4,2,1}};
		MaxPresentValue mp = new MaxPresentValue();
		
		long startTime = System.nanoTime();
		System.out.println(mp.maxValue1(grid));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(mp.maxValue2(grid));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(mp.maxValue3(grid));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}
