package src.mathtest;

/**
 * 给你�?根长度为 n 的绳子，请把绳子剪成整数长度�? m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记�? k[0],k[1]...k[m - 1] �?
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少�?
 * 例如，当绳子的长度是8时，我们把它剪成长度分别�?2�?3�?3的三段，此时得到的最大乘积是18�?
 * 答案�?要取�? 1e9+7�?1000000007），如计算初始结果为�?1000000008，请返回 1�?
 * @author Dreihunde
 *
 */
public class CuttingRope2 {
	//method 1 math+模拟 m = ln(n) 约为/3 O(n) O(1)
    public int cuttingRope1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long mod = 1000000007;
        long ans = 1;
        if (n % 3 == 0) {
            while (n > 0) {
                ans = (ans * 3) % mod;
                n -= 3;
            }
        } else if (n % 3 == 1) {
            while (n > 4) {
                ans = (ans * 3) % mod;
                n -= 3;
            }
            ans = (ans * 4) % mod;
        } else {
            while (n > 2) {
                ans = (ans * 3) % mod;
                n -= 3;
            }
            ans = (ans * 2) % mod;
        }
        return (int) ans;
    }

    //method 1 math + 快�?�幂优化 约为/3 O(ln(n)) O(1)
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long mod = 1000000007;
        long ans = 1;
        if (n % 3 == 0) {
            ans = fastPow(3, n / 3, mod) % mod;
        } else if (n % 3 == 1) {
            ans = fastPow(3, n / 3 - 1, mod) % mod;
            ans = (ans * 4) % mod;
        } else {
            ans = fastPow(3, n / 3, mod) % mod;
            ans = (ans * 2) % mod;
        }
        return (int) ans;
    }

    private long fastPow(long x, int n, long mod) {
        long ret = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = (ret * x) % mod;
            }
            x = (x * x) % mod;
            n >>= 1;
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
