package src.mathtest;

/**
 * ������������ a �� b �������ǵĳ������� a/b ��Ҫ�󲻵�ʹ�ó˺� '*'������ '/' �Լ�������� '%' ��
 * @author Dreihunde
 *
 */
public class FastDivide {
	//method 1 �ۼ������ O(a/b) O(1)
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

    //method 2 ���ٳ� 
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
