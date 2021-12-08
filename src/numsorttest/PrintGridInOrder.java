package numsorttest;

/**
 * ����һ�����󣬰��մ���������˳ʱ���˳�����δ�ӡ��ÿһ�����֡�
 * @author Dreihunde
 *
 */
public class PrintGridInOrder {
	//method 1 ������� O(mn) O(1)
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return new int[0];
        int n = matrix[0].length;
        int[] rev = new int[m * n];
        int index = 0;
        //ѭ������
        for (int i = 0; i < (Math.min(m, n) + 1) / 2; i++) {
            //���������
            for (int j = i; j < n - i; j++) {
                rev[index] = matrix[i][j];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //�ұߴ��ϵ���
            for (int j = i + 1; j < m - i - 1; j++) {
                rev[index] = matrix[j][n - 1 - i];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //������ҵ���
            for (int j = n - 1 - i; j >= i; j--) {
                rev[index] = matrix[m - 1 - i][j];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //��ߴ��µ���
            for (int j = m - 1 - i - 1; j > i; j--) {
                rev[index] = matrix[j][i];
                index++;
                if (index >= m * n)
                    return rev;
            }
            
        }
        return rev;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		PrintGridInOrder pg = new PrintGridInOrder();
		CommonTest.printNum(pg.spiralOrder(matrix));

	}

}
