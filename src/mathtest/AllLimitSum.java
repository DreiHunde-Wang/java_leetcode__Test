package mathtest;

/**
 * �� 1+2+...+n ��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
 * @author Dreihunde
 *
 */
public class AllLimitSum {
	//method 1 �ݹ� ��·�ж���ֹ O(n) O(n)
    public int sumNums1(int n) {
        boolean flag = n > 0 && (n += sumNums1(n - 1)) > 0;
        return n;
    }

    //method 2 ���ٳ� O(logn) O(1)
    public int sumNums2(int n) {
        int ans = 0;
        boolean flag;
        int A = n;
        int B = n + 1;
        
        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = (B & 1) > 0 && (ans += A) > 0;
        A <<= 1;
        B >>= 1;


        return ans >> 1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		AllLimitSum al = new AllLimitSum();
		long startTime = System.nanoTime();
		System.out.println(al.sumNums1(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
		
		startTime = System.nanoTime();
		System.out.println(al.sumNums2(n));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
