package src.stringnumtest;

/**
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * 以下是井字游戏的规则：
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * @author Dreihunde
 *
 */
public class IsVaild3X3Game {
	 //method 1 O(C) O(1)
    public boolean validTicTacToe(String[] board) {
        int xCnt = 0;
        int oCnt = 0;

        for (String s : board) {
            for (int i = 0; i < 3; i++) {
                xCnt = s.charAt(i) == 'X' ? xCnt + 1 : xCnt;
                oCnt = s.charAt(i) == 'O' ? oCnt + 1 : oCnt;
            }
        }

        if (oCnt != xCnt && oCnt != xCnt - 1) {
            return false;
        }
        if (isWin(board, 'X') && oCnt != xCnt - 1) {
            return false;
        }
        if (isWin(board, 'O') && oCnt != xCnt) {
            return false;
        }

        return true;

    }

    private boolean isWin(String[] board, char p) {
        for (int i = 0; i < 3; i++) {
            //每一列
            if (board[0].charAt(i) == p && board[1].charAt(i) == p  && board[2].charAt(i) == p ) {
                return true;
            }
            //每一行
            if (board[i].charAt(0) == p && board[i].charAt(1) == p  && board[i].charAt(2) == p ) {
                return true;
            }
        }
        //左对角线
        if (board[0].charAt(0) == p && board[1].charAt(1) == p  && board[2].charAt(2) == p)
            return true;
        //右对角线
        if (board[0].charAt(2) == p && board[1].charAt(1) == p  && board[2].charAt(0) == p)
            return true;

        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] board = new String[] {"XOX","O O","XOX"};
		
		IsVaild3X3Game iv = new IsVaild3X3Game();
		System.out.println(iv.validTicTacToe(board));

	}

}
