package src.mathtest;

/**
 * ����һ���� n x n ��������ɵĳ��У�ÿ������������һ�������彨��������һ���±�� 0 ��ʼ�� n x n �������� grid ��
 * ���� grid[r][c] ��ʾ������ r �� c �еĽ������ �߶� ��
 * ���е� ����� �Ǵ�Զ���۲����ʱ�����н������γɵ��ⲿ�������Ӷ����ϡ��������ĸ���Ҫ����۲⵽�� ����� ���ܲ�ͬ��
 * ���Ǳ�����Ϊ ���������Ľ����� �ĸ߶����� ������������ͬ��������������ܲ�ͬ�� �� �߶�Ϊ 0 �Ľ�����ĸ߶�Ҳ�������ӡ�
 * Ȼ�������ӵĽ�����߶� ����Ӱ�� ���κ���Ҫ����۲���еõ��� ����� ��
 * �ڲ��ı���κ���Ҫ����۲⵽�ĳ��� ����� ��ǰ���£����ؽ�����������ӵ� ���߶������ܺ� ��
 * @author Dreihunde
 *
 */
public class MaxIncreaseInSkyline {
	//method 1 ���� O(n2) O(n)
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        //ÿһ�е����ֵ
        int[] maxI = new int[n];
        //ÿһ�е����ֵ
        int[] maxJ = new int[n];
        int sum = 0;

        //�����õ�ÿһ�к�ÿһ�е����ֵ
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxI[i] = Math.max(maxI[i], grid[i][j]);
                maxJ[i] = Math.max(maxJ[i], grid[j][i]);
            }
        }

        //�����൱������Լ����ֵ(�к��ж�Ӧ���ֵ����Сֵ)��ȥ��ǰֵ
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += Math.min(maxI[i], maxJ[j]) - grid[i][j];
            }
        }

        return sum;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[][] {{3,0,8,4}, {2,4,5,7}, {9,2,6,3}, {0,3,1,0}};
		MaxIncreaseInSkyline mi = new MaxIncreaseInSkyline();
		System.out.println(mi.maxIncreaseKeepingSkyline(grid));
	}

}
