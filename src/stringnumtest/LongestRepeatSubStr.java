package stringnumtest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * ����һ���ַ��� s ������������ �ظ��Ӵ� ������s �������Ӵ����� s �г��� 2 �λ����Ρ���Щ����֮����ܴ����ص���
 * ���� ����һ�� ���ܾ�������ȵ��ظ��Ӵ������ s �����ظ��Ӵ�����ô��Ϊ "" ��
 * @author Dreihunde
 *
 */
public class LongestRepeatSubStr {
	public String longestDupSubstring(String s) {
        Random random = new Random();
        // ������������
        int a1 = random.nextInt(75) + 26;
        int a2 = random.nextInt(75) + 26;
        // ��������ģ
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int n = s.length();
        // �ȶ������ַ����б���
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = s.charAt(i) - 'a';
        }
        // ���ֲ��ҵķ�Χ��[1, n-1]
        int l = 1, r = n - 1;
        int length = 0, start = -1;
        while (l <= r) {
            int m = l + (r - l + 1) / 2;
            int idx = check(arr, m, a1, a2, mod1, mod2);
            if (idx != -1) {
                // ���ظ��Ӵ����ƶ���߽�
                l = m + 1;
                length = m;
                start = idx;
            } else {
                // ���ظ��Ӵ����ƶ��ұ߽�
                r = m - 1;
            }
        }
        return start != -1 ? s.substring(start, start + length) : "";
    }

    public int check(int[] arr, int m, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, m, mod1);
        long aL2 = pow(a2, m, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < m; ++i) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }
        }
        // �洢һ����������Ƿ���ֹ�
        Set<Long> seen = new HashSet<Long>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - m; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + m - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + m - 1]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }

            long num = h1 * mod2 + h2;
            // ����ظ����򷵻��ظ��������
            if (!seen.add(num)) {
                return start;
            }
        }
        // û���ظ����򷵻�-1
        return -1;
    }

    public long pow(int a, int m, int mod) {
        long ans = 1;
        long contribute = a;
        while (m > 0) {
            if (m % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) {
                contribute += mod;
            }
            m /= 2;
        }
        return ans;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "banana";
		LongestRepeatSubStr lr = new LongestRepeatSubStr();
		System.out.println(lr.longestDupSubstring(s));

	}

}
