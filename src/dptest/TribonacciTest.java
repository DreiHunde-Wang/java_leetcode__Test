package src.dptest;


/**
 * 泰波那契序列 Tn 定义如下：�?
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下
 * Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回�? n 个泰波那契数 Tn 的�?��??
 * @author Dreihunde
 *
 */
public class TribonacciTest {
	//method 1 打表�?
    static int[] cache = new int[40];
    static {
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 1;
        for (int i = 3; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
    }
    public static int tribonacci1(int n) {
        return cache[n];
    }
    
    //method 2 动�?�规�?
    public static int tribonacci2(int n) {
        if (n == 0)
        	return 0;
        if (n <= 2)
        	return 1;
        int p = 0;
        int q = 1;
        int r = 1;
        int m = 2;
        for (int i = 3; i <= n; i++) {
        	m = p + q + r;
        	p = q;
        	q = r;
        	r = m;
        }
        
        return m;
    }
    
    //method 3 快�?�幂
    public static int tribonacci3(int n) {
       if (n == 0) {
           return 0;
       }
       if (n <= 2) {
           return 1;
       }
       int[][] q = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
       int[][] res = pow(q, n);
       return res[0][2];
   }

   public static int[][] pow(int[][] a, int n) {
       int[][] ret = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
       while (n > 0) {
           if ((n & 1) == 1) {
               ret = multiply(ret, a);
           }
           n >>= 1;
           a = multiply(a, a);
       }
       return ret;
   }

   public static int[][] multiply(int[][] a, int[][] b) {
       int[][] c = new int[3][3];
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j] + a[i][2] * b[2][j];
           }
       }
       return c;
   }

    
    public static void main(String[] args) {
    	int n = 35;
    	long startTime = System.nanoTime();
		System.out.println(tribonacci1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(tribonacci2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(tribonacci3(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
