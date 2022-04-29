package printtreenode;

/**
 * ����һ�� n * n ���� grid ������������ 0 �� 1 ��ɡ��������Ĳ�����ʾ�þ��� grid ��
 * ����Ҫ�����ܱ�ʾ����� �Ĳ��� �ĸ���㡣
 * ע�⣬�� isLeaf Ϊ False ʱ������԰� True ���� False ��ֵ���ڵ㣬����ֵ���ᱻ������� ���� ��
 * �Ĳ������ݽṹ�У�ÿ���ڲ��ڵ�ֻ���ĸ��ӽڵ㡣���⣬ÿ���ڵ㶼���������ԣ�
 * val������Ҷ�ӽ��������������ֵ��1 ��Ӧ True��0 ��Ӧ False��
 * isLeaf: ������ڵ���һ��Ҷ�ӽ��ʱΪ True��������� 4 ���ӽڵ���Ϊ False
 * ���ӣ�https://leetcode-cn.com/problems/construct-quad-tree
 * @author Dreihunde
 *
 */
public class ConstructFourBranchTree {
	//method 1 �ݹ� O(n^2logn) O(logn)
    public FourNode construct1(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid.length);
    }

    public FourNode dfs(int[][] grid, int r0, int c0, int r1, int c1) {
        boolean same = true;
        for (int i = r0; i < r1; ++i) {
            for (int j = c0; j < c1; ++j) {
                if (grid[i][j] != grid[r0][c0]) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
        }

        if (same) {
            return new FourNode(grid[r0][c0] == 1, true);
        }

        FourNode ret = new FourNode(
            true,
            false,
            dfs(grid, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
            dfs(grid, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
            dfs(grid, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
            dfs(grid, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }
    //method 2 ǰ׺��+�ݹ� O(n^2) O(n^2)
    public FourNode construct(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs2(grid, pre, 0, 0, n, n);
    }

    public FourNode dfs2(int[][] grid, int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        if (total == 0) {
            return new FourNode(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new FourNode(true, true);
        }

        FourNode ret = new FourNode(
            true,
            false,
            dfs2(grid, pre, r0, c0, (r0 + r1) / 2, (c0 + c1) / 2),
            dfs2(grid, pre, r0, (c0 + c1) / 2, (r0 + r1) / 2, c1),
            dfs2(grid, pre, (r0 + r1) / 2, c0, r1, (c0 + c1) / 2),
            dfs2(grid, pre, (r0 + r1) / 2, (c0 + c1) / 2, r1, c1)
        );
        return ret;
    }

    public int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r1][c0] - pre[r0][c1] + pre[r0][c0];
    }

}

class FourNode {
    public boolean val;
    public boolean isLeaf;
    public FourNode topLeft;
    public FourNode topRight;
    public FourNode bottomLeft;
    public FourNode bottomRight;

    
    public FourNode() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public FourNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public FourNode(boolean val, boolean isLeaf, FourNode topLeft, FourNode topRight, FourNode bottomLeft, FourNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};