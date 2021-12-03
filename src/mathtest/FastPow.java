package mathtest;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * @author Dreihunde
 *
 */
public class FastPow {
	
	//method 1 快速乘 O(logn) O(1)
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        boolean isPositive = false;
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        } 

        return pow(x, b);
    }

    private double pow(double x, long n) {
        double ret = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret *= x;
            }
            n >>= 1;
            x *= x;
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
