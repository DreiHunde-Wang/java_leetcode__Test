package src.dfsandbfs;

/**
 * ����һ����СΪ m x n �ľ��� board ��ʾ�װ壬���У�ÿ����Ԫ�������һ��ս�� 'X' ������һ����λ '.' ��
 * �����ڼװ� board �Ϸ��õ� ս�� ��������
 * ս�� ֻ��ˮƽ���ߴ�ֱ������ board �ϡ����仰˵��ս��ֻ�ܰ� 1 x k��1 �У�k �У��� k x 1��k �У�1 �У�����״���죬
 * ���� k �����������С������ս��֮��������һ��ˮƽ��ֱ�Ŀ�λ�ָ� ����û�����ڵ�ս������
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

    //method 2 dfs + �Ż� O(mn) O(1)
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
