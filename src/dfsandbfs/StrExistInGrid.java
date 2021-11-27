package dfsandbfs;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * @author Dreihunde
 *
 */
public class StrExistInGrid {
	//回溯+ 剪枝
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, words, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, char[] words, int index) {
        if (i < 0 || j < 0 || i > board.length - 1|| j > board[0].length - 1 || board[i][j] != words[index]) {
            return false;
        }
        if (index == words.length - 1) 
            return true;
        board[i][j] = '.';
        boolean res = dfs(board, i - 1, j, words, index + 1) || dfs(board, i + 1, j, words, index + 1) || dfs(board, i, j - 1, words, index + 1) || dfs(board, i, j + 1, words, index + 1);
        board[i][j] = words[index];
        return res;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = new char[][] {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
		String word = "ABCCED";
		
		StrExistInGrid se = new StrExistInGrid();
		
		System.out.println(se.exist(board, word));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
