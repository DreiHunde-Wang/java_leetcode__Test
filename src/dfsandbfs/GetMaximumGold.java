package src.dfsandbfs;

/**
 * 你要�?发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网�? grid 进行了标注�??
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0�?
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * 每当矿工进入�?个单元，就会收集该单元格中的�?有黄金�??
 * 矿工每次可以从当前位置向上下左右四个方向走�??
 * 每个单元格只能被�?采（进入）一次�??
 * 不得�?采（进入）黄金数目为 0 的单元格�?
 * 矿工可以从网格中 任意�?�? 有黄金的单元格出发或者是停止�?
 * 链接：https://leetcode-cn.com/problems/path-with-maximum-gold
 * @author Dreihunde
 *
 */
public class GetMaximumGold {
	//method 1 遍历 + 溯回 O(mn + min(mn, T) * 3^min(mn, T)) O(mn + min(mn, T)) T = 25表示�?多包含黄金的单元格数�?
    private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int[][] grid;
    int m, n;
    int ans;
    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    dfs(i, j, 0);
                }
            }
        }
        return ans;
    }

    private void dfs(int cx, int cy, int gold) {
        gold += grid[cx][cy];
        int temp = grid[cx][cy];
        if (gold > ans) {
            ans = gold;
        }
        grid[cx][cy] = 0;
        for (int[] dir : dirs) {
            int nx = cx + dir[0];
            int ny = cy + dir[1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 0) {
                continue;
            }
            dfs(nx, ny, gold);
        }
        grid[cx][cy] = temp;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
