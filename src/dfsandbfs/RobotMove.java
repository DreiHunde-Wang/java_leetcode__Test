package src.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * @author Dreihunde
 *
 */
public class RobotMove {
	boolean[][] visited;
    //method 1 回溯dfs
    public int movingCount1(int m, int n, int k) {
        int count = 0;
        visited = new boolean[m][n];
        count = dfs(m, n, k, 0, 0);

        return count;
    }

    private int dfs(int m, int n, int k, int i, int j) {
        if (i < 0 || j < 0 || i > m - 1 | j > n - 1 || rankSum(i) + rankSum(j) > k || visited[i][j] == true)
            return 0;
        visited[i][j] = true;
        //只前进，不后退
        return 1 + dfs(m, n, k, i + 1, j) + dfs(m, n, k, i, j + 1);
    }

    private int rankSum(int i) {
        int rev = 0;
        while (i > 0) {
            rev += i % 10;
            i /= 10;
        }
        return rev;
    }

    //method 2 bfs
     public int movingCount2(int m, int n, int k) {
        int count = 0;
        visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            visited[temp[0]][temp[1]] = true;
            count++;
            if (temp[0] + 1 < m && temp[1] < n && !visited[temp[0] + 1][temp[1]] && rankSum(temp[0] + 1) + rankSum(temp[1]) <= k) {
                visited[temp[0] + 1][temp[1]] = true;
                queue.offer(new int[]{temp[0] + 1, temp[1]});
            }
            if (temp[0] < m && temp[1] + 1 < n && !visited[temp[0]][temp[1] + 1] && rankSum(temp[0]) + rankSum(temp[1] + 1) <= k) {
                visited[temp[0]][temp[1] + 1] = true;
                queue.offer(new int[]{temp[0], temp[1] + 1});
            }
        }

        return count;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RobotMove rm = new RobotMove();
		//ans = 15
		int m = 16;
		int n = 8;
		int k = 4;
		
		long startTime=System.nanoTime(); 
		System.out.println(rm.movingCount1(m, n, k));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(rm.movingCount2(m, n, k));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		

	}

}
