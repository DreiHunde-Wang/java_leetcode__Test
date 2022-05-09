package src.dptest;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * @author Dreihunde
 *
 */
public class FibTest {
	static int Mod = 1000000007;

    //method 1 动态规划 O(n)
    public static int fib1(int n) {
        if (n <= 1)
            return n;
        int p = 0;
        int q = 0;
        int r = 1;

        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q) % Mod;
        }
        return r;
    }

    //method 2 快速幂计算 O(logn)
    public static int fib2(int n) {
        if (n <= 1)
            return n;
        int[][] M = new int[][]{{1, 1}, {1, 0}};
        int[][] res = pow(M, n - 1);
        return res[0][0];
        
    }

    public static int[][] pow(int[][] M, int n) {
        int[][] ret = new int[][]{{1, 0}, {0, 1}};
        
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, M);
            }
            n = n >> 1;
            M = multiply(M, M);
        }
        return ret;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] rev = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rev[i][j] = (int)(((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % Mod);
            }
        }
        return rev;
    }
    
    static int mod = (int)1e9+7;
    static int N = 110;
    static int[] cache = new int[N];
    static {
        cache[1] = 1;
        for (int i = 2; i < N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
            cache[i] %= mod;
        }
    }
    public static int fib3(int n) {
        return cache[n];
    }
    
   
    
    public static void main(String[] args) {
    	int n = 10;
    	long startTime = System.nanoTime();
		System.out.println(fib1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(fib2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(fib3(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}
}
