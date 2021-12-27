package numsorttest;

/**
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、
 * 右下角 (row2, col2) 的子矩阵的元素总和。
 * @author Dreihunde
 *
 */
public class Return2DSubArrSum {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

class NumMatrix {
	//method 1 前缀和 O(mn) O(mn)
    int[][] matrixSum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        matrixSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixSum[i + 1][j + 1] = matrixSum[i][j + 1] + matrixSum[i + 1][j] + matrix[i][j] - matrixSum[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrixSum[row2 + 1][col2 + 1] + matrixSum[row1][col1] - matrixSum[row1][col2 + 1] - matrixSum[row2 + 1][col1];
    }
}
