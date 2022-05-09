package src.dptest;

import java.util.ArrayList;
import java.util.List;

/**
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元�? (row, column) �?始，并尝试进�? k 次移动�??
 * 行和列是 �? 0 �?�? 的，�?以左上单元格�? (0,0) ，右下单元格�? (n - 1, n - 1) �?
 * 象棋骑士�?8种可能的走法，如下图�?示�?�每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格�?
 * col+-1 and row +-2 or col +-2 and row +-1
 * 每次骑士要移动时，它都会随机�?8种可能的移动中�?�择�?�?(即使棋子会离�?棋盘)，然后移动到那里�?
 * 骑士继续移动，直到它走了 k 步或离开了棋盘�??
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概�? �?
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * @author Dreihunde
 *
 */
public class KnightProbability {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		int k = 2;
		int row = 0;
		int column = 0;
		SolutionKP1 s1 = new SolutionKP1();
		System.out.println(s1.knightProbability(n, k, row, column));

	}

}

//method 1 dfs O(C^k) O(C^k) C = 8 超时
class SolutionKP1 {
  List<Double> remianPros;
  int kmax;
  public double knightProbability(int n, int k, int row, int column) {
      this.remianPros = new ArrayList<>();
      dfs(n, k, row, column, 1);
      double ans = 0;
      for (Double remainPro : remianPros) {
          ans += remainPro.doubleValue();
      }
      return ans; 
  }

  private void dfs(int n, int k, int row, int col, double remainPro) {
      if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
          return;
      }
      if (k == 0) {
          remianPros.add(remainPro);
          return;
      }
      
      dfs(n, k - 1, row + 2, col + 1, remainPro * 1 / 8);
      dfs(n, k - 1, row - 2, col + 1, remainPro * 1 / 8);
      dfs(n, k - 1, row + 2, col - 1, remainPro * 1 / 8);
      dfs(n, k - 1, row - 2, col - 1, remainPro * 1 / 8);
      dfs(n, k - 1, row + 1, col + 2, remainPro * 1 / 8);
      dfs(n, k - 1, row + 1, col - 2, remainPro * 1 / 8);
      dfs(n, k - 1, row - 1, col + 2, remainPro * 1 / 8);
      dfs(n, k - 1, row - 1, col - 2, remainPro * 1 / 8);
  }
}

//method 2 记忆化搜�? O(k*n^2) O(k*n^2)
class SolutionKP2 {

  private static final int[][] DIRS = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};

  public double knightProbability(int n, int k, int row, int column) {
      // 记忆化搜�?
      double[][][] memo = new double[n][n][k + 1];
      return dfs(n, k, row, column, memo);
  }

  public double dfs(int n, int k, int i, int j, double[][][] memo) {
      // 走出边界了，这条路不通，概率�?0
      if (i < 0 || j < 0 || i >= n || j >= n) {
          return 0;
      }
      // k 步走完了还没超出边界，这�?步的概率�?1，还�?要乘上前面的 k �? 1/8
      if (k == 0) {
          return 1;
      }
		
      // 缓存中存在，直接返回
      if (memo[i][j][k] != 0) {
          return memo[i][j][k];
      }

      // 每一个方向的概率都是 1/8
      double ans = 0;
      for (int[] dir : DIRS) {
          ans += dfs(n, k - 1, i + dir[0], j + dir[1], memo) / 8.0;
      }

      memo[i][j][k] = ans;

      return ans;
  }
}

//method 3 dp O(k*n^2) O(k*n^2)
class SolutionKP3 {

  private static final int[][] DIRS = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};

  public double knightProbability(int n, int k, int row, int column) {
      // 动�?�规�?
      double[][][] dp = new double[n][n][k + 1];
      // k �? 0 �?始变�?
      for (int kk = 0; kk <= k; kk++) {
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  // �? 0 步就�? [i, j] 的概率为1
                  if (kk == 0) {
                      dp[i][j][kk] = 1;
                  } else {
                      // 八个方向
                      for (int[] dir : DIRS) {
                          // 上一个坐标，这里�? 减号 也是可以�?
                          int ni = i + dir[0];
                          int nj = j + dir[1];
                          if (ni >= 0 && nj >= 0 && ni < n && nj < n) {
                              dp[i][j][kk] += dp[ni][nj][kk - 1] / 8.0;
                          }
                      }
                  }
              }
          }
      }
      // 返回�? k 步到 [row, column] 坐标的概�?
      return dp[row][column][k];
  }
}
