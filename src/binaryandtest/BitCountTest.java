package binaryandtest;

import numsorttest.CommonTest;

/**
 * ����һ���Ǹ����� n ������� 0 �� n ֮���ÿ�����ֵĶ����Ʊ�ʾ�� 1 �ĸ����������һ�����顣
 * @author Dreihunde
 *
 */
public class BitCountTest {
	//method 1 bitCount O(nlogn)  O(1)
    public int[] countBits1(int n) {
        int[] rev = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            rev[i] = bitCount(i);
        }
        return rev;
    }

    private int bitCount(int i) {
        int ans = 0;
        while (i > 0) {
            i = i & (i - 1);
            ans++;
        }
        return ans;
    }

    //method 2 O(n) O(1)
    public int[] countBits2(int n) {
        int[] rev = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            rev[i] = rev[i - highBit] + 1;
        }
        return rev;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 7;
		
		BitCountTest bc = new BitCountTest();
		long startTime=System.nanoTime(); 
		CommonTest.printNum(bc.countBits1(n));
		long endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime=System.nanoTime(); 
		CommonTest.printNum(bc.countBits2(n));
		endTime=System.nanoTime(); 
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
	}

}
