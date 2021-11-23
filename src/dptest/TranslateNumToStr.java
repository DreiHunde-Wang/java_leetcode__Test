package dptest;

import numsorttest.NumMass;

/**
 * ����һ�����֣����ǰ������¹����������Ϊ�ַ�����
 * 0 ����� ��a�� ��1 ����� ��b����������11 ����� ��l����������25 ����� ��z����
 * һ�����ֿ����ж�����롣����ʵ��һ����������������һ�������ж����ֲ�ͬ�ķ��뷽����
 * @author Dreihunde
 *
 */
public class TranslateNumToStr {
	//��̬�滮
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int p = 0;
        int q = 0;
        int r = 1;

        //f(n) = f(n - 1) + f(n - 2)(��ǰ��λ����10С��25ʱ)
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
