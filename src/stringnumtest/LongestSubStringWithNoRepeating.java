package src.stringnumtest;

import java.util.HashMap;

/**
 * ����һ���ַ��� s �������ҳ����в������ظ��ַ��� ��������ַ��� �ĳ��ȡ�
 * @author Dreihunde
 *
 */
public class LongestSubStringWithNoRepeating {
	//method 1 HaspMap O(n) O(n)
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        //���ظ��ĵ�һ��Ԫ��
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //����ǰ�ַ��Ѿ�����¼������һ�γ��ֵ�λ�ô��ڵ���startʱ������startλ��
            if (map.containsKey(c)) {
                int t = map.get(c);
                start = t >= start ? t + 1 : start;
            } 
            //����ÿ���ַ���Ӧ��λ��
            map.put(c, i);
            //������󳤶�
            maxLen = Math.max(i - start + 1, maxLen);
            
        }

        return maxLen;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "abcabcbb";
		LongestSubStringWithNoRepeating ls = new LongestSubStringWithNoRepeating();
		System.out.println(ls.lengthOfLongestSubstring(s));

	}

}
