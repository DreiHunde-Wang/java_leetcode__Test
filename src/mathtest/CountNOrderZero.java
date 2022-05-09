package src.mathtest;

import src.dptest.LongestSubSum;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * @author Dreihunde
 *
 */
public class CountNOrderZero {
	//method 1 出现0意味有*10，即存在2和5,由于2的公倍数更多，所以只用计算5
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
    
    //除以5(向下取整)以后不为0，意味着依然存在5的公倍数
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
