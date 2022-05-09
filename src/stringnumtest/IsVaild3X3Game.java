package src.stringnumtest;

/**
 * ����һ���ַ������� board ��ʾ������Ϸ�����̡����ҽ����ھ�����Ϸ�����У������п��ܴﵽ board ����ʾ��״̬ʱ���ŷ��� true ��
 * ������Ϸ��������һ�� 3 x 3 ���飬���ַ� ' '��'X' �� 'O' ��ɡ��ַ� ' ' ����һ����λ��
 * �����Ǿ�����Ϸ�Ĺ���
 * ����������ַ������λ��' '���С�
 * ��� 1 ���Ƿ��ַ� 'X' ������� 2 ���Ƿ��ַ� 'O' ��
 * 'X' �� 'O' ֻ��������ڿ�λ�У���������ѷ����ַ���λ�ý�����䡣
 * ���� 3 ����ͬ���ҷǿգ����ַ�����κ��С��л�Խ���ʱ����Ϸ������
 * ������λ�÷ǿ�ʱ��Ҳ��Ϊ��Ϸ������
 * �����Ϸ��������Ҳ������ٷ����ַ���
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
            //ÿһ��
            if (board[0].charAt(i) == p && board[1].charAt(i) == p  && board[2].charAt(i) == p ) {
                return true;
            }
            //ÿһ��
            if (board[i].charAt(0) == p && board[i].charAt(1) == p  && board[i].charAt(2) == p ) {
                return true;
            }
        }
        //��Խ���
        if (board[0].charAt(0) == p && board[1].charAt(1) == p  && board[2].charAt(2) == p)
            return true;
        //�ҶԽ���
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
