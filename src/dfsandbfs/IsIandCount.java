package src.dfsandbfs;
import java.util.Queue;
import java.util.LinkedList;

public class IsIandCount {
	//method 1 DFS
    public static int numIslands1(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void DFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i > grid.length - 1|| j > grid[0].length - 1|| grid[i][j] != '1')
            return;
        grid[i][j] = '2';
        DFS(grid, i + 1, j);
        DFS(grid, i - 1, j);
        DFS(grid, i, j + 1);
        DFS(grid, i, j - 1);
    }
    
    //method 2 BFS
    public static int numIslands2(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    BFS(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private static void BFS(char[][] grid, int i, int j) {
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[]{i, j});
        while (!list.isEmpty()) {
            int[] cur = list.remove();
            i = cur[0];
            j = cur[1];
            if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '2';
                list.add(new int[]{i + 1, j});
                list.add(new int[]{i - 1, j});
                list.add(new int[]{i, j - 1});
                list.add(new int[]{i, j + 1});
            }
        }
    }
    
    //method 3
    public static int numIslands3(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i - 1][j] == '1')
                    	uf.union(i * n + j, (i - 1) * n + j);
                    if (i + 1 < m && grid[i + 1][j] == '1')
                    	uf.union(i * n + j, (i + 1) * n + j);
                    if (j - 1 >= 0 && grid[i][j - 1] == '1')
                    	uf.union(i * n + j, i * n + j - 1);
                    if (j + 1 < n && grid[i][j + 1] == '1')
                    	uf.union(i * n + j, i * n + j + 1);
                    
                }
            }
        }

        return uf.getCount();
    }
    
    public static void main(String[] args) {
    	char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		long startTime = System.nanoTime();
		System.out.println(numIslands1(grid));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		startTime = System.nanoTime();
		System.out.println(numIslands2(grid));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
		startTime = System.nanoTime();
		System.out.println(numIslands3(grid));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}

class UnionFind {
	int count;
	int[] parent;
	int[] rank;
	
	public UnionFind(char[][] grid) {
		// TODO Auto-generated constructor stub
		count = 0;
		int m = grid.length;
		int n = grid[0].length;
		
		parent = new int[m * n];
		rank = new int[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					parent[i * n + j] = i * n + j;
					count++;
				}
				rank[i * n + j] = 0;
			}
		}
		
	}
	
	public int find(int i) {
		if (parent[i] != i)
			parent[i] = find(parent[i]);
		return parent[i];
	}
	
	public void union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		
		if (rootx != rooty) {
			if (rank[rootx] > rank[rooty]) {
				parent[rooty] = rootx;
			} else if (rank[rootx] < rank[rooty]) {
				parent[rootx] = rooty;
			} else {
				parent[rooty] = rootx;
				rank[rootx] += 1;
			}
			count--;
		}
	}
	
	public int getCount() {
		return this.count;
	}
	
}
