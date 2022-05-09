package src.dptest;

/**
 * �?只青蛙一次可以跳�?1级台阶，也可以跳�?2级台阶�?�求该青蛙跳上一�? n 级的台阶总共有多少种跳法�?
 * 答案�?要取�? 1e9+7�?1000000007），如计算初始结果为�?1000000008，请返回 1�?
 * n = 0 输出�?1
 * @author Dreihunde
 *
 */
public class JumpOrder {
	static int Mod = (int)1e9 + 7;

	 //method 1 动�?�规�?
    public static int numWays1(int n) {
        if (n == 0)
            return 1;
        
        if (n == 1)
            return 1;
        
        int p = 1;
        int q = 1;
        int m = 2;
        for (int i = 2; i <= n; i++) {
            m = (p + q) % Mod;
            p = q;
            q = m;
        }

        return m;
    }

    //method 2 矩阵快�?�幂
    public static int numWays2(int n) {
        if (n == 0)
            return 1;
        
        if (n == 1)
            return 1;

        int[][] M = new int[][]{{1, 1}, {1, 0}};
        int[][] ans = Pow(M, n - 1);
        int rev = (ans[0][0] + ans[0][1]) % Mod;
        return rev;
    }

    private static int[][] Pow(int[][] M, int n) {
        int[][] ret = new int[][]{{1, 0}, {0, 1}};
        while (n > 0) {
            if ( (n & 1) == 1) {
                ret = multiply(ret, M);
            }
            n >>= 1;
            M = multiply(M, M);
        }
        return ret;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int)(((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % Mod);
            }
        }
        return c;
    }
    
    public static void main(String[] args) {
    	int n = 10;
    	long startTime = System.nanoTime();
		System.out.println(numWays1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(numWays2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
