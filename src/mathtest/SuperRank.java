package mathtest;

/**
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * @author Dreihunde
 *
 */
public class SuperRank {
	//method 1 快速幂 + 分配律(a⋅b)modm=[(amodm)⋅(bmodm)]modm
    int mod = 1337;
    public int superPow1(int a, int[] b) {
        int ans = 1;
        
        for (int i = b.length - 1; i >= 0; i--) {
            ans = (int) ((long) ans * pow(a, b[i]) % mod);
            a = pow(a, 10);
        }

        return ans;
    }

    //method 2 秦九韶算法 
    public int superPow2(int a, int[] b) {
        int ans = 1;
        for (int n : b) {
            ans = (int) ((long) pow(ans, 10) * pow(a, n) % mod);
        }
        return ans;
    }

    private int pow(int a, int n) {
        int ret = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                ret = (int) ((long) ret * a % mod);
            }
            n >>= 1;
            a = (int) ((long) a * a % mod);
        }
        return ret;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
