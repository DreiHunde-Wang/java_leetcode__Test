package simulationtest;

import java.util.ArrayList;
import java.util.List;

/**
 * ����һ����ʾ�������ַ��� n ��������������Ļ��������������������������ֹһ�������ؽ�С���Ǹ���
 * ������ġ�����Ϊ����������ľ���ֵ��С��
 * https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * @author Dreihunde
 *
 */
public class NearestPalindromic {
	//method 1 ģ�� O(logn) O(logn)
	/**
	 * �ֱ���������ÿһ�ֿ��ܵķ�����Ӧ�Ļ����������������ҵ���ԭ������Ҳ�����ԭ��������Ϊ�𰸡�
	 * ��ԭ����ǰ�벿���滻��벿�ֵõ��Ļ���������
	 * ��ԭ����ǰ�벿�ּ�һ��Ľ���滻��벿�ֵõ��Ļ���������
	 * ��ԭ����ǰ�벿�ּ�һ��Ľ���滻��벿�ֵõ��Ļ���������
	 * Ϊ��ֹλ���仯���¹���Ļ��������������ֱ�ӹ���999��999 ��100��001 ��Ϊ��ѡ�𰸡�
	 */
    public String nearestPalindromic(String n) {
        long selfNumber = Long.parseLong(n);
        long ans = -1;
        List<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate != selfNumber) {
                if (ans == -1 ||
                    Math.abs(candidate - selfNumber) < Math.abs(ans - selfNumber) ||
                    Math.abs(candidate - selfNumber) == Math.abs(ans - selfNumber) && candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }

    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {{
            add((long) Math.pow(10, len - 1) - 1);
            add((long) Math.pow(10, len) + 1);
        }};
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            
            // len & 1 ����Ϊ1 ż��Ϊ0
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            candidates.add(Long.parseLong(candidate));
        }
        return candidates;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
