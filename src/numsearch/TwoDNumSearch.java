package numsearch;

public class TwoDNumSearch {
	//ÏßÐÔËÑË÷
	/**
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) 
            return false;
        int cols = matrix[0].length;
        int rows = matrix.length;

        int i = 0;
        int j = cols - 1;
        int num = matrix[i][j];

        while (i < rows && j >= 0) {
            num = matrix[i][j];
            if (num > target) {
                j--;
            } else if (num < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;

    }
	
	//±éÀú
	public static boolean findNumberIn2DArray2(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) 
            return false;
		int cols = matrix[0].length;
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
        	for (int j = 0; j < cols; j++) {
        		if (matrix[i][j] == target)
        			return true;
        	}
        }
        return false;
	}
	
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,4,7,11,15}, 
									  {2,5,8,12,19},
									  {3,6,9,16,22},
									  {10,13,14,17,24},
									  {18,21,23,26,30}};
		int target = 30;
		long startTime = System.nanoTime();
		System.out.println(findNumberIn2DArray1(matrix, target));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(findNumberIn2DArray2(matrix, target));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
