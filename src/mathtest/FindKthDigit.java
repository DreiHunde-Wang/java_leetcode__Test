package src.mathtest;

/**
 * ������0123456789101112131415���ĸ�ʽ���л���һ���ַ������С�
 * ����������У���5λ�����±�0��ʼ��������5����13λ��1����19λ��4���ȵȡ�
 * ��дһ���������������nλ��Ӧ�����֡�
 * @author Dreihunde
 *
 */
public class FindKthDigit {
	
	//method 1 ��ѧ
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }
        long start = 1;
        int digit = 1;
        long count = 9;
        
        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * digit * start;
        }

        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 19;
		FindKthDigit dk = new FindKthDigit();
		System.out.println(dk.findNthDigit(n));
	}

}
