package src.mathtest;

/**
 * ����һ������ n ����ʾ�����еĶ�������������ѭһ�ֶ��ص����ƣ�
 * �����ǰ�������� ż�� ����ôÿ֧���鶼������һ֧������ԡ�
 * �ܹ����� n / 2 ���������Ҳ��� n / 2 ֧���������һ�֡�
 * �����ǰ������Ϊ ���� ����ô��������ֿղ�����һ֧���飬����Ķ�����ԡ�
 * �ܹ����� (n - 1) / 2 ���������Ҳ��� (n - 1) / 2 + 1 ֧���������һ�֡�
 * �����ڱ����н��е���Դ�����ֱ��������ʤ����Ϊֹ��
 * @author Dreihunde
 *
 */
public class NumOfMatch {
	//method 1 ģ�� O(n) O(1)
    public int numberOfMatches1(int n) {
        int sum = 0;
        while (n > 1) {
            sum += n / 2;
            n = n % 2 + n / 2;
        }
        return sum;
    }

    //method 2 ��ѧ(n - 1�����鱻��̭��ζ�ű���n - 1��) O(1) O(1)
    public int numberOfMatches(int n) {
        return n - 1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
