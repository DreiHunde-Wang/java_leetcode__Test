package src.dfsandbfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给你�?个大小为 m x n 的整数矩阵 isWater ，它代表了一个由 陆地 �? 水域 单元格组成的地图�?
 * 如果 isWater[i][j] == 0 ，格子�?(i, j) 是一�? 陆地 格子�?
 * 如果 isWater[i][j] == 1 ，格子�?(i, j) 是一�? 水域 格子�?
 * 你需要按照如下规则给每个单元格安排高度：
 * 每个格子的高度都必须是非负的�?
 * 如果�?个格子是�? 水域 ，那么它的高度必须为 0 �?
 * 任意相邻的格子高度差 至多 �? 1 。当两个格子在正东�?�南、西、北方向上相互紧挨着，就称它们为相邻的格子�?�（也就是说它们有一条公共边�?
 * 找到�?种安排高度的方案，使得矩阵中的最高高度�?� 最大 �??
 * 请你返回�?个大小为 m x n 的整数矩�? height ，其�? height[i][j] 是格�? (i, j) 的高度�?�如果有多种解法，请返回 任意�?个 �??
 * @author Dreihunde
 *
 */
public class HighestPeak {
	//method 1 多源BFS O(mn) O(mn)
    int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = -1;
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = temp[0] + dir[i][0];
                int ny = temp[1] + dir[i][1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || ans[nx][ny] != -1) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                ans[nx][ny] = ans[temp[0]][temp[1]] + 1;
            }
        }

        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
