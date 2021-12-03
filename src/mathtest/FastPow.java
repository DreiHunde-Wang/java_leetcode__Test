package mathtest;

/**
 * ʵ�� pow(x, n) �������� x �� n ���ݺ���������xn��������ʹ�ÿ⺯����ͬʱ����Ҫ���Ǵ������⡣
 * @author Dreihunde
 *
 */
public class FastPow {
	
	//method 1 ���ٳ� O(logn) O(1)
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
