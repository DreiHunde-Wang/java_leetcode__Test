package src.dptest;

/**
 * �������ַ�����ʾһ��ѧ���ĳ��ڼ�¼�����е�ÿ���ַ�������ǵ���ĳ��������ȱ�ڡ��ٵ�������������¼��ֻ�����������ַ���
 * 'A'��Absent��ȱ��
 * 'L'��Late���ٵ�
 * 'P'��Present������
 * ���ѧ���ܹ� ͬʱ ����������������������Ի�ó��ڽ�����
 * �� �ܳ��� �ƣ�ѧ��ȱ�ڣ�'A'���ϸ� �������졣
 * ѧ�� ���� ���� ���� 3 ��� ���� 3 �����ϵĳٵ���'L'����¼��
 * ����һ������ n ����ʾ���ڼ�¼�ĳ��ȣ������������㷵�ؼ�¼����Ϊ n ʱ�����ܻ�ó��ڽ����ļ�¼��� ���� ��
 * �𰸿��ܴܺ����Է��ض� 109 + 7 ȡ�� �Ľ����
 * @author Dreihunde
 *
 */
public class CheckRecord {
	 //method dfs
	static int mod = (int)1e9+7;
	static int[][][] cache;
    public static int checkRecord1(int n) {
        cache = new int[n + 1][2][3];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
        return dfs(n, 0, 0);
    }
    static int dfs(int u, int acnt, int lcnt) {
        if (acnt >= 2) return 0;
        if (lcnt >= 3) return 0;
        if (u == 0) return 1;
        if (cache[u][acnt][lcnt] != -1) return cache[u][acnt][lcnt];
        int ans = 0;
        ans = dfs(u - 1, acnt + 1, 0) % mod; // A ȱϯ
        ans = (ans + dfs(u - 1, acnt, lcnt + 1)) % mod; // L �ٵ�
        ans = (ans + dfs(u - 1, acnt, 0)) % mod; // P ����
        cache[u][acnt][lcnt] = ans;
        return ans;
    }

    //method 2 ��̬�滮
    public static int checkRecord2(int n) {
        int[][][] f = new int[n + 1][2][3];
        f[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == 1 && k == 0) { // A
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][0]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][1]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][2]) % mod;
                    }
                    if (k != 0) { // L
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k - 1]) % mod;
                    }
                    if (k == 0) { // P
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][0]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][1]) % mod;
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][2]) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                ans += f[n][j][k];
                ans %= mod;
            }
        }
        return ans;
    }

    //method 3 ������
    static int N = 6;
    static long[][] mul(long[][] a, long[][] b) {
        int r = a.length, c = b[0].length, z = b.length;
        long[][] ans = new long[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < z; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                    ans[i][j] %= mod;
                }
            }
        }
        return ans;
    }
    public static int checkRecord3(int n) {
        long[][] ans = new long[][]{
            {1}, {0}, {0}, {0}, {0}, {0}
        };
        long[][] mat = new long[][]{
            {1, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0}
        };
        while (n != 0) {
            if ((n & 1) != 0) ans = mul(mat, ans);
            mat = mul(mat, mat);
            n >>= 1;
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += ans[i][0];
            res %= mod;
        }
        return res;
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1000;
		long startTime = System.nanoTime();
		System.out.println(checkRecord1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(checkRecord2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(checkRecord3(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
