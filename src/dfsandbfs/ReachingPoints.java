package dfsandbfs;

/**
 * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 * 链接：https://leetcode-cn.com/problems/reaching-points
 * @author Dreihunde
 *
 */
public class ReachingPoints {
	//method 1 dfs(栈溢出) O(2^max(tx, ty)) O(max(tx, ty))
    public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        return dfs1(sx, sy, tx, ty);
    }

    private boolean dfs1(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        } else if (sx > tx || sy > ty) {
            return false;
        } else {
            return dfs1(sx, sx + sy, tx, ty) || dfs1(sx + sy, sy, tx, ty);
        }
    }

    //method 2 逆向处理 O(log(max(tx, ty))) O(1)
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }


}
