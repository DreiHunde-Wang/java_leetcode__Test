package binaryandtest;

/**
 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ�� ��+������-������*������/�� ����������š�
 * @author Dreihunde
 *
 */
public class AchieveAndWithBitand {
	//method 1 �� 
    public int add1(int a, int b) {
        int c = 0;
        while (b != 0) {
            //��λ
            c = (a & b) << 1;
            //����λ�൱�����
            a ^= b;
            b = c;
        }

        return a;
    }

    //method 2 �ݹ��Ż�
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
