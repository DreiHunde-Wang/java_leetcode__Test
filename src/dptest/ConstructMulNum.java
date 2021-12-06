package dptest;

import printtreenode.ComnTest;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * @author Dreihunde
 *
 */
public class ConstructMulNum {
	//method 1 遍历O(n2) O(1) 超时
    public int[] constructArr1(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = mulSum(a, i);
        }
        return b;
    }

    private int mulSum(int[] a, int index) {
        int sum = 1;
        for (int i = 0; i < a.length; i++) {
            if (i == index)
                continue;
            sum *= a[i];
        }
        return sum;
    }

    //method 2 dp O(n) O(1)
    public int[] constructArr2(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        if (n == 0)
            return b;
        b[0] = 1;
        //i之前的乘积和
        for (int i = 1; i < n; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        //i之后的乘积和
        int tmp = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            b[i] *= tmp;
        }
        return b;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[] {1, 2, 3, 4};
		
		ConstructMulNum cm = new ConstructMulNum();
		
		long startTime = System.nanoTime();
		ComnTest.printNum(cm.constructArr1(a));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		ComnTest.printNum(cm.constructArr2(a));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
	}

}
