package dptest;

import numsorttest.NumMass;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * @author Dreihunde
 *
 */
public class TranslateNumToStr {
	//动态规划
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int p = 0;
        int q = 0;
        int r = 1;

        //f(n) = f(n - 1) + f(n - 2)(当前两位大于10小于25时)
        for (int i = 0; i < str.length(); i++) {
            //f(n - 2) f(0) = 0
            p = q;
            //f(n - 1) f(1) = 1
            q = r;
            r = 0;
            //f(n) = f(n - 1)
            r += q;
            if (i == 0)
                continue;
            String pre = str.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                //f(n) = f(n - 1) + f(n - 2)
                r += p;
            }
        }

        return r;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 12258;
		
		TranslateNumToStr tl = new TranslateNumToStr();
		long startTime = System.nanoTime();
		System.out.println(tl.translateNum(n));
		long endTime = System.nanoTime();
		System.out.println("time cost:" + (endTime - startTime)/1000 + "ms");

	}

}
