package src.mathtest;

/**
 * ����һ������ n ����1��n��n��������ʮ���Ʊ�ʾ��1���ֵĴ�����
 * ���磬����12��1��12��Щ�����а���1 ��������1��10��11��12��1һ��������5�Ρ�
 * ���ӣ�https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * @author Dreihunde
 *
 */
public class CountOneNumInTen {
	//method 1 math O(logn) O(1)
    //ans = (n / 10 ^ k) * 10 ^ k + min (max (n mod 10 ^ (k + 1) - 10 ^ k + 1, 0), 10 ^ k)
    public int countDigitOne(int n) {
        // mulk ��ʾ 10^k
        // ������Ĵ����У����Է��� k ��û�б�ֱ��ʹ�õ�������ʹ�� 10^k��
        // ��Ϊ���ô��뿴��������ֱ�ۣ����ﱣ���� k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
