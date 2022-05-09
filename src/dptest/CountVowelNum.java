package src.dptest;

/**
 * 给你�?个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串�?
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'�?
 * 每个元音 'a' 后面都只能跟�? 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或�?�是 'i'
 * 每个元音 'i' 后面 不能 再跟�?另一个�?'i'
 * 每个元音 'o' 后面只能跟着 'i' 或�?�是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，�?以请你返�? 模�?10^9 + 7 之后的结果�??
 * @author Dreihunde
 *
 */
public class CountVowelNum {
	//method 1 dp O(Cn) O(C)
    public int countVowelPermutation1(int n) {
        long[] pre = new long[5];
        long[] cur = new long[5];
        for (int i = 0; i < 5; i++) {
            pre[i] = 1;
        }
        long mod = 1000000007;
        for (int i = 2; i <= n; i++) {
            cur[0] = (pre[1] + pre[2] + pre[4]) % mod;
            cur[1] = (pre[0] + pre[2]) % mod;
            cur[2] = (pre[1] + pre[3]) % mod;
            cur[3] = pre[2];
            cur[4] = (pre[2] + pre[3]) % mod;
            for (int j = 0; j < 5; j++) {
                pre[j] = cur[j];
            } 
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + pre[i]) % mod;
        }
        return (int) ans;
    }

    
  //method 2 快�?�幂 O(C^3logn) O(C^2)
    public int countVowelPermutation2(int n) {
        long[][] factor = new long[][]{{0, 1, 1, 0, 1}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 1, 1, 0}};
        long mod = 1000000007;
        long[][] res = fastPow(factor, n - 1, mod);
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int) ans;
    }

    public long[][] fastPow(long[][] matrix, int n, long mod) {
        int m = matrix.length;
        long[][] res = new long[m][m];
        long[][] curr = matrix;

        for (int i = 0; i < m; ++i) {
            res[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) == 1) {
                res = multiply(res, curr, mod);
            }
            curr = multiply(curr, curr, mod);
            n >>= 1;
        }
        return res;
    }

    public long[][] multiply(long[][] matrixA, long[][] matrixB, long mod) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        long[][] res = new long[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < matrixA[i].length; ++k) {
                    res[i][j] = (res[i][j] + matrixA[i][k] * matrixB[k][j]) % mod;
                }
            }
        }
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		CountVowelNum cv = new CountVowelNum();
		System.out.println(cv.countVowelPermutation1(n));
		System.out.println(cv.countVowelPermutation2(n));
		

	}

}
