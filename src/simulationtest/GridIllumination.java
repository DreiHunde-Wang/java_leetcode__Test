package simulationtest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 在大小为 n x n 的网格 grid 上，每个单元格都有一盏灯，最初灯都处于 关闭 状态。
 * 给你一个由灯的位置组成的二维数组 lamps ，其中 lamps[i] = [rowi, coli] 表示 打开 位于 grid[rowi][coli] 的灯。
 * 即便同一盏灯可能在 lamps 中多次列出，不会影响这盏灯处于 打开 状态。
 * 当一盏灯处于打开状态，它将会照亮 自身所在单元格 以及同一 行 、同一 列 和两条 对角线 上的 所有其他单元格 。
 * 另给你一个二维数组 queries ，其中 queries[j] = [rowj, colj] 。
 * 对于第 j 个查询，如果单元格 [rowj, colj] 是被照亮的，则查询结果为 1 ，否则为 0 。
 * 在第 j 次查询之后 [按照查询的顺序] ，关闭 位于单元格 grid[rowj][colj] 上及相邻 8 个方向上（与单元格 grid[rowi][coli] 共享角或边）的任何灯。
 * 返回一个整数数组 ans 作为答案， ans[j] 应等于第 j 次查询 queries[j] 的结果，1 表示照亮，0 表示未照亮。
链接：https://leetcode-cn.com/problems/grid-illumination
 * @author Dreihunde
 *
 */
public class GridIllumination {
	//method 1 hashmap记录 O(l + q) O(l) 其中 l 和 q 分别是 lamps 和 queries 的长度。
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        if (queries.length == 0) {
            return new int[0];
        }
        if (lamps.length == 0) {
            return new int[queries.length];
        }
        Map<Integer, Integer> rows = new HashMap<>();
        Map<Integer, Integer> cols = new HashMap<>();
        Map<Integer, Integer> diagonals = new HashMap<>();
        Map<Integer, Integer> antiDiagnals = new HashMap<>();
        Set<Long> points = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0];
            int y = lamp[1];
            //如果该点已经存在，就跳过
            if (!points.add(hash(x, y))) {
                continue;
            }
            rows.put(x, rows.getOrDefault(x, 0) + 1);
            cols.put(y, cols.getOrDefault(y, 0) + 1);
            diagonals.put(x - y, diagonals.getOrDefault(x - y, 0) + 1);
            antiDiagnals.put(x + y, antiDiagnals.getOrDefault(x + y, 0) + 1);
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            if (rows.getOrDefault(x, 0) > 0) {
                ans[i] = 1;
            } else if (cols.getOrDefault(y, 0) > 0) {
                ans[i] = 1;
            } else if (diagonals.getOrDefault(x - y, 0) > 0) {
                ans[i] = 1;
            } else if (antiDiagnals.getOrDefault(x + y, 0) > 0) {
                ans[i] = 1;
            }

            for (int j = x - 1; j <= x + 1; j++) {
                for (int k = y - 1; k <= y + 1; k++) {
                    if (j < 0 || k < 0 || j >= n || k >= n) {
                        continue;
                    }
                    if (points.remove(hash(j, k))){
                        rows.put(j, rows.get(j) - 1);
                        if (rows.get(j) == 0) {
                            rows.remove(j);
                        }
                        cols.put(k, cols.get(k) - 1);
                        if (cols.get(k) == 0) {
                            cols.remove(k);
                        }
                        diagonals.put(j - k, diagonals.get(j - k) - 1);
                        if (diagonals.get(j - k) == 0) {
                            diagonals.remove(j - k);
                        }
                        antiDiagnals.put(j + k, antiDiagnals.get(j + k) - 1);
                        if (antiDiagnals.get(j + k) == 0) {
                            antiDiagnals.remove(j + k);
                        }
                    }
                }
            }
        }
        return ans;
    }
    //x, y的最大值为10^9
    public long hash(int x, int y) {
        return (long) x + ((long) y << 32);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
