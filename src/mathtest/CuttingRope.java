package src.mathtest;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * @author Dreihunde
 *
 */
public class CuttingRope {
	
	//method 1 math 最佳长度为n / m = e 约为3
    public int cuttingRope(int n) {
        if (n <= 3)
            return n - 1;
        int m = n / 3;
        int res = n % 3;
        if (res == 0)
            return pow(3, m);
        if (res == 1)
            return pow(3, m - 1) * 4;
        return pow(3, m) * 2;
    }

    private int pow(int a, int n) {
        int ret = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                ret *= a;
            }
            n >>>= 1;
            a *= a;
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		CuttingRope cr = new CuttingRope();
		System.out.println(cr.cuttingRope(n));
	}

}
