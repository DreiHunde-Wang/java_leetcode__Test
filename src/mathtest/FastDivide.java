package src.mathtest;

/**
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 * @author Dreihunde
 *
 */
public class FastDivide {
	//method 1 累减替代除 O(a/b) O(1)
    public int divide1(int a, int b) {
        if (b == 1) {
            return a;
        } 
        if (b == -1) {
            return a == Integer.MIN_VALUE? Integer.MAX_VALUE : -a;
        }
        if (a == b)
            return 1;
        int ans = 0;
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        while (a <= b) {
            a += -b;
            ans++;
        }
        return isPositive? ans : -ans;
    }

    //method 2 快速除 
    public int divide2(int a, int b) {
        if (b == 1) {
            return a;
        } 
        if (b == -1) {
            return a == Integer.MIN_VALUE? Integer.MAX_VALUE : -a;
        }
        if (a == b)
            return 1;
        int ans = 0;
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;
        while (a <= b) {
            int weight = 1;
            int ret = b;
            while (ret + ret >= a && ret >= Integer.MIN_VALUE >> 1) {
                weight += weight;
                ret += ret;
            }
            a -= ret;
            ans += weight;
        }
        return isPositive ? ans : -ans;
    }
    
    public static void main(String[] args) {
		int a = 2147483647;
		int b = 3;
		FastDivide fd = new FastDivide();
		System.out.println(fd.divide1(a, b));
		System.out.println(fd.divide2(a, b));
	}

}
