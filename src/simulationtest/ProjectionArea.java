package src.simulationtest;

/**
 * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的�?1 x 1 x 1 立方体�??
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上�??
 * 现在，我们查看这些立方体�? xy 、yz �? zx 平面上的投影�?
 * 投影 就像影子，将 三维 形体映射到一�? 二维 平面上�?�从顶部、前面和侧面看立方体时，我们会看到�?�影子�?��??
 * 返回 �?有三个投影的总面�? �?
 * 链接：https://leetcode-cn.com/problems/projection-area-of-3d-shapes
 * @author Dreihunde
 *
 */
public class ProjectionArea {
	//method 1 模拟 O(mn + m + n) O(m + n)
    public int projectionArea1(int[][] grid) {
        int totalCount = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] yCount = new int[m];
        int[] zCount = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    totalCount++;
                }
                yCount[i] = Math.max(yCount[i], grid[i][j]);
                zCount[j] = Math.max(zCount[j], grid[i][j]);
            }
        }
        for (int y : yCount) {
            totalCount += y;
        }
        for (int z : zCount) {
            totalCount += z;
        }
        return totalCount;
    }

    //method 2 模拟 + 优化(当m == n) O(n^2) O(1)
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, yzArea = 0, zxArea = 0;
        for (int i = 0; i < n; i++) {
            int yzHeight = 0, zxHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] > 0 ? 1 : 0;
                yzHeight = Math.max(yzHeight, grid[j][i]);
                zxHeight = Math.max(zxHeight, grid[i][j]);
            }
            yzArea += yzHeight;
            zxArea += zxHeight;
        }
        return xyArea + yzArea + zxArea;
    }


}
