package numsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ����һ�� m * n �ľ��󣬾����е����� ������ͬ �����㰴 ���� ˳�򷵻ؾ����е�������������
 * ��������ָ����������ͬʱ��������������Ԫ�أ�
 * ��ͬһ�е�����Ԫ������С
 * ��ͬһ�е�����Ԫ�������
 * ���ӣ�https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 * @author Dreihunde
 *
 */
public class FindLuckNum {
	//method 1 ����+��ѯ����ֵ O(mn) O(m + n)
    public List<Integer> luckyNumbers1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowMin = new int[m];
        int[] colMax = new int[n];
        Arrays.fill(rowMin, 100001);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int row : rowMin) {
            set.add(row);
        }
        for (int col : colMax) {
            if (set.contains(col)) {
                ans.add(col);
            }
        }
        return ans;

    }

    //method 2 ����+������ѯ O(mn) O(m + n)
    public List<Integer> luckyNumbers2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] rowMin = new int[m];
        int[] colMax = new int[n];
        Arrays.fill(rowMin, 100001);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int row : rowMin) {
            set.add(row);
        }
        for (int col : colMax) {
            if (set.contains(col)) {
                ans.add(col);
            }
        }
        return ans;

    }

    //method 3 ���� + math ���ֻ��һ��������(ÿһ����Сֵ������ֵ=ÿһ�����ֵ�����Сֵ) O(mn) O(m + n)
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] colMax = new int[n];
        int[] rowMin = new int[m];
        Arrays.fill(rowMin, 100001);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
                
            }
        }
        int minMax = colMax[0];
        for (int col : colMax) {
            minMax = Math.min(minMax, col);
        }
        int maxMin = rowMin[0];
        for (int row : rowMin) {
            maxMin = Math.max(maxMin, row);
        }
        if (minMax == maxMin) {
            ans.add(minMax);
        }
        return ans;

    }

    //method 4 ģ�� O((m + n) * mn) O(1)
    public List<Integer> luckyNumbers4(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isMin = true, isMax = true;
                for (int k = 0; k < n; k++) {
                    if (matrix[i][k] < matrix[i][j]) {
                        isMin = false;
                        break;
                    }
                }
                if (!isMin) {
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    if (matrix[k][j] > matrix[i][j]) {
                        isMax = false;
                        break;
                    }
                }
                if (isMax) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] martix = new int[][] {{7, 8}, {1, 2}};
		FindLuckNum fl = new FindLuckNum();
		System.out.println(fl.luckyNumbers(martix));
	}

}
