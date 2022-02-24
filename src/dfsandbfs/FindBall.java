package dfsandbfs;

public class FindBall {
	//method 1 ��˳��dfs O(mn) O(1)
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        //ÿ�����㶼��һ����
        for (int j = 0; j < n; j++) {
            ans[j] = dfs(grid, 0, j);
        }
        return ans;
    }


    private int dfs(int[][] grid, int i, int j) {
        //����ײ���������
        if (i == grid.length) {
            return (j >= 0 && j < grid[0].length) ? j : -1;
        }
        //�ų��߳��߽�����
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return -1;
        }

        //�ų�����ס�����
        if (grid[i][j] == 1 && (j + 1 >= grid[0].length || grid[i][j + 1] == -1)) {
            return -1;
        }
        if (grid[i][j] == -1 && (j - 1 < 0 || grid[i][j - 1] == 1)) {
            return -1;
        }

        //ȥ����һ��
        return dfs(grid, i + 1, j + grid[i][j]);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
