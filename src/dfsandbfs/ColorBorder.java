package dfsandbfs;

import numsorttest.CommonTest;

/**
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 两个网格块属于同一 连通分量 需满足下述全部条件：
 * 两个网格块颜色相同
 * 在上、下、左、右任意一个方向上相邻
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
 * 在上、下、左、右四个方向上与不属于同一连通分量的网格块相邻
 * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * @author Dreihunde
 *
 */
public class ColorBorder {
	//method 1 dfs
    boolean[][] visited;
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        visited = new boolean[grid.length][grid[0].length];
        dfs(grid, row, col, color, grid[row][col]);
        return grid;
    }

    private void dfs(int[][] grid, int row, int col, int color, int cur) {
        if (row < 0 || col < 0 || row > grid.length - 1 || col > grid[0].length - 1 || grid[row][col] != cur || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        int[][] diret = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int i = 0; i < 4; i++) {
            int nr = row + diret[i][0];
            int nc = col + diret[i][1];
            if (nr < 0 || nc < 0 || nr > grid.length - 1|| nc > grid[0].length - 1|| (grid[nr][nc] != cur && !visited[nr][nc])){
                grid[row][col] = color;
            }
            dfs(grid, nr, nc, color, cur);
        }    
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] {{1,2,1,2,1,2},
			{2,2,2,2,1,2}, {1,2,2,2,1,2}
		};
		int row = 1;
		int col = 3;
		int color = 1;
		
		ColorBorder cb = new ColorBorder();
		CommonTest.printNum(cb.colorBorder(grid, row, col, color));

	}

}
