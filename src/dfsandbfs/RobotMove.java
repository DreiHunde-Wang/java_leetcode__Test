package src.dfsandbfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ������һ��m��n�еķ��񣬴����� [0,0] ������ [m-1,n-1] ��һ�������˴����� [0, 0] �ĸ��ӿ�ʼ�ƶ���
 * ��ÿ�ο��������ҡ��ϡ����ƶ�һ�񣨲����ƶ��������⣩��Ҳ���ܽ�������������������λ֮�ʹ���k�ĸ��ӡ�
 * ���磬��kΪ18ʱ���������ܹ����뷽�� [35, 37] ��
 * ��Ϊ3+5+3+7=18���������ܽ��뷽�� [35, 38]����Ϊ3+5+3+8=19�����ʸû������ܹ�������ٸ����ӣ�
 * @author Dreihunde
 *
 */
public class RobotMove {
	boolean[][] visited;
    //method 1 ����dfs
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
        //ֻǰ����������
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
