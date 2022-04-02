package dfsandbfs;

/**
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。
 * 如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，
 * 如果球卡在盒子里，则返回 -1 。
 * 链接：https://leetcode-cn.com/problems/where-will-the-ball-fall
 * @author Dreihunde
 *
 */
public class FindBall {
	//method 1 按顺序dfs O(mn) O(1)
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        //每个顶点都有一个球
        for (int j = 0; j < n; j++) {
            ans[j] = dfs(grid, 0, j);
        }
        return ans;
    }


    private int dfs(int[][] grid, int i, int j) {
        //到达底部就输出结果
        if (i == grid.length) {
            return (j >= 0 && j < grid[0].length) ? j : -1;
        }
        //排除走出边界的情况
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return -1;
        }

        //排除被堵住的情况
        if (grid[i][j] == 1 && (j + 1 >= grid[0].length || grid[i][j + 1] == -1)) {
            return -1;
        }
        if (grid[i][j] == -1 && (j - 1 < 0 || grid[i][j - 1] == 1)) {
            return -1;
        }

        //去往下一层
        return dfs(grid, i + 1, j + grid[i][j]);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
