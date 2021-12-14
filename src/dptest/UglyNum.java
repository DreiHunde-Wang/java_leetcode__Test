package dptest;

import java.util.HashSet;
import java.util.PriorityQueue;

import numsorttest.CommonTest;

/**
 * ���ǰ�ֻ���������� 2��3 �� 5 ��������������Ugly Number�����󰴴�С�����˳��ĵ� n ��������
 * @author Dreihunde
 *
 */
public class UglyNum {
	//method 1 dp O(n) O(n)
    public int nthUglyNumber1(int n) {
        //�ӵ�һ��������ʼ
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            //num2 = ��p2������ * 2
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);

            //���dp[i]���Ӧ����չֵ�ظ������Ӧ��չֵ�Ļ���+1
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
        //����n��ȡ������ѭ������ʱcurΪ��n������
        for (int i = 0; i < n; i++) {
            cur = heap.poll();
            for (long factor : factors) {
                //set�ܳɹ���ӣ������С������
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
