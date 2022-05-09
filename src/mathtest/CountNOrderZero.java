package src.mathtest;

import src.dptest.LongestSubSum;

/**
 * ����һ������ n ������ n! �����β�����������
 * ��ʾ n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * @author Dreihunde
 *
 */
public class CountNOrderZero {
	//method 1 ����0��ζ��*10��������2��5,����2�Ĺ��������࣬����ֻ�ü���5
    public int trailingZeroes1(int n) {
        int ans = 0;
        for (int i = 1; i <= n ; i++) {
            int t = i;
            while (t % 5 == 0) {
                ans++;
                t /= 5;
            }
        }
        return ans;
    }
    
    //����5(����ȡ��)�Ժ�Ϊ0����ζ����Ȼ����5�Ĺ�����
    public int trailingZeroes2(int n) {
        int zeroCount = 0;
        long currentMultiple = 5;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 100;
		CountNOrderZero cz = new CountNOrderZero();
		
		long startTime = System.nanoTime();
		System.out.println(cz.trailingZeroes1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(cz.trailingZeroes2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
