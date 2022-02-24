package dfsandbfs;

/**
 * ��һ����СΪ m x n �Ķ�ά���� grid ��ʾһ�����ӡ����� n �������ӵĶ����͵ײ����ǿ��ŵġ�
 * �����е�ÿ����Ԫ����һ���Խ��ߵ��壬�����Ԫ��������ǣ����Խ������������Ҳࡣ
 * �������Ҳ�ĵ��������ϽǺ����½ǣ����������� 1 ��ʾ��
 * ���������ĵ��������ϽǺ����½ǣ����������� -1 ��ʾ��
 * ������ÿһ�еĶ��˸���һ����ÿ���򶼿��ܿ����������ӵײ���������
 * �����ǡ�ÿ������鵲��֮��� "V" ��ͼ�������߱�һ�鵲�������ӵ�����һ����ϣ��ͻῨס��
 * ����һ����СΪ n ������ answer ������ answer[i] ������ڶ����ĵ� i �к�ӵײ�����������һ�ж�Ӧ���±꣬
 * ������ں�����򷵻� -1 ��
 * ���ӣ�https://leetcode-cn.com/problems/where-will-the-ball-fall
 * @author Dreihunde
 *
 */
public class FindBall {
	//method 1 ��˳��dfs O(mn) O(1)
    public int[] findBall(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        //ÿ�����㶼��һ����
        for (int j = 0; j < n; j++) {
            ans[j] = dfs(grid, 0, j);
        }
        return ans;
    }


    private int dfs(int[][] grid, int i, int j) {
        //����ײ���������
        if (i == grid.length) {
            return (j >= 0 && j < grid[0].length) ? j : -1;
        }
        //�ų��߳��߽�����
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return -1;
        }

        //�ų�����ס�����
        if (grid[i][j] == 1 && (j + 1 >= grid[0].length || grid[i][j + 1] == -1)) {
            return -1;
        }
        if (grid[i][j] == -1 && (j - 1 < 0 || grid[i][j - 1] == 1)) {
            return -1;
        }

        //ȥ����һ��
        return dfs(grid, i + 1, j + grid[i][j]);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
