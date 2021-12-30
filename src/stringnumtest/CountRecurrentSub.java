package stringnumtest;

import java.util.Arrays;

/**
 * ����һ���ַ��� s �����������ַ������ж��ٸ��������ַ�����
 * ���в�ͬ��ʼλ�û����λ�õ��Ӵ�����ʹ������ͬ���ַ���ɣ�Ҳ�ᱻ������ͬ���Ӵ���
 * @author Dreihunde
 *
 */
public class CountRecurrentSub {
	//method 1 ����+������չ O(n2) O(1)
    public int countSubstrings1(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    //method 2 Manacher�㷨 O(n) O(n)
    public int countSubstrings2(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        //f(i)Ϊ��iΪ���ĵ������չ�뾶
        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // ��ʼ�� f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // ������չ
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // ��̬ά�� iMax �� rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // ͳ�ƴ�, ��ǰ����Ϊ (f[i] - 1) / 2 ��ȡ��
            ans += f[i] / 2;
        }

        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcdcba";
		
		CountRecurrentSub cr = new CountRecurrentSub();
		System.out.println(cr.countSubstrings1(s));
		
		System.out.println(cr.countSubstrings2(s));

	}

}
