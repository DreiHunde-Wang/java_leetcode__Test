package src.simulationtest;

/**
 * ������0123456789101112131415���ĸ�ʽ���л���һ���ַ������С�
 * ����������У���5λ�����±�0��ʼ��������5����13λ��1����19λ��4���ȵȡ�
 * ��дһ���������������nλ��Ӧ�����֡�
 * ���ӣ�https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * @author Dreihunde
 *
 */
public class FindNthDigit {
	//method 1 ģ�� O(logn) O(logn)
    public int findNthDigit(int n) {
        int digit = 1; //��λ
        long start = 1; //��ʼֵ
        long count = 9;//��λ����

        while (n > count) {
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * start * digit;
        }

        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
