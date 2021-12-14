package dptest;

import java.util.HashSet;
import java.util.PriorityQueue;

import numsorttest.CommonTest;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * @author Dreihunde
 *
 */
public class UglyNum {
	//method 1 dp O(n) O(n)
    public int nthUglyNumber1(int n) {
        //从第一个丑数开始
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            //num2 = 第p2个丑数 * 2
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);

            //如果dp[i]与对应的扩展值重复，则对应扩展值的基数+1
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    //method 2 heap O(nlogn) O(n)
    public int nthUglyNumber2(int n) {
        long[] factors = new long[]{2, 3, 5};
        PriorityQueue<Long> heap = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();

        heap.add(1L);
        set.add(1L);

        long cur = 0;
        //进行n次取操作，循环结束时cur为第n个丑数
        for (int i = 0; i < n; i++) {
            cur = heap.poll();
            for (long factor : factors) {
                //set能成功添加，则加入小根堆中
                if (set.add(cur * factor)) {
                    heap.offer(cur * factor);
                }
            }
        }
        return (int) cur;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 10;
		
		UglyNum un = new UglyNum();
		long startTime=System.nanoTime(); 
		System.out.println(un.nthUglyNumber1(n));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		System.out.println(un.nthUglyNumber2(n));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
