package src.dfsandbfs;

/**
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，
 * 返回在甲板 board 上放置的 战舰 的数量。
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，
 * 其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 * @author Dreihunde
 *
 */
public class CountShip {
	//method 1 dfs O(mn) O(n)
    public int countBattleships1(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.' || visited[i][j] == true) {
                    continue;
                }
                count++;
                dfs1(board, i, j, visited);
            }
        }
        return count;
    }

    private void dfs1(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || visited[i][j] == true || board[i][j] == '.') {
            return;
        }
        visited[i][j] = true;
        dfs1(board, i + 1, j, visited);
        dfs1(board, i - 1, j, visited);
        dfs1(board, i, j + 1, visited);
        dfs1(board, i, j - 1, visited);
    }

    //method 2 dfs + 优化 O(mn) O(1)
    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                count++;
                dfs2(board, i, j);
            }
        }
        return count;
    }

    private void dfs2(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] == '.') {
            return;
        }
        board[i][j] = '.';
        dfs2(board, i + 1, j);
        dfs2(board, i - 1, j);
        dfs2(board, i, j + 1);
        dfs2(board, i, j - 1);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
