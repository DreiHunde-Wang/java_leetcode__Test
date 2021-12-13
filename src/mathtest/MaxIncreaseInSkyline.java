package mathtest;

/**
 * 给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，
 * 其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
 * 城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 天际线 可能不同。
 * 我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。 高度为 0 的建筑物的高度也可以增加。
 * 然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。
 * 在不改变从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的 最大高度增量总和 。
 * @author Dreihunde
 *
 */
public class MaxIncreaseInSkyline {
	//method 1 遍历 O(n2) O(n)
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        //每一行的最大值
        int[] maxI = new int[n];
        //每一列的最大值
        int[] maxJ = new int[n];
        int sum = 0;

        //遍历得到每一行和每一列的最大值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxI[i] = Math.max(maxI[i], grid[i][j]);
                maxJ[i] = Math.max(maxJ[i], grid[j][i]);
            }
        }

        //增量相当于满足约束的值(行和列对应最大值的最小值)减去当前值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.min(maxI[i], maxJ[j]) - grid[i][j];
            }
        }

        return sum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] {{3,0,8,4}, {2,4,5,7}, {9,2,6,3}, {0,3,1,0}};
		MaxIncreaseInSkyline mi = new MaxIncreaseInSkyline();
		System.out.println(mi.maxIncreaseKeepingSkyline(grid));
	}

}
