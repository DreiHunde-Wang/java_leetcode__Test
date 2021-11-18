package dfsandbfs;

public class ComputeLandArea {
	public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, countArea(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private static int countArea(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1|| j > grid[0].length - 1|| grid[i][j] != 1)
            return 0;
        grid[i][j] = 2;
        return 1 + countArea(grid, i + 1, j) + countArea(grid, i - 1, j) + countArea(grid, i, j + 1) + countArea(grid, i, j - 1);
    }
    
    public static void main(String[] args) {
    	//[[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
		int[][] grid = new int[][] {{0,0,1,0,0,0,0,1,0,0,0,0,0},
									{0,0,0,0,0,0,0,1,1,1,0,0,0},
									{0,1,1,0,1,0,0,0,0,0,0,0,0},
									{0,1,0,0,1,1,0,0,1,0,1,0,0},
									{0,1,0,0,1,1,0,0,1,1,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,1,1,1,0,0,0},
									{0,0,0,0,0,0,0,1,1,0,0,0,0}};
		long startTime = System.nanoTime();
		System.out.println(maxAreaOfIsland(grid));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
