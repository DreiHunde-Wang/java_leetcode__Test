package dfsandbfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。
 * 数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 * @author Dreihunde
 *
 */

//method 1 bfs + 有限步数 O(n^2) O(n) n为blocked的长度
public class IsEscapePossible {

	//封锁，没被封锁，找寻到目标
    private static final int BLOCKED = -1;
    private static final int VAILD = 0;
    private static final int FOUND = 1;
    
    //四个方向
    private static final int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    //最大边界
    private static final int boundary = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length <= 1) {
            return true;
        }
        
        //将blocked转入Set，方便使用contains方法
        Set<Point> blockedSet = new HashSet<>();

        for (int[] b : blocked) {
            blockedSet.add(new Point(b[0], b[1]));
        }
        	
        int result = getResult(blocked, source, target, blockedSet);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
        	//如果从source处bfs没被封锁，那就从target处bfs
            result = getResult(blocked, target, source, blockedSet);
            return result != BLOCKED;
        }
    }


    private int getResult(int[][] blocked, int[] source, int[] target, Set<Point> blockedSet) {
        int sx = source[0];
        int sy = source[1];
        Point startP = new Point(sx, sy);

        int tx = target[0];
        int ty = target[1];
        Point targetP = new Point(tx, ty);
        
        //n个blocked最多封锁 n * (n - 1) / 2;
        int count = blocked.length * (blocked.length - 1) / 2;
        ArrayDeque<Point> queue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();
        queue.offer(startP);
        visited.add(startP);

        while (!queue.isEmpty() && count > 0) {
            Point temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                Point newTemp = temp.getNewPoint(dir[i][0], dir[i][1]);

                if (newTemp.x < 0 || newTemp.y < 0 || newTemp.x > boundary - 1 || newTemp.y > boundary - 1 || blockedSet.contains(newTemp) || visited.contains(newTemp)) {
                    continue;
                }
                if (newTemp.equals(targetP)) {
                    return FOUND;
                }
                count--;
                visited.add(newTemp);
                queue.offer(newTemp);
            }
        }
        if (count > 0) {
            return BLOCKED;
        }
        return VAILD;
    }
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	int[][] blocked = new int[][] {{1, 0}, {0, 1}};
    	int[] source = new int[] {0, 0};
    	int[] target = new int[] {1, 1}; 
    	IsEscapePossible ie = new IsEscapePossible();
    	System.out.println(ie.isEscapePossible(blocked, source, target));

	}
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //使用set的contains方法时，要在对象里重新构建hashCode和equals方法
    public int hashCode() {
        return (int) ((long) x << 20 | y);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pair2 = (Point) obj;
            return x == pair2.x && y == pair2.y;
        }
        return false;
    }

    public Point getNewPoint(int xadd, int yadd) {
        return new Point(x + xadd, y + yadd);
    }
}

//method 2 离散化 + 广度优先搜索 O(n^2) O(n)
/**
 * 思路与算法
 * 我们也可以借助离散化技巧将网格「压缩」成一个规模较小的但等价的新网格，并在新网格上进行常规的广度优先搜索。
 * 以网格的每一行为例，可以发现，不同的行坐标只有：
 * 障碍所在的行，最多有 nn 个；
 * source 和arget 所在的行，最多有 22 个。
 * 网格的上下边界（即 -1和 10^6），有2个。
 * 因此不同的行坐标最多只有 n+4 个，我们可以对行坐标进行离散化，具体的规则如下：
 * 我们将行坐标进行升序排序；
 * 上边界离散化为 -1。上边界是排序后的第0个行坐标；
 * 如果排序后的第 i 个行坐标与第 i-1个行坐标相同，那么它们离散化之后的值也相同；
 * 如果排序后的第 i 个行坐标与第 i-1个行坐标相差 1，那么它们离散化之后的值也相差 1；
 * 如果排序后的第 i 个行坐标与第 i-1个行坐标相差超过 1，那么它们离散化之后的值相差 2。
 * 这样的正确性在于：在离散化前，如果两个行坐标本身相邻，那么在离散化之后它们也必须相邻。如果它们不相邻，可以把它们之间间隔的若干行直接「压缩」成一行，即行坐标相差 2。
 * 对于列坐标的离散化方法也是如此。在离散化完成之后，新的网格的规模不会超过 2(n+4)×2(n+4)，进行广度优先搜索需要的时间是可接受的。
 * @author Dreihunde
 *
 */
class Solution {
    static final int BOUNDARY = 1000000;
    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }
        // 离散化
        TreeSet<Integer> rows = new TreeSet<Integer>();
        TreeSet<Integer> columns = new TreeSet<Integer>();
        for (int[] pos : blocked) {
            rows.add(pos[0]);
            columns.add(pos[1]);
        }
        rows.add(source[0]);
        rows.add(target[0]);
        columns.add(source[1]);
        columns.add(target[1]);

        Map<Integer, Integer> rMapping = new HashMap<Integer, Integer>();
        Map<Integer, Integer> cMapping = new HashMap<Integer, Integer>();

        int firstRow = rows.first();
        int rId = (firstRow == 0 ? 0 : 1);
        rMapping.put(firstRow, rId);
        int prevRow = firstRow;
        for (int row : rows) {
            if (row == firstRow) {
                continue;
            }
            rId += (row == prevRow + 1 ? 1 : 2);
            rMapping.put(row, rId);
            prevRow = row;
        }
        if (prevRow != BOUNDARY - 1) {
            ++rId;
        }

        int firstColumn = columns.first();
        int cId = (firstColumn == 0 ? 0 : 1);
        cMapping.put(firstColumn, cId);
        int prevColumn = firstColumn;
        for (int column : columns) {
            if (column == firstColumn) {
                continue;
            }
            cId += (column == prevColumn + 1 ? 1 : 2);
            cMapping.put(column, cId);
            prevColumn = column;
        }
        if (prevColumn != BOUNDARY - 1) {
            ++cId;
        }

        int[][] grid = new int[rId + 1][cId + 1];
        for (int[] pos : blocked) {
            int x = pos[0], y = pos[1];
            grid[rMapping.get(x)][cMapping.get(y)] = 1;
        }
        
        int sx = rMapping.get(source[0]), sy = cMapping.get(source[1]);
        int tx = rMapping.get(target[0]), ty = cMapping.get(target[1]);

        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{sx, sy});
        grid[sx][sy] = 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                if (nx >= 0 && nx <= rId && ny >= 0 && ny <= cId && grid[nx][ny] != 1) {
                    if (nx == tx && ny == ty) {
                        return true;
                    }
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = 1;
                }
            }
        }
        return false;
    }
}
