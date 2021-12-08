package numsorttest;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * @author Dreihunde
 *
 */
public class PrintGridInOrder {
	//method 1 暴力拆解 O(mn) O(1)
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return new int[0];
        int n = matrix[0].length;
        int[] rev = new int[m * n];
        int index = 0;
        //循环几轮
        for (int i = 0; i < (Math.min(m, n) + 1) / 2; i++) {
            //上面从左到右
            for (int j = i; j < n - i; j++) {
                rev[index] = matrix[i][j];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //右边从上到下
            for (int j = i + 1; j < m - i - 1; j++) {
                rev[index] = matrix[j][n - 1 - i];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //下面从右到左
            for (int j = n - 1 - i; j >= i; j--) {
                rev[index] = matrix[m - 1 - i][j];
                index++;
                if (index >= m * n)
                    return rev;
            }
            //左边从下到上
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
