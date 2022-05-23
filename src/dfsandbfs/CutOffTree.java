package src.dfsandbfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 m x n 的矩阵表示， 在这个矩阵中：
 * 0 表示障碍，无法触碰
 * 1 表示地面，可以行走
 * 比 1 大的数 表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 * 链接：https://leetcode.cn/problems/cut-off-trees-for-golf-event
 * @author Dreihunde
 *
 */
public class CutOffTree {
	int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m,n;
    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<coaPoint> queue = new PriorityQueue<>((a, b) -> (a.val - b.val));
        m = forest.size();
        n = forest.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = forest.get(i).get(j);
                if (val > 1)    queue.offer(new coaPoint(i, j, val));
            }
        }


        int cx = 0;
        int cy = 0;
        int count = 0;

        while (!queue.isEmpty()) {
        	coaPoint p = queue.poll();
            // int steps = bfs(forest, cx, cy, p.x, p.y);
            // int steps = dijkstra(forest, cx, cy, p.x, p.y);
            int steps = A_bfs(forest, cx, cy, p.x, p.y);
            if (steps == -1) {
                return -1;
            }
            count += steps;
            cx = p.x;
            cy = p.y;
        }

        return count;

    }

    //method 1 优先队列排序 + bfs搜索路径 O(m^2n^2) O(mn)
    public int bfs(List<List<Integer>> forest, int cx, int cy, int tx, int ty) {
        int step = 0;
        if (cx == tx && cy == ty) {
            return step;
        }

        boolean[][] vis = new boolean[m][n];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{cx, cy});
        vis[cx][cy] = true;
        
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                int px = p[0];
                int py = p[1];
                for (int[] dir : dirs) {
                    int nx = px + dir[0];
                    int ny = py + dir[1];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || forest.get(nx).get(ny) == 0 || vis[nx][ny]) {
                        continue;
                    }
                    if (nx == tx && ny == ty) {
                        return step;
                    }
                    queue.offer(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }   
            }
        }
        return -1;
    }

    //method 2 优先队列排序 + dijkstra搜索路径 O(m^2n^2logmn) O(mn)
    public int dijkstra(List<List<Integer>> forest, int cx, int cy, int tx, int ty) {
        if (cx == tx && cy == ty) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        boolean[][] vis = new boolean[m][n];
        pq.offer(new int[]{0, cx * n + cy});
        vis[cx][cy] = true;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int dist = arr[0];
            int loc = arr[1];
            for (int[] dir: dirs) {
                int nx = loc / n + dir[0];
                int ny = loc % n + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n ||forest.get(nx).get(ny) == 0 || vis[nx][ny]) {
                    continue;
                }
                if (nx == tx && ny == ty) {
                    return dist + 1;
                }
                vis[nx][ny] = true;
                pq.offer(new int[]{dist + 1, nx * n + ny});
            }
        }
        return -1;
    }

    //method 3 优先队列排序 + 启发式算法
    public int A_bfs(List<List<Integer>> forest, int cx, int cy, int tx, int ty) {
        if (cx == tx && cy == ty) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int[][] costed = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(costed[i], Integer.MAX_VALUE);
        }
        pq.offer(new int[]{0, 0, cx * n + cy});
        costed[cx][cy] = Math.abs(tx - cx) + Math.abs(ty - cy);
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int cost = arr[0];
            int dist = arr[1];
            int loc = arr[2];
            int px = loc / n;
            int py = loc % n;
            if (px == tx && py == ty) {
                return dist;
            }
            for (int[] dir: dirs) {
                int nx = px + dir[0];
                int ny = py + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n ||forest.get(nx).get(ny) == 0) {
                    continue;
                }
                int ncost = dist + 1 + Math.abs(tx - cx) + Math.abs(ty - cy);
                if (ncost < costed[nx][ny]) {
                    pq.offer(new int[]{ncost, dist + 1, nx * n + ny});
                    costed[nx][ny] = ncost;
                }
                
            }
        }
        return -1;
    }

}

class coaPoint {
    int x;
    int y;
    int val;

    public coaPoint() {

    }

    public coaPoint(int _x, int _y, int _val) {
        this.x = _x;
        this.y = _y;
        this.val = _val;
    }
}
