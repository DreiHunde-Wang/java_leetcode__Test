package src.binaryandtest;
/**
 * ����һ����������������Ķ����Ʊ�ʾ�Ƿ����� 0��1 ������֣����仰˵�����Ƕ����Ʊ�ʾ��������λ������������ͬ��
 * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 * @author Dreihunde
 *
 */
public class HasAlternatingBits {
	
	//method 1 λ����+ģ�� O(logn) O(1)
    public boolean hasAlternatingBits1(int n) {
        boolean ret = true;
        while (n > 0 || (n >> 1) > 0) {
            ret = ret && ((n & 1) != ((n >> 1) & 1));
            if (!ret) {
                return ret;
            }
            n >>= 1;
        }
        return ret;
    }

    //method 2 λ����+�Ż� O(1) O(1)
    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

}
