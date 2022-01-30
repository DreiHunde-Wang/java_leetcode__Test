package stringnumtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���� ��һ���ɿո�ָ��ĵ��ʡ�ÿ�� ���� ����Сд��ĸ��ɡ�
 * ���ĳ������������һ��������ǡ�ó���һ�Σ�����һ��������ȴ û�г��� ����ô������ʾ��� �������� ��
 * �������� ���� s1 �� s2 ���������� �����õ��� ���б������б��е��ʿ��԰� ����˳�� ��֯��
 * https://leetcode-cn.com/problems/uncommon-words-from-two-sentences/
 * @author Dreihunde
 *
 */
public class UncommonFromSentences {
	//method 1 map���� O(|s1| + |s2|) O(|s1| + |s2|)
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> rev = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] s1str = s1.split(" ");
        String[] s2str = s2.split(" ");
        for (String str1 : s1str) {
            map.put(str1, map.getOrDefault(str1, 0) + 1);
        }
        for (String str2 : s2str) {
            map.put(str2, map.getOrDefault(str2, 0) + 1);
        }
        
        for (String key : map.keySet()) {
            if (map.get(key) == 1) {
                rev.add(key);
            }
        }
        return rev.toArray(new String[rev.size()]);
        // String[] ans = new String[rev.size()];
        // for (int i = 0; i < rev.size(); i++) {
        //     ans[i] = rev.get(i);
        // }
        // return ans;
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
