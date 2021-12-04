package binaryandtest;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 * @author Dreihunde
 *
 */
public class AchieveAndWithBitand {
	//method 1 与 
    public int add1(int a, int b) {
        int c = 0;
        while (b != 0) {
            //进位
            c = (a & b) << 1;
            //不进位相当于异或
            a ^= b;
            b = c;
        }

        return a;
    }

    //method 2 递归优化
    public int add2(int a, int b) {
        if (b == 0)
            return a;
        return add2(a ^ b, (a & b) << 1);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 3;
		int b = 5;
		
		AchieveAndWithBitand aa = new AchieveAndWithBitand();
		long startTime = System.nanoTime();
		System.out.println(aa.add1(a, b));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");
				
		startTime = System.nanoTime();
		System.out.println(aa.add2(a, b));
		endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
