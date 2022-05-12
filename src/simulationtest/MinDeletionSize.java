package src.simulationtest;

/**
 * ������ n ��Сд��ĸ�ַ�����ɵ����� strs������ÿ���ַ���������ȡ�
 * ��Щ�ַ�������ÿ��һ�У��ų�һ���������磬strs = ["abc", "bce", "cae"] ��������Ϊ��
 * abc
 * bce
 * cae
 * ����Ҫ�ҳ���ɾ�� ���ǰ��ֵ����������е� �С�
 * ����������ӣ��±�� 0 ��ʼ���У��� 0��'a', 'b', 'c'������ 2��'c', 'e', 'e'�����ǰ��������еģ����� 1��'b', 'c', 'a'�����ǣ�����Ҫɾ���� 1 ��
 * ��������Ҫɾ����������
 * ���ӣ�https://leetcode.cn/problems/delete-columns-to-make-sorted
 * @author Dreihunde
 *
 */
public class MinDeletionSize {
	class Solution {
	    //method 1 mask˳����� O(nm) O(m)
	    public int minDeletionSize1(String[] strs) {
	        if (strs.length == 0) {
	            return -1;
	        }
	        int[] mask = new int[strs[0].length()];
	        for (int i = 0; i < strs.length; i++) {
	            for (int j = 0; j < strs[i].length(); j++) {
	                if (mask[j] == -1) {
	                    continue;
	                }
	                int t = strs[i].charAt(j) - 'a';
	                if (t < mask[j]) {
	                    mask[j] = -1;
	                } else {
	                    mask[j] = t;
	                }
	            }
	        }
	        int count = 0;
	        for (int t : mask) {
	            count += t == -1 ? 1 : 0;
	        }
	        return count;
	    }

	    //method 2 ģ�� O(nm) O(1)
	    public int minDeletionSize(String[] strs) {
	        int n = strs.length;
	        int m = strs[0].length();
	        int count = 0;
	        for (int i = 0; i < m; i++) {
	            for (int j = 1; j < n; j++) {
	                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
	                    count++;
	                    break;
	                }
	            }
	        }
	        return count;
	    }

	}

}
