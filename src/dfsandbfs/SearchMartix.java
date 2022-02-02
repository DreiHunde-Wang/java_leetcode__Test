package dfsandbfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmlwi1/
 * @author Dreihunde
 *
 */
public class SearchMartix {
	//method 1 提前终止的遍历 O(mn) O(mn)
	public boolean searchMatrix1(int[][] matrix, int target) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        boolean[][] visited = new boolean[m][n];

	        int i = 0;
	        int j = 0;
	        while (true) {
	            visited[i][j] = true;
	            if (matrix[i][j] == target) {
	                break;
	            }
	            if (i + 1 < m && !visited[i + 1][j] && matrix[i + 1][j] <= target) {
	                i++;
	            } else if (j + 1 < n && !visited[i][j + 1] && matrix[i][j + 1] <= target) {
	                j++;
	            } else if (i - 1 >= 0 && matrix[i - 1][j] <= target) {
	                i--;
	            } else {
	                return false;
	            }
	        }
	        return true;
	 }
	 
	//method 2 bfs(超时) O(mn) O(mn)
 	public boolean searchMatrix2(int[][] matrix, int target) {
	        int m = matrix.length;
	        int n = matrix[0].length;
	        boolean[][] visited = new boolean[m][n];

	        Queue<int[]> queue = new ArrayDeque<>();
	        int i = 0, j = 0;
	        queue.offer(new int[] {i ,j});
	        while (!queue.isEmpty()) {
	        	int[] temp = queue.poll();
	        	i = temp[0];
	        	j = temp[1];
	            visited[i][j] = true;
	            if (matrix[i][j] == target) {
	                return true;
	            }
	            if (i + 1 < m && !visited[i + 1][j] && matrix[i + 1][j] <= target) {
	                queue.offer(new int[] {i + 1, j});
	            } 
	            if (j + 1 < n && !visited[i][j + 1] && matrix[i][j + 1] <= target) {
	                queue.offer(new int[] {i, j + 1});
	            } 
	        }
	        return false;
	}
 	
 	//method 3 优化遍历(从右上角开始找) O(mn) O(1)
 	public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
        	if (matrix[i][j] == target) {
        		return true;
        	} else if (matrix[i][j] < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return false;
 	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] martix = new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
		int target = 5;
		SearchMartix sm = new SearchMartix();
		System.out.println(sm.searchMatrix(martix, target));

	}

}
